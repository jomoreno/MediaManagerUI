/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.bl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import mediamanager.database.DatabaseManager;

/**
 *
 * @author Josue
 */
public class MediaManager {
    
    public static JTree getJTreeByAlphabet(String mediaType)
    {
        JTree tree = null;
        
        switch (mediaType)
         {
             case "Movies": {
                 List<Movie> movieList = DatabaseManager.getInstance().GetAllMovies(mediaType);
                 tree = createJTreeByAlphabet((List<Media>)(List<?>)movieList, mediaType);
             } break;
             case "Anime Series":{
                 List<AnimeSerie> animeList = DatabaseManager.getInstance().GetAllAnimeSeries(mediaType);
                 tree = createJTreeByAlphabet((List<Media>)(List<?>)animeList, mediaType);
             } break;
             case "TV Series":{
                  List<TVSerie> tvList = DatabaseManager.getInstance().GetAllTVSeries(mediaType);
                  tree = createJTreeByAlphabet((List<Media>)(List<?>)tvList, mediaType);
             } break;
             case "Games":{
                  List<Game> gameList = DatabaseManager.getInstance().GetAllGames(mediaType);
                  tree = createJTreeByAlphabet((List<Media>)(List<?>)gameList, mediaType);
             } break;
             default: {
                 // bring movies.
                  List<Movie> movieList = DatabaseManager.getInstance().GetAllMovies(mediaType);
                  tree = createJTreeByAlphabet((List<Media>)(List<?>)movieList, mediaType);
             } break;
         }
        
        tree.setSelectionModel(new DefaultTreeSelectionModel());
        return tree;
        
    }
    
    public static JTree getJTreeByType(String mediaType)
    {
        JTree tree = null;
        
        switch (mediaType)
         {
             case "Movies": {
                 List<Movie> movieList = DatabaseManager.getInstance().GetAllMovies(mediaType);
                 tree = createJTreeByType((List<Media>)(List<?>)movieList, mediaType);
             } break;
             case "Anime Series":{
                 List<AnimeSerie> animeList = DatabaseManager.getInstance().GetAllAnimeSeries(mediaType);
                 tree = createJTreeByType((List<Media>)(List<?>)animeList, mediaType);
             } break;
             case "TV Series":{
                  List<TVSerie> tvList = DatabaseManager.getInstance().GetAllTVSeries(mediaType);
                  tree = createJTreeByType((List<Media>)(List<?>)tvList, mediaType);
             } break;
             case "Games":{
                  List<Game> gameList = DatabaseManager.getInstance().GetAllGames(mediaType);
                  tree = createJTreeByType((List<Media>)(List<?>)gameList, mediaType);
             } break;
             default: {
                 // bring movies.
                  List<Movie> movieList = DatabaseManager.getInstance().GetAllMovies(mediaType);
                  tree = createJTreeByType((List<Media>)(List<?>)movieList, mediaType);
             } break;
         }
        
        tree.setSelectionModel(new DefaultTreeSelectionModel());
        return tree;
    }
    
    public static JTree getJTreeByYear(String mediaType)
    {
        JTree tree = null;
        
        switch (mediaType)
         {
             case "Movies": {
                 List<Movie> movieList = DatabaseManager.getInstance().GetAllMovies(mediaType);
                 tree = createJTreeByYear((List<Media>)(List<?>)movieList, mediaType);
             } break;
             case "Anime Series":{
                 List<AnimeSerie> animeList = DatabaseManager.getInstance().GetAllAnimeSeries(mediaType);
                 tree = createJTreeByYear((List<Media>)(List<?>)animeList, mediaType);
             } break;
             case "TV Series":{
                  List<TVSerie> tvList = DatabaseManager.getInstance().GetAllTVSeries(mediaType);
                  tree = createJTreeByYear((List<Media>)(List<?>)tvList, mediaType);
             } break;
             case "Games":{
                  List<Game> gameList = DatabaseManager.getInstance().GetAllGames(mediaType);
                  tree = createJTreeByYear((List<Media>)(List<?>)gameList, mediaType);
             } break;
             default: {
                 // bring movies.
                  List<Movie> movieList = DatabaseManager.getInstance().GetAllMovies(mediaType);
                  tree = createJTreeByYear((List<Media>)(List<?>)movieList, mediaType);
             } break;
         }
        
        tree.setSelectionModel(new DefaultTreeSelectionModel());
        return tree;
    }
    
    protected static JTree createJTreeByAlphabet(List<Media> mediaToOrder,String mediaType)
    {
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode(mediaType);
        
        String[] chars = {"#","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] specialChars = {"0","1","2","3","4","5","6","7","8","9","."};
        
        for(int i=0;i<chars.length;i++)
        {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(chars[i]);
            
            for (Iterator<Media> it = mediaToOrder.iterator(); it.hasNext();) {
                Media media = it.next();
                if("#".equals(chars[i]))
                {
                    for(int j=0;j<specialChars.length;j++)
                    {
                        if(media.getName().startsWith(specialChars[j]))
                        {
                            DefaultMutableTreeNode leafNode = new DefaultMutableTreeNode(media.getName());
                            node.add(leafNode);
                        }
                    }
                }
                else
                {
                    if(media.getName().startsWith(chars[i]))
                    {
                          DefaultMutableTreeNode leafNode = new DefaultMutableTreeNode(media.getName());
                          node.add(leafNode);
                    }
                }
            }
            
            top.add(node);
            
        }
        
        JTree tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        return tree;
        
    }
   
    protected static JTree createJTreeByType(List<Media> mediaToOrder,String mediaType)
    {
        DefaultMutableTreeNode top =new DefaultMutableTreeNode(mediaType);
        String[] type = getMediaTypeCategories(mediaType);
        
        for(int i=0;i<type.length;i++)
        {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(type[i]);
            
            for (Iterator<Media> it = mediaToOrder.iterator(); it.hasNext();) {
                Media media = it.next();
                if(media.getType() != null && media.getType().equals(type[i]))
                {
                    DefaultMutableTreeNode leafNode = new DefaultMutableTreeNode(media.getName());
                    node.add(leafNode);
                }
            }
            
            if(node.getChildCount() > 0)
            {
                top.add(node);
            }
        }
        
        JTree tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        return tree;
        
    }
    
    protected static JTree createJTreeByYear(List<Media> mediaToOrder,String mediaType)
    {
         DefaultMutableTreeNode top =new DefaultMutableTreeNode(mediaType);
         int[] years = getYears();
         
         for(int i=0;i<years.length;i++)
         {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(years[i]);
            for (Iterator<Media> it = mediaToOrder.iterator(); it.hasNext();) {
                Media media = it.next();
                if(media.getYear() == years[i])
                {
                    DefaultMutableTreeNode leafNode = new DefaultMutableTreeNode(media.getName());
                    node.add(leafNode);
                }
            }
            
            if(node.getChildCount() > 0)
            {
                top.add(node);
            }
         }
         
        JTree tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        return tree;
         
    }
     
    public static String[] getMediaTypeCategories(String mediaType)
    {
        switch(mediaType)
        {
            case "Movies": {
                 return new String[] {"Comedy","Suspense","Drama","Adventure","Action","Horror","Terror","Documentary","Other","Fantasy","Romance", "Special", "Mystery", "Sci-Fi"};
             } 
             case "Anime Series":{
                 return new String[] {"Comedy","Suspense","Drama","Adventure","Action","Horror","Terror","Other","Mystery","Sci-Fi"};
             } 
             case "TV Series":{
                 return new String[] {"Comedy","Drama","Suspense","Action","Fantasy","Adventure"};
             } 
             case "Games":{
                 return new String[] {"Action","Suspense","Sports","Fantasy","Other"};
             } 
             default: {
                 return new String[] {"Comedy","Suspense","Drama","Adventure","Action","Horror","Terror","Documentary","Other","Fantasy"};
             }
        }
    }
    
    private static int[] getYears()
    {
        SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");
        java.util.Date nowDate = new java.util.Date();
        int currentYear = Integer.parseInt(formatNowYear.format(nowDate));
        int maxAmountOfYears = currentYear - 1900;
        int[] years = new int[maxAmountOfYears];
        for(int i=0;i<years.length;i++)
        {
            years[i] = currentYear;
            currentYear--;
        }
        return years;
    }
    
    public static String[] getStringYears()
    {
        SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");
        java.util.Date nowDate = new java.util.Date();
        int currentYear = Integer.parseInt(formatNowYear.format(nowDate));
        int maxAmountOfYears = currentYear - 1900;
        String[] years = new String[maxAmountOfYears];
        for(int i=0;i<years.length;i++)
        {
            years[i] =  Integer.toString(currentYear);
            currentYear--;
        }
        return years;
    }
    
    public static Media getMovieInformation(String mediaType,String name)
    {
        return DatabaseManager.getInstance().getAllInformation(mediaType, name);
    }
    
    public static Media getGameInformation(String mediaType,String name)
    {
        return DatabaseManager.getInstance().getAllInformation(mediaType, name);
    }
    
    public static Media getTVSerieInformation(String mediaType,String name)
    {
        return DatabaseManager.getInstance().getAllInformation(mediaType, name);
    }
    
    public static Media getAnimeSerieInformation(String mediaType,String name)
    {
        return DatabaseManager.getInstance().getAllInformation(mediaType, name);
    }
    
    public static Media getVideoInformation(String mediaType, String name)
    {
        return DatabaseManager.getInstance().getVideoInformation(mediaType, name);
    }
    
    public static boolean updateMediaInformation(String name,String description,String type,int year,String previousName,String mediaType)
    {
        return DatabaseManager.getInstance().updateMediaInformation(name, description, type, year, previousName, mediaType);
    }
    
    public static boolean insertMediaInformation(String name,String description,String type,int year,String mediaType)
    {
        return DatabaseManager.getInstance().insertMediaInformation(name, description, type, year, mediaType);
    }
    
    public static boolean updateImages(String mediaType,String name,byte[] imageBytes,ElementTypes type)
    {
        return DatabaseManager.getInstance().updateImages(mediaType, name, imageBytes, type);
    }
    
    public static boolean updateVideo(String mediaType,String name,byte[] videoBytes,String extention)
    {
        return DatabaseManager.getInstance().updateVideo(mediaType, name, videoBytes, extention);
    }
    
    public static boolean deleteMediaInformation(String mediaType,String name)
    {
        return DatabaseManager.getInstance().deleteMediaInformation(mediaType, name);
    }
          
    public static String[] searchMediaInformation(String mediaType,String name)
    {
        return DatabaseManager.getInstance().searchMediaInformation(mediaType, name);
    }
    
    public static boolean isConnectionAvailable()
    {
        return DatabaseManager.getInstance().isConnectionAvailable();
    }
    
}
