import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.plaf.LayerUI;

public class TurnProgressBarRedTest {
  public JComponent makeUI() {
    final BoundedRangeModel model = new DefaultBoundedRangeModel();
    final BlockedColorLayerUI layerUI = new BlockedColorLayerUI();
    final JPanel p = new JPanel(new GridLayout(4, 1, 12, 12));
    p.setBorder(BorderFactory.createEmptyBorder(24,24,24,24));

    final JProgressBar pb1 = new JProgressBar(model);
    pb1.setStringPainted(true);
    p.add(pb1);
    final JProgressBar pb2 = new JProgressBar(model);
    pb2.setStringPainted(true);
    p.add(pb2);

    p.add(new JProgressBar(model));
    p.add(new JLayer<JProgressBar>(new JProgressBar(model), layerUI));

    JPanel box = new JPanel();
    box.add(new JButton(new AbstractAction("+10") {
      private int i = 0;
      @Override public void actionPerformed(ActionEvent e) {
        model.setValue(i = (i>=100) ? 0 : i + 10);
      }
    }));
    //http://msdn.microsoft.com/en-us/library/windows/desktop/aa511486.aspx
    box.add(new JCheckBox(new AbstractAction(
        "<html>Turn the progress bar red<br />"+
        " when there is a user recoverable condition<br />"+
        " that prevents making further progress.") {
      @Override public void actionPerformed(ActionEvent e) {
        boolean b = ((JCheckBox)e.getSource()).isSelected();
        pb2.setForeground(b? new Color(255,234,132,100) : pb1.getForeground());
        layerUI.isPreventing = b;
        p.repaint();
      }
    }));

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(p, BorderLayout.NORTH);
    panel.add(box, BorderLayout.SOUTH);
    return panel;
  }
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override public void run() {
        createAndShowGUI();
      }
    });
  }
  public static void createAndShowGUI() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch(Exception e) {
      e.printStackTrace();
    }
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.getContentPane().add(new TurnProgressBarRedTest().makeUI());
    f.setSize(320, 240);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }
}
class BlockedColorLayerUI extends LayerUI<JProgressBar> {
  public boolean isPreventing = false;
  private BufferedImage bi;
  private int prevw = -1;
  private int prevh = -1;
  @Override public void paint(Graphics g, JComponent c) {
    if(isPreventing) {
      JLayer jlayer = (JLayer)c;
      JProgressBar progress = (JProgressBar)jlayer.getView();
      int w = progress.getSize().width;
      int h = progress.getSize().height;

      if(bi==null || w!=prevw || h!=prevh) {
        bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
      }
      prevw = w;
      prevh = h;

      Graphics2D g2 = bi.createGraphics();
      super.paint(g2, c);
      g2.dispose();

      Image image = c.createImage(
                      new FilteredImageSource(bi.getSource(),
                      new RedGreenChannelSwapFilter()));
      g.drawImage(image, 0, 0, null);
    } else {
      super.paint(g, c);
    }
  }
}
class RedGreenChannelSwapFilter extends RGBImageFilter {
  @Override public int filterRGB(int x, int y, int argb) {
    int r = (int)((argb >> 16) & 0xff);
    int g = (int)((argb >>  8) & 0xff);
    int b = (int)((argb      ) & 0xff);
    return (argb & 0xff000000) | (g<<16) | (r<<8) | (b);
  }
}