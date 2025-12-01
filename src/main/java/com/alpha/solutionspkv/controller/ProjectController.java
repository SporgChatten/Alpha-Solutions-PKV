package com.alpha.solutionspkv.controller;

import com.alpha.solutionspkv.model.Project;
import com.alpha.solutionspkv.service.ProjectService;
import com.alpha.solutionspkv.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final SessionService sessionService;

    public ProjectController(ProjectService projectService, SessionService sessionService) {
        this.projectService = projectService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String viewAllProjects(Model model) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("currentUser", sessionService.getCurrentUser());
        return "projects/list";
    }

    @GetMapping("/new")
    public String showNewProjectForm(Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        model.addAttribute("project", new Project());
        return "projects/form";
    }

    @PostMapping
    public String saveProject(@ModelAttribute Project project) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/{id}")
    public String viewProject(@PathVariable int id, Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

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

        project.setId(id);
        projectService.updateProject(project);
        return "redirect:/projects";
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable int id) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        projectService.deleteProject(id);
        return "redirect:/projects";
    }


    @PostMapping("/{id}/task")
    public String showProjectTasks(@PathVariable int id) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        projectService.deleteProject(id);
        return "projects/view";
    }
}