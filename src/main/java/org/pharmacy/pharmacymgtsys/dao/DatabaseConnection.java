package org.pharmacy.pharmacymgtsys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * This is where Database Connection is initiated
 * It connects to MySQL database using jdbc
 *
 * @author Daniel, jonathan, Hannah, felix, Martin, Bright
 * @version 1.0
 */
public class DatabaseConnection {
    private static Connection connection = null;

    public DatabaseConnection() {
    }

    /**
     * This method gets database properties
     * from resources/db.properties and
     * initiates the connection
     */
    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        } else {
            try {
                InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (SQLException | IOException var5) {
                Exception e = var5;
                e.printStackTrace();
                throw new SQLException("Error connecting to the database");
            }
        }
    }
}

