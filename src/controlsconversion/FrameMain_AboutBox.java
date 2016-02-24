package controlsconversion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
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

public class FrameMain_AboutBox extends JDialog {



  JPanel panel1 = new JPanel();
  ImageIcon image1 = new ImageIcon();
  String product = "";
  String version = "1.0";
  String copyright = "Copyright (c) 2003";
  String comments = "";
  JEditorPane jepMsg = new JEditorPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JButton jButton1 = new JButton();
  public FrameMain_AboutBox(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    File file = new File(System.getProperty("user.dir") + File.separatorChar + "resources", "about.html");
    URL url = file.toURL();
    image1 = new ImageIcon(controlsconversion.FrameMain.class.getResource("about.png"));
    this.setTitle("About");
    panel1.setLayout(borderLayout1);
    jepMsg.setPage(url);
    jepMsg.setContentType("text/html");
    jButton1.setText("Ok");
    jButton1.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                 jButton_actionPerformed(e);
               }
             });
    jepMsg.setPreferredSize(new Dimension(600, 400));

    this.getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(jepMsg,  BorderLayout.CENTER);
    panel1.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jButton1, null);
    setResizable(true);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }
  //Close the dialog
  void cancel() {
    dispose();
  }
  //Close the dialog on a button event
  public void jButton_actionPerformed(ActionEvent e) {
    if (e.getSource() == jButton1) {
      cancel();
    }
  }
}