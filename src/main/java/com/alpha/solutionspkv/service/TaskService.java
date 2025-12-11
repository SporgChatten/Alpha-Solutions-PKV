package com.alpha.solutionspkv.service;

import com.alpha.solutionspkv.model.Task;
import com.alpha.solutionspkv.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByProjectId(int projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public Task findById(int id) {
        return taskRepository.findById(id);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void update(Task task) {
        taskRepository.update(task);
    }

    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findSubTasks(Integer parentTaskId) {
        return taskRepository.findSubtasks(parentTaskId);
    }

    public List<Task> findRootTasksByProjectId(int projectId) {
        List<Task> all = taskRepository.findByProjectId(projectId);

        List<Task> roots = new ArrayList<>();
        for (Task t : all) {
            if (t.getParentTaskId() == null) {
                roots.add(t);
            }
        }

        return roots;
    }

    public BigDecimal getTotalEstimatedCostForTask(int taskId) {
        Task task = taskRepository.findById(taskId);
        List<Task> subtasks = taskRepository.findSubtasks(taskId);

        BigDecimal total = task.getEstimatedCost() != null ? task.getEstimatedCost() : BigDecimal.ZERO;

        for (Task sub : subtasks) {
            if (sub.getEstimatedCost() != null) {
                total = total.add(sub.getEstimatedCost());
            }
        }
        return total;
    }

    public BigDecimal getTotalEstimatedCostForProject(int projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        BigDecimal total = BigDecimal.ZERO;

        for (Task t : tasks) {
            if (t.getEstimatedCost() != null) {
                total = total.add(t.getEstimatedCost());
            }
        }
        return total;
    }

}