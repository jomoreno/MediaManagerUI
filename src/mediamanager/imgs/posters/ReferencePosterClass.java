/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.imgs.posters;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Josue
 */
public class ReferencePosterClass {
    
    public static BufferedImage getRandomImage()
    {
        BufferedImage returnImg = null;
        try  
        {
            int imagesCount = 88;
            Random randomGenerator = new Random();
            int position = randomGenerator.nextInt(imagesCount) + 1;
            URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("poster"+ position + ".jpg");
            if(resource == null)
            {
                resource = mediamanager.imgs.ReferenceImageClass.class.getResource("default.jpg");
            }
            returnImg = ImageIO.read(resource);
           
        } catch (IOException ex) { }
        return returnImg;
    }
    
    public static URL getPosterURL()
    {
        URL resource = null;
        try  
        {
            int imagesCount = 88;
            Random randomGenerator = new Random();
            int position = randomGenerator.nextInt(imagesCount) + 1;
            resource = mediamanager.imgs.posters.ReferencePosterClass.class.getResource("poster"+ position + ".jpg");
            if(resource == null)
            {
                resource = mediamanager.imgs.ReferenceImageClass.class.getResource("default.jpg");
            }
        }
        catch (Exception ex) { }
        return resource;
    }
    
}
