/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mediamanager.bl.*;

/**
 *
 * @author Josue
 */
public class DatabaseManager {
    
    private DatabaseManager() { } // make your constructor private, so the only war
                              // to access "application" is through singleton pattern

    private static DatabaseManager _app;

    public static DatabaseManager getInstance() 
    {
        if (_app == null)
            _app = new DatabaseManager();
        return _app;
    }
    
    public List<Movie> GetAllMovies(String mediaType)
    {
        List<Movie> movies = MediaFactory.createEmptyMovieList();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getConnection();
            String moviesTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            String query = "select NAME,TYPE,YEAR,DESCRIPTION from " + moviesTable;
            ResultSet results = DatabaseAccess.getResultSet(conn, query);
            
            while(results.next())
            {
                Movie m = MediaFactory.createEmptyMovie();
                m.setName(results.getString("NAME"));
                m.setType(results.getString("TYPE"));
                m.setYear((int)results.getShort("YEAR"));
                m.setDescription(results.getString("DESCRIPTION"));
                movies.add(m);
            }
            
            conn.close();
            
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return movies;
    }
    
    public List<TVSerie> GetAllTVSeries(String mediaType)
    {
        List<TVSerie> series = MediaFactory.createEmptyTVSeriesList();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getConnection();
            String seriesTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            String query = "select NAME,TYPE,YEAR,DESCRIPTION from " + seriesTable;
            ResultSet results = DatabaseAccess.getResultSet(conn, query);
            
            while(results.next())
            {
                TVSerie s = MediaFactory.createEmpyTVSerie();
                s.setName(results.getString("NAME"));
                s.setType(results.getString("TYPE"));
                s.setYear((int)results.getShort("YEAR"));
                s.setDescription(results.getString("DESCRIPTION"));
                series.add(s);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
        return series;
    }
    
    public List<AnimeSerie> GetAllAnimeSeries(String mediaType)
    {
        List<AnimeSerie> series = MediaFactory.createEmptyAnimeSerieList();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getConnection();
            String animeTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            String query = "select NAME,TYPE,YEAR,DESCRIPTION from " + animeTable;
            ResultSet results = DatabaseAccess.getResultSet(conn, query);
            
            while(results.next())
            {
                AnimeSerie s = MediaFactory.createEmptyAnimeSerie();
                s.setName(results.getString("NAME"));
                s.setType(results.getString("TYPE"));
                s.setYear((int)results.getShort("YEAR"));
                s.setDescription(results.getString("DESCRIPTION"));
                series.add(s);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
        return series;
    }
 
    public List<Game> GetAllGames(String mediaType)
    {
        List<Game> games = MediaFactory.createEmptyGameList();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getConnection();
            String animeTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            String query = "select NAME,TYPE,YEAR,DESCRIPTION from " + animeTable;
            ResultSet results = DatabaseAccess.getResultSet(conn, query);
            
            while(results.next())
            {
                Game g = MediaFactory.createEmptyGame();
                g.setName(results.getString("NAME"));
                g.setType(results.getString("TYPE"));
                g.setYear((int)results.getShort("YEAR"));
                g.setDescription(results.getString("DESCRIPTION"));
                games.add(g);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
        return games;
    }    
    
    public Media getAllInformation(String mediaType,String name)
    {
        Media media = MediaFactory.createEmptyMedia();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getConnection();
            String mediaTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            ResultSet results = DatabaseAccess.getAllInformation(conn, mediaTable, name);
            while(results.next())
            {   
                
                byte[] bytes1 = results.getBytes("IMAGE1");
                media.setImage(bytes1, 0);
                byte[] bytes2 = results.getBytes("IMAGE2");
                media.setImage(bytes2, 1);
                byte[] bytes3 = results.getBytes("IMAGE3");
                media.setImage(bytes3, 2);
                media.setName(name);
                media.setDescription(results.getString("DESCRIPTION"));
                media.setType(results.getString("TYPE"));
                media.setYear(results.getInt("YEAR"));
                
            }
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
        return media;
    }
    
    public Media getVideoInformation(String mediaType,String name)
    {
        Media media = MediaFactory.createEmptyMedia();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getConnection();
             String mediaTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
             ResultSet results = DatabaseAccess.getVideoInformation(conn, mediaTable, name);
            while(results.next())
            { 
                byte[] videoData = results.getBytes("VIDEO");
                String extension = results.getString("VIDEOEXTENTION");
                media = new Media(name,videoData,extension);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
        return media;
    }
    
    public boolean updateMediaInformation(String name,String description,String type,int year,String previousName,String mediaType)
    {
        boolean result = false;
        try
        {
            String mediaTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            result = DatabaseAccess.updateMediaInformation(DataBaseConnection.getConnection(), mediaTable, name, type, description, year, previousName);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean insertMediaInformation(String name,String description,String type,int year,String mediaType)
    {
        boolean result  = false;
        try
        {
            String mediaTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            result = DatabaseAccess.insertMediaInformation(DataBaseConnection.getConnection(), mediaTable, name, type, description, year);
        }
        catch (InstantiationException | IllegalAccessException ex){
             Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean updateImages(String mediaType,String name,byte[] imageBytes,ElementTypes type)
    {
        boolean result = false;
        try
        {
            String mediaTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            result = DatabaseAccess.insertBytesInTables(DataBaseConnection.getConnection(), mediaTable, name, imageBytes, type);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean updateVideo(String mediaType,String name,byte[] videoBytes,String extension)
    {
        boolean result = false;
        try
        {
            String mediaTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            result = DatabaseAccess.insertVideoBytesAndExtentionInTables(DataBaseConnection.getConnection(), mediaTable, name, videoBytes,extension); 
         } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean deleteMediaInformation(String mediaType,String name)
    {
        boolean result = false;
        try
        {
            String mediaTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
            result = DatabaseAccess.deleteMediaInformation(DataBaseConnection.getConnection(), mediaTable, name);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String[] searchMediaInformation(String mediaType,String name)
    {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getConnection();
             String mediaTable = mediamanager.config.ConfigurationReader.geTableName(mediaType);
             ResultSet results = DatabaseAccess.searchLikeMediaInformation(conn, mediaTable, name);
            while(results.next())
            { 
                String sname = results.getString("NAME");
                list.add(sname);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
        return list.toArray(new String[list.size()]);
    }
    
    public boolean isConnectionAvailable()
    {
        try
        {
            return DataBaseConnection.isConnectionAvailable();
        }
        catch (InstantiationException | SQLException | IllegalAccessException | ClassNotFoundException ex)
        {
            return false;
        }
    }
    
    
}
