/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Paulo
 */
public class ConnectionDAO {
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:postgresql://localhost/database5";
        
        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);
		
        return DriverManager.getConnection(url, properties);
    }
    
}
