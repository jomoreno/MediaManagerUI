/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Josue
 */
public class TreeCellRender extends DefaultTreeCellRenderer
{
    private JLabel label = new JLabel( );
 
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean isExpanded, boolean isLeaf,int row, boolean hasFocus )
    {
        JLabel defaultL = (JLabel)super.getTreeCellRendererComponent(
            tree, value, isSelected, isExpanded,
            isLeaf, row, hasFocus );
 
        label.setIcon( defaultL.getIcon( ) );
        label.setText( defaultL.getText( ) );
        label.setIconTextGap( defaultL.getIconTextGap( ) );
        label.setFont( defaultL.getFont( ) );
        label.setBackground( isSelected ?
            getBackgroundSelectionColor( ) :
            getBackgroundNonSelectionColor( ) );
        label.setForeground( isSelected ?
            getTextSelectionColor( ) :
            getTextNonSelectionColor( ) );
        label.setOpaque( isSelected );
        return label;
    }
}