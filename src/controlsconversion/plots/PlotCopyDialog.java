package controlsconversion.plots;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import controlsconversion.Anchor;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class PlotCopyDialog extends JDialog {
  JPanel panel1 = new JPanel();
  JComboBox jcb = new JComboBox();
  JLabel jLabel1 = new JLabel();
  JButton jbtCancel = new JButton();
  JButton jbtCreate = new JButton();
  Anchor anchor;
  GridBagLayout gridBagLayout1 = new GridBagLayout();


  public PlotCopyDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public PlotCopyDialog(Frame frame, String title, boolean modal, Anchor anchor) {
    super(frame, title, modal);
    try {
      this.anchor = anchor;
      jbInit();
      PlotSet[] plots = anchor.getPlots();
     for (int i = 0; i < plots.length; i++) {
       jcb.addItem(plots[i]);
     }

      pack();
      this.setVisible(true);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }


  public PlotCopyDialog() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(gridBagLayout1);
    jLabel1.setToolTipText("");
    jLabel1.setText("Choose a Plot to copy");
    jbtCancel.setText("Cancel");
    jbtCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
	jbtCancel_actionPerformed(e);
      }
    });
    jbtCreate.setText("Create Copy");
    jbtCreate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
	jbtCreate_actionPerformed(e);
      }
    });
    panel1.setPreferredSize(new Dimension(400, 300));
    getContentPane().add(panel1);
    panel1.add(jcb,  new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 13, 0, 111), 247, 4));
    panel1.add(jLabel1,  new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(62, 13, 0, 159), 116, 2));
    panel1.add(jbtCreate,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(142, 33, 25, 0), 4, 0));
    panel1.add(jbtCancel,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(142, 84, 25, 111), 6, 0));
  }

  void jbtCancel_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void jbtCreate_actionPerformed(ActionEvent e) {
    Object obj = jcb.getSelectedItem();
    if(obj == null){
      JOptionPane.showMessageDialog(new Frame(), "Select a plot if you want to copy it!",
                                    "I'll assume you just slipped.....", JOptionPane.WARNING_MESSAGE);
      return;
    }
    else{
      PlotSet plot =((PlotSet)obj).copyPlot();
      plot.setTitle("Copy of " + plot.getTitle());
      anchor.addPlotSet(plot);
      this.dispose();
    }
  }
}