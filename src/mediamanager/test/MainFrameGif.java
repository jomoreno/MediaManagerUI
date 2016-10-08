/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.test;


import com.sun.org.apache.xerces.internal.util.URI;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 *
 * @author Tekken
 */
public class MainFrameGif extends JFrame {
    
     JPanel contentPane;
    
    JLabel headerLabel = new JLabel();

    public MainFrameGif() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            contentPane = (JPanel) getContentPane();
            contentPane.setLayout(new BorderLayout());
            setSize(new Dimension(400, 300));
            setTitle("Your Job Crashed!");
            // add the header label
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText("   Your job crashed during the save process!");
            contentPane.add(headerLabel, java.awt.BorderLayout.NORTH);
            // add the image label
            Image ii = Toolkit.getDefaultToolkit().getImage(getClass().getResource("C:/Users/Tekken/Documents/NetBeansProjects/MediaManager/src/mediamanager/imgs/41lbMGX.gif"));
            System.out.println(ii);
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon((Icon) ii);
            
            contentPane.add(imageLabel, java.awt.BorderLayout.CENTER);
            // show it
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error");
        }
    }

    public static void main(String[] args) throws URI.MalformedURIException {
        new MainFrameGif();
    }
    
}
