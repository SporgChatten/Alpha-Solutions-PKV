package com.alpha.solutionspkv.Repository.Rowmapper;

import com.alpha.solutionspkv.Model.Employee;
import com.alpha.solutionspkv.Model.Project;
import com.alpha.solutionspkv.Model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();

        task.setTaskId(rs.getInt("task_id"));
        task.setName(rs.getString("name"));
        task.setDescription(rs.getString("description"));
        task.setEstimatedHours(rs.getDouble("estimated_hours"));
        task.setActualHours(rs.getDouble("actual_hours"));
        task.setCost(rs.getDouble("cost"));

        // ----- PROJECT -----
        int projectId = rs.getInt("project_id");
        Project project = new Project();
        project.setProjectId(projectId);
        task.setProject(project);

        // ----- ASSIGNED EMPLOYEE -----
        int employeeId = rs.getInt("assigned_to");
        if (!rs.wasNull()) { // assigned_to kan være NULL
            Employee employee = new Employee();
            employee.setEmployeeId(employeeId);
            task.setAssignedTo(employee);
        }

        return task;
    }
}
