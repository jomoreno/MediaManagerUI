
package mediamanager.gui;

import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.io.File;

/* ImagePreview.java by FileChooserDemo2.java. */
public class ImagePreview extends JComponent implements PropertyChangeListener {
    ImageIcon thumbnail = null;
    File file = null;

    public ImagePreview(JFileChooser fc) {
        setPreferredSize(new Dimension(200, 100));
        fc.addPropertyChangeListener(this);
    }

    public void loadImage() {
        if (file == null) {
            thumbnail = null;
            return;
        }

        //Don't use createImageIcon (which is a wrapper for getResource)
        //because the image we're trying to load is probably not one
        //of this program's own resources.
        ImageIcon tmpIcon = new ImageIcon(file.getPath());
        if (tmpIcon != null) {
            if (tmpIcon.getIconHeight() > 100 && tmpIcon.getIconWidth() > 190) {
                if(tmpIcon.getIconHeight() > tmpIcon.getIconWidth())
                {
                    thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(-1, 130,Image.SCALE_SMOOTH));
                }
                else
                {
                    thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(190, -1,Image.SCALE_SMOOTH));
                }
            } 
            else 
            { //no need to miniaturize
                 if(tmpIcon.getIconWidth() > 190)
                 {
                    thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(190,-1, Image.SCALE_SMOOTH));
                 }
                 else
                 {
                    if(tmpIcon.getIconHeight() > 100)
                    {
                        thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(-1,130, Image.SCALE_SMOOTH));
                    }
                    else
                    {
                        thumbnail = tmpIcon;
                    }
                 }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        boolean update = false;
        String prop = e.getPropertyName();
        //If the directory changed, don't show an image.
        switch (prop) {
            case JFileChooser.DIRECTORY_CHANGED_PROPERTY:
                file = null;
                update = true;

            //If a file became selected, find out which one.
                break;
            case JFileChooser.SELECTED_FILE_CHANGED_PROPERTY:
                file = (File) e.getNewValue();
                update = true;
                break;
        }

        //Update the preview accordingly.
        if (update) {
            thumbnail = null;
            if (isShowing()) {
                loadImage();
                repaint();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (thumbnail == null) {
            loadImage();
        }
        if (thumbnail != null) {
            int x = getWidth()/2 - thumbnail.getIconWidth()/2;
            int y = getHeight()/2 - thumbnail.getIconHeight()/2;

            if (y < 0) {
                y = 0;
            }

            if (x < 5) {
                x = 5;
            }
            thumbnail.paintIcon(this, g, x, y);
        }
    }
}
