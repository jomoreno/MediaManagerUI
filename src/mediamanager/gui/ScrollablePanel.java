/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

  Code obtained from https://community.oracle.com/thread/1352788?start=0&tstart=0

 */
package mediamanager.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.Scrollable;

public class ScrollablePanel extends JPanel
          implements Scrollable
     {
          @Override
          public Dimension getPreferredScrollableViewportSize()
          {
               return getPreferredSize();
          }
      
          @Override
          public int getScrollableUnitIncrement(
               Rectangle visibleRect, int orientation, int direction)
          {
               return 20;
          }
      
          @Override
          public int getScrollableBlockIncrement(
               Rectangle visibleRect, int orientation, int direction)
          {
               return 60;
          }
      
          @Override
          public boolean getScrollableTracksViewportWidth()
          {
               if (getParent() instanceof JViewport)
                {
                    return (((JViewport)getParent()).getWidth()> getPreferredSize().width);
                }

                return false;
          }
      
          @Override
          public boolean getScrollableTracksViewportHeight()
          {
                if (getParent() instanceof JViewport)
                {
                    return (((JViewport)getParent()).getHeight() > getPreferredSize().height);
                }

                return false;
          }
     }