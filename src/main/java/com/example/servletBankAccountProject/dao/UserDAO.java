package com.example.servletBankAccountProject.dao;

import com.example.servletBankAccountProject.dao.mapper.UserMapper;
import com.example.servletBankAccountProject.exceptions.UserException;
import com.example.servletBankAccountProject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements Crud<User> {

    private static UserDAO instance;


    public static synchronized UserDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public Optional<User> findById(int id) throws UserException {
        Connection connection = DataSource.getConnection();
        User user = null;
        UserMapper userMapper=new UserMapper();
        String find = "SELECT * FROM users WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(find)) {
            preparedStatement.setInt(1, id);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()) {
                user=userMapper.extractFromResultSet(rs);
            }

        } catch (Exception e) {
            user = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public void create(User entity) {
        Connection connection = DataSource.getConnection();
        String insert = "INSERT INTO users (email, first_name, last_name, password, role, state) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement=connection.prepareStatement(insert)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getRole().toString());
            preparedStatement.setString(6, entity.getState().toString());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<User> findAll() {
        Connection connection = DataSource.getConnection();
        List<User> users;
        UserMapper userMapper=new UserMapper();
        String SELECT_ALL_USERS = "SELECT * FROM users";
        try (PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs=preparedStatement.executeQuery();
            users=new ArrayList<>();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            users = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }
        return users;
    }

    @Override
    public void update(User user) {
        Connection connection = DataSource.getConnection();
        String UPDATE_USER_ROLE_AND_STATE = "UPDATE users SET role=role + ?, state=state + ? WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USER_ROLE_AND_STATE)){
            preparedStatement.setString(1, user.getState().toString());
            preparedStatement.setString(2, user.getRole().toString());
            preparedStatement.setLong(3, user.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
