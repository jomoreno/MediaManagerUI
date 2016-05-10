/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.JInternalFrame;
import mediamanager.imgs.BackgroundImage;

/**
 *
 * @author Josue
 */
public class CustomInternalFrame extends JInternalFrame{
    
    //private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    BufferedImage img;
    
    public CustomInternalFrame(String title,final float alpha,boolean setBackGround) {
        super(title);
        //this.setOpaque(false);
        this.setResizable(false);
        this.setClosable(true);
        //comp = comp.derive(alpha);
        if(setBackGround)
        {
            setBackGound();
        }
        this.pack();
        
    }
 
    /**
     *
     */
    private void setBackGound()
    {
        img = mediamanager.imgs.ReferenceImageClass.getRandomImage();
    }
    
    public void setIcon(Icon anIcon){
        setFrameIcon(anIcon);
    }
     
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(100, 0, 4, 85));
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        if(img != null)
        {
            g.drawImage(img, 0, 0, this);
        }
    }
    
   @Override
   public void paint(Graphics g) {
      //Graphics2D g2 = (Graphics2D) g;
      //g2.setComposite(comp);
      super.paint(g);
   }

   public void setAlpha(float alpha) {
      //comp = comp.derive(alpha);
   }
    
}
