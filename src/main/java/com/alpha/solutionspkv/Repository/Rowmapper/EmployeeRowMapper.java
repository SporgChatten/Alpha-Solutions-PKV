package com.alpha.solutionspkv.Repository.Rowmapper;

import com.alpha.solutionspkv.Model.Employee;
import com.alpha.solutionspkv.Model.User;
import com.alpha.solutionspkv.Model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

        // Map User objektet først
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role"))); // ADMIN / MANAGER / EMPLOYEE

        // Map Employee
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setUser(user);
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setHourlyRate(rs.getDouble("hourly_rate"));

        return employee;
    }
}

