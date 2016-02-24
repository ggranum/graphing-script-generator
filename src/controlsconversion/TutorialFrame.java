package controlsconversion;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class TutorialFrame extends JFrame {
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JEditorPane jepTutorial = new JEditorPane();
  private static final String filePath = System.getProperty("user.dir") + File.separatorChar + "resources" +
  File.separatorChar + "help";
  JScrollPane jspTutorial = new JScrollPane();

  public TutorialFrame() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setTitle("Tutorial");
    File file = new File(filePath, "tutorial.html");
    URL url = file.toURL();
    //jPanel1.setPreferredSize(new Dimension(800, 600));
    jPanel1.setToolTipText("");
    jPanel1.setLayout(borderLayout1);
    jepTutorial.setEditable(false);

    jepTutorial.setContentType("text/html");
    jepTutorial.setPage(url);
    jspTutorial.setPreferredSize(new Dimension(790, 595));
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jspTutorial, BorderLayout.CENTER);
    jspTutorial.getViewport().add(jepTutorial, null);
  }
}