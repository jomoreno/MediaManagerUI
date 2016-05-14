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
    
    public static String getExportHTMLMedia(String mediaType)
    {
        StringBuilder sb = new StringBuilder();
        List<Media> mediaList = getGenericMediaList(mediaType);
        
        sb.append("<html>");
        sb.append("<body bgcolor='gray'>");
        sb.append("<h2 style='color:black;'>").append(mediaType).append(" List</h2>");
        sb.append("<table style='width:1200px'>");
        sb.append("<tr style='vertical-align: text-top; color:white; font:14px Arial; background-color:#A0A0A0;'><td><strong>Name</strong></td><td><strong>Description</strong></td><td><strong>Type</strong></td><td><strong>Year</strong></td></tr>");
        
        for(Media m : mediaList)
        {
            sb.append("<tr style='vertical-align: text-top; color:white; font:14px Arial; background-color:#A0A0A0;'><td valign=\"top\">").append(m.getName()).append("</td><td valign=\"top\">").
                          append(m.getDescription()).append("</td><td valign=\"top\">").
                    append(m.getType()).append("</td><td valign=\"top\">").append(m.getYear()).append("</td></tr>");
        }
        
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
        return sb.toString();
    }
    
    public static String getExportTabMedia(String mediaType)
    {
        StringBuilder sb = new StringBuilder();
        List<Media> mediaList = getGenericMediaList(mediaType);
        for(Media m : mediaList)
        {
            sb.append(m.getName());
            sb.append("|");
            sb.append(m.getYear());
            sb.append("|");
            sb.append(m.getType());
            sb.append("|");
            sb.append(m.getDescription());
            sb.append("\r\n");
        }
        return sb.toString();
    }
    
    public static String getExportHTMLMediaExample(String mediaType)
    {
        StringBuilder sb = new StringBuilder();
        List<Media> mediaList = getGenericMediaList(mediaType);
        
        sb.append("<html>");
        sb.append("<body bgcolor='gray'>");
        sb.append("<h2>").append(mediaType).append(" List</h2>");
        sb.append("<table style='width:800px;'>");
        sb.append("<tr><td><strong>Name</strong></td><td><strong>Description</strong></td><td><strong>Type</strong></td><td><strong>Year</strong></td></tr>");
        
        for(int i=0;i<10;i++)
        {
            sb.append("<tr><td valign=\"top\">").append(mediaList.get(i).getName()).append("</td><td valign=\"top\">").
                          append(mediaList.get(i).getDescription()).append("</td><td valign=\"top\">").
                    append(mediaList.get(i).getType()).append("</td><td valign=\"top\">").append(mediaList.get(i).getYear()).append("</td></tr>");
        }
        
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
        return sb.toString();
    }
    
    public static String getExportTabMediaExample(String mediaType)
    {
        StringBuilder sb = new StringBuilder();
        List<Media> mediaList = getGenericMediaList(mediaType);
        for(int i=0;i<10;i++)
        {
            sb.append(mediaList.get(i).getName());
            sb.append("|");
            sb.append(mediaList.get(i).getYear());
            sb.append("|");
            sb.append(mediaList.get(i).getType());
            sb.append("|");
            sb.append(mediaList.get(i).getDescription());
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
    
    private static List<Media> getGenericMediaList(String mediaType)
    {
        List<Media> genericList = new ArrayList<>();
        switch (mediaType)
         {
             case "Movies": {
                 List<Movie> movieList = DatabaseManager.getInstance().GetAllMovies(mediaType);
                 for(Movie m : movieList)
                 {
                     genericList.add(m);
                 }
             } break;
             case "Anime Series":{
                 List<AnimeSerie> animeList = DatabaseManager.getInstance().GetAllAnimeSeries(mediaType);
                 for(AnimeSerie an : animeList)
                 {
                     genericList.add(an);
                 }
             } break;
             case "TV Series":{
                 List<TVSerie> tvList = DatabaseManager.getInstance().GetAllTVSeries(mediaType);
                 for(TVSerie tv : tvList)
                 {
                     genericList.add(tv);
                 }
             } break;
             case "Games":{
                  List<Game> gameList = DatabaseManager.getInstance().GetAllGames(mediaType);
                  for(Game gm : gameList)
                  {
                     genericList.add(gm);
                  }
             } break;
             default: {
                 // bring movies.
                  List<Movie> movieList = DatabaseManager.getInstance().GetAllMovies(mediaType);
                  for(Movie m : movieList)
                  {
                     genericList.add(m);
                  }
             } break;
         }
        return genericList;
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
