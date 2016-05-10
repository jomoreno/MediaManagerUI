/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.videos;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import mediamanager.bl.MediaManager;

/**
 *
 * @author Josue
 */
public class UploaderVideoTracker extends SwingWorker{
    
    byte[] _videoData;
    String _media,_extention,_name;
    String _messageError,_messageOK;
    Boolean _result = false;
    JProgressBar _progressBar;
    JLabel _message;
    
    public UploaderVideoTracker(byte[] videoData,String mediaType,String extention,String name, JProgressBar progressBar, JLabel message)
    {
        _videoData = videoData;
        _media = mediaType;
        _extention = extention;
        _name = name;
        _messageOK = "Video added sucessfully!";
        _messageError = "Error trying to update video clip";
        _progressBar = progressBar;
        _message = message;
        _message.setText("Uploading video...");
    }
    
    @Override
    protected Object doInBackground() throws Exception {
        _progressBar.setIndeterminate(true);
        _result = MediaManager.updateVideo(_media, _name, _videoData, _extention);
        return null;
    }
    
     @Override
    public void done(){
         if(_result)
         {
            _progressBar.setIndeterminate(false);
            _progressBar.setValue(100); 
            _message.setText(_messageOK);
         }
         else
         {
             _progressBar.setIndeterminate(false);
             _progressBar.setValue(0);
             _message.setText(_messageError);
         }
    }
}
