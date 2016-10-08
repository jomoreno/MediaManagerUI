/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import mediamanager.bl.ElementTypes;
import static mediamanager.bl.ElementTypes.IMAGE1;
import mediamanager.bl.Media;
import mediamanager.bl.MediaManager;
import mediamanager.gui.BackgroundMenuBar;
import mediamanager.gui.InternalGUICreator;
import static mediamanager.gui.InternalGUICreator.TransparentRender;
import mediamanager.gui.MediaExportUI;
import mediamanager.gui.MediaImportUI;
import mediamanager.gui.ProgressBarFrame;
import mediamanager.gui.StatusBar;

/**
 *
 * @author Josue
 */
public class MediaManagerUI extends javax.swing.JFrame {

    
    private BackgroundMenuBar _menuBar;
    private JMenu _menuFile, _menuOptions, _menuHelp;
    private JMenuItem _itemFileExit,_itemFileExport,_itemFileImport,_itemOptionSetBackgroundColor,_itemHelpVersion;
    
    private JPanel _mainPanel;
    private JPanel _leftSearchPanel;
    private JTree _searchTree;
    private JScrollPane _searchTreeScrollPane;
    
    private JPanel _rigthUpperPanel;
    private JPanel _rigthCenterPanel;
    
    private JPanel _informationPanel;
    private JPanel _image1Panel;
    
    private JPanel _image2Panel;
    private JPanel _image3Panel;
    
    private JComboBox _searchTypeCbb;
    private JComboBox _displayModeCbb;
    private JLabel _searchImage;
    private JLabel _updateTreeImage;
    
    private JTextArea _name;
    private JTextArea _description;
    private JLabel _category;
    private JComboBox _type;
    private JComboBox _year;
    
    private JLabel _addMediaInformation;
    private JLabel _addVideo;
    private JLabel _saveInformation;
    private JLabel _playVideo;
    private JLabel _deleteInformation;
    private JLabel _cleanInformation;
    private JLabel _exitApp;
    private JLabel _importInformation;
    private JLabel _exportInformation;
    private JLabel _collapseTree;
    
    private JFileChooser _fileChooser;
    
    private JProgressBar _videoProgressBar;
    private JLabel _videoProcessingLabel;
    
    private JToolBar _toolBar;
    
    private JFrame _defaultFrame;
    private JDialog _dialog;
    private JTextField _searchField;    
    private JList _tmpList;
    private JScrollPane _scrollSearchResult;
    
    private JButton _btnSearch;
    private StatusBar _statusBar;
    
    private DefaultMutableTreeNode selectedNode;
    
    private static final String[] SPECIAL_CHARS = {"0","1","2","3","4","5","6","7","8","9","."};
    
    private static MediaManagerUI managerUI = null;
    
    /**
     * Creates new form MediaManagerUI
     */
    private MediaManagerUI() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setSize(1480, 900);
        this.setResizable(false);
        setUIComponents();
        setIcon();
    }
    
    public static MediaManagerUI getInstance()
    {
        if(managerUI == null)
        {
            managerUI = new MediaManagerUI();
        }
        return managerUI;
    }
    
    private void setUIComponents(){
        
        _defaultFrame = new JFrame();
        
        // <editor-fold defaultstate="collapsed" desc="Tool Bar and Main Panels">
        
        _mainPanel = InternalGUICreator.createBackgroundPanel(0, 40, 1480, 860);
        this.add(_mainPanel);
        this.setTitle("Media Management");
        
        _toolBar = InternalGUICreator.createToolBar(3,0,1465,35);
        _toolBar.setAutoscrolls(false);
        _toolBar.setFloatable(false);
        
        //_toolBar.addSeparator();

         Image imgImport = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/import.png"));
        _importInformation = InternalGUICreator.createSizedImageOnLabel(10, 5,25,25, imgImport, 0.9f,"Import information data!");
        _importInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                showImportOptions();
            }
        });
        _importInformation.setEnabled(true);
        _toolBar.add(_importInformation);
        
         Image imgExport = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/export.png"));
        _exportInformation = InternalGUICreator.createSizedImageOnLabel(40, 5,25,25, imgExport, 0.9f,"Export information data!");
        _exportInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
               showExportOptions();
            }
        });
        _exportInformation.setEnabled(true);
        _toolBar.add(_exportInformation);
        _toolBar.add(InternalGUICreator.createVerticalSeparator(70,3));
        
        Image imgClean = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/clean1.png"));
        _cleanInformation = InternalGUICreator.createSizedImageOnLabel(75, 5,25,25, imgClean, 0.9f,"Clear all windows and selections in the screen.");
        _cleanInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                clearInformation();
                _searchTree.clearSelection();
            }
        });
        _cleanInformation.setEnabled(true);
        _toolBar.add(_cleanInformation);
        //_toolBar.addSeparator();
        
         Image imgExit = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/Close-icon.png"));
         _exitApp  = InternalGUICreator.createSizedImageOnLabel(1435, 5,25,25, imgExit, 0.9f,"Exit the application.");
         _exitApp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                dispose();
                System.exit(0);
            }
        });
        _exitApp.setEnabled(true);
        _toolBar.add(_exitApp);
        //_toolBar.addSeparator();
        
        _videoProcessingLabel = InternalGUICreator.createLabel("Updating video file...", 1020, 6, 150, 20,false);
        _toolBar.add(_videoProcessingLabel);
        _videoProcessingLabel.setVisible(false);
        
        _videoProgressBar = InternalGUICreator.createJProgressBar(1160, 7, 200, 20);
         _toolBar.add(_videoProgressBar);
         _videoProgressBar.setVisible(false);
        
        _toolBar.setLayout(null);
        _toolBar.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(_toolBar);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Left Panel GUI">

        _leftSearchPanel = InternalGUICreator.createLoweredPanel("Search Media", 5, 5, 390, 771,0.4f);
        _mainPanel.add(_leftSearchPanel);
        
        _searchTree = MediaManager.getJTreeByAlphabet("Movies");
        _searchTree.setCellRenderer(TransparentRender);
        _searchTree.setOpaque(true);
        setTreeListener(_searchTree);
        
        TreeWillExpandListener expandCollapseListener = new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                _collapseTree.setEnabled(true);
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
               _collapseTree.setEnabled(false);
            }
        };
        _searchTree.addTreeWillExpandListener(expandCollapseListener);
       
        _searchTreeScrollPane = new JScrollPane(_searchTree);
        _searchTreeScrollPane.setLocation(5, 50);
        _searchTreeScrollPane.setSize(340,710);
        _searchTreeScrollPane.setOpaque(false);
        _searchTreeScrollPane.getViewport().setOpaque(false);

        _searchTree.setBounds(0, 0, 100, 500);
        _searchTree.setSize(100, 500);

        _leftSearchPanel.add(_searchTreeScrollPane);

        
        JLabel displayMode = InternalGUICreator.createLabel("Display : ", 9, 25, 55, 18, false);
        _leftSearchPanel.add(displayMode);
        _displayModeCbb  = InternalGUICreator.createComboBox(65, 25, 85, 18, new String[] { "A-Z","Type","Year"});
        _leftSearchPanel.add(_displayModeCbb);   
        _displayModeCbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                enableSearchOption();
            }
        });
        
        JLabel searchMode = InternalGUICreator.createLabel("Search : ", 154, 25, 50, 18, false);
        _leftSearchPanel.add(searchMode);
        _searchTypeCbb = InternalGUICreator.createComboBox(204, 25, 140, 18, new String[] { "Movies","Anime Series","TV Series","Games"});
        _leftSearchPanel.add(_searchTypeCbb);
        _searchTypeCbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                enableSearchOption();
                searchMediaBy();
                setStatusBarMessage(getTreeCountMsg());
            }
        });
        
        Image imgUpd = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/update.png"));
        _updateTreeImage = InternalGUICreator.createSizedImageOnLabel(350, 50,30,30, imgUpd, 0.9f,"Update the current tree information!");
        _updateTreeImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                searchMediaBy();
                clearInformation();
                setStatusBarMessage(getTreeCountMsg());
            }
        });
        _updateTreeImage.setEnabled(true);
        _leftSearchPanel.add(_updateTreeImage);
        
        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/Search1.png"));
        _searchImage = InternalGUICreator.createSizedImageOnLabel(350, 90,30,30, img, 0.9f,"Search media by name!");
        _searchImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                createSearchDialogWindow();
            }
        });
        _searchImage.setEnabled(true);
        _leftSearchPanel.add(_searchImage);
        
        Image imgAddInformation = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/add2.png"));
        _addMediaInformation = InternalGUICreator.createSizedImageOnLabel(351, 130,28,28, imgAddInformation, 0.9f,"Add the current information to the selected group.");
        _addMediaInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                String mediaType = _searchTypeCbb.getSelectedItem().toString();
                boolean hasData = (!_name.getText().trim().equals("") && !_description.getText().trim().equals("")); 
                if(hasData)
                {
                    int result = createConfirmationMessage("Do you really want to insert this new information to "+mediaType+" list?");
                    if(result == JOptionPane.OK_OPTION)
                    {
                        addBasicInformationOfMedia();
                    }
                }
                else
                {
                    createErrorMessage("Name and description are required in order to add and element to the "+ mediaType +" list!");
                }
            }
        });
        _addMediaInformation.setEnabled(true);
        _leftSearchPanel.add(_addMediaInformation);    
        
        Image imgSave = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/save3.png"));
        _saveInformation = InternalGUICreator.createSizedImageOnLabel(352, 175,25,25, imgSave, 0.9f,"Save (update) the current information for the selected media.");
        _saveInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(_saveInformation.isEnabled())
                {
                    if(!_addMediaInformation.isEnabled())
                    {
                        addBasicInformationOfMedia();
                    }
                    else
                    {
                        updateBasicInformationOfMedia();
                    }
                }
            }
        });
        _saveInformation.setEnabled(false);
        _leftSearchPanel.add(_saveInformation);
        
        Image imgDelete = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/delete.png"));
        _deleteInformation = InternalGUICreator.createSizedImageOnLabel(352, 220,28,28, imgDelete, 0.9f,"Delete the selected media item.");
        _deleteInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(_deleteInformation.isEnabled())
                {
                    if(isLeafItemInTreeSelected())
                    {
                        int result = createConfirmationMessage("Do you really want to delete the selected item?");
                        if(result == JOptionPane.OK_OPTION)
                        {
                            deleteInformationOfMedia();
                        }  
                    }
                }
            }
        });
        _deleteInformation.setEnabled(false);
        _leftSearchPanel.add(_deleteInformation);
        
        Image imgPlay = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/play7.png"));
        _playVideo = InternalGUICreator.createSizedImageOnLabel(350, 260,32,32, imgPlay, 0.9f,"Play the video clip corresponding to the selected media.");
        _playVideo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    if(_playVideo.isEnabled())
                    {
                        Image imgPlay = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/play3.png"));
                        if(getVideoInformation())
                        {
                            mediamanager.player.VideoReproduction.playMediaVideoFile(imgPlay);
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MediaManagerUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        _playVideo.setEnabled(false);
        _leftSearchPanel.add(_playVideo);

        // _collapseTree
        Image imgCollapse = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/collapse.png"));
        _collapseTree = InternalGUICreator.createSizedImageOnLabel(348, 300,36,36, imgCollapse, 0.9f,"Collapse the tree to original state!");
        _collapseTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    if(_collapseTree.isEnabled())
                    {
                        collapseAllTree(_searchTree);
                        _searchTree.clearSelection();
                        _collapseTree.setEnabled(false);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MediaManagerUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         _collapseTree.setEnabled(false);
        _leftSearchPanel.add(_collapseTree);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Upper Panel GUI">
        
        _rigthUpperPanel = InternalGUICreator.createLoweredPanel("Media Information", 400, 5, 1070, 375, 0.4f);
        
        _informationPanel = InternalGUICreator.createRaisedPanel(10 , 25, 522,340,0.4f);
        _rigthUpperPanel.add(_informationPanel);
        
        JLabel name = InternalGUICreator.createLabel("Name : ", 20, 15, 100, 18,false);
        _informationPanel.add(name);
        _name = InternalGUICreator.createNormalTextArea(0.3f,100, 15, 400, 50,50,2);
        _name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB)
                {
                    if (e.getModifiers() > 0) {
                       _name.transferFocusBackward(); 
                    }
                    else
                    {
                        _name.transferFocus();
                    }
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        });
        JScrollPane jscrollName = new JScrollPane(_name);
        jscrollName.setLocation(100, 15);
        jscrollName.setSize(400, 50);
        _informationPanel.add(jscrollName);
        
        JLabel description = InternalGUICreator.createLabel("Description : ", 20, 80, 100, 18, false);
        _informationPanel.add(description);
        _description = InternalGUICreator.createNormalTextArea(0.3f,100, 80, 400, 125,50,5);
        _description.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB)
                {
                    if (e.getModifiers() > 0) {
                       _description.transferFocusBackward(); 
                    }
                    else
                    {
                        _description.transferFocus();
                    }
                    e.consume();
                }
            }
        });
            
        JScrollPane jscrollDescription = new JScrollPane(_description);
        jscrollDescription.setLocation(100, 80);
        jscrollDescription.setSize(400, 125);
        _informationPanel.add(jscrollDescription);
        
        JLabel type = InternalGUICreator.createLabel("Media Type : ", 20, 220, 100, 18, false);
        _informationPanel.add(type);
        _type = InternalGUICreator.createComboBox(100, 218, 150, 24, MediaManager.getMediaTypeCategories("Movies")); 
        _informationPanel.add(_type);
        
        JLabel category = InternalGUICreator.createLabel("Category : ", 20, 255, 100, 18, false);
        _informationPanel.add(category);
        _category = InternalGUICreator.createLabel("",107, 253, 150, 24,false);
        _informationPanel.add(_category);
        
        JLabel year = InternalGUICreator.createLabel("Year : ", 20, 287, 100, 22, false);
        _informationPanel.add(year);
        _year = InternalGUICreator.createComboBox(100, 287, 150, 24, MediaManager.getStringYears());
        _informationPanel.add(_year); 
        // 475 287
        Image imgAddVideo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/movietrackadd.png"));
        _addVideo = InternalGUICreator.createSizedImageOnLabel(475, 287,25,25, imgAddVideo, 0.9f,"Add a video clip to the selected media.");
        _addVideo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                updateVideo();
            }
        });
        _addVideo.setEnabled(false);
        _informationPanel.add(_addVideo);
        
        _image1Panel = InternalGUICreator.createRaisedPanel(538 , 25, 522,340,0.2f);
        _rigthUpperPanel.add(_image1Panel);
        _image1Panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                updateImage(ElementTypes.IMAGE1);
            }
        });
        
        _mainPanel.add(_rigthUpperPanel);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Center Panel GUI">
        
        _rigthCenterPanel = InternalGUICreator.createLoweredPanel("Images", 400, 400, 1070, 375, 0.4f);
        _mainPanel.add(_rigthCenterPanel);
        
        _image2Panel = InternalGUICreator.createRaisedPanel(10 , 25, 522,340,0.2f);
        _rigthCenterPanel.add(_image2Panel);
        _image2Panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                updateImage(ElementTypes.IMAGE2);
            }
        });
        
        _image3Panel = InternalGUICreator.createRaisedPanel(538 , 25, 522,340,0.2f);
        _rigthCenterPanel.add(_image3Panel);
        _image3Panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                updateImage(ElementTypes.IMAGE3);
            }
        });
        
        // </editor-fold>
    
        // <editor-fold defaultstate="collapsed" desc="Menu Bar">
        _menuBar = InternalGUICreator.createCustomMenuBar(Color.LIGHT_GRAY);

        //_menuFile, _menuOptions, _menuHelp
        //_itemFileExit,_itemFileExport,_itemFileImport,_itemOptionSetBackgroundColor,_itemHelpVersion;
        
        _menuFile = new JMenu("File");
        
        Image importImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/Import_A-16.png"));
        _itemFileImport = new JMenuItem("File Import",
                         new ImageIcon(importImg));
        _itemFileImport.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        _itemFileImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImportOptions();
            }
        });
        _menuFile.add(_itemFileImport); 
        
        Image exportImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/Export_A-16.png"));
         _itemFileExport = new JMenuItem("File Export",
                         new ImageIcon(exportImg));
        _itemFileExport.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        _itemFileExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExportOptions();
            }
        });
        _menuFile.add(_itemFileExport); 
        
        Image exitImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/DeleteRed.png"));
        _itemFileExit = new JMenuItem("Exit", new ImageIcon(exitImg));
        _itemFileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        _menuFile.add(_itemFileExit); 
        _menuBar.add(_menuFile);
        
        _menuOptions = new JMenu("Options");
        
        Image pickColorImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/find.png"));
        _itemOptionSetBackgroundColor = new JMenuItem("Search Media", new ImageIcon(pickColorImg));
        _itemOptionSetBackgroundColor.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        _itemOptionSetBackgroundColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 createSearchDialogWindow();
            }
        });
        _menuOptions.add(_itemOptionSetBackgroundColor); 
        
        _menuBar.add(_menuOptions);
        
        _menuHelp = new JMenu("Help");
        
        Image helpImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/Information.png"));
        _itemHelpVersion = new JMenuItem("About", new ImageIcon(helpImg));
        _itemHelpVersion.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        _itemHelpVersion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        _menuHelp.add(_itemHelpVersion);
        _menuBar.add(_menuHelp);
        
        this.setJMenuBar(_menuBar);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Status Bar">
        _statusBar = InternalGUICreator.createStatusBar(5, 780, 1462, 24);
        _statusBar.setVisible(true);
        _mainPanel.add(_statusBar);
        
        setStatusBarMessage(getTreeCountMsg());
        // </editor-fold>
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Tree Methods">
    
    private void setTreeListener(JTree searchTree)
    {
        searchTree.addTreeSelectionListener(new TreeSelectionListener(){
            @Override
            public void valueChanged(TreeSelectionEvent evt)
            {
                clearInformation();
                
                _saveInformation.setEnabled(false);
                _playVideo.setEnabled(false);
                _addVideo.setEnabled(false);
                _cleanInformation.setEnabled(false);
                _deleteInformation.setEnabled(false);
                _collapseTree.setEnabled(false);
                
                String selectedValue = verifySelectionOnLeafNode();
                if(selectedValue != null)
                {
                    String mediaTypeSelected = _searchTypeCbb.getSelectedItem().toString();
                    _saveInformation.setEnabled(true);
                    _playVideo.setEnabled(true);
                    _addVideo.setEnabled(true);
                    _cleanInformation.setEnabled(true);
                    _deleteInformation.setEnabled(true);
                    _collapseTree.setEnabled(true);
                    setStatusBarMessage(selectedValue + " selected from " + mediaTypeSelected + " list...");   

                    switch(mediaTypeSelected)
                    {
                        case "Movies":{
                            Media media = MediaManager.getMovieInformation(mediaTypeSelected, selectedValue);
                            printMediaInformation(media,"Movie");
                        } break;
                        case "Anime Series":{
                            Media media = MediaManager.getAnimeSerieInformation(mediaTypeSelected, selectedValue);
                            printMediaInformation(media,"Anime Serie");
                        } break;
                        case "TV Series":{
                            Media media = MediaManager.getTVSerieInformation(mediaTypeSelected, selectedValue);
                            printMediaInformation(media,"TV Serie");
                        } break;
                        case "Games":{
                            Media media = MediaManager.getGameInformation(mediaTypeSelected, selectedValue);
                            printMediaInformation(media,"Game");
                        } break;
                        default:{} break;
                    }
                }
            }
        });
    }
    
    private TreePath findObject(Object object) {
        java.util.Enumeration nodes = ( (DefaultMutableTreeNode)_searchTree.getModel().getRoot()).preorderEnumeration();
        while (nodes.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes.nextElement();
            if (node.getUserObject().toString().equals(object.toString())) {
                return new TreePath(node.getPath());
            }
        }
        return null;
    } 
    
    private int getNumberOfNodes(TreeModel model)  
    {  
        return getNumberOfNodes(model, model.getRoot());  
    }  

    private int getNumberOfNodes(TreeModel model, Object node)  
    {  
        int count = 1;
        int nChildren;  
            nChildren = model.getChildCount(node);
        for (int i = 0; i < nChildren; i++)  
        {  
            count += getNumberOfNodes(model, model.getChild(node, i));  
        }  
        return count;  
    }
    
    private void collapseAllTree(JTree tree)
    {
        int row = tree.getRowCount() - 1;
        while (row >= 1) {
            tree.collapseRow(row);
            row--;
        } 
    }
    
    private String verifySelectionOnLeafNode()
    {
        TreePath path = _searchTree.getSelectionPath();
        if (path != null)
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
            selectedNode = node;
            if (node.isLeaf())
            {
                if(node.getParent().getParent() != null)
                {
                    return node.toString();
                }
                else return null;
            }
            else return null;
        }
        else return null;
    }    
    
    private String getSelectedNode()
    {
        String name = "";
        TreePath path = _searchTree.getSelectionPath();
        if (path != null)
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
            name = node.toString();
        }
        return name;
    }
    
    private boolean isNodeParentOfElement(DefaultMutableTreeNode node, String nameComparator)
    {
        return node != null && node.toString().startsWith(nameComparator.substring(0,1));
    }
    
    private void insertInformationOnTree(String name)
    {
        // establish the initial letter of the name...
        // the tree will be always be loaded...
        // but might not be selected at all...
        // so the selectedNode will tell us what is being selected
        if(selectedNode != null && selectedNode.isLeaf())
        {
            // evaluate if parent is root or not
            if(selectedNode.getParent().getParent() == null)
            {
                // this is an element/letter that doesn't have any elements on it...
                // but parent is the root!
                if(isNodeParentOfElement(selectedNode,name))
                {
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode;
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
                    DefaultTreeModel model = (DefaultTreeModel)_searchTree.getModel();
                    int position = getPositionOfInsertion(parent,name);
                    System.out.println("Position " + position);
                    model.insertNodeInto(child, parent, position);
                }
                else
                {
                    // empty element/node selected... but is not the one where the element should go!
                    DefaultMutableTreeNode parent = getNodeFromStartOfString(name);
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
                    DefaultTreeModel model = (DefaultTreeModel)_searchTree.getModel();
                    int position = getPositionOfInsertion(parent,name);
                    System.out.println("Position " + position);
                    model.insertNodeInto(child, parent, position);
                }
            }
            else
            {
                // this is a normal leaf, the parent should be the element...
                // but we always need to check if the value being inserted will belog to this element or not
                if(isNodeParentOfElement(selectedNode,name))
                {
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
                    DefaultTreeModel model = (DefaultTreeModel)_searchTree.getModel();
                    int position = getPositionOfInsertion(parent,name);
                    System.out.println("Position " + position);
                    model.insertNodeInto(child, parent, position);
                }
                else
                {
                    // normal leaf selected but the selected element is not were the new item should be inserted
                     DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
                    DefaultMutableTreeNode parent = getNodeFromStartOfString(name);
                    DefaultTreeModel model = (DefaultTreeModel)_searchTree.getModel();
                    int position = getPositionOfInsertion(parent,name);
                    System.out.println("Position " + position);
                    model.insertNodeInto(child, parent, position);
                }
            }
        }
        else
        {
            // selecting a letter in the tree, so that's the node 
            // where we should insert the new child, but we need to check if it really should be where the element will be inserted or not
            if(selectedNode != null)
            {
                if(isNodeParentOfElement(selectedNode,name))
                {
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
                    DefaultTreeModel model = (DefaultTreeModel)_searchTree.getModel();
                    int position = getPositionOfInsertion(selectedNode,name);
                    System.out.println("Position " + position);
                    model.insertNodeInto(child, selectedNode, position);
                }
                else
                {
                    // need to get the real node where to insert the information
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
                    DefaultTreeModel model = (DefaultTreeModel)_searchTree.getModel();
                    DefaultMutableTreeNode parentNode = getNodeFromStartOfString(name);
                    int position = getPositionOfInsertion(parentNode,name);
                    System.out.println("Position " + position);
                    model.insertNodeInto(child, parentNode, position);
                }
            }
            else
            {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
                DefaultTreeModel model = (DefaultTreeModel)_searchTree.getModel();
                DefaultMutableTreeNode parentNode = getNodeFromStartOfString(name);
                int position = getPositionOfInsertion(parentNode,name);
                System.out.println("Position " + position);
                model.insertNodeInto(child, parentNode, position);
            }
        }
    }
    
    private DefaultMutableTreeNode getNodeFromStartOfString(String name)
    {
        String initialChar = name.substring(0,1);
        if(Arrays.asList(SPECIAL_CHARS).contains(initialChar))
        {
            initialChar = "#";
        }
        TreePath path = findObject(initialChar);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        return node;
    }
    
    private int getPositionOfInsertion(DefaultMutableTreeNode node, String nameComparator)
    {
        int position = 0;
        System.out.println("Comparing the node " + node.toString() + " with the start of the nameComparator " + nameComparator.substring(0,1));
        if(isNodeParentOfElement(node,nameComparator))
        {
            System.out.println("CORRECT NODE FOUND");
            // the node selected is the one where we need to do the insertion
            int totalNodeCounts = node.getChildCount();
            for(int i=0;i<totalNodeCounts;i++)
            {
                String currentNodeName = node.getChildAt(i).toString();
                System.out.println("Comparing " + currentNodeName + " with " + nameComparator);
                System.out.println("Result " + currentNodeName.compareTo(nameComparator));
                if(currentNodeName.compareTo(nameComparator) < 0)
                {
                    position = i;
                }
            }
        }
        else
        {
            // we need to find the right node where to insert the new child
            DefaultMutableTreeNode parentNode = getNodeFromStartOfString(nameComparator);
            System.out.println("Parent found "+parentNode.toString()+ " for element " + nameComparator);
            int totalNodeCounts = parentNode.getChildCount();
            for(int i=0;i<totalNodeCounts;i++)
            {
                String currentNodeName = parentNode.getChildAt(i).toString();
                System.out.println("Comparing " + currentNodeName + " with " + nameComparator);
                if(currentNodeName.compareTo(nameComparator) < 0)
                {
                    position = i;
                }
            }
        }
        return position;
    }
    
    private void deleteInformationOnTree()
    {
        // we will delete only leafs that are not elements like letters
        if(selectedNode.isLeaf())
        {
            if(selectedNode.getParent().getParent() == null)
            {
                // you irgnore this element nothing to do...
            }
            else
            {
                DefaultTreeModel model = (DefaultTreeModel)_searchTree.getModel();
                model.removeNodeFromParent(selectedNode);
            }
        }
    }    
    
    private boolean isLeafItemInTreeSelected()
    {
        TreePath path = _searchTree.getSelectionPath();
        if (path != null)
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
            return node.isLeaf();
        }
        return false;
    }    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Update, Delete and Insertion Methods">
    
    private void updateVideo()
    {
        String selectedNode = verifySelectionOnLeafNode();
        if(selectedNode != null)
        {
            JFileChooser fileChooser = InternalGUICreator.createFileVideoChooser();
            int returnVal = fileChooser.showDialog(MediaManagerUI.this,"Choose Video");
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                try 
                {
                    //_videoProgressBar.setVisible(true);
                    //_videoProcessingLabel.setVisible(true);
                    //_videoProgressBar.setIndeterminate(true);
                           
                    File file = fileChooser.getSelectedFile();
                    Path filePath = Paths.get(file.getAbsolutePath());
                    String extention = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
                    byte[] data = Files.readAllBytes(filePath);
                    String mediaType = _searchTypeCbb.getSelectedItem().toString();
                    String name = "";
                    TreePath path = _searchTree.getSelectionPath();
                    if (path != null)
                    {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
                        name = node.toString();
                    }
                   
                   ProgressBarFrame progressBar = new ProgressBarFrame(data,mediaType,extention,name);
                   progressBar.setVisible(true);
                    
                } 
                catch (IOException ex) {
                    Logger.getLogger(MediaManagerUI.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Error " + ex.getMessage());
                }
            }
        }
    }
    
    private void updateImage(ElementTypes type)
    {
        String selectedNode = verifySelectionOnLeafNode();
        if(selectedNode != null)
        {
            if(_fileChooser == null)
            {
                _fileChooser = InternalGUICreator.createFileImageChooser();
            }
            int returnVal = _fileChooser.showDialog(MediaManagerUI.this,"Choose Image");
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = _fileChooser.getSelectedFile();  
                try   
                {  
                    Image image = ImageIO.read(file); 
                    
                    boolean isGIF = InternalGUICreator.validateGIFImageFromFile(file);

                    switch(type)
                    {
                        case IMAGE1: {
                            setImageInUpperImagePanel(image,isGIF);
                        } break;
                        case IMAGE2: {
                            seImageInCenterLeftImagePanel(image,isGIF);
                        } break;
                        case IMAGE3: {
                            setImageInCenterRightImagePanel(image,isGIF);
                        } break;
                        default:{}break;
                    }  
                    
                    // save in current selected media
                    byte[] b;
                    try (RandomAccessFile f = new RandomAccessFile(file.getAbsolutePath(), "r")) {
                        b = new byte[(int)f.length()];
                        f.read(b);
                    }

                    System.out.println("Largo de bytes "+ b.length);

                    updateImageByPosition(type,b);
                } catch (IOException e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
        }
    }
    
    private void updateBasicInformationOfMedia()
    {
        String name = _name.getText();
        String description = _description.getText();
        
        String type = _type.getSelectedItem() != null ? _type.getSelectedItem().toString() : "";
        int year = _year.getSelectedItem() != null ? Integer.valueOf(_year.getSelectedItem().toString()) : 1901;
        
        String mediaType = _searchTypeCbb.getSelectedItem().toString();
        String previousName = name;
        
        TreePath path = _searchTree.getSelectionPath();
        if (path != null)
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
            previousName = node.toString();
            node.setUserObject(name);
        }
        else
        {
            createErrorMessage("No media was selected to made an update.");
            return;
        }
        
        if(MediaManager.updateMediaInformation(name, description, type, year, previousName, mediaType))
        {
            createOkMessage("Information updated sucessfully!");
            setStatusBarMessage("Information updated sucessfully! Updated element "+name);
        }
        else
        {
            createErrorMessage("Error trying to update information");
            setStatusBarMessage("Error trying to update information! Name: "+name+" - Media Type: "+mediaType);
        }
    }
    
    private void deleteInformationOfMedia()
    {
        String mediaType = _searchTypeCbb.getSelectedItem().toString();
        String name = _name.getText();
        if(MediaManager.deleteMediaInformation(mediaType, name))
        {
            deleteInformationOnTree();
            createOkMessage("Information deleted sucessfully!");
            clearInformation();
            setStatusBarMessage("Information deleted sucessfully! Deleted element "+ name);
        }
        else
        {
            createErrorMessage("Error trying to delete the information.");
            setStatusBarMessage("Error trying to delete the information... Name: "+name+" - Media Type: "+mediaType);
        }    
    }
    
    private void addBasicInformationOfMedia()
    {
        String name = _name.getText();
        String description = _description.getText();
        
        String type = _type.getSelectedItem() != null ? _type.getSelectedItem().toString() : "";
        int year = _year.getSelectedItem() != null ? Integer.valueOf(_year.getSelectedItem().toString()) : 1901;
        
        if(name.equals("") || description.equals("") || type.equals(""))
        {
            createErrorMessage("The name, description and type are required to insert media information.");
            return;
        }
        
        String mediaType = _searchTypeCbb.getSelectedItem().toString();
        if(MediaManager.insertMediaInformation(name, description, type, year, mediaType))
        {
            insertInformationOnTree(name);
            createOkMessage("Information inserted sucessfully!");
            clearInformation();
            setStatusBarMessage("Information inserted sucessfully! Added element "+name);
        }
        else
        {
            createErrorMessage("Error trying to insert information.");
            setStatusBarMessage("Error trying to insert information... Name: "+name+" - Media Type: " + mediaType);
        } 
    }
    
    private void updateImageByPosition(ElementTypes eType,byte[] image)
    {
        String mediaType = _searchTypeCbb.getSelectedItem().toString();
        String name = "";
        TreePath path = _searchTree.getSelectionPath();
        if (path != null)
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
            name = node.toString();
        }
        
        if(MediaManager.updateImages(mediaType, name, image, eType))
        {
            createOkMessage("Image added sucessfully!");
        }
        else
        {
            createErrorMessage("Error trying to update image");
        }
    }    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Search Methods">
    
    private void createSearchDialogWindow()
    {
        String selectedMedia = _searchTypeCbb.getSelectedItem().toString();
        
        _dialog = new JDialog(_defaultFrame);
        _dialog.setTitle("Search media...");
        _dialog.setSize(new Dimension(300, 500));
        _dialog.setLocationRelativeTo(_defaultFrame);
        _dialog.setModal(true);
        _dialog.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel labelTitle = new JLabel("Search current media : " + selectedMedia);
        labelTitle.setBounds(10, 5, 260, 20);
        labelTitle.setSize(260, 20);
        labelTitle.setVisible(true);
        panel.add(labelTitle);
        
        _searchField = new JTextField();
        _searchField.setBounds(10, 30, 265, 20);
        _searchField.setVisible(true);
        _searchField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String searchCriteria = _searchField.getText();
                searchMedia(searchCriteria);
                _tmpList.repaint();
                _dialog.repaint();
            }
        });
        panel.add(_searchField);
        
        _scrollSearchResult = new JScrollPane();
        _scrollSearchResult.setBounds(10, 55, 265, 370);
        _scrollSearchResult.setVisible(true);
        
        
        _tmpList = new JList();
        _tmpList.setVisible(true);
        _tmpList.setAutoscrolls(true);
        _tmpList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        _tmpList.addListSelectionListener(new ListSelectionListener() {
            @Override 
            public void valueChanged(ListSelectionEvent e) {
                if(!_tmpList.isSelectionEmpty())
                {
                    _btnSearch.setEnabled(true);
                }
                else
                {
                    _btnSearch.setEnabled(false);
                }
            }
        });
        
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
              JList theList = (JList) mouseEvent.getSource();
              if (mouseEvent.getClickCount() == 2) {
                int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                  Object o = theList.getModel().getElementAt(index);
                  System.out.println("Double-clicked on: " + o.toString());
                  TreePath path = findObject(o.toString());
                  collapseAllTree(_searchTree);
                  _searchTree.setSelectionPath(path);
                  _searchTree.scrollPathToVisible(path);
                  _dialog.dispose();
                }
              }
        }
        };
        
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch( keyCode ) { 
                    case KeyEvent.VK_UP:
                        System.out.println("UP");
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("DOWN");
                        break;
                    case KeyEvent.VK_ENTER:{
                        JList theList = (JList)e.getSource();
                        System.out.println(theList.getSelectedValue().toString());
                        TreePath path = findObject(theList.getSelectedValue().toString());
                        collapseAllTree(_searchTree);
                        _searchTree.setSelectionPath(path);
                        _searchTree.scrollPathToVisible(path);
                        _dialog.dispose();
                    }
                        break;
                 }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               
            }
        };
        
        _tmpList.addKeyListener(keyListener);
        _tmpList.addMouseListener(mouseListener);
        _scrollSearchResult.setViewportView(_tmpList);
        panel.add(_scrollSearchResult);
        
        _btnSearch = new JButton("Select");
        _btnSearch.setBounds(195, 435, 80, 20);
        _btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // select item from tree
                if(!_tmpList.isSelectionEmpty())
                {
                    String nameSelected = _tmpList.getModel().getElementAt(_tmpList.getSelectedIndex()).toString();
                    TreePath path = findObject(nameSelected);
                    collapseAllTree(_searchTree);
                    _searchTree.setSelectionPath(path);
                    _searchTree.scrollPathToVisible(path);
                    _dialog.dispose();
                }
            }
        });
        _btnSearch.setEnabled(false);
        panel.add(_btnSearch);
        
        _dialog.add(panel);
        _dialog.repaint();
        _dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/Search2.png")));
        
        _dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _dialog.setVisible(true);
        
    }

    private void enableSearchOption()
    {
        String seachBy =  _searchTypeCbb.getSelectedItem().toString();
        String displayBy =  _displayModeCbb.getSelectedItem().toString();
        if("".equals(displayBy) || "".equals(seachBy))
        {
            _searchImage.setEnabled(false);
        }
        else
        {
            _searchImage.setEnabled(true);
        }
    }
    
    private void searchMediaBy()
    {
        String mediaType = _searchTypeCbb.getSelectedItem().toString();
        String searchBy = _displayModeCbb.getSelectedItem().toString();
        
        _informationPanel.remove(_type);
        _type = InternalGUICreator.createComboBox(100, 218, 150, 24, MediaManager.getMediaTypeCategories(mediaType)); 
        _informationPanel.add(_type);
        _informationPanel.repaint();
       
        _searchTree = null;
        
        switch(searchBy)
        {
            case "A-Z":{
                _searchTree = MediaManager.getJTreeByAlphabet(mediaType);
            } break;
            case "Type":{
                _searchTree = MediaManager.getJTreeByType(mediaType);
            } break;
            case "Year":{
                _searchTree = MediaManager.getJTreeByYear(mediaType);
            } break;
            default: {
                _searchTree = MediaManager.getJTreeByAlphabet(mediaType);
            } break;
        }
                
        _searchTree.setCellRenderer(TransparentRender);
        _searchTree.setOpaque(true);
        setTreeListener(_searchTree);

        _searchTreeScrollPane.removeAll();
        
         _leftSearchPanel.remove(_searchTreeScrollPane);
        
        _searchTreeScrollPane = new JScrollPane(_searchTree);
        _searchTreeScrollPane.setLocation(5, 50);
        _searchTreeScrollPane.setSize(340,710);
        _searchTreeScrollPane.setOpaque(false);
        _searchTreeScrollPane.getViewport().setOpaque(false);

        _searchTree.setBounds(0, 0, 100, 500);
        _searchTree.setSize(100, 500);
        
       _leftSearchPanel.add(_searchTreeScrollPane);
        _leftSearchPanel.repaint();
        _searchTreeScrollPane.repaint();
       
    }
    
    private void searchMedia(String criteria)
    {
        String selectedMedia = _searchTypeCbb.getSelectedItem().toString();
        String[] tmpList = MediaManager.searchMediaInformation(selectedMedia, criteria);
        _tmpList.setListData(tmpList);
    }    
    
    private boolean getVideoInformation()
    {
        boolean isVideoAvailable = false;
        String name = getSelectedNode();
        String mediaType = _searchTypeCbb.getSelectedItem().toString();
        if(!name.equals(""))
        {
            Media mediaVideoInformation = MediaManager.getVideoInformation(mediaType, name);
            if(mediaVideoInformation.constainsVideoData())
            {
                // do something with data
                try ( 
                    FileOutputStream fout = new FileOutputStream("video"+mediaVideoInformation.getExtension())) {
                    fout.flush();
                    fout.write(mediaVideoInformation.getVideoData());
                    fout.close();
                    isVideoAvailable = true;
                }  
                catch(FileNotFoundException ex)
                {
                    
                }
                catch(IOException ioe)
                {
                    
                }
            }
            else
            {
                createOkMessage("Current media doesn't have a video clip to play.");
            }
        }
        else
        {
            createOkMessage("No media was selected in order to play video clip.");
        }
        return isVideoAvailable;
    }    
    
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Set/Show Methods">
       
    private void printMediaInformation(Media media,String category)
    {
        clearImagesInformation();
        
        _name.setText(media.getName());
        _description.setText(media.getDescription());
        _category.setText(category);
        _type.setSelectedItem(media.getType());
        _year.setSelectedItem(String.valueOf(media.getYear()));
        
        
        for(int i=0;i<3;i++)
        {
            if(i==0)
            {
                byte[] image1 = media.getImage(i);
                if(image1 != null && image1.length > 0)
                {
                    // read bytes as stream
                    Image img = Toolkit.getDefaultToolkit().createImage(image1);
                    
                    setImageInUpperImagePanel(img,InternalGUICreator.validateGIFImage(image1));
                }
            }
            else
            {
                if(i==1)
                {
                    byte[] image2 = media.getImage(i);
                    if(image2 != null && image2.length > 0)
                    {
                        // read bytes as stream
                        Image img = Toolkit.getDefaultToolkit().createImage(image2);
                        seImageInCenterLeftImagePanel(img,InternalGUICreator.validateGIFImage(image2));
                    }
                }
                else
                {
                    byte[] image3 = media.getImage(i);
                    if(image3 != null && image3.length > 0)
                    {
                        // read bytes as stream
                        Image img = Toolkit.getDefaultToolkit().createImage(image3);
                        setImageInCenterRightImagePanel(img,InternalGUICreator.validateGIFImage(image3));
                    }
                }
            } 
        }
    }
    
    private void setImageInCenterRightImagePanel(Image image,boolean isGIF)
    {
        ImageIcon icon = new ImageIcon (image);  
        ImageIcon scaledImage = InternalGUICreator.getStretchScaledImage(icon, 510, 310, isGIF);
        JLabel imageLabeled = InternalGUICreator.getLabelPositionedScaledImage(scaledImage,522,340,15,0.9f);
        _image3Panel.removeAll();
        _image3Panel.repaint();
        _image3Panel.add(imageLabeled);
        _image3Panel.update(_image3Panel.getGraphics());
    }
    
    private void seImageInCenterLeftImagePanel(Image image,boolean isGIF)
    {
        ImageIcon icon = new ImageIcon (image);  
        ImageIcon scaledImage = InternalGUICreator.getStretchScaledImage(icon, 510, 310, isGIF);
        JLabel imageLabeled = InternalGUICreator.getLabelPositionedScaledImage(scaledImage,522,340,15,0.9f);
        _image2Panel.removeAll();
        _image2Panel.repaint();
        _image2Panel.add(imageLabeled);
        _image2Panel.update(_image2Panel.getGraphics());
    }
    
    private void setImageInUpperImagePanel(Image image,boolean isGIF)
    {
        ImageIcon icon = new ImageIcon (image);  
        ImageIcon scaledImage = InternalGUICreator.getStretchScaledImage(icon, 510, 310, isGIF);
        JLabel imageLabeled = InternalGUICreator.getLabelPositionedScaledImage(scaledImage,522,340,15,0.9f);
        _image1Panel.removeAll();
        _image1Panel.repaint();
        _image1Panel.add(imageLabeled);
        _image1Panel.update(_image1Panel.getGraphics());
    }    
    
    private void setIcon()
    {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/icon5.png")));
    }   
    
    // </editor-fold>   
    
    // <editor-fold defaultstate="collapsed" desc="Messaging Methods">
       
    private void setStatusBarMessage(String message)
    {
        _statusBar.setMessage(message);
    }

    private void createOkMessage(String message)
    {
        Image imgOk = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/ok2.png"));
        ImageIcon icon = new ImageIcon(imgOk.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(this,message,"Message",JOptionPane.INFORMATION_MESSAGE,icon);
    }
    
    private int createConfirmationMessage(String message)
    {
        Image imgOk = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/Question-icon.png"));
        ImageIcon icon = new ImageIcon(imgOk.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        return JOptionPane.showConfirmDialog(this,message,"Message",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,icon);
    }
    
    private void createErrorMessage(String message)
    {
        Image imgError = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/error1.png"));
        ImageIcon icon = new ImageIcon(imgError.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(this,message,"Error",JOptionPane.ERROR_MESSAGE,icon);
    }    
    
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Others Methods">

    private String getTreeCountMsg()
    {
        return getNumberOfNodes(_searchTree.getModel()) + " elements were found under " + _searchTypeCbb.getSelectedItem().toString() + " list...";
    }
    
    private void showImportOptions()
    {
        Image search = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/Search1.png"));
        Image clear = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/edit-clear.png"));
        MediaImportUI.getInstance(this,search,clear);
    }
    
    private void showExportOptions()
    {
        Image export = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/export.png"));
        Image folderSearch = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imgs/icons/examples.png"));
        MediaExportUI.getInstance(this,export,folderSearch);
    }
    
    private void clearInformation()
    {
        // do something on changed
        // remove all values in UI and load new ones if selected node is leaf
        clearImagesInformation();
        // remove information
        _saveInformation.setEnabled(false);
        _playVideo.setEnabled(false);
        _deleteInformation.setEnabled(false);
        _addVideo.setEnabled(false);
        _name.setText("");
        _description.setText("");
        _category.setText("");
        setStatusBarMessage("");
    }
    
    private void clearImagesInformation()
    {
        _image3Panel.removeAll();
        _image1Panel.removeAll();
        _image2Panel.removeAll();
        _image1Panel.repaint();
        _image2Panel.repaint();
        _image3Panel.repaint();
        _image3Panel.update(_image3Panel.getGraphics());
        _image2Panel.update(_image2Panel.getGraphics());
        _image1Panel.update(_image1Panel.getGraphics());
    }    
    
    // </editor-fold>    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        javax.swing.UIManager.getLookAndFeelDefaults().put("_videoProgressBar.background", (new Color(getRandomColorNumber(), 
                                                                                                        getRandomColorNumber(), 
                                                                                                            getRandomColorNumber())));
        /*
        try {
            
            //javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
             
             
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   
                    break;
                }
            }
             
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MediaManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MediaManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MediaManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MediaManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        */
        //</editor-fold>
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MediaManagerUI().setVisible(true);
            }
        });
    }
    
    private static int getRandomColorNumber()
    {
        return (int)((Math.random() * 1000) % 255);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
