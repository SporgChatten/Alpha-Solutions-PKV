package com.alpha.solutionspkv.repository;

import com.alpha.solutionspkv.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        String roleStr = rs.getString("role");
        if (roleStr != null) {
            user.setRole(User.Role.valueOf(roleStr));
        }
        return user;
    };

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Find all users
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public List<User> findUsersAssignedToProject(int projectId) {
        String sql = """
                SELECT u.*
                FROM users u
                JOIN project_users pu ON pu.user_id = u.id
                WHERE pu.project_id = ?
                ORDER BY u.username
                """;
        return jdbcTemplate.query(sql, userRowMapper, projectId);
    }

    public List<User> findUsersNotAssignedToProject(int projectId) {
        String sql = """
                SELECT u.*
                FROM users u
                WHERE u.id NOT IN (
                    SELECT pu.user_id FROM project_users pu WHERE pu.project_id = ?
                )
                ORDER BY u.username
                """;
        return jdbcTemplate.query(sql, userRowMapper, projectId);
    }

    // Find user by ID
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, id);
        return users.isEmpty() ? null : users.get(0);
    }

    // Find user by username
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, username);
        return users.isEmpty() ? null : users.get(0);
    }

    // Find user by username and password (for login)
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, username, password);
        return users.isEmpty() ? null : users.get(0);
    }

    // Save new user
    public void save(User user) {
        String sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        String role = user.getRole() != null ? user.getRole().name() : User.Role.EMPLOYEE.name();
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), role);
    }

    // Update user
    public void update(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, role = ? WHERE id = ?";
        String role = user.getRole() != null ? user.getRole().name() : User.Role.EMPLOYEE.name();
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), role, user.getId());
    }

    // Delete user
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
