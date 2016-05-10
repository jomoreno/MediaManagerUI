/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.test;

import javax.swing.*;  
import javax.swing.tree.DefaultTreeCellRenderer;  
import java.awt.*;  
  
public class TestTransparentTree {  
  
    public TestTransparentTree() {  
        JFrame frame = new JFrame();  
  
        JTree tree = mediamanager.bl.MediaManager.getJTreeByAlphabet("Movies"); 
        tree.setCellRenderer(new MyRenderer());  
        tree.setBackground(new Color(0, 0, 0, 0));  
  
        GradientPane gradient = new GradientPane();  
        gradient.setLayout(new BorderLayout());  
        frame.setContentPane(gradient);  
  
        JScrollPane scroll = new JScrollPane(tree);  
        scroll.setOpaque(false);  
        gradient.add(scroll, BorderLayout.CENTER);  
  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.pack();  
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);  
  
    }  
  
    public static void main(String[] args) {  
  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                new TestTransparentTree();  
            }  
        });  
    }  
  
  
    @SuppressWarnings("serial")  
    public static class GradientPane extends JLayeredPane {  
  
        @Override  
        protected void paintComponent(Graphics g) {  
            super.paintComponent(g);  
              
            int h = getHeight();  
            int w = getWidth();  
  
            GradientPaint gradientPaint = new GradientPaint(0, 0, Color.WHITE, 0, h, Color.LIGHT_GRAY);  
  
            Graphics2D g2D = (Graphics2D) g;  
            g2D.setPaint(gradientPaint);  
            g2D.fillRect(0, 0, w, h);  
            repaint();  
        }  
    }  
  
    private class MyRenderer extends DefaultTreeCellRenderer {  
  
        public Component getTreeCellRendererComponent(  
                JTree tree,  
                Object value,  
                boolean sel,  
                boolean expanded,  
                boolean leaf,  
                int row,  
                boolean hasFocus) {  
  
            super.getTreeCellRendererComponent(  
                    tree, value, sel,  
                    expanded, leaf, row,  
                    hasFocus);  
  
            setBackgroundNonSelectionColor(tree.getBackground());  
  
            return this;  
        }  
    }  
}