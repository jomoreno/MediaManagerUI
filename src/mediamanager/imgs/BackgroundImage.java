
package mediamanager.imgs;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author 
 */
public class BackgroundImage implements Border{
    public BufferedImage back;

    public BackgroundImage(){
        
        int imagesCount =   100;
        Random randomGenerator = new Random();
        int position = randomGenerator.nextInt(imagesCount) + 1;
        try  
        {
           if(imagesCount > 0)
           {
               URL resource = mediamanager.imgs.ReferenceImageClass.class.getResource("img"+ position + ".jpg");
               if(resource == null)
               {
                   resource = mediamanager.imgs.ReferenceImageClass.class.getResource("default.jpg");
               }
               back = ImageIO.read(resource);
           }
        } catch (Exception ex) { 
        }
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawImage(back, x ,y , null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
return false;
}

}