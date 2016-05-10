/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.test;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.net.URL;

class ImagePanel extends JPanel {

    private Image image;

    ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,getWidth(),getHeight(),this);
    }

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://i.stack.imgur.com/iQFxo.gif");
        final Image image = new ImageIcon(url).getImage();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame("Image");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setLocationByPlatform(true);

                ImagePanel imagePanel = new ImagePanel(image);
                imagePanel.setLayout(new GridLayout(5,10,10,10));
                imagePanel.setBorder(new EmptyBorder(20,20,20,20));
                for (int ii=1; ii<51; ii++) {
                    imagePanel.add(new JButton("" + ii));
                }

                f.setContentPane(imagePanel);
                f.pack();
                f.setVisible(true);
            }
        });
    }
}