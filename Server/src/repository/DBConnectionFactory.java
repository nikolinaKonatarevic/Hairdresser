/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import constant.MyServerConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikolina
 */
public class DBConnectionFactory {

    private Connection connection;
    private static DBConnectionFactory instance;

    private DBConnectionFactory() {

    }

    public static DBConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, IOException {
        if (connection == null || connection.isClosed()) {
            try {
                Properties properties = new Properties();
                // properties.load(new FileInputStream());
                properties.load(new FileInputStream("dbconfig.properties"));
                String url = properties.getProperty(MyServerConstants.DB_CONFIG_URL);
                String username = properties.getProperty(MyServerConstants.DB_CONFIG_USERNAME);
                String password = properties.getProperty(MyServerConstants.DB_CONFIG_PASSWORD);

                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(DBConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return connection;
    }
}
