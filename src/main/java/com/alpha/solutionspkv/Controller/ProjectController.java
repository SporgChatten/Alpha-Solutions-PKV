package com.alpha.solutionspkv.Controller;

import com.alpha.solutionspkv.Model.Project;
import com.alpha.solutionspkv.Model.User;
import com.alpha.solutionspkv.Service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "projects";
    }

    @GetMapping("/projects/{id}")
    public String projectDetails(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.findById(id));
        return "project-details";
    }

    @GetMapping("/projects/create")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new Project());
        return "create-project";
    }

    @PostMapping("/projects/create")
    public String createProject(Project project, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            return "redirect:/login";
        }
        project.setCreatedBy(loggedUser);
        projectService.create(project);
        return "redirect:/projects";
    }
}