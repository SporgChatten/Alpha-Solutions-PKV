package com.alpha.solutionspkv.Repository;

import com.alpha.solutionspkv.Model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {


    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public Employee findById(int id) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
    }

    public void create(Employee employee) {
        String sql = "INSERT INTO employee (user_id, first_name, last_name, hourly_rate) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                employee.getUser() != null ? employee.getUser().getUserId() : null,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getHourlyRate()
        );
    }

    public void update(Employee employee) {
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, hourly_rate = ?, user_id = ? " +
                "WHERE employee_id = ?";

        jdbcTemplate.update(sql,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getHourlyRate(),
                employee.getUser() != null ? employee.getUser().getUserId() : null,
                employee.getEmployeeId()
        );
    }

    public void delete(int employeeId) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        jdbcTemplate.update(sql, employeeId);
    }

}
