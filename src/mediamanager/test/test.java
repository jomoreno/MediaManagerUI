/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.Serializable;


public class test extends Applet implements Runnable
{
    public static Image makeColorTransparent(Image im, final Color color) 
      {
        ImageFilter filter = new RGBImageFilter() 
        {

              public int markerRGB = color.getRGB() | 0xFF000000;               //color to make transparent

              public final int filterRGB(int x, int y, int rgb) 
              {
                if ( ( rgb | 0xFF000000 ) == markerRGB ) 
                {
                  // Mark the alpha bits as zero - transparent
                  return 0x00FFFFFF & rgb;
                }

                else 
                {
                  // nothing to do
                  return rgb;
                }
             }
        }; 

            ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
            return Toolkit.getDefaultToolkit().createImage(ip);

      }


    Image test;
    public void init()
    {

        setSize(600,600);

    }

    public void update (Graphics g)                                             //overriding the update for double buffering
    {       
        // initialize buffer
        Image dbImage = createImage (this.getSize().width, this.getSize().height);
        Graphics dbg = dbImage.getGraphics ();


        // clear screen in background
        dbg.setColor (getBackground());
        dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);


        // draw elements in background
        dbg.setColor (getForeground());
        paint (dbg);

        // draw image on the screen
        g.drawImage (dbImage, 0, 0, this);      
    }


    public void paint(Graphics g)
    {
        test = getImage(getCodeBase (), "tt.gif");
        Image testt = makeColorTransparent(test, Color.white);               

        g.drawImage (testt,0,0, this);
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        repaint();

    }

}