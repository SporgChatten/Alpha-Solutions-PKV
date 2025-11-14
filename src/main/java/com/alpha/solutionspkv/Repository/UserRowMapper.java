package com.alpha.solutionspkv.Repository;

import com.alpha.solutionspkv.Model.Role;
import com.alpha.solutionspkv.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        // Role (DB → enum)
        String roleString = rs.getString("role");
        if (roleString != null) {
            user.setRole(Role.valueOf(roleString));
        }

        // firstName & lastName kommer fra employee-tabellen (via JOIN)
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));

        return user;
    }
}