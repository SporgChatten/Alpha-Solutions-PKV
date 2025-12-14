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

    public List<Project> findAllForUser(int userId) {
        String sql = """
                SELECT p.*
                FROM projects p
                JOIN project_users pu ON pu.project_id = p.id
                WHERE pu.user_id = ?
                ORDER BY p.id
                """;
        return jdbcTemplate.query(sql, projectRowMapper, userId);
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

    public int save(Project project) {
        String sql = "INSERT INTO projects (name, description) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new IllegalStateException("Project insert succeeded but no generated key was returned.");
        }
        return key.intValue();
    }

    public void update(Project project) {
        String sql = "UPDATE projects SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, project.getName(), project.getDescription(), project.getId());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM projects WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean isUserAssignedToProject(int userId, int projectId) {
        String sql = "SELECT COUNT(*) FROM project_users WHERE user_id = ? AND project_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, projectId);
        return count != null && count > 0;
    }

    public void assignUserToProject(int userId, int projectId) {
        String sql = "INSERT IGNORE INTO project_users (project_id, user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, projectId, userId);
    }

    public void unassignUserFromProject(int userId, int projectId) {
        String sql = "DELETE FROM project_users WHERE project_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, projectId, userId);
    }
}
