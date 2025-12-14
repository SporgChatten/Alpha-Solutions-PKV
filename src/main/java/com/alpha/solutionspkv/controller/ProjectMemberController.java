package com.alpha.solutionspkv.controller;

import com.alpha.solutionspkv.model.Project;
import com.alpha.solutionspkv.model.User;
import com.alpha.solutionspkv.service.ProjectService;
import com.alpha.solutionspkv.service.SessionService;
import com.alpha.solutionspkv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects/{projectId}/members")
public class ProjectMemberController {

    private final SessionService sessionService;
    private final ProjectService projectService;
    private final UserService userService;

    public ProjectMemberController(SessionService sessionService, ProjectService projectService, UserService userService) {
        this.sessionService = sessionService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping
    public String membersPage(@PathVariable int projectId, Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        User currentUser = sessionService.getCurrentUser();
        if (!currentUser.canManageProjects()) return "redirect:/projects";
        if (!projectService.canAccessProject(currentUser, projectId)) return "redirect:/projects";

        Project project = projectService.getProjectById(projectId);
        if (project == null) return "redirect:/projects";

        model.addAttribute("project", project);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("assignedUsers", userService.getUsersAssignedToProject(projectId));
        model.addAttribute("availableUsers", userService.getUsersNotAssignedToProject(projectId));
        return "projects/members";
    }

    @PostMapping("/assign")
    public String assign(@PathVariable int projectId, @RequestParam int userId) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        User currentUser = sessionService.getCurrentUser();
        if (!currentUser.canManageProjects()) return "redirect:/projects";
        if (!projectService.canAccessProject(currentUser, projectId)) return "redirect:/projects";

        projectService.assignUserToProject(userId, projectId);
        return "redirect:/projects/" + projectId + "/members";
    }

    @PostMapping("/{userId}/remove")
    public String remove(@PathVariable int projectId, @PathVariable int userId) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        User currentUser = sessionService.getCurrentUser();
        if (!currentUser.canManageProjects()) return "redirect:/projects";
        if (!projectService.canAccessProject(currentUser, projectId)) return "redirect:/projects";

        projectService.unassignUserFromProject(userId, projectId);
        return "redirect:/projects/" + projectId + "/members";
    }
}
