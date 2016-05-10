/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Josue
 */

public class TransparentInternalFrame extends JInternalFrame {
   //private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);

   public TransparentInternalFrame(String title, final float alpha) {
      super(title);
      setClosable(true);
      setVisible(true);
      setLayout(null);
      //comp = comp.derive(alpha); 
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