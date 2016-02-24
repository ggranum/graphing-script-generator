package controlsconversion.plots;


import java.util.ArrayList;
import java.util.Hashtable;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controlsconversion.Anchor;
import java.awt.event.*;
import javax.swing.border.Border;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class CreatePlot extends JFrame {

  ArrayList fileNames;

  Hashtable allIndx;

  Anchor anchor;

  XYLine selected = null;

  int yLineCount = 0;

  int altYLineCount = 0;

  public static final String[] lineStyles = {
	"-", ":", "-.", "--"};

  public static final String[] lineStyleStrings = {
	"Solid", "Dotted", "Dash Dot", "Dashed"};

// This is the full set. cyan (c) and yellow (y) suck.
  public static final String[] lineColors = {
	"b", "g", "r", "c", "m", "y", "k"};

  // This is the full set. cyan (c) and yellow (y) suck.
  public static final String[] lineColorStrings = {
	"Blue", "Green", "Red", "Cyan", "Magenta", "Yellow", "Black"};


  public static final String[] lineMarkers = {
	"none", ".", "o", "x", "+", "*", "s", "d", "v", "^", "<", ">", "p", "h"};


  PlotSet plot;

  JPanel jPanel1 = new JPanel();


  JLabel jLabel1 = new JLabel();

  JComboBox jcbFile = new JComboBox();

  JLabel jLabel2 = new JLabel();

  JLabel jLabel3 = new JLabel();

  JButton jbtAdd = new JButton();


  JPanel jPanel2 = new JPanel();


  TitledBorder titledBorder1;

  BorderLayout borderLayout1 = new BorderLayout();


  JScrollPane jScrollPane1 = new JScrollPane();


  JList jlsPlotList = new JList();


  JLabel jlbTitle = new JLabel();

  JTextField jtfTitle = new JTextField();

  JLabel jlbXLabel = new JLabel();

  JTextField jtfXLabel = new JTextField();

  JTextField jtfYLabel = new JTextField();

  JLabel jlbYLabel = new JLabel();


  DefaultListModel dmPlotList = new DefaultListModel();


  JButton jbtCreate = new JButton();

  JButton jbtRemove = new JButton();


  JLabel jLabel7 = new JLabel();


  JLabel jlbLineColor = new JLabel();


  JLabel jLabel8 = new JLabel();

  JLabel jLabel9 = new JLabel();


  JComboBox jcbLineColor = new JComboBox();

  JComboBox jcbSetX = new JComboBox();

  JComboBox jcbSetY = new JComboBox();

  JComboBox jcbLineMarker = new JComboBox();

  JComboBox jcbLineStyle = new JComboBox();

  JCheckBox jckbPlotAlternate = new JCheckBox();

  JLabel jLabel10 = new JLabel();

  JLabel jLabel11 = new JLabel();

  JTextField jtfLegend = new JTextField();

  JTextField jtfAltYLabel = new JTextField();

  public CreatePlot() {
    super("Create New Plot");
	try {
	  jbInit();
	}
	catch (Exception e) {
	  e.printStackTrace();
	}
  }


  public CreatePlot(Anchor anchor) {
	super("Create New Plot");
	this.fileNames = anchor.getOutputFileNames();
	this.allIndx = anchor.getAllIndices();
	this.anchor = anchor;
	try {
	  jbInit();
	  init();
	}
	catch (Exception e) {
	  e.printStackTrace();
	}

  }


  public CreatePlot(Anchor anchor, PlotSet plot) {
	super("Edit Existing Plot");
	this.fileNames = anchor.getOutputFileNames();
	this.allIndx = anchor.getAllIndices();
	this.anchor = anchor;
	this.plot = plot;
	try {
	  jbInit();
	  for (int i = 0; i < fileNames.size(); i++) {
		jcbFile.addItem(fileNames.get(i));
	  }
	  ArrayList lines = plot.getPlots();
	  for (int i = 0; i < lines.size(); i++) {
		dmPlotList.addElement(lines.get(i));
	  }
	  jtfTitle.setText(plot.getTitle());
	  jtfXLabel.setText(plot.getXLabel());
	  jtfYLabel.setText(plot.getYLabel());
	  jtfAltYLabel.setText(plot.getAltYLabel());
	  init();
	  jbtCreate.setText("Update");
	}
	catch (Exception e) {
	  e.printStackTrace();
	}

  }


  private void jbInit() throws Exception {

	jlsPlotList.setModel(dmPlotList);
	jlsPlotList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	jlsPlotList.addMouseListener(new java.awt.event.MouseAdapter() {
	  public void mouseClicked(MouseEvent e) {
		jlsPlotList_mouseClicked(e);
	  }
	});

	titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,
	  new Color(165, 163, 151)), "Y versus X");
	jPanel1.setMaximumSize(new Dimension(32767, 32767));
	jPanel1.setMinimumSize(new Dimension(1, 1));
	jPanel1.setOpaque(true);
	jPanel1.setPreferredSize(new Dimension(800, 600));
	jPanel1.setLayout(null);
	jLabel1.setText("Available Data Sets:");
	jLabel1.setBounds(new Rectangle(22, 176, 199, 15));
	jcbFile.setBounds(new Rectangle(11, 215, 238, 21));

	jLabel2.setBounds(new Rectangle(11, 197, 172, 15));
	jLabel2.setText("Pick a File:");
	jLabel3.setRequestFocusEnabled(true);
	jLabel3.setText("Then Pick a set for the X-axis:");
	jLabel3.setVerticalTextPosition(SwingConstants.CENTER);
	jLabel3.setBounds(new Rectangle(19, 239, 172, 15));
	jbtAdd.setBounds(new Rectangle(9, 478, 88, 25));
	jbtAdd.setPreferredSize(new Dimension(73, 25));
	jbtAdd.setToolTipText("");
	jbtAdd.setMnemonic('A');
	jbtAdd.setText("Add Set");
	jbtAdd.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jbtAdd_actionPerformed(e);
	  }
	});

	jPanel2.setBorder(titledBorder1);
	jPanel2.setBounds(new Rectangle(374, 14, 433, 509));
	jPanel2.setLayout(borderLayout1);
	jlbTitle.setRequestFocusEnabled(true);
	jlbTitle.setText("Plot Title:");
	jlbTitle.setBounds(new Rectangle(11, 4, 48, 15));
	jtfTitle.setText("Title");
	jtfTitle.setBounds(new Rectangle(11, 23, 325, 20));
	jlbXLabel.setText("X Label");
	jlbXLabel.setBounds(new Rectangle(11, 45, 76, 15));
	jtfXLabel.setText("");
	jtfXLabel.setBounds(new Rectangle(11, 60, 240, 21));
	jtfYLabel.setText("");
	jtfYLabel.setBounds(new Rectangle(11, 99, 240, 21));
	jlbYLabel.setBounds(new Rectangle(11, 84, 76, 15));
	jlbYLabel.setText("Y Label");
	this.setTitle("");
	jbtCreate.setBounds(new Rectangle(148, 522, 135, 32));
	jbtCreate.setMnemonic('C');
	jbtCreate.setText("Create Plot");
	jbtCreate.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jbtCreate_actionPerformed(e);
	  }
	});
	jbtRemove.setText("Remove Selected Set(s)");
	jbtRemove.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jbtRemove_actionPerformed(e);
	  }
	});
	jbtRemove.setMnemonic('R');
	jbtRemove.setToolTipText("");
	jbtRemove.setBounds(new Rectangle(372, 526, 157, 25));
	jLabel7.setText("Line Color");
	jLabel7.setBounds(new Rectangle(15, 324, 76, 15));

	jLabel7.setText("Line Style");
	jLabel7.setBounds(new Rectangle(14, 414, 78, 16));

	jlbLineColor.setText("Line Color");
	jlbLineColor.setBounds(new Rectangle(14, 393, 61, 18));

	jLabel8.setBounds(new Rectangle(12, 353, 78, 16));
	jLabel8.setText("Line Style");
	jLabel8.setBounds(new Rectangle(14, 438, 78, 16));
	jLabel8.setText("Line Marker");
	jLabel9.setBounds(new Rectangle(19, 282, 172, 15));
	jLabel9.setText("Then Pick a set for the Y-axis:");
	jLabel9.setRequestFocusEnabled(true);

	jcbLineColor.setBounds(new Rectangle(80, 393, 127, 21));
	jcbLineColor.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jcbLineColor_actionPerformed(e);
	  }
	});
	jcbSetX.setBounds(new Rectangle(23, 257, 226, 21));
	jcbSetX.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jcbSetX_actionPerformed(e);
	  }
	});
	jcbSetY.setBounds(new Rectangle(23, 300, 226, 21));
	jcbSetY.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jcbSetY_actionPerformed(e);
	  }
	});
	jcbLineMarker.setBounds(new Rectangle(80, 440, 127, 21));
	jcbLineMarker.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jcbLineMarker_actionPerformed(e);
	  }
	});
	jcbLineStyle.setBounds(new Rectangle(80, 419, 127, 21));
	jcbLineStyle.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jcbLineStyle_actionPerformed(e);
	  }
	});
	jckbPlotAlternate.setEnabled(false);
	jckbPlotAlternate.setToolTipText("");
	jckbPlotAlternate.setText("Plot on Alternate Y-Axis");
	jckbPlotAlternate.setBounds(new Rectangle(23, 325, 222, 23));
    jckbPlotAlternate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
	jckbPlotAlternate_actionPerformed(e);
      }
    });
	jtfAltYLabel.setBounds(new Rectangle(11, 141, 240, 21));
	jtfAltYLabel.setText("");
	jLabel10.setText("Alternate Y Label");
	jLabel10.setBounds(new Rectangle(11, 126, 143, 15));
	jLabel11.setBounds(new Rectangle(11, 352, 143, 15));
	jLabel11.setText("Legend Entry");
	jtfLegend.setBounds(new Rectangle(11, 367, 240, 21));
	jtfLegend.addKeyListener(new java.awt.event.KeyAdapter() {


	  public void keyReleased(KeyEvent e) {
		jtfLegend_keyReleased(e);
	  }
	});
	jtfAltYLabel.setBounds(new Rectangle(11, 141, 240, 21));
	jPanel1.add(jPanel2, null);
	jPanel2.add(jScrollPane1, BorderLayout.CENTER);
	jPanel1.add(jlbTitle, null);
	jPanel1.add(jtfTitle, null);
	jPanel1.add(jtfXLabel, null);
	jPanel1.add(jlbYLabel, null);
	jPanel1.add(jtfYLabel, null);
	jPanel1.add(jlbXLabel, null);
	jPanel1.add(jLabel10, null);
	jPanel1.add(jtfAltYLabel, null);
	jPanel1.add(jLabel1, null);
	jPanel1.add(jcbFile, null);
	jPanel1.add(jLabel2, null);
	jPanel1.add(jcbSetY, null);
	jPanel1.add(jckbPlotAlternate, null);
	jScrollPane1.getViewport().add(jlsPlotList, null);
	this.getContentPane().add(jPanel1, BorderLayout.CENTER);
	jPanel1.add(jbtRemove, null);
	jPanel1.add(jtfAltYLabel, null);
	jPanel1.add(jtfLegend, null);
	jPanel1.add(jlbLineColor, null);
	jPanel1.add(jLabel7, null);
	jPanel1.add(jLabel8, null);
	jPanel1.add(jcbLineColor, null);
	jPanel1.add(jcbLineMarker, null);
	jPanel1.add(jcbLineStyle, null);
	jPanel1.add(jLabel11, null);
	jPanel1.add(jbtAdd, null);
	jPanel1.add(jLabel3, null);
	jPanel1.add(jLabel9, null);
	jPanel1.add(jcbSetX, null);
	jPanel1.add(jbtCreate, null);

  }


  void init() {
	jckbPlotAlternate.setEnabled(false);
	for (int i = 0; i < fileNames.size(); i++) {
	  jcbFile.addItem(fileNames.get(i));
	}
	jcbFile.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jcbFile_actionPerformed(e);
	  }
	});
	jcbFile.setSelectedIndex(0);

	for (int i = 0; i < lineColors.length; i++) {
	  jcbLineColor.addItem(lineColorStrings[i]);
	}
	for (int i = 0; i < lineMarkers.length; i++) {
	  jcbLineMarker.addItem(lineMarkers[i]);
	}

	for (int i = 0; i < lineStyles.length; i++) {
	  jcbLineStyle.addItem(lineStyleStrings[i]);
	}

	this.pack();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();
	if (frameSize.height > screenSize.height) {
	  frameSize.height = screenSize.height;
	}
	if (frameSize.width > screenSize.width) {
	  frameSize.width = screenSize.width;
	}
	this.setLocation( (screenSize.width - frameSize.width) / 2,
					 (screenSize.height - frameSize.height) / 2);
	this.setVisible(true);

  }


  void jbtAdd_actionPerformed(ActionEvent e) {
	String legend = jtfLegend.getText();
	String file = (String) jcbFile.getSelectedItem();

	String xAxis = (String) jcbSetX.getSelectedItem();
	String yAxis = (String) jcbSetY.getSelectedItem();

	String lineColor = lineColors[jcbLineColor.getSelectedIndex()];
	String lineStyle = lineStyles[jcbLineStyle.getSelectedIndex()];
	String lineMarker = lineMarkers[jcbLineMarker.getSelectedIndex()];
	if (lineMarker.equals("none")) {
	  lineMarker = "";
	}

	boolean isAltY = jckbPlotAlternate.isSelected();

	if (file == null) {
	  // wtf?
	  return;
	}
	else if (legend == null || legend == "") {
	  //msg
	}
	else if (xAxis == null || yAxis == null) {
	  //wtf?
	  return;
	}
	int xIndx = jcbSetX.getSelectedIndex();
	int yIndx = jcbSetY.getSelectedIndex();
	XYLine plot = new XYLine(file, legend, xAxis, xIndx, yAxis, yIndx, lineColor, lineMarker,
							 lineStyle, isAltY);
	dmPlotList.addElement(plot);

	if (isAltY) {
	  altYLineCount++;
	}
	else {
	  yLineCount++;
	}
	setSelected(null);

  }


  void jbtRemove_actionPerformed(ActionEvent e) {
	if (selected != null) {

	  if (selected.isAlternateYAxis()) {
		altYLineCount--;
	  }
	  else {
		yLineCount--;
		if (yLineCount < altYLineCount) {
		  JOptionPane.showMessageDialog(new Frame(),
										"You must have at least as many plots on the\n" +
										"Y Axis as on the Alternate Y Axis.", "Cannot Delete Line",
										JOptionPane.WARNING_MESSAGE);
		  yLineCount++;
		  return;
		}

	  }
	  jckbPlotAlternate.setEnabled(yLineCount < altYLineCount);
	  dmPlotList.removeElement(selected);
	  setSelected(null);

	}

  }


  void jbtCreate_actionPerformed(ActionEvent e) {
	String title = jtfTitle.getText();
	String xLabel = jtfXLabel.getText();
	String yLabel = jtfYLabel.getText();
	String altYLabel = jtfAltYLabel.getText();
	ArrayList xyLinePlots = new ArrayList();
	boolean usesAltY = false;
	for (int i = 0; i < dmPlotList.getSize(); i++) {
	  xyLinePlots.add(dmPlotList.get(i));
	  if ( ( (XYLine) dmPlotList.get(i)).isAlternateYAxis()) {
		usesAltY = true;
	  }
	}

	if (this.plot != null) {
	  plot.setTitle(title);
	  plot.setAltYLabel(altYLabel);
	  plot.setXLabel(xLabel);
	  plot.setYLabel(yLabel);
	  plot.setYLines(xyLinePlots);
	  plot.setUsesAlternateAxis(true);
	}
	else {
	  PlotSet set = new PlotSet(title, xLabel, yLabel, altYLabel, xyLinePlots, usesAltY);
	  anchor.addPlotSet(set);
	}
	this.dispose();
  }


  void jlsPlotList_mouseClicked(MouseEvent e) {
	XYLine sel = (XYLine) jlsPlotList.getSelectedValue();
	if (sel == null) {
	  return;
	}
	if (selected == null) {
	  this.setSelected(sel);

	}
	else if (selected.equals(sel)) {
	  this.setSelected(null);
	  jlsPlotList.clearSelection();
	}
	else {
	  this.setSelected(sel);
	}
  }


  protected void setSelected(XYLine sel) {
	this.selected = sel;
	if (sel == null) {
	  jlsPlotList.clearSelection();

	  //Reset Parameters
	  jtfLegend.setText("");
	  jcbLineColor.setSelectedIndex(0);
	  jcbLineMarker.setSelectedIndex(0);
	  jcbLineStyle.setSelectedIndex(0);

	  jckbPlotAlternate.setEnabled(yLineCount > altYLineCount);
	  jbtAdd.setEnabled(true);
	}
	else {
	  jbtAdd.setEnabled(false);
	  jtfLegend.setText(sel.getLegend());
      String str = sel.getLineColor();
      for(int i = 0; i < lineColors.length; i++){
        if(str.equals(lineColors[i])){
          jcbLineColor.setSelectedIndex(i);
          break;
        }
      }
	  str = sel.getLineStyle();
      for(int i = 0; i < lineStyles.length; i++){
		if (str.equals(lineStyles[i])) {
          jcbLineStyle.setSelectedIndex(i);
          break;
		}
	  }
	  str = sel.getLineMarker();
      if(str.equals("")){
        jcbLineMarker.setSelectedIndex(0);
      }
      for(int i = 0; i < lineMarkers.length; i++){
        if (str.equals(lineMarkers[i])) {
          jcbLineMarker.setSelectedIndex(i+1);
          break;
        }
      }




	  jcbFile.setSelectedItem(sel.getFile());
	  jcbSetX.setSelectedItem(sel.getXAxis());
	  jcbSetY.setSelectedItem(sel.getYAxis());
	  if (sel.isAlternateYAxis()) {
		jckbPlotAlternate.setSelected(true);
		jckbPlotAlternate.setEnabled(true);
	  }
	  else {
		jckbPlotAlternate.setSelected(false);
		jckbPlotAlternate.setEnabled(yLineCount > altYLineCount + 1);
	  }

	}

  }


  void jcbFile_actionPerformed(ActionEvent e) {
	// [fileName, index_X, index_x (int), index_Y, index_Y (int), plotName, ]
	String name = (String) jcbFile.getSelectedItem();
	ArrayList vals = (ArrayList) allIndx.get(name);

	boolean found_X = false;
	boolean found_Y = false;
	boolean editing = (selected != null);
	if (editing) {
	  for (int i = 0; i < vals.size(); i++) {
		if ( ( (String) jcbSetX.getItemAt(i)).endsWith( (String) vals.get(i))) {
		  found_X = true;
		  break;
		}
	  }
	  for (int i = 0; i < vals.size(); i++) {
		if ( ( (String) jcbSetY.getItemAt(i)).endsWith( (String) vals.get(i))) {
		  found_Y = true;
		  break;
		}
	  }
	}
	if (!editing || (editing && (!found_X || !found_Y))) {
	  jcbSetX.removeAllItems();
	  jcbSetY.removeAllItems();
	  for (int i = 0; i < vals.size(); i++) {
		jcbSetX.addItem("{" + String.valueOf(i + 1) + "} " + vals.get(i));
		jcbSetY.addItem("{" + String.valueOf(i + 1) + "} " + vals.get(i));
	  }
	}
    else{
      selected.setFile((String)jcbFile.getSelectedItem());
    }

  }


  void jcbSetX_actionPerformed(ActionEvent e) {
	if (selected != null) {
	  selected.setXAxis( (String) jcbSetX.getSelectedItem());
	}

  }


  void jcbSetY_actionPerformed(ActionEvent e) {
	if (selected != null) {
	  selected.setYAxis( (String) jcbSetY.getSelectedItem());
	}
	if (jtfLegend.getText() == null || jtfLegend.getText().equals("")) {
	  String strA = (String) jcbSetY.getSelectedItem();
	  String strB = (String) jcbSetX.getSelectedItem();
	  strA = strA.substring(strA.lastIndexOf('}') + 1).trim();
	  strB = strB.substring(strB.lastIndexOf('}') + 1).trim();
	  jtfLegend.setText(strA + " vs. " + strB);
	}

  }


  void jcbLineColor_actionPerformed(ActionEvent e) {
	if (selected != null) {
      String lineColor = lineColors[jcbLineColor.getSelectedIndex()];
	  selected.setLineColor(lineColor);
	}
  }


  void jcbLineStyle_actionPerformed(ActionEvent e) {
	if (selected != null) {
      String lineStyle = lineStyles[jcbLineStyle.getSelectedIndex()];
      selected.setLineStyle(lineStyle);
	}

  }


  void jcbLineMarker_actionPerformed(ActionEvent e) {
	if (selected != null) {
      String lineMarker = lineMarkers[jcbLineMarker.getSelectedIndex()];
      if(lineMarker.equals("none")){
        lineMarker = "";
      }
	  selected.setLineMarker(lineMarker);
	}

  }


  void jtfLegend_keyReleased(KeyEvent e) {
	if (selected != null) {
	  selected.setLegend(jtfLegend.getText());
	}
    if(e.getModifiers() == e.CTRL_MASK){
      if(e.getKeyText(e.getKeyCode()).equals("D")){
        String strA = (String) jcbSetY.getSelectedItem();
     String strB = (String) jcbSetX.getSelectedItem();
     strA = strA.substring(strA.lastIndexOf('}') + 1).trim();
     strB = strB.substring(strB.lastIndexOf('}') + 1).trim();
     jtfLegend.setText(strA + " vs. " + strB);

      }
    }

  }

  void jckbPlotAlternate_actionPerformed(ActionEvent e) {
    if(selected != null){
      selected.setAlternateYAxis(jckbPlotAlternate.isSelected());
    }
  }


}
