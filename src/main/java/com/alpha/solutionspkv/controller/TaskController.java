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

    @GetMapping
    public String listTasks(@PathVariable int projectId, Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        Project project = projectService.getProjectById(projectId);
        List<Task> tasks = taskService.findByProjectId(projectId);

        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);

        return "tasks/list";
    }

    @GetMapping("/new")
    public String showNewTaskForm(@PathVariable int projectId, Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        Project project = projectService.getProjectById(projectId);
        Task task = new Task();
        task.setProjectId(projectId);
        model.addAttribute("task", task);
        model.addAttribute("project", project);
        return "tasks/form";
    }

    @PostMapping("/new")
    public String saveTask(@PathVariable int projectId, @ModelAttribute Task task) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        task.setProjectId(projectId);
        taskService.save(task);
        return "redirect:/projects/" + projectId + "/tasks";
    }

    @GetMapping("/{id}/edit")
    public String showEditTask(@PathVariable int projectId,
                               @PathVariable int id,
                               Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        Project project = projectService.getProjectById(projectId);
        Task task = taskService.findById(id);
        if (task == null) {
            return "redirect:/projects/" + projectId + "/tasks";
        }
        model.addAttribute("project", project);
        model.addAttribute("task", task);
        return "tasks/edit";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable int projectId, @PathVariable int id, @ModelAttribute Task task) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        task.setId(id);
        task.setProjectId(projectId);
        taskService.update(task);
        return "redirect:/projects/" + projectId + "/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable int projectId, @PathVariable int id) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        taskService.deleteById(id);
        return "redirect:/projects/" + projectId + "/tasks";
    }
}