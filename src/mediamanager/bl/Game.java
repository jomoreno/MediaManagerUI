/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.bl;

/**
 *
 * @author Josue
 */
public class Game extends Media {
    
    public Game()
    {
        super();
    }
    
    public Game(String name,String desc,String type,int year)
    {
        super(name,desc,type,year);
    }
    
    public void setGameType(GameTypes type)
    {
        super.setType(type.toString());
    }
   
}
