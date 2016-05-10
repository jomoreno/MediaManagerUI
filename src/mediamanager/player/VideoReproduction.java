/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.player;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import mediamanager.gui.MediaPlayerContainer;

/**
 *
 * @author Josue
 */
public class VideoReproduction {
    
    public static void playVideo() throws IOException
    {
        Process process = new ProcessBuilder("exe").start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

    }
    
    public static void playMediaVideoFile(Image icon)
    {
        String completeFolderPath = System.getProperty("user.dir");
        System.out.println(completeFolderPath);
        String filePath = completeFolderPath + "\\video.mp4";
        MediaPlayerContainer player = new MediaPlayerContainer(filePath, icon);
        player.playVideo();
        player.setVisible(true);
    }
    
    
}
