package com.alpha.solutionspkv.service;

import com.alpha.solutionspkv.model.Task;
import com.alpha.solutionspkv.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) { this.taskRepository = taskRepository; }

    public List<Task> findAll() { return taskRepository.findAll(); }

    public List<Task> findByProjectId(int projectId) { return taskRepository.findByProjectId(projectId); }

    public Task findById(int id) { return taskRepository.findById(id); }

    public void save(Task task) { taskRepository.save(task); }

    public void update(Task task) { taskRepository.update(task); }

    public void deleteById(int id) { taskRepository.deleteById(id); }
}
