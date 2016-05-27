/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

https://community.oracle.com/thread/1352788?start=0&tstart=0

 */
package mediamanager.test;

import javax.swing.*;
import java.awt.*;

public class EditorPaneTest {

    public EditorPaneTest() {
         JFrame frame = new JFrame("EditorPane Test");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         JEditorPane textComponent = new JEditorPane();
         textComponent.setContentType("text/html");
         
         String text = 
              "This sentence should wrap and not cause the horiztonal scroll bar to display.<br/>"
              + "<br/>This<br/>sentence<br/>should<br/>cause<br/>"
              + "the<br/>vertical<br/>scroll<br/>bar<br/>to<br/>display.<br/>";
              
         
         textComponent.setText(text);
         
         ScrollablePanel panel = new ScrollablePanel();
         panel.setLayout(new BorderLayout());
         //the purpose of putting the JEditorPane in a JPanel
         //is to force it to the bottom with BorderLayout
         panel.add(textComponent, BorderLayout.SOUTH);
         
         JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         
         //this line enables wrapping
         //but disables vertical scrolling
         //(comment it out to switch)
        //panel.setPreferredSize(new Dimension(scrollPane.getViewport().getWidth(), scrollPane.getViewport().getHeight()));
         
         frame.add(scrollPane);
         frame.setSize(100, 200);
         frame.setVisible(true); 
    }
    
    public static void main(String[] args) {
        new EditorPaneTest();
    }
    
    
    
     public class ScrollablePanel extends JPanel
          implements Scrollable
     {
          public Dimension getPreferredScrollableViewportSize()
          {
               return getPreferredSize();
          }
      
          public int getScrollableUnitIncrement(
               Rectangle visibleRect, int orientation, int direction)
          {
               return 20;
          }
      
          public int getScrollableBlockIncrement(
               Rectangle visibleRect, int orientation, int direction)
          {
               return 60;
          }
      
          public boolean getScrollableTracksViewportWidth()
          {
               return true;
          }
      
          public boolean getScrollableTracksViewportHeight()
          {
                 if (getParent() instanceof JViewport)
                 {
                     return (((JViewport)getParent()).getHeight() > getPreferredSize().height);
                 }

                 return false;
          }
     }
}