package mediamanager.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;


public class SplashWindow extends JFrame {

  private static final long serialVersionUID = 9090438525613758648L;

  private static SplashWindow instance;

  private boolean paintCalled = false;

  private Image image;

  public static void main(String[] args){
      URL resource = SplashWindow.class.getResource("movieposter2.jpg");
      try {
          splash(resource);
      } catch (InterruptedException ex) {
          Logger.getLogger(SplashWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  private SplashWindow(Image image) {
    super();
    this.image = image;
    JLabel label = new JLabel("Smile", new ImageIcon(image), SwingConstants.CENTER);
    label.setVerticalTextPosition(JLabel.CENTER);
    label.setHorizontalTextPosition(JLabel.LEFT);
    label.setBorder(BorderFactory.createLineBorder(Color.black));
    this.add(label);
    this.setUndecorated(true);
    this.setAlwaysOnTop(true);
    this.pack();
    this.setLocationRelativeTo(null);

  }

  public static void splash(URL imageURL) throws InterruptedException {
    if (imageURL != null) {
      splash(Toolkit.getDefaultToolkit().createImage(imageURL));
    }
  }

  public static void splash(Image image) throws InterruptedException {
    if (instance == null && image != null) {
      instance = new SplashWindow(image);
      instance.setVisible(true);
      /*
      if (!EventQueue.isDispatchThread() && Runtime.getRuntime().availableProcessors() == 1) {

        synchronized (instance) {
          while (!instance.paintCalled) {
            try {
              instance.wait();
            } catch (InterruptedException e) {
            }
          }
        }
      }
      */
      Thread.sleep(5000);
      instance.setVisible(false);
      disposeSplash();
      
    }
  }

  @Override
  public void update(Graphics g) {
    paint(g);
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(image, 0, 0, this);
    if (!paintCalled) {
      paintCalled = true;
      synchronized (this) {
        notifyAll();
      }
    }
  }

  public static void disposeSplash() {
    instance.setVisible(false);
    instance.dispose();
  }
}