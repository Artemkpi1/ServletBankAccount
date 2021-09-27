package com.example.servletBankAccountProject.dao.mapper;

import com.example.servletBankAccountProject.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user=new User();

        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));

        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        user.setRole(rs.getString("role"));
        user.setState(rs.getString("state"));

        return user;
    }
}
