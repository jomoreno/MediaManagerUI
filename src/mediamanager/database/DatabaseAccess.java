/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import mediamanager.bl.ElementTypes;


/**
 *
 * @author a07729a
 */
class DatabaseAccess {
    
    protected static ResultSet getResultSet(Connection conn,String query)
    {
        ResultSet resultSet = null;
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;       
    }
    
    protected static boolean insertUpdateOrDeleteInformation(Connection conn,String query)
    {
        boolean inserted = false;
        try
        {
            Statement stm = conn.createStatement();
            int updatedCount = stm.executeUpdate(query);
            inserted = (updatedCount > 0);
        }
        catch(SQLException ex){
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex); 
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
               Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return inserted;
    }
    
    protected static boolean insertBytesInTables(Connection conn,String tableName,String name,byte[] bytesToInsert,ElementTypes type)
    {
        PreparedStatement statement = null;
        boolean updated = false;
        try
        {
            String insertBytes = "update "+ tableName +" set " + type.toString() +"= ? where NAME=?"; 
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(insertBytes);
            statement.setBytes(1, bytesToInsert);
            statement.setString(2, name);
            int updateCount = statement.executeUpdate();
            conn.commit();
            updated = (updateCount > 0);
        }
        catch (SQLException ex){
             if (conn != null) {
                try {
                    System.err.println("Transaction is being rolled back");
                    conn.rollback();
                } catch(SQLException excep) { }
             }
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
               Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return updated;
    }
    
    protected static boolean insertVideoBytesAndExtentionInTables(Connection conn,String tableName,String name,byte[] bytesToInsert,String extention)
    {
        PreparedStatement statement = null;
        boolean updated = false;
        try
        {
            String insertBytes = "update "+ tableName +" set VIDEO=?,VIDEOEXTENTION=? where NAME=?"; 
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(insertBytes);
            statement.setBytes(1, bytesToInsert);
            statement.setString(2, extention);
            statement.setString(3, name);
            int updateCount = statement.executeUpdate();
            conn.commit();
            updated = (updateCount > 0);
        }
        catch (SQLException ex){
             if (conn != null) {
                try {
                    System.err.println("Transaction is being rolled back");
                    conn.rollback();
                } catch(SQLException excep) { }
             }
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
               Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return updated;
    }
    
    protected static boolean insertMediaInformation(Connection conn,String tableName,String name,String type,String desc,int year)
    {
        PreparedStatement statement;
        boolean inserted = false;
        try
        {
            String insertInformation = "insert into "+ tableName +" (NAME,TYPE,DESCRIPTION,YEAR) values (?,?,?,?)";
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(insertInformation);
            statement.setString(1, name);
            statement.setString(2, type);
            statement.setString(3, desc);
            statement.setShort(4, Short.valueOf(String.valueOf(year)));
            int insertCount = statement.executeUpdate();
            conn.commit();
            inserted = (insertCount > 0);
        }
        catch (Exception ex){
            if (conn != null) {
                try {
                    System.err.println("Transaction is being rolled back");
                    conn.rollback();
                }
                catch(SQLException e){}
            }
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
               Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return inserted;      
    }
    
    protected static ResultSet getAllInformation(Connection conn,String tableName,String name)
    {
        PreparedStatement statement;
        ResultSet resultSet = null;
        try
        {
            String getInformation = "select NAME,DESCRIPTION,TYPE,YEAR,IMAGE1,IMAGE2,IMAGE3 from "+ tableName +" where NAME=?";
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(getInformation);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
        }
        catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

    protected static ResultSet getVideoInformation(Connection conn,String tableName,String name)
    {
        PreparedStatement statement;
        ResultSet resultSet = null;
        try
        {
            String insertInformation = "select VIDEOEXTENTION,VIDEO from "+ tableName +" where NAME=?";
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(insertInformation);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
        }
        catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
        
    }
    
    protected static boolean updateMediaInformation(Connection conn,String tableName,String name,String type,String desc,int year,String oldName)
    {
        PreparedStatement statement;
        boolean inserted = false;
        try
        {
            String insertInformation = "update "+ tableName +" set NAME=?,TYPE=?,DESCRIPTION=?,YEAR=? where NAME=?";
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(insertInformation);
            statement.setString(1, name);
            statement.setString(2, type);
            statement.setString(3, desc);
            statement.setShort(4, Short.valueOf(String.valueOf(year)));
            statement.setString(5, oldName);
            int insertCount = statement.executeUpdate();
            conn.commit();
            inserted = (insertCount > 0);
        }
        catch (Exception ex){
            if (conn != null) {
                try {
                    System.err.println("Transaction is being rolled back");
                    conn.rollback();
                    conn.close();
                }
                catch(SQLException e){}
            }
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
               Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return inserted;      
    }
    
    protected static boolean deleteMediaInformation(Connection conn,String tableName,String name)
    {
        PreparedStatement statement;
        boolean deleted = false;
        try
        {
            String deleteInformation = "delete "+ tableName +" where NAME=?";
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(deleteInformation);
            statement.setString(1, name);
            int deleteCount = statement.executeUpdate();
            conn.commit();
            deleted = (deleteCount > 0);
        }
        catch (Exception ex){
            if (conn != null) {
                try {
                    System.err.println("Transaction is being rolled back");
                    conn.rollback();
                    conn.close();
                }
                catch(SQLException e){}
            }
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
               Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return deleted;      
    }
    
    protected static ResultSet searchLikeMediaInformation(Connection conn,String tableName,String name)
    {
        PreparedStatement statement;
        ResultSet resultSet = null;
        try
        {
            String searchInformation = "select NAME from "+ tableName +" where NAME like '%"+name+"%'";
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(searchInformation);
            resultSet = statement.executeQuery();
        }
        catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }
    
}
