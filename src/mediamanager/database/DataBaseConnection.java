/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.database;


import java.sql.Connection;
import java.sql.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;

import mediamanager.config.ConfigurationReader;

/**
 *
 * @author a07729a
 */
public class DataBaseConnection {
    
    public static Connection getConnection() throws InstantiationException, IllegalAccessException
    {
        
        String server = ConfigurationReader.getDataServer();
        String database = ConfigurationReader.getDataBaseName();
        String userName = ConfigurationReader.getUserName();
        String password = ConfigurationReader.getPassword();
        
        Connection conn = null;
        try
        {
            Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = d.connect(
                "jdbc:sqlserver://"+server+";databaseName="+database+";user="+userName+";password="+password+";",new Properties());
            
        }
        catch (SQLException | ClassNotFoundException ex){
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return conn;
    }
    
    public static boolean isConnectionAvailable() throws InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException
    {
        String server = ConfigurationReader.getDataServer();
        String database = ConfigurationReader.getDataBaseName();
        String userName = ConfigurationReader.getUserName();
        String password = ConfigurationReader.getPassword();
        Connection conn = null;
        Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = d.connect(
                "jdbc:sqlserver://"+server+";databaseName="+database+";user="+userName+";password="+password+";",new Properties());
        conn.close();
        return true;
    }
    
}
