/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import jdk.nashorn.internal.scripts.JS;
import mediamanager.MediaManagerUI;
import mediamanager.bl.MediaManager;

/**
 *
 * @author Tekken
 */
public class MediaImportUI extends JDialog {
    
    private static MediaImportUI mediaUI = null;
    
    private JLabel _information;
    private JLabel _fileType;
    private JLabel _fileSearchImg;
    private JLabel _fileClearImg;
    private JComboBox _fileOptions;
    private JComboBox _mediaType;
    private JTable _displayableTable;
    private JScrollPane _scrollPane;
    private JButton _okProcess;
    private JButton _cancelProcess;
    private JProgressBar _progressBar;
    private JTextArea _logTextArea;
    private JScrollPane _scrollTextArea;
    
    private JFileChooser _fileChooser;
    
    private Object[][] emptyData1 = { {"","","",""}, { "","","","" } };
    private Object[][] emptyData2 = { {"A","B","C","D"}, { "AA","BB","CC","DD" } };
    private String[][] importedData;
    
    CustomTableModel customModel;
    
    private MediaImportUI(JFrame owner,Image searchIcon,Image clearIcon){
        super(owner);                                                                
        setUIComponents(searchIcon,clearIcon);
    }
    
    private static int getRandomColorNumber()
    {
        return (int)((Math.random() * 1000) % 255);
    }
    
    public static MediaImportUI getInstance(JFrame owner,Image searchIcon,Image clearIcon)
    {
        if(mediaUI == null)
        {
            mediaUI = new MediaImportUI(owner,searchIcon,clearIcon);
        }
        return mediaUI;
    }
    
    private void setUIComponents(Image searchIcon,Image clearIcon)
    {
        _information = new JLabel("Select the option that better fits the type of file you want to import.");
        _information.setBounds(10, 10, 400, 21);
        add(_information);
        
        _fileType = new JLabel("File Type -> ");
        _fileType.setBounds(10, 35, 70, 21);
        add(_fileType);
        
        _fileOptions = new JComboBox(new String[] { "Tab delimited file" });
        _fileOptions.setBounds(90, 35, 300, 21);
        add(_fileOptions);
        
        _mediaType = InternalGUICreator.createComboBox(400, 35, 110, 21, new String[] { "Movies","Anime Series","TV Series","Games"});
        add(_mediaType);
        
        _fileSearchImg = InternalGUICreator.createImageOnLabel(560, 30, searchIcon, TOP_ALIGNMENT);
        _fileSearchImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                _fileChooser = InternalGUICreator.createFileTextChooser();
                parseSelectedFile();
            }
        });
        add(_fileSearchImg);
        
        _fileClearImg = InternalGUICreator.createImageOnLabel(610, 30, clearIcon, TOP_ALIGNMENT);
        _fileClearImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                
                customModel = new CustomTableModel(emptyData1);
                _displayableTable.setModel(customModel);
                setDefaultValuesForTable();
                _logTextArea.setText("");
                _progressBar.setValue(0);
            }
        });
        add(_fileClearImg);
        
        customModel = new CustomTableModel(emptyData2);
        _displayableTable = new JTable(customModel);
        setDefaultValuesForTable();
        
        _scrollPane = new JScrollPane(_displayableTable);
        _scrollPane.setBounds(10, 65, 775, 200);
        add(_scrollPane);
        
        _okProcess = new JButton("Process File");
        _okProcess.setBounds(532,280,120,21);
        _okProcess.setEnabled(false);
        _okProcess.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showConfirmDialog(null, "Are you sure to process this file?","Choose",JOptionPane.YES_NO_OPTION);
                if(selection == JOptionPane.YES_OPTION){
                    try
                    {
                        processData(_mediaType.getSelectedItem().toString());
                    }
                    catch(InterruptedException ex)
                    {
                        _logTextArea.append(ex.getMessage()+"\n");
                    }
                }
            }
        });
        add(_okProcess);
        
        _cancelProcess = new JButton("Cancel/Close");
        _cancelProcess.setBounds(662,280,120,21);
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
        
        _progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 1, 100);
        _progressBar.setBounds(10, 280, 500, 21);
        _progressBar.setStringPainted(true);
        _progressBar.setForeground(new Color(getRandomColorNumber(),getRandomColorNumber(),getRandomColorNumber(),100));
        _progressBar.repaint();
        _progressBar.setValue(0);
        add(_progressBar);
        
        _logTextArea = new JTextArea("");
        _logTextArea.setBounds(10, 320, 775, 130);
        _logTextArea.setBackground(Color.GRAY);
        _logTextArea.setForeground(new Color(0,80,0));
        _logTextArea.setEditable(false);
        _logTextArea.setFont(_logTextArea.getFont().deriveFont(12.0f));
        
        _scrollTextArea = new JScrollPane(_logTextArea,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        _scrollTextArea.setBounds(10, 320, 775, 130);
        add(_scrollTextArea);
        
        setTitle("Import options");
        setIconImage(searchIcon);
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
    
    private void setDefaultValuesForTable(){
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBorder(BorderFactory.createLineBorder(Color.RED));
        headerRenderer.setBackground(new Color(200, 198, 45));

        for (int i = 0; i < _displayableTable.getModel().getColumnCount(); i++) {
                _displayableTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        _displayableTable.setGridColor(Color.GRAY);
        _displayableTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                if(row % 2 == 0)
                {
                    setBackground(Color.WHITE);
                }
                else
                {
                    setBackground(Color.LIGHT_GRAY);
                }
                
                return this;
            }   
        });
        _displayableTable.setFillsViewportHeight(true);
    }
    
    private void parseSelectedFile()
    {
        _logTextArea.setText("");
        _okProcess.setEnabled(false);
        
        ArrayList<List<String>> tableData = new ArrayList<>();

        int returnVal = _fileChooser.showDialog(MediaImportUI.this,"Choose File");
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            int recordCounts = 0;
            int badRecords = 0;
            File file = _fileChooser.getSelectedFile();  
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                _logTextArea.append("Reading file...\n");
                while ((line = br.readLine()) != null) {
                    recordCounts++;
                   _logTextArea.append("Parsing record... " + recordCounts+"\n");
                   String separator = "\\|";
                   String[] splitLine = line.split(separator,-1);
                   if(splitLine.length == 4)
                   {
                       try
                       {
                            int year = Integer.parseInt(splitLine[1]);
                            List<String> row = Arrays.asList(splitLine);
                            tableData.add(row);
                       }
                       catch(Exception ex)
                       {
                           _logTextArea.append(ex.getMessage()+"\n");
                           badRecords++;
                       }
                   }
                   else
                   {
                       // write error
                       _logTextArea.append("Error reading the file, number of columns don't match the expected ones... \n");
                       badRecords++;
                   }
                }
                
                String[][] arrayTableData = new String[tableData.size()][];
                for (int i = 0; i < tableData.size(); i++) {
                    List<String> record = tableData.get(i);
                    arrayTableData[i] = record.toArray(new String[record.size()]);
                }

                _logTextArea.setText("");
                _progressBar.setValue(0);
                
                customModel = new CustomTableModel(arrayTableData);
                _displayableTable.setModel(customModel);
                _logTextArea.append("Parsing done! Number of good recods: "+ arrayTableData.length +". Number of bad records: "+ badRecords+"\n");
                _logTextArea.requestFocusInWindow();
                _logTextArea.requestFocus();
                _okProcess.setEnabled(true);
            }
            catch(java.io.IOException ex)
            {
                // write error
                _logTextArea.append("Error reading the file...\n");
                _logTextArea.append(ex.getMessage()+"\n");
            } 
        }
    }
    
    private void processData(String mediaType) throws InterruptedException
    {
        Object[][] data = ((CustomTableModel)_displayableTable.getModel()).getDataOnModel();
        _progressBar.setMinimum(0);
        _progressBar.setMaximum(data.length -1);
        for (int i=0;i< data.length;i++) {
            if (MediaManager.insertMediaInformation((String)data[i][0], (String)data[i][3], (String)data[i][2], Integer.parseInt((String)data[i][1]), mediaType)) {
                _logTextArea.append("Records added...\n");
            }
            else
            {
                _logTextArea.append("Error trying to insert the record "+ i +"...\n");
            }
            _progressBar.setValue(i);
        }
        createOkMessage("File processed... Check log for results!");
        _logTextArea.requestFocusInWindow();
        _logTextArea.requestFocus();
        _okProcess.setEnabled(false);
    }
    
    private void createOkMessage(String message)
    {
        JOptionPane.showMessageDialog(this,message,"Message",JOptionPane.INFORMATION_MESSAGE,null);
    }
    
    private void createErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(this,message,"Error",JOptionPane.ERROR_MESSAGE,null);
    }
}


class CustomTableModel extends AbstractTableModel
{
    private Object[][] data;
    private final String[] columnNames = {"Name",
                        "Year",
                        "Type",
                        "Description"};
     
     public CustomTableModel(Object[][] data)
     {
        this.data = new Object[data.length][columnNames.length];
        System.out.println("Length " + this.data.length  + " Width " + this.data[0].length );
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j <= columnNames.length - 1; j++)
            {
                this.data[i][j] = data[i][j];
                System.out.println(this.data[i][j]);
            }
        }
     }
    
     public Object[][] getDataOnModel()
     {
         return data;
     }
     
    @Override
    public String getColumnName(int column) {
        return (columnNames == null ? "" : columnNames[column]);
    }
     
    @Override
    public int getRowCount() {
        return (data == null ? 0 : data.length);
    }

    @Override
    public int getColumnCount() {
        return (data == null ? 0 : columnNames.length);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (columnNames == null ? new Object() : data[rowIndex][columnIndex]);
    }
    
}
