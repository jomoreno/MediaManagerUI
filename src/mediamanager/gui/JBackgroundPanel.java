/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import mediamanager.imgs.ReferenceImageClass;

public class JBackgroundPanel extends JPanel {
  private BufferedImage img;
  private int R,G,B;
  
  public JBackgroundPanel(int x,int y,int w,int h) {
    // load the background image
      setLayout(null);
      setLocation(x,y);
      setSize(w, h);
      
      Random randomGenerator = new Random();
      R = randomGenerator.nextInt(255);
      G = randomGenerator.nextInt(255);
      B = randomGenerator.nextInt(255);
      
      /*
      try {
        img = ReferenceImageClass.getRandomImage();
      } catch(Exception e) {
            e.printStackTrace();
      }
    */
  }
 
  @Override
  protected void paintComponent(Graphics g) {
    //super.paintComponent(g);
    // paint the background image and scale it to fill the entire space
    //g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
      
    Graphics2D g2d = (Graphics2D)g;
    
    int w = getWidth( );
    int h = getHeight( );
    
    Color color1 = getBackground( );
    //Color color2 = Color.getHSBColor(R, G, B);
 
// Paint a gradient from top to bottom
    GradientPaint gp = new GradientPaint(0, 0, color1,0, h, color1 );

    g2d.setPaint( gp );
    g2d.fillRect( 0, 0, w, h );  
      
      
  }
}