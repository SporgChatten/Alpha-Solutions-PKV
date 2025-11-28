package com.alpha.solutionspkv.controller;

import com.alpha.solutionspkv.model.Project;
import com.alpha.solutionspkv.model.Task;
import com.alpha.solutionspkv.service.ProjectService;
import com.alpha.solutionspkv.service.SessionService;
import com.alpha.solutionspkv.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final SessionService sessionService;

    public TaskController(ProjectService projectService, SessionService sessionService, TaskService taskService) {
        this.projectService = projectService;
        this.sessionService = sessionService;
        this.taskService = taskService;
    }

    private boolean checkLogin() {
        return sessionService.isLoggedIn();
    }

    @GetMapping
    public String listTasks(@PathVariable int projectId, Model model) {
        if (!checkLogin()) return "redirect:/login";

        Project project = projectService.getProjectById(projectId);
        List<Task> tasks = taskService.findByProjectId(projectId);

        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);

        return "tasks/list";
    }

    @GetMapping("/new")
    public String showNewTaskForm(@PathVariable int projectId, Model model) {
        if (!checkLogin()) return "redirect:/login";
        Project project = projectService.getProjectById(projectId);
        Task task = new Task();
        task.setProjectId(projectId);
        model.addAttribute("task", task);
        model.addAttribute("project", project);
        return "tasks/form";
    }

    @PostMapping("/new")
    public String saveTask(@PathVariable int projectId, @ModelAttribute Task task) {
        if (!checkLogin()) return "redirect:/login";
        task.setProjectId(projectId);
        taskService.save(task);
        return "redirect:/projects/" + projectId + "/tasks";
    }


}
