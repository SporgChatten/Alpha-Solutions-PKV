package com.alpha.solutionspkv.Repository;

import com.alpha.solutionspkv.Model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> findAll() {
        String sql = "SELECT * FROM project";
        return jdbcTemplate.query(sql, new ProjectRowMapper());
    }

    public Project findById(int projectId) {
        String sql = "SELECT * FROM project WHERE project_id = ?";
        return jdbcTemplate.queryForObject(sql, new ProjectRowMapper(), projectId);
    }

    public void create(Project project) {
        String sql = "INSERT INTO project (name, description, start_date, end_date, budget, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getBudget(),
                project.getCreatedBy() != null ? project.getCreatedBy().getUserId() : null
        );
    }

    public void update(Project project) {
        String sql = "UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ?, " +
                "budget = ?, created_by = ? WHERE project_id = ?";

        jdbcTemplate.update(sql,
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getBudget(),
                project.getCreatedBy() != null ? project.getCreatedBy().getUserId() : null,
                project.getProjectId()
        );
    }
    public void delete(int id) {
        String sql = "DELETE FROM project WHERE project_id = ?";
        jdbcTemplate.update(sql, id);
    }
}

