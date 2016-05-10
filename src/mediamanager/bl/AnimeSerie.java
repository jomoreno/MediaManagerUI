/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.bl;

/**
 *
 * @author Josue
 */
public class AnimeSerie extends Media {
    
    public AnimeSerie()
    {
        super();
    }
    
    public AnimeSerie(String name,String desc,String type,int year)
    {
        super(name,desc,type,year);
    }
    
    public void setAnimeType(AnimeSerieTypes type)
    {
        super.setType(type.toString());
    }
    
}
