/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mediamanager.bl.MediaManager;


/**
 *
 * @author Josue
 */
public class SplashInitScreen {
    
    private JDialog dialog;
    private JFrame frame;
    private JProgressBar progress;
    
        protected void initUI() throws MalformedURLException {
        showSplashScreen();
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {

            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(10);
                    publish(i);// Notify progress
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                progress.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                showFrame();
                hideSplashScreen();
            }

        };
        worker.execute();
    }
        
        protected void hideSplashScreen() {
        dialog.setVisible(false);
        dialog.dispose();
    }

    protected void showSplashScreen() throws MalformedURLException {
        dialog = new JDialog((Frame) null);
        dialog.setModal(false);
        dialog.setUndecorated(true);
        URL resource = mediamanager.imgs.posters.ReferencePosterClass.getPosterURL();
        Image img = Toolkit.getDefaultToolkit().createImage(resource);
        JLabel background = new JLabel(new ImageIcon(img));
        background.setLayout(new BorderLayout());
        dialog.add(background);
        JLabel text = new JLabel("Loading, please wait...");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Verdana", Font.BOLD, 12));
        text.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        background.add(text);
        progress = new JProgressBar();
        background.add(progress, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }    
        
    protected void showFrame() {
        
        if(MediaManager.isConnectionAvailable())
        {
            MediaManagerUI.getInstance().setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"The's no connection to the database, system will shut down","Error",JOptionPane.ERROR_MESSAGE,null);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
        UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new SplashInitScreen().initUI();
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
    
}
