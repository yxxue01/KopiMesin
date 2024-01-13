/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model  ;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class Database implements AutoCloseable{
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl( "jdbc:mysql://localhost/kopimesin" );
        config.setUsername( "root" );
        config.setPassword( "" );
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(300000);
        config.setConnectionTimeout(120000);
        config.setLeakDetectionThreshold(300000);
        ds = new HikariDataSource( config );
    }

    public static synchronized Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    @Override
    public synchronized void close() {
        if (ds != null) {
            ds.close();
            ds = null;
        }
    }
}
