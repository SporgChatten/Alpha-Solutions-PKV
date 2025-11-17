package com.alpha.solutionspkv.Service;

import com.alpha.solutionspkv.Model.Task;
import com.alpha.solutionspkv.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    private TaskService taskService;

    public TaskService(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Task findById(int taskId) {
        return taskRepository.findById(taskId);
    }

    public void create(Task task) { taskRepository.create(task); }

    public void update(Task task) { taskRepository.update(task); }

    public void delete(int taskId) {taskRepository.delete(taskId);}
}
