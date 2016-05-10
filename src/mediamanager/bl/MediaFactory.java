/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.bl;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Josue
 */
public class MediaFactory {
        
    public static Movie createEmptyMovie()
    {
        return new Movie();
    }
    
    public static TVSerie createEmpyTVSerie()
    {
        return new TVSerie();
    }
    
    public static AnimeSerie createEmptyAnimeSerie()
    {
        return new AnimeSerie();
    }
    
    public static Game createEmptyGame()
    {
        return new Game();
    }
    
    public static Media createEmptyMedia()
    {
        return new Media();
    }
    
    public static List<Movie> createEmptyMovieList()
    {
        return new ArrayList<>();
    }
    
    public static List<TVSerie> createEmptyTVSeriesList()
    {
        return new ArrayList<>();
    }
    
    public static List<Game> createEmptyGameList()
    {
        return new ArrayList<>();
    }
    
    public static List<AnimeSerie> createEmptyAnimeSerieList()
    {
        return new ArrayList<>();
    }
    
    
    
}
