/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.HORIZONTAL;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author Josue
 */
public class InternalGUICreator {
    public static TreeCellRenderer TreeCellRender;
    public static TreeCellRenderer TransparentRender;
    
    public static JButton createButton(String display,int x,int y,final int w,final int h,final boolean opaque,final Color backgroud)
    {
        final JButton btn = new JButton(display);
        btn.setLocation(x,y);
        btn.setSize(w, h);
        btn.setOpaque(opaque);
        return btn;
    }
    
    public static JToolBar createToolBar(int x,int y,final int w,final int h)
    {
        JToolBar toolBar = new JToolBar("Default ToolBar",HORIZONTAL);
        toolBar.setLocation(x, y);
        toolBar.setSize(w, h);
        toolBar.setBackground(Color.LIGHT_GRAY);
        return toolBar;
    }
    
    public static BackgroundMenuBar createCustomMenuBar(Color color)
    {
        BackgroundMenuBar bgMb = new BackgroundMenuBar();
        bgMb.setColor(color);
        return bgMb;
    }
    
    public static JComponent createVerticalSeparator(int x, int y) {
        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        
        sep.setBounds(x, y, 3, 30);
        return sep;
    }
    
    public static JButton createImageButton(int x,int y,final int w,final int h,String iconName,String toolTipText,String altText)
    {
        URL imageURL = mediamanager.imgs.icons.GetURLIconReference.getIconURL(iconName);
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setIcon(getStretchScaledImage(new ImageIcon(imageURL, altText),20,20));
        button.setLocation(x, y);
        return button;
    }
    
    public static InvisibleInternalFrame createTransparentInternalFrame(String title,final float alpha,int x,int y,int w,int h)
    {
        mediamanager.gui.InvisibleInternalFrame internalFrame = new InvisibleInternalFrame(title,alpha,w,h);
        internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        internalFrame.setLocation(x, y);
        internalFrame.setVisible(true);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null);
        
        JLabel titleLeft = InternalGUICreator.createLabel(title, 5, 1, 200, 20, false);
        internalFrame.add(titleLeft);
        return internalFrame;
    }
    
    public static JLabel createLabel(String display,int x, int y,final int w,final int h,final boolean opaque)
    {
        final JLabel label = new JLabel(display);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Serif", Font.PLAIN | Font.BOLD, 13));
        label.setLocation(x,y);
        label.setSize(w, h);
        label.setOpaque(opaque);
        return label;
    }
    
    public static JPanel createLoweredPanel(String title,int x,int y,int w,int h,final float alpha)
    {
        JPanel panel = new JPanel(new GridLayout());
        panel.setSize(w, h);
        panel.setLocation(x, y);
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), title, TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.TOP,new Font(Font.DIALOG, Font.BOLD, 12));
        panel.setBorder(border);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        panel.setOpaque(false);
        return panel;
    }
    
    public static StatusBar createStatusBar(int x,int y,int w, int h)
    {
        StatusBar stBar = new StatusBar(x, y, w, h);
        return stBar;
    }
    
    public static JPanel createRaisedPanel(int x,int y,int w,int h,final float alpha)
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(w, h);
        panel.setLocation(x, y);
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createRaisedSoftBevelBorder());
        panel.setBorder(border);
        panel.setOpaque(false);
        panel.setLayout(null);
        return panel;
    }
    
    public static JScrollPane createScrollPaneForTree(int x,int y,int w,int h,final float alpha)
    {
        
        JScrollPane scrollPanel = new JScrollPane()
        {
            /*
            private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
            @Override
             public void paint(Graphics g) {
                comp = comp.derive(alpha);
                Graphics2D g2 = (Graphics2D) g;
                g2.setComposite(comp);
                super.paint(g);
             }
             */
        };
        
        scrollPanel.setLocation(x, y);
        scrollPanel.setSize(w,h);
        scrollPanel.setOpaque(false);
        scrollPanel.getViewport().setOpaque(false);
        return scrollPanel;
    }
    
    public static JComboBox createComboBox(int x,int y,int w,int h,String[] elements)
    {
        JComboBox comboBox = new JComboBox();
        comboBox.setOpaque(false);
        comboBox.setLocation(x, y);
        comboBox.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 13));
        comboBox.setBackground(new Color(255,255,255,0));
        comboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        comboBox.setSize(w, h);
        for(int i=0;i<elements.length;i++)
        {
            comboBox.addItem(elements[i]);
        }
        
        return comboBox;
        
    }
    
    public static JTextField createTextField(int x,int y,int w,int h,final float alpha)
    {
        JTextField textField = new JTextField();
        /*
        {
            //private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            
            @Override
             public void paint(Graphics g) {
                //comp = comp.derive(alpha);
                //Graphics2D g2 = (Graphics2D) g;
                //g2.setComposite(comp);
                //super.paint(g);
             }
        };
        * */
        textField.setLocation(x,y);
        textField.setSize(w, h);
        //textField.setEditable(false);
        textField.setOpaque(false);
        return textField;
    }
    
    public static JTextArea createNormalTextArea(final float alpha,int x,int y,int w,int h,int c,int r)
    {
        JTextArea textArea = new JTextArea(r,c);
        /*
        {
            //private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            
            @Override
             public void paint(Graphics g) {
                //comp = comp.derive(alpha);
                //Graphics2D g2 = (Graphics2D) g;
                //g2.setComposite(comp);
                //super.paint(g);
             }
        };
        */
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setLocation(x,y);
        textArea.setSize(w, h);
        textArea.setOpaque(false);
        textArea.setFocusTraversalKeysEnabled(false);
        textArea.setFont(new Font("Serif", Font.ITALIC, 13));
        return textArea;
    }
    
    public static JScrollPane createTextArea(JTextArea textArea,int x,int y,int w,int h,final float alpha)
    {
        JScrollPane scrollText = new JScrollPane(textArea);
        /*
        {
            private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            
            @Override
             public void paint(Graphics g) {
                //comp = comp.derive(alpha);
                //Graphics2D g2 = (Graphics2D) g;
                //g2.setComposite(comp);
                //super.paint(g);
             }
        };
        * */
        scrollText.setLocation(x,y);
        scrollText.setSize(w, h);
        scrollText.setOpaque(false);
        
        return scrollText;
    }
    
    public static JBackgroundPanel createBackgroundPanel(int x,int y,int w,int h)
    {
        JBackgroundPanel panel = new JBackgroundPanel(x, y, w, h);
        return panel;
    }
    
    public static JTree createDefaultTree()
    {
        JTree tree = new JTree();  
        tree.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        tree.setCellRenderer(new TrasparentRender());  
        tree.setBackground(new Color(0, 0, 0, 0)); 
        tree.setOpaque(false);
        return tree;
    }
    
    public static JLabel createImageOnLabel(int x, int y,Image img, final float alpha)
    {
        JLabel labelImage;
        ImageIcon icon = new ImageIcon(img);
        labelImage = new JLabel(icon){
            /*
            private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
            
            @Override
             public void paint(Graphics g) {
                comp = comp.derive(alpha);
                Graphics2D g2 = (Graphics2D) g;
                g2.setComposite(comp);
                super.paint(g);
             }
             */ 
        };
        
        labelImage.setLocation(x,y);
        labelImage.setSize(icon.getIconWidth(),icon.getIconHeight());
        return  labelImage;
    }
    
    public static JLabel createSizedImageOnLabel(int x, int y,int width,int height,Image img, final float alpha,String toolTip)
    {
        JLabel labelImage;
        ImageIcon icon = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        labelImage = new JLabel(icon)
        {
            private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
            
            @Override
             public void paint(Graphics g) {
                comp = comp.derive(alpha);
                Graphics2D g2 = (Graphics2D) g;
                g2.setComposite(comp);
                super.paint(g);
             }
        };
        
        labelImage.setToolTipText(toolTip);
        labelImage.setLocation(x,y);
        labelImage.setSize(icon.getIconWidth(),icon.getIconHeight());
        return  labelImage;
    }
    
    public static JProgressBar createJProgressBar(int x,int y, int w,int h)
    {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setLocation(x,y);
        progressBar.setSize(w, h);
        progressBar.setOpaque(false);
        return progressBar;
    }
    
    public static JFileChooser createFileImageChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new ImageFilter());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setAccessory(new ImagePreview(fileChooser));
        return fileChooser;
    }
    
    public static JFileChooser createFileTextChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilterText());
        fileChooser.setAcceptAllFileFilterUsed(false);
        return fileChooser;
    }
    
    public static JFileChooser createFileVideoChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new VideoFilter());
        fileChooser.setAcceptAllFileFilterUsed(false);
        return fileChooser;
    }
    
    public static ImageIcon getStretchScaledImage(ImageIcon image,int maxWidth,int maxHeight)
    {
        System.out.println("Width "+ image.getIconWidth() + " Height "+ image.getIconHeight());
        if(image.getIconWidth() > maxWidth && image.getIconHeight() > maxHeight)
        {
            if(image.getIconWidth() > image.getIconHeight())
            {
                float width = (float)image.getIconWidth();
                float height = (float)image.getIconHeight();
                float percentage = (height / width) * 100;
                if(percentage > 90)
                {
                    System.out.println("Found > 90");
                    return new ImageIcon(image.getImage().getScaledInstance(-1, maxHeight,Image.SCALE_SMOOTH));
                }
                else if(percentage > 80)
                {
                    System.out.println("Found > 80");
                    return new ImageIcon(image.getImage().getScaledInstance(-1, maxHeight,Image.SCALE_SMOOTH));
                }
                else if(percentage > 70)
                {
                    System.out.println("Found > 70");
                    return new ImageIcon(image.getImage().getScaledInstance(maxWidth-100, -1,Image.SCALE_SMOOTH));
                }
                else if(percentage > 60)
                {
                    System.out.println("Found > 60");
                    return new ImageIcon(image.getImage().getScaledInstance(maxWidth-50, -1,Image.SCALE_SMOOTH));
                }
                else
                {
                    System.out.println("Found < 70");
                    return new ImageIcon(image.getImage().getScaledInstance(maxWidth, -1,Image.SCALE_SMOOTH));
                }
            }
            else
            {
                System.out.println("Found Height greater than width");
                return new ImageIcon(image.getImage().getScaledInstance(-1, maxHeight,Image.SCALE_SMOOTH));
            }
        }
        else
        {
            System.out.println("Found equal or lower than max values");
            if(image.getIconWidth() > image.getIconHeight())
            {
                if(image.getIconWidth() < maxWidth)
                {
                    if(image.getIconWidth() < (maxWidth-80))
                    {
                        return image;
                    }
                    else
                    {
                        float width = (float)image.getIconWidth();
                        float height = (float)image.getIconHeight();
                        float percentage = (height / width) * 100;
                        if(percentage > 90)
                        {
                            return new ImageIcon(image.getImage().getScaledInstance(-1, maxHeight,Image.SCALE_SMOOTH));
                        }
                        else if(percentage > 80)
                        {
                            return new ImageIcon(image.getImage().getScaledInstance(-1, maxHeight,Image.SCALE_SMOOTH));
                        }
                        else if(percentage > 70)
                        {
                            return new ImageIcon(image.getImage().getScaledInstance(maxWidth-70, -1,Image.SCALE_SMOOTH));
                        }
                        else if(percentage > 60)
                        {
                            return new ImageIcon(image.getImage().getScaledInstance(maxWidth-40, -1,Image.SCALE_SMOOTH));
                        }
                        else
                        {
                            return new ImageIcon(image.getImage().getScaledInstance(maxWidth, -1,Image.SCALE_SMOOTH));
                        }
                    }
                }
                else
                {
                    return new ImageIcon(image.getImage().getScaledInstance(maxWidth, -1,Image.SCALE_SMOOTH));
                }
            }
            else
            {
                if(image.getIconHeight() > image.getIconWidth())
                {
                    if(image.getIconHeight() < maxHeight)
                    {
                        return image;
                    }
                    else
                        return new ImageIcon(image.getImage().getScaledInstance(-1, maxHeight,Image.SCALE_SMOOTH));
                }
                else
                {
                    return new ImageIcon(image.getImage().getScaledInstance(-1, maxHeight,Image.SCALE_SMOOTH));
                }
            }
        }
    }
    
    public static JLabel getLabelPositionedScaledImage(ImageIcon scaledImage,int maxWidth,int maxHeight,int topShift,final float alpha)
    {
        int imgW = scaledImage.getIconWidth();
        int imgH = scaledImage.getIconHeight();
        int internalWidthShift = (maxWidth - imgW) / 2;
        int internalHeightShift = (maxHeight - imgH) / 2;
        
        JLabel imageLabeled = new JLabel(scaledImage)
        {
           private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);

           @Override
            public void paint(Graphics g) {
               comp = comp.derive(0.8f);
               Graphics2D g2 = (Graphics2D) g;
               g2.setComposite(comp);
               super.paint(g);
            }
        };
        
        imageLabeled.setSize(imgW, imgH);
        imageLabeled.setLocation(internalWidthShift, internalHeightShift);
        imageLabeled.setOpaque(false);
        return imageLabeled;
    }
    
}
