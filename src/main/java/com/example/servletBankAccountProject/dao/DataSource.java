package com.example.servletBankAccountProject.dao;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/bankAccount");
        config.setUsername("postgres");
        config.setPassword("123");
        config.addDataSourceProperty("cachePrepStmts" , "true");
        config.addDataSourceProperty("prepStmtCacheSize" , "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(20);
        config.setConnectionTimeout(20000);
        config.setIdleTimeout(20000);
        config.setMaxLifetime(20000);

        ds = new HikariDataSource(config);
    }

    private DataSource() {}

    public static synchronized Connection getConnection() {
        Connection connection=null;
        try {
            connection = ds.getConnection();
        } catch (SQLException ex) {
            //TODO LOGGER
//            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                //TODO LOGGER
//                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

}
