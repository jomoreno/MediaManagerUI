/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.imgs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Josue
 */
public class ReferenceImageClass { 

    
    public static BufferedImage getRandomImage()
    {
        BufferedImage returnImg = null;
        try  
        {
            int imagesCount = 100;
            Random randomGenerator = new Random();
            int position = randomGenerator.nextInt(imagesCount) + 1;
            URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("img"+ position + ".jpg");
            if(resource == null)
            {
                resource = mediamanager.imgs.ReferenceImageClass.class.getResource("default.jpg");
            }
            returnImg = ImageIO.read(resource);
           
        } catch (IOException ex) { }
        return returnImg;
    }
    
    public static BufferedImage getRandomPoster(String type)
    {
        BufferedImage img = null;
        try  
        {
            Random randomGenerator = new Random();
            int position = randomGenerator.nextInt(4) + 1;
            switch(type)
            {
                case "Anime Serie": {
                    URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("\\posters\\animeposter"+ position + ".jpg");
                    img = ImageIO.read(resource);
                } break;
                case "Movie": {
                    URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("\\posters\\movieposter"+ position + ".jpg");
                    img = ImageIO.read(resource);
                } break;
                case "TV Serie": {
                    URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("\\posters\\tvseriesposter"+ position + ".jpg");
                    img = ImageIO.read(resource);
                } break;
                case "Game": {
                    URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("\\posters\\gameposter"+ position + ".jpg");
                    img = ImageIO.read(resource);
                } break;
                case "Book": {
                    URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("\\posters\\booksposter"+ position + ".jpg");
                    img = ImageIO.read(resource);
                } break;
                default: {
                    URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("\\posters\\animeposter"+ position + ".jpg");
                    img = ImageIO.read(resource);
                } break;
            }
        }
        catch (Exception ex){
            Logger.getLogger(ReferenceImageClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return img;
    }
}


