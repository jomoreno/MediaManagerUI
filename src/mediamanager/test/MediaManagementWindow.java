/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.test;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import mediamanager.gui.InternalGUICreator;
import mediamanager.gui.TrasparentRender;
import mediamanager.gui.JBackgroundPanel;
import mediamanager.test.TestTransparentTree;

/**
 *
 * @author Josue
 */
public class MediaManagementWindow extends javax.swing.JFrame implements TreeSelectionListener {

    private JTree tree;
    
    /**
     * Creates new form MediaManagementWindow
     */
    public MediaManagementWindow() {
        initComponents();
        
        
        JBackgroundPanel panel = new JBackgroundPanel(0,20,1440,900);
        panel.setLocation(10,10);
        panel.setSize(getWidth(), getHeight());
        panel.setVisible(true);
       
        JButton btn = InternalGUICreator.createButton("Click",10, 10, 70, 35, false, Color.darkGray);
        panel.add(btn);
        
        JComboBox cbb = InternalGUICreator.createComboBox(10, 70, 100, 45, new String[] {"C","B","A","D"});
        panel.add(cbb);
        
        JPanel panel1 = InternalGUICreator.createLoweredPanel("TEST", 200, 10, 500,700,0.5f);
        panel1.setVisible(true);
        

        this.add(panel);
       
        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("The Java Series");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.setBackground(new Color(0, 0, 0, 0));  
        tree.setCellRenderer(new TrasparentRender());
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        JScrollPane scroll = new JScrollPane(tree){
//            private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
//            
//            @Override
//             public void paint(Graphics g) {
//                comp = comp.derive(0.2f);
//                Graphics2D g2 = (Graphics2D) g;
//                g2.setComposite(comp);
//                super.paint(g);
//             }
        };
        
        scroll.setOpaque(false);  
        scroll.setVisible(true);
        scroll.setSize(500, 600);
        scroll.setLocation(10, 10);
        
        panel1.add(scroll);
        panel.add(panel1);

        //Create the scroll pane and add the tree to it. 

//        JTree tree1 = new JTree();  
//        tree1.setCellRenderer(new TrasparentRender());  
//        tree1.setBackground(new Color(0, 0, 0, 0));  
//  
//        JScrollPane scroll = new JScrollPane(tree1);  
//        scroll.setOpaque(false);  
//        scroll.setVisible(true);
//        scroll.setSize(500, 600);
//        scroll.setLocation(50, 50);
//        panel.add(scroll);
        
    }


    
    
    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;

        category = new DefaultMutableTreeNode("Books for Java Programmers");
                 
        top.add(category);

        //original Tutorial
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Tutorial: A Short Course on the Basics",
            "tutorial.html"));
        category.add(book);

        //Tutorial Continued
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Tutorial Continued: The Rest of the JDK",
            "tutorialcont.html"));
        category.add(book);

        //JFC Swing Tutorial
        book = new DefaultMutableTreeNode(new BookInfo
            ("The JFC Swing Tutorial: A Guide to Constructing GUIs",
            "swingtutorial.html"));
        category.add(book);

        //Bloch
        book = new DefaultMutableTreeNode(new BookInfo
            ("Effective Java Programming Language Guide",
	     "bloch.html"));
        category.add(book);

        //Arnold/Gosling
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Programming Language", "arnold.html"));
        category.add(book);

        //Chan
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Developers Almanac",
             "chan.html"));
        category.add(book);

        category = new DefaultMutableTreeNode("Books for Java Implementers");
        top.add(category);

        //VM
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Virtual Machine Specification",
             "vm.html"));
        category.add(book);

        //Language Spec
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Language Specification",
             "jls.html"));
        category.add(book);
    }
    
    private class BookInfo {
        public String bookName;
        public URL bookURL;

        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                                   + filename);
            }
        }

        public String toString() {
            return bookName;
        }
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            BookInfo book = (BookInfo)nodeInfo;
                System.out.print(book.bookURL + ":  \n    ");
            
        } else {
             
        }
    }    
    
    
    
    
    
    
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
            .addGap(0, 1214, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MediaManagementWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
