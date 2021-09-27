package com.example.servletBankAccountProject.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Crud<T> {
    void create(T entity);
    Optional<T> findById(int id) throws Exception;
    List<T> findAll();
    void update(T entity);
    void delete(T entity);

    default void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
