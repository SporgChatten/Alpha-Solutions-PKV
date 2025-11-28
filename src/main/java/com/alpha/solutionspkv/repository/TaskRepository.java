package com.alpha.solutionspkv.repository;

import com.alpha.solutionspkv.model.Project;
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
        return jdbcTemplate.query(sql, new Object[]{projectId}, TaskRowMapper);
    }

    public Task findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        List<Task> tasks = jdbcTemplate.query(sql, new Object[]{id}, TaskRowMapper);
        return tasks.isEmpty() ? null : tasks.get(0);
    }

    public void save(Task task) {
        String sql = "INSERT INTO tasks (project_id, name, description) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, task.getProjectId(), task.getName(), task.getDescription());
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, task.getName(), task.getDescription(), task.getId());
    }

    public  void deleteById(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
