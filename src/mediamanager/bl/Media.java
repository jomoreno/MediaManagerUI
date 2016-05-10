/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.bl;

import java.util.Iterator;
import mediamanager.interfaces.IMedia;

/**
 *
 * @author Josue
 */
public class Media implements IMedia, Iterable<Media> {
    
    private String name;
    private String description;
    private String type;
    private int year;
    private String extension;
    private byte[][] images = new byte[3][];
    private byte[] videoData;
    
    public Media(){}
    
    public Media(String name,String desc,String type,int year)
    {
        this.name = name;
        this.description = desc;
        this.type = type;
        this.year = year;
        images = new byte[3][];
    }
    
    public Media(String name, byte[] video, String extension)
    {
        this.name = name;
        this.videoData = video;
        this.extension = extension;
    }
    
    public Media(String name,String desc,String type,int year,byte[][] images,String extension,byte[] videoData)
    {
        this.name = name;
        this.description = desc;
        this.type = type;
        this.year = year;
        this.images = images;
        this.extension = extension;
        this.videoData = videoData;
    }
     
    @Override
    public String getName(){ return name;}
    @Override
    public String getDescription() { return description;}
    @Override
    public String getType(){ return type;}
    @Override
    public int getYear(){return year;}
    @Override
    public String getExtension(){ return extension;}
    @Override
    public byte[] getImage(int position){ return images[position];}
    @Override
    public byte[] getVideoData(){ return videoData;}
    
    @Override
    public void setName(String name){
        this.name = name;
    }
    @Override
    public void setDescription(String desc){
        description = desc;
    }
    @Override
    public void setType(String type){
        this.type = type;
    }

    @Override
    public void setYear(int year){
        this.year = year;
    }
    @Override
    public void setExtension(String ext){
        this.extension = ext;
    }
    @Override
    public void setImage(byte[] bytes,int position){
        images[position] = bytes;
    }
    @Override
    public void setVideoData(byte[] bytes)
    {
        videoData = bytes;
    }

    @Override
    public Iterator<Media> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean constainsVideoData()
    {
        return (this.videoData != null && this.videoData.length > 0 && this.extension != null && !this.extension.equals(""));
    }
    
    
}
