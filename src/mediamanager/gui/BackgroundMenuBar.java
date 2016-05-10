/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JMenuBar;

/**
 *
 * @author Tekken
 */
public class BackgroundMenuBar extends JMenuBar {
    Color bgColor = Color.LIGHT_GRAY;
    
    public void setColor(Color color)
    {
        bgColor = color;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    
}
