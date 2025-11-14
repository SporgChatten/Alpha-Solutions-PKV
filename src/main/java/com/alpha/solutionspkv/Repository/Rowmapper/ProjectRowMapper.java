package com.alpha.solutionspkv.Repository.Rowmapper;

import com.alpha.solutionspkv.Model.Project;
import com.alpha.solutionspkv.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();

        project.setProjectId(rs.getInt("project_id"));
        project.setName(rs.getString("name"));
        project.setDescription(rs.getString("description"));

        if (rs.getDate("start_date") != null) {
            project.setStartDate(rs.getDate("start_date").toLocalDate());
        }

        if (rs.getDate("end_date") != null) {
            project.setEndDate(rs.getDate("end_date").toLocalDate());
        }

        project.setBudget(rs.getDouble("budget"));

        // ----- CREATED BY USER -----
        int createdById = rs.getInt("created_by");
        if (!rs.wasNull()) {
            User creator = new User();
            creator.setUserId(createdById);
            project.setCreatedBy(creator);
        }

        // TASKS hentes separat → sættes til null her
        project.setTasks(null);

        return project;
    }
}

