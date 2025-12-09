package com.alpha.solutionspkv.repository;

import com.alpha.solutionspkv.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Task> TaskRowMapper = (rs, rowNum) -> {
        Task task = new Task();

        task.setId(rs.getInt("id"));
        task.setProjectId(rs.getInt("project_id"));
        task.setName(rs.getString("name"));
        task.setDescription(rs.getString("description"));
        String statusStr = rs.getString("status");
        if (statusStr != null) {
            task.setStatus(Task.Status.valueOf(statusStr));
        }
        task.setParentTaskId(rs.getObject("parent_task_id", Integer.class));

        return task;
    };

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, TaskRowMapper);
    }

    public List<Task> findByProjectId(int projectId) {
        String sql = "SELECT * FROM tasks WHERE project_id = ?";
        return jdbcTemplate.query(sql, TaskRowMapper, projectId);
    }

    public List<Task> findSubtasks(int parentTaskId) {
        String sql = "SELECT * FROM tasks WHERE parent_task_id = ?";
        return jdbcTemplate.query(sql, TaskRowMapper, parentTaskId);
    }

    public Task findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        List<Task> tasks = jdbcTemplate.query(sql, TaskRowMapper, id);
        return tasks.isEmpty() ? null : tasks.get(0);
    }

    public void save(Task task) {
        String sql = "INSERT INTO tasks (project_id, name, description, status, parent_task_id) VALUES (?, ?, ?, ?, ?)";
        String status = task.getStatus() != null ? task.getStatus().name() : Task.Status.NOT_STARTED.name();
        jdbcTemplate.update(sql, task.getProjectId(), task.getName(), task.getDescription(), status,
                task.getParentTaskId()
        );
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET name = ?, description = ?, status = ?, parent_task_id = ? WHERE id = ?";
        String status = task.getStatus() != null ? task.getStatus().name() : Task.Status.IN_PROGRESS.name();
        jdbcTemplate.update(sql, task.getName(), task.getDescription(), status, task.getParentTaskId(), task.getId());
    }

    public  void deleteById(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}