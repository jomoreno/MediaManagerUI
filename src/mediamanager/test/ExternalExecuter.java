/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author Josue
 */
public class ExternalExecuter {
    
    public static void main (String args[]) throws IOException
    {
        //ActiveDirectoryQuery.class.getResource("VLCPortable/VLCPortable.exe").getFile()
        
        Process process = new ProcessBuilder("AAA.exe","testmovie.mp4").start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        System.out.printf("Output of running %s is:", Arrays.toString(args));

        while ((line = br.readLine()) != null) {
        System.out.println(line);
}
    }
    
}
