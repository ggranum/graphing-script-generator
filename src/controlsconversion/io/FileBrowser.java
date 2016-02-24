package controlsconversion.io;


import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class FileBrowser extends JPanel{
  JFileChooser jFileChooser1 = new JFileChooser();
  BorderLayout borderLayout1 = new BorderLayout();
  public FileBrowser() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jFileChooser1.setBorder(null);
    jFileChooser1.setPreferredSize(new Dimension(444, 245));
    jFileChooser1.setToolTipText("Hold Control Or Shift to select multiple files.");
    jFileChooser1.setAcceptAllFileFilterUsed(true);
    jFileChooser1.setControlButtonsAreShown(true);
    jFileChooser1.setDialogTitle("Choose Files To Add");
    jFileChooser1.setFileSelectionMode(0);
    this.add(jFileChooser1, BorderLayout.CENTER);
  }

  public void getBrowserFrame(){
    JFrame frame = new JFrame("Choose Files to convert");
    frame.pack();
    frame.show();
  }

}