package com.alpha.solutionspkv.repository;

import com.alpha.solutionspkv.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project project = new Project();

        project.setId(rs.getInt("id"));
        project.setName(rs.getString("name"));
        project.setDescription(rs.getString("description"));
        return project;
    };

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> findAll() {
        String sql = "SELECT * FROM projects";
        return jdbcTemplate.query(sql, projectRowMapper);
    }
    
    public Project findById(int id) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        List<Project> projects = jdbcTemplate.query(sql, projectRowMapper, id);
        return projects.isEmpty() ? null : projects.get(0);
    }
    
    public void save(Project project) {
        String sql = "INSERT INTO projects (name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, project.getName(), project.getDescription());
    }
    
    public void update(Project project) {
        String sql = "UPDATE projects SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, project.getName(), project.getDescription(), project.getId());
    }
    
    public void deleteById(int id) {
        String sql = "DELETE FROM projects WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
    public boolean isUserAssignedToProject(int userId, int projectId) {
        String sql = "SELECT COUNT(*) FROM project_users WHERE user_id = ? AND project_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, projectId);
        return count != null && count > 0;
    }