/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.bl;

/**
 *
 * @author Josue
 */
public class Movie extends Media {
    
    public Movie()
    {
        super();
    }
    
    public Movie(String name,String desc,String type,int year)
    {
        super(name,desc,type,year);
    }
    
    public void setMovieType(MovieTypes type)
    {
        super.setType(type.toString());
    }
    
}
