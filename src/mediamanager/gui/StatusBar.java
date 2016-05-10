/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Tekken
 */
public class StatusBar extends JLabel {
     
    /** Creates a new instance of StatusBar */
    public StatusBar(int x,int y,int w, int h) {
        super();
        super.setBounds(x, y, w, h);
        super.setBorder(BorderFactory.createLoweredBevelBorder());
        super.setOpaque(true);
        super.setBackground(Color.LIGHT_GRAY);
        setMessage("Ready...");
    }
     
    public void setMessage(String message) {
        setText(" "+message);        
    }        
}
