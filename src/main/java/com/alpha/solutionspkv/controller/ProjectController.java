package com.alpha.solutionspkv.controller;

import com.alpha.solutionspkv.model.Project;
import com.alpha.solutionspkv.model.User;
import com.alpha.solutionspkv.service.ProjectService;
import com.alpha.solutionspkv.service.SessionService;
import com.alpha.solutionspkv.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final SessionService sessionService;

    public ProjectController(ProjectService projectService, TaskService taskService, SessionService sessionService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String viewAllProjects(Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        User currentUser = sessionService.getCurrentUser();

        List<Project> projects = (currentUser.getRole() == User.Role.ADMIN)
                ? projectService.getAllProjects()
                : projectService.getProjectsForUser(currentUser.getId());

        for (Project project : projects) {
            BigDecimal totalCost = taskService.getTotalEstimatedCostForProject(project.getId());
            project.setEstimatedCost(totalCost);
        }

        model.addAttribute("projects", projects);
        model.addAttribute("currentUser", currentUser);
        return "projects/list";
    }

    @GetMapping("/new")
    public String showNewProjectForm(Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";
        if (!sessionService.getCurrentUser().canManageProjects()) return "redirect:/projects";

        model.addAttribute("project", new Project());
        return "projects/form";
    }

    @PostMapping
    public String saveProject(@ModelAttribute Project project) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";
        if (!sessionService.getCurrentUser().canManageProjects()) return "redirect:/projects";

        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/{id}")
    public String viewProject(@PathVariable int id, Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";
        if (!projectService.canAccessProject(sessionService.getCurrentUser(), id)) return "redirect:/projects";

        Project project = projectService.getProjectById(id);
        if (project != null) {
            model.addAttribute("project", project);
            return "projects/view";
        }
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";
        if (!sessionService.getCurrentUser().canManageProjects()) return "redirect:/projects";
        if (!projectService.canAccessProject(sessionService.getCurrentUser(), id)) return "redirect:/projects";

        Project project = projectService.getProjectById(id);
        if (project != null) {
            model.addAttribute("project", project);
            return "projects/edit";
        }
        return "redirect:/projects";
    }

    @PostMapping("/{id}")
    public String updateProject(@PathVariable int id, @ModelAttribute Project project) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";
        if (!sessionService.getCurrentUser().canManageProjects()) return "redirect:/projects";
        if (!projectService.canAccessProject(sessionService.getCurrentUser(), id)) return "redirect:/projects";

        project.setId(id);
        projectService.updateProject(project);
        return "redirect:/projects";
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable int id) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";
        if (!sessionService.getCurrentUser().canManageProjects()) return "redirect:/projects";
        if (!projectService.canAccessProject(sessionService.getCurrentUser(), id)) return "redirect:/projects";

        projectService.deleteProject(id);
        return "redirect:/projects";
    }


    @PostMapping("/{id}/task")
    public String showProjectTasks(@PathVariable int id) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";
        if (!projectService.canAccessProject(sessionService.getCurrentUser(), id)) return "redirect:/projects";

        projectService.deleteProject(id);
        return "projects/view";
    }
}
