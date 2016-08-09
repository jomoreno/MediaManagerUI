/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.test;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import mediamanager.gui.InternalGUICreator;

public class Animation {
public static void main(String args[]) {
JLabel imageLabel = new JLabel();
JLabel headerLabel = new JLabel();

JFrame frame = new JFrame("JFrame Animation");
JPanel jPanel = new JPanel();

//Add a window listner for close button
frame.addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent e) {
System.exit(0);
}
});

// add the header label
headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
headerLabel.setText("Animated Image!");
jPanel.add(headerLabel, java.awt.BorderLayout.NORTH);
//frame.getContentPane().add(headerLabel, java.awt.BorderLayout.NORTH);

// add the image label
//Image imgImport = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/import.png"));

ImageIcon ii = new ImageIcon("C:/Users/Tekken/Documents/NetBeansProjects/MediaManager/src/mediamanager/imgs/anime.gif");

ImageIcon scaledImage = InternalGUICreator.getStretchScaledImage(ii, 500, 281,true);

imageLabel.setIcon(scaledImage);
jPanel.add(imageLabel, BorderLayout.CENTER);

frame.getContentPane().add(jPanel, java.awt.BorderLayout.CENTER);
frame.pack();
frame.setVisible(true);

}
}
