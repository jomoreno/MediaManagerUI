/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Josue
 */
public class TrasparentRender extends DefaultTreeCellRenderer{
    @Override
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
