package com.alpha.solutionspkv.Repository;

import com.alpha.solutionspkv.Model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM task";
        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    public Task findById(int taskId) {
        String sql = "SELECT * FROM task WHERE task_id = ?";
        return jdbcTemplate.queryForObject(sql, new TaskRowMapper(), taskId);
    }

    public void create(Task task) {
        String sql = "INSERT INTO task (project_id, assigned_to, name, description, estimated_hours, actual_hours, cost) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                task.getProject() != null ? task.getProject().getProjectId() : null,
                task.getAssignedTo() != null ? task.getAssignedTo().getEmployeeId() : null,
                task.getName(),
                task.getDescription(),
                task.getEstimatedHours(),
                task.getActualHours(),
                task.getCost()
        );
    }

    public void update(Task task) {
        String sql = "UPDATE task SET project_id = ?, assigned_to = ?, name = ?, description = ?, " +
                "estimated_hours = ?, actual_hours = ?, cost = ? WHERE task_id = ?";

        jdbcTemplate.update(sql,
                task.getProject() != null ? task.getProject().getProjectId() : null,
                task.getAssignedTo() != null ? task.getAssignedTo().getEmployeeId() : null,
                task.getName(),
                task.getDescription(),
                task.getEstimatedHours(),
                task.getActualHours(),
                task.getCost(),
                task.getTaskId()
        );
    }

    public void delete(int taskId) {
        String sql = "DELETE FROM task WHERE task_id = ?";
        jdbcTemplate.update(sql, taskId);
    }
}

