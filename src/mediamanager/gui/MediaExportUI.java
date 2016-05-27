/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.TOP_ALIGNMENT;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import mediamanager.bl.MediaManager;

/**
 *
 * @author Tekken
 */
public class MediaExportUI extends JDialog {
    
    private static MediaExportUI mediaUI = null;
    
    private JLabel _information;
    private JLabel _fileType;
    private JComboBox _fileOptions;
    private JComboBox _mediaType;
    private JLabel _mediaLabel;
    private JLabel _showExampleIcon;
    private JFileChooser _fileChooser;
    
    private JButton _okProcess;
    private JButton _cancelProcess;
    
    private String folderPath;
    private String fileName;
    
    private JProgressBar _progressBar;
    
    private HTMLEditorKit _kit;
    private JScrollPane _scrollPaneHTML;
    private ScrollablePanel _scrollPane;
    private JScrollPane _scrollPaneText;
    private JEditorPane _editor;
    private JTextArea _textArea;
    
    public MediaExportUI(JFrame frame, Image exportIcon,Image folderSearch)
    {
        super(frame);
        setUIComponents(exportIcon,folderSearch);
    }
    
    public static MediaExportUI getInstance(JFrame owner,Image exportIcon,Image exampleIcon)
    {
        if(mediaUI == null)
        {
            mediaUI = new MediaExportUI(owner,exportIcon,exampleIcon);
        }
        return mediaUI;
    }
    
    private static int getRandomColorNumber()
    {
        return (int)((Math.random() * 1000) % 255);
    }
    
    private void setUIComponents(Image exportIcon,Image exampleIcon)
    {
        _information = new JLabel("Select the option that better fits the type of export you want.");
        _information.setBounds(10, 10, 400, 21);
        add(_information);
        
        _mediaLabel = new JLabel("Media Type");
        _mediaLabel.setBounds(10, 35, 80, 21);
        add(_mediaLabel);
        
        _mediaType = InternalGUICreator.createComboBox(95, 35, 110, 21, new String[] { "Movies","Anime Series","TV Series","Games"});
        add(_mediaType);
        
        _fileType = new JLabel("File Type -> ");
        _fileType.setBounds(250, 35, 70, 21);
        add(_fileType);
        
        _fileOptions = new JComboBox(new String[] { "Tab delimited file", "HTML" });
        _fileOptions.setBounds(340, 35, 300, 21);
        add(_fileOptions);
        
        _showExampleIcon = InternalGUICreator.createImageOnLabel(660, 30, exampleIcon, TOP_ALIGNMENT);
        _showExampleIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                
                boolean isTabDelimitedFile = (_fileOptions.getSelectedIndex() == 0);
                showExportExample(isTabDelimitedFile);
                
            }
        });
        add(_showExampleIcon);
        
        _editor = new JEditorPane();
        _editor.setSize(1500,900);
        _kit = new HTMLEditorKit();
        _editor.setEditorKit(_kit);
        StyleSheet styleSheet = _kit.getStyleSheet();
        styleSheet.addRule("body {color:gray; font-family:times; margin: 4px; }");
        styleSheet.addRule("h2 {color:black;}");
        styleSheet.addRule("tr {vertical-align: top; color:white; font:10px monaco; background-color:#A0A0A0; width:900px; }");
        styleSheet.addRule("td {vertical-align: top; text-align: left; border-style: solid; }");
         
        _scrollPane = new ScrollablePanel();
        _scrollPane.setLayout(new BorderLayout());
        _scrollPane.add(_editor, BorderLayout.SOUTH);
        
        _textArea = new JTextArea();
        _textArea.setEditable(false);
        _textArea.setWrapStyleWord(false);
        
        _scrollPaneText = new JScrollPane(_textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        _scrollPaneText.setBounds(10, 65, 775, 340);
        _scrollPaneText.setAutoscrolls(true);
        _scrollPaneText.setVisible(true);
        
        _scrollPaneHTML = new JScrollPane(_scrollPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        _scrollPaneHTML.setBounds(10, 65, 775, 340);
        _scrollPaneHTML.setAutoscrolls(true);
        _scrollPaneHTML.setVisible(false);
        
        add(_scrollPaneText);
        add(_scrollPaneHTML);
        
        _progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 1, 100);
        _progressBar.setBounds(10, 430, 500, 21);
        _progressBar.setStringPainted(true);
        _progressBar.setForeground(new Color(getRandomColorNumber(),getRandomColorNumber(),getRandomColorNumber(),100));
        _progressBar.repaint();
        _progressBar.setValue(0);
        add(_progressBar);
        
        _okProcess = new JButton("Export File");
        _okProcess.setBounds(532,430,120,21);
        _okProcess.setEnabled(true);
        _okProcess.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showConfirmDialog(null, "Do you really want to export the information?","Choose",JOptionPane.YES_NO_OPTION);
                if(selection == JOptionPane.YES_OPTION){
                    saveExportFileOnDisk();
                }
            }
        });
        add(_okProcess);
        
        _cancelProcess = new JButton("Cancel/Close");
        _cancelProcess.setBounds(662,430,120,21);
        _cancelProcess.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(null, "Are you sure to quit?","Choose",JOptionPane.YES_NO_OPTION);
                if(quit == JOptionPane.YES_OPTION){
                    mediaUI = null;
                    dispose();
                }
            }
        });
        add(_cancelProcess);
        
        setTitle("Export options");
        setIconImage(exportIcon);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        setSize(800,500);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                System.out.println(e.getID() + "closed " + e.getNewState());
                mediaUI = null;
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(e.getID() + "closing" + e.getNewState());
                int quit = JOptionPane.showConfirmDialog(null, "Are you sure to quit?","Choose",JOptionPane.YES_NO_OPTION);
                if(quit == JOptionPane.YES_OPTION){
                    super.windowClosing(e);
                    mediaUI = null;
                    dispose();
                }
            }
        });
        setVisible(true);
        pack();
    }
    
    private void openFolderOnSystem(String folderPath)
    {
        try
        {
            Desktop.getDesktop().open(new File(folderPath));
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private void createOkMessage(String message)
    {
        JOptionPane.showMessageDialog(this,message,"Message",JOptionPane.INFORMATION_MESSAGE,null);
    }
    
    private void createErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(this,message,"Error",JOptionPane.ERROR_MESSAGE,null);
    }
    
    private void showExportExample(boolean isTabDelimitedFile)
    {
        if(isTabDelimitedFile)
        {
            _scrollPaneHTML.setVisible(false);
            _textArea.setText(MediaManager.getExportTabMediaExample(_mediaType.getSelectedItem().toString()));
            _scrollPaneText.setVisible(true);
        }
        else
        {
            _scrollPaneText.setVisible(false);
            Document doc = _kit.createDefaultDocument();
            _editor.setDocument(doc);
            _editor.setText(MediaManager.getExportHTMLMediaExample(_mediaType.getSelectedItem().toString()));
            //_editor.setVisible(true);
            _scrollPaneHTML.setVisible(true);
        }
    }
    
    private void saveExportFileOnDisk()
    {
        _progressBar.setValue(0);
        boolean isTabDelimitedFile = (_fileOptions.getSelectedIndex() == 0);
        _fileChooser = isTabDelimitedFile ? InternalGUICreator.createFileTextChooser() : InternalGUICreator.createFileHTMLChooser();
        int returnVal = _fileChooser.showSaveDialog(MediaExportUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            _progressBar.setValue(5);
            folderPath = _fileChooser.getCurrentDirectory().getAbsolutePath();
            String file = _fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Folder selected "+ folderPath);
            System.out.println("File selected "+ file);

            String data;

            if(isTabDelimitedFile)
            {
                file = (file.endsWith(".txt")) ? file : (file + ".txt");
                data = MediaManager.getExportTabMedia(_mediaType.getSelectedItem().toString());
                _progressBar.setValue(75);
            }
            else
            {
                file = (file.endsWith(".html")) ? file : (file + ".html");
                data = MediaManager.getExportHTMLMedia(_mediaType.getSelectedItem().toString());
                _progressBar.setValue(76);
            }
            try
            {
                try(PrintWriter out = new PrintWriter(file))
                {
                    out.println(data);
                }

                _progressBar.setValue(100);
                createOkMessage("Export file was created sucessfully!");

            }catch(FileNotFoundException ex)
            {
                createErrorMessage("Error trying to export file" + ex.getMessage());
            }
        }
    }
    
}
