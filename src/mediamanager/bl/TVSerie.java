/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.bl;


/**
 *
 * @author Josue
 */
public class TVSerie extends Media {
   
    public TVSerie()
    {
        super();
    }
    
    public TVSerie(String name,String desc,String type,int year)
    {
        super(name,desc,type,year);
    }
    
    public void SetTVSerie(TVSerieTypes type)
    {
        super.setType(type.toString());
    }
}

