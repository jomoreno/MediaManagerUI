/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.test;

/**
  * DevDaily.com
  * A sample program showing how to use an animated gif image
  * in a Java Swing application.
  */

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {
    JPanel contentPane;
    JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();

    public MainFrame() {
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
            Image img = Toolkit.getDefaultToolkit().createImage("imgs/icons/120515.gif");
            ImageIcon ii = new ImageIcon(img);
            imageLabel.setIcon(ii);
            imageLabel.setVisible(true);
            contentPane.add(imageLabel, java.awt.BorderLayout.CENTER);
            // show it
            
            
            this.setLocationRelativeTo(null);
            this.pack();
            this.setVisible(true);
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }

}
