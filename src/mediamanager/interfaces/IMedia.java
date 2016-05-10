/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.interfaces;

/**
 *
 * @author Josue
 */
public interface IMedia {
    
    public String getName();
    public String getDescription();
    public String getType();
    public int getYear();
    public String getExtension();
    public byte[] getImage(int position);
    public byte[] getVideoData();
    
    public void setName(String name);
    public void setDescription(String desc);
    public void setType(String type);
    public void setYear(int year);
    public void setExtension(String ext);
    public void setImage(byte[] bytes,int position);
    public void setVideoData(byte[] bytes);
    public boolean constainsVideoData();
    
}
