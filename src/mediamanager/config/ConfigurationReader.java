/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author Josue
 */
public class ConfigurationReader {
    
    private static Properties getProperties()
    {
        Properties prop = new Properties();
        
    	try {
                //load a properties file
                // config file should be read as a stream not as a file. 
                InputStream is = ConfigurationReader.class.getResourceAsStream("config.properties");
                InputStreamReader isr = new InputStreamReader(is);
                prop.load(isr);
                
 
    	} catch (IOException ex) {
                ex.printStackTrace();
                return null;
        }
        
        return prop;
    }
    
    public static String getDataServer()
    {
        return getProperties().getProperty("server");             
    }
    
    public static String getDataBaseName()
    {
        return getProperties().getProperty("datasource");             
    }
    
    public static String getUserName()
    {
        return getProperties().getProperty("username");             
    }
    
     public static String getPassword()
    {
        return getProperties().getProperty("password");             
    }
    
     public static String geTableName(String mediaType)
     {
         switch (mediaType)
         {
             case "Movies": {
                 return getProperties().getProperty("moviestable");
             }
             case "Anime Series":{
                 return getProperties().getProperty("animeseriestable");
             } 
             case "TV Series":{
                 return getProperties().getProperty("tvseriestable");
             } 
             case "Games":{
                 return getProperties().getProperty("gamestable");
             } 
             default: {
                 // bring movies
                 return getProperties().getProperty("moviestable");
             }
         }
     }
     
     
}
