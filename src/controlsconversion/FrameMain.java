package controlsconversion;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import controlsconversion.convert.ConvertFiles;
import java.util.Hashtable;
import controlsconversion.output.mFileCreation;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Utilities;
import controlsconversion.io.PlotWriter;
import controlsconversion.plots.PlotCopyDialog;
import controlsconversion.plots.CreatePlot;
import controlsconversion.plots.PlotSet;
import java.io.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class FrameMain extends JFrame {

  protected Anchor anchor = new Anchor(this);

  protected UserData userData = new UserData();

  JPanel contentPane;

  JMenuBar jMenuBar1 = new JMenuBar();

  JMenu jMenuFile = new JMenu();

  JMenuItem jMenuFileExit = new JMenuItem();

  JMenu jMenuHelp = new JMenu();

  JMenuItem jMenuHelpAbout = new JMenuItem();

  JToolBar jToolBar = new JToolBar();

  JButton jbtnAdd = new JButton();

  JButton jButton2 = new JButton();

  ImageIcon image1;

  ImageIcon image2;

  ImageIcon image3;

  JLabel statusBar = new JLabel();

  BorderLayout borderLayout1 = new BorderLayout();

  JSplitPane jSplitPane1 = new JSplitPane();

  JMenuItem jmnu_File_Add = new JMenuItem();

  DefaultListModel readList = new DefaultListModel();

  DefaultListModel writeList = new DefaultListModel();

  JList jlstRead = new JList(readList);

  JScrollPane jScrollPane1 = new JScrollPane();

  JList jlstWrite = new JList(writeList);

  JScrollPane jScrollPane2 = new JScrollPane();

  JMenuItem jmnFile_ChangeDir = new JMenuItem();

  JMenuItem jmnFile_Run = new JMenuItem();

  JMenu jmnuUtility = new JMenu();

  JMenuItem jmnuUtility_Autocreate = new JMenuItem();

  JMenu jmnuPlots = new JMenu();

  JMenuItem jmnuPlots_New = new JMenuItem();

  JMenuItem jmnuPlots_CreateScript = new JMenuItem();

  JMenuItem jmnuPlots_Copy = new JMenuItem();

  JMenuItem jmiFile_Save = new JMenuItem();

  JMenuItem jmiFile_SaveAs = new JMenuItem();

  JMenuItem jmiFile_Open = new JMenuItem();

  JMenuItem jmiFile_New = new JMenuItem();
  JMenuItem jmiHelp_Tutorial = new JMenuItem();

  //Construct the frame

  public FrameMain() {
	enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	try {
	  jbInit();
	  init();
	}
	catch (Exception e) {
	  e.printStackTrace();
	}
  }


  protected void openFile(File file) {
	if (file != null) {
	  try {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		this.anchor = (Anchor) in.readObject();
		anchor.setMain(this);
		userData.setLastOpenDir(file.getParentFile());
		userData.putRecent(file);
		if (anchor.getOutputFileNames().size() > 0) {
		  jmnuPlots.setEnabled(true);
		  jmnuUtility.setEnabled(true);
		}
		else {
		  jmnuPlots.setEnabled(false);
		  jmnuUtility.setEnabled(false);
		}
		PlotSet[] plots = anchor.getPlots();
		for (int i = 0; i < plots.length; i++) {
		  this.addPlotToMenu(plots[i]);
		}
		anchor.setChanged(false);

	  }
	  catch (ClassNotFoundException ex) {
		ex.printStackTrace();
	  }
	  catch (IOException ex) {
		ex.printStackTrace();
	  }

	}

  }


  protected void init() {
	File userFile = new File(System.getProperty("user.dir"), "init.dat");
	if (userFile.exists()) {
	  try {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(userFile));
		userData = (UserData) in.readObject();
		if (userData != null) {
		  final File[] files = userData.getRecent();
		  jMenuFile.add(new JSeparator(), jMenuFile.getItemCount() - 2);
		  for (int i = 0; i < files.length; i++) {
			if (files[i] != null) {
			  final File file = files[i];
			  JMenuItem item = new JMenuItem(file.getName());
			  item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				  openFile(file);
				}
			  });

			  jMenuFile.add(item, jMenuFile.getItemCount() - 2);
			}
		  }
		}
		else {
		  userData = new UserData();
		}

	  }
	  catch (Exception ex) {
		ex.printStackTrace();
	  }
	}
	else {
	  this.userData = new UserData();
	}
  }


  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
	if (e.getID() == WindowEvent.WINDOW_CLOSING) {
	  jMenuFileExit_actionPerformed(null);
	}
	else {
	  super.processWindowEvent(e);
	}
  }


  //Component initialization
  private void jbInit() throws Exception {

	image1 = new ImageIcon(controlsconversion.FrameMain.class.getResource("openFile.png"));
	image2 = new ImageIcon(controlsconversion.FrameMain.class.getResource("closeFile.png"));
	image3 = new ImageIcon(controlsconversion.FrameMain.class.getResource("help.png"));
	contentPane = (JPanel)this.getContentPane();
	jlstRead = new JList(readList);
	jlstWrite = new JList(writeList);
	contentPane.setLayout(borderLayout1);
	this.setSize(new Dimension(794, 702));
	this.setTitle("Matlab Script Generator");
	statusBar.setText(" ");
	jMenuFile.setText("File");
	jMenuFileExit.setMnemonic('X');
	jMenuFileExit.setText("Exit");
	jMenuFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke('X',
	  java.awt.event.KeyEvent.CTRL_MASK, false));
	jMenuFileExit.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jMenuFileExit_actionPerformed(e);
	  }
	});
	jMenuHelp.setText("Help");
	jMenuHelpAbout.setText("About");
	jMenuHelpAbout.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jMenuHelpAbout_actionPerformed(e);
	  }
	});
	jbtnAdd.setIcon(image1);
	jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jbtnAdd_actionPerformed(e);
	  }
	});
	jbtnAdd.setToolTipText("Add File To Convert");
	jButton2.setIcon(image2);
	jButton2.setToolTipText("Remove File From List");
	jToolBar.setOrientation(JToolBar.VERTICAL);
	jToolBar.setToolTipText("");

	jmnu_File_Add.setMnemonic('F');
	jmnu_File_Add.setText("Add File");
	jmnu_File_Add.setAccelerator(javax.swing.KeyStroke.getKeyStroke('I',
	  java.awt.event.KeyEvent.CTRL_MASK, false));
	jmnu_File_Add.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmnu_File_Add_actionPerformed(e);
	  }
	});
	jlstRead.setBorder(BorderFactory.createEtchedBorder());
	jlstRead.setMaximumSize(new Dimension(3000, 6000));
	jlstRead.setMinimumSize(new Dimension(300, 600));
	jlstRead.setPreferredSize(new Dimension(300, 600));
	jlstRead.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	jSplitPane1.setPreferredSize(new Dimension(302, 607));
	jSplitPane1.setTopComponent(jScrollPane1);
	jlstWrite.setMaximumSize(new Dimension(3000, 6000));
	jlstWrite.setMinimumSize(new Dimension(300, 600));
	jlstWrite.setOpaque(true);
	jlstWrite.setPreferredSize(new Dimension(300, 600));
	jScrollPane1.setPreferredSize(new Dimension(300, 600));
	jScrollPane2.setPreferredSize(new Dimension(300, 600));
	jScrollPane1.setRequestFocusEnabled(true);
	jmnFile_ChangeDir.setText("Change Output Dir");
	jmnFile_ChangeDir.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmnFile_ChangeDir_actionPerformed(e);
	  }
	});
	jmnFile_Run.setText("Run");
	jmnFile_Run.setAccelerator(javax.swing.KeyStroke.getKeyStroke('R',
	  java.awt.event.KeyEvent.CTRL_MASK, false));
	jmnFile_Run.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmnFile_Run_actionPerformed(e);
	  }
	});
	jmnuUtility.setText("Utilities");
	jmnuUtility_Autocreate.setText("Autocreate M-File");
	jmnuUtility_Autocreate.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmnuUtility_Autocreate_actionPerformed(e);
	  }
	});
	jmnuPlots.setText("Plots");
	jmnuPlots_New.setText("New");
	jmnuPlots_New.setAccelerator(javax.swing.KeyStroke.getKeyStroke('P',
	  java.awt.event.KeyEvent.CTRL_MASK, false));
	jmnuPlots_New.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmnuPlots_New_actionPerformed(e);
	  }
	});
	jmnuPlots_CreateScript.setText("Create Matlab Script");
	jmnuPlots_CreateScript.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmnuPlots_CreateScript_actionPerformed(e);
	  }
	});
	jmnuPlots_Copy.setEnabled(false);
	jmnuPlots_Copy.setText("Copy Plot");
	jmnuPlots_Copy.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmnuPlots_Copy_actionPerformed(e);
	  }
	});
	jmiFile_Save.setMnemonic('S');
	jmiFile_Save.setText("Save");
	jmiFile_Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke('S',
	  java.awt.event.KeyEvent.CTRL_MASK, false));
	jmiFile_Save.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmiFile_Save_actionPerformed(e);
	  }
	});
	jmiFile_SaveAs.setMnemonic('A');
	jmiFile_SaveAs.setText("Save As");
	jmiFile_SaveAs.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmiFile_SaveAs_actionPerformed(e);
	  }
	});
	jmiFile_Open.setText("Open");
	jmiFile_Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke('O',
	  java.awt.event.KeyEvent.CTRL_MASK, false));
	jmiFile_Open.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmiFile_Open_actionPerformed(e);
	  }
	});
	jmiFile_New.setText("New");
	jmiFile_New.setAccelerator(javax.swing.KeyStroke.getKeyStroke('N',
	  java.awt.event.KeyEvent.CTRL_MASK, false));
	jmiFile_New.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jmiFile_New_actionPerformed(e);
	  }
	});
	jmiHelp_Tutorial.setText("Tutorial");
    jmiHelp_Tutorial.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0, false));
    jmiHelp_Tutorial.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
	jmiHelp_Tutorial_actionPerformed(e);
      }
    });
    jToolBar.add(jbtnAdd);
	jToolBar.add(jButton2);
	contentPane.add(jSplitPane1, BorderLayout.CENTER);
	jSplitPane1.add(jScrollPane2, JSplitPane.BOTTOM);
	jScrollPane1.getViewport().add(jlstRead, null);
	jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
	jScrollPane2.getViewport().add(jlstWrite, null);
	jMenuFile.add(jmnu_File_Add);
	jMenuFile.add(jmnFile_ChangeDir);
	jMenuFile.add(jmnFile_Run);
	jMenuFile.addSeparator();
	jMenuFile.add(jmiFile_New);
	jMenuFile.add(jmiFile_Save);
	jMenuFile.add(jmiFile_SaveAs);
	jMenuFile.add(jmiFile_Open);
	jMenuFile.addSeparator();
	jMenuFile.add(jMenuFileExit);
	jMenuHelp.add(jmiHelp_Tutorial);
    jMenuHelp.addSeparator();
    jMenuHelp.add(jMenuHelpAbout);
	jMenuBar1.add(jMenuFile);
	jMenuBar1.add(jmnuUtility);
	jMenuBar1.add(jmnuPlots);
	jMenuBar1.add(jMenuHelp);
	this.setJMenuBar(jMenuBar1);
	contentPane.add(statusBar, BorderLayout.SOUTH);
	contentPane.add(jToolBar, BorderLayout.WEST);
	jmnuUtility.add(jmnuUtility_Autocreate);
	jmnuPlots.add(jmnuPlots_New);
	jmnuPlots.add(jmnuPlots_Copy);
	jmnuPlots.addSeparator();
	jmnuPlots.addSeparator();
	jmnuPlots.add(jmnuPlots_CreateScript);
	this.setMenusEnabled(false);

  }


  //File | Exit action performed
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
	File userFile = new File(System.getProperty("user.dir"), "init.dat");
	try {
	  ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userFile));
	  out.writeObject(userData);
	  out.close();
	}
	catch (Exception ex) {
	  ex.printStackTrace();
	}

	if (anchor.isChanged()) {
	  //Custom button text
	  Object[] options = {
		"Yes, save", "No, just quit", "Return to Program"};
	  int n = JOptionPane.showOptionDialog(new Frame(),
		"There are unsaved changes, would you like to save them before exiting?",
										   "Save your changes?", JOptionPane.YES_NO_CANCEL_OPTION,
										   JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	  if (n == JOptionPane.YES_OPTION) {
		jmiFile_Save_actionPerformed(null);
	  }
	  else if (n == JOptionPane.CANCEL_OPTION) {
		return;
	  }

	}
	this.dispose();
	System.exit(0);
  }


  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
	FrameMain_AboutBox dlg = new FrameMain_AboutBox(this);
	Dimension dlgSize = dlg.getPreferredSize();
	Dimension frmSize = getSize();
	Point loc = getLocation();
	dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
					(frmSize.height - dlgSize.height) / 2 + loc.y);
	dlg.setModal(true);
	dlg.pack();
	dlg.show();
  }


  void jmnu_File_Add_actionPerformed(ActionEvent e) {
	//Create a file chooser
    anchor.setChanged(true);
	final JFileChooser fc = new JFileChooser();
	fc.setCurrentDirectory(userData.getLastOutputDir());
	fc.setDialogTitle("Add Files To Convert:");
	fc.setFileFilter(new DefaultFileFilter("txt", "Text Files"));
	fc.setMultiSelectionEnabled(true);
//In response to a button click:
	int returnVal = fc.showOpenDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	  File[] files = fc.getSelectedFiles();
	  if (files == null) {
		File file = fc.getSelectedFile();
		if (file != null) {
		  files = new File[1];
		  files[0] = file;
		}
		else {
		  return;
		}
	  }
	  for (int i = 0; i < files.length; i++) {
		String name;
		if (files[i].isFile()) {
		  anchor.inputFiles.add(files[i]);
		  if (userData.getLastOutputDir() == null) {
			userData.setLastOutputDir(files[0].getParentFile());
		  }

		  name = files[i].getName();

		  readList.addElement(name);
		  name = name.substring(0, name.indexOf('.'));
		  anchor.outputFileNames.add(name);
		  writeList.addElement(name + ".m");
		}
	  }

	}
  }


  void jbtnAdd_actionPerformed(ActionEvent e) {
    anchor.setChanged(true);
	jmnu_File_Add_actionPerformed(null);
  }


  void jmnFile_ChangeDir_actionPerformed(ActionEvent e) {
	//Create a file chooser
	final JFileChooser fc = new JFileChooser();
	//fc.setf
	fc.setDialogTitle("Change Output Directory:");
	fc.setMultiSelectionEnabled(false);
	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//In response to a button click:
	int returnVal = fc.showOpenDialog(this);

	if (returnVal == JFileChooser.APPROVE_OPTION) {
	  File file;
	  if ( (file = fc.getSelectedFile()) != null) {
		userData.setLastOutputDir(file);
	  }

	}
  }


  void jmnFile_Run_actionPerformed(ActionEvent e) {

	if (userData.getLastOutputDir() == null) {
	  //custom title, warning icon
	  JOptionPane.showMessageDialog(new Frame(),
									"You need to add files or set the output direcotry before running.",
									"No output directory set", JOptionPane.WARNING_MESSAGE);

	}
    if(anchor.inputFiles.size() <=0){
      //custom title, warning icon
    JOptionPane.showMessageDialog(new Frame(),
                                  "You need to add files before running.",
                                  "No output directory set", JOptionPane.WARNING_MESSAGE);

    }

	ConvertFiles conv = new ConvertFiles(anchor.inputFiles, anchor.outputFileNames,
										 userData.getLastOutputDir(), this);

  }


  public void doneConverting(Hashtable allIndx) {
	anchor.allIndices = allIndx;
	JOptionPane.showMessageDialog(new Frame(),
	  "     Done creating output files.\nYou can now use the other menu options", "Done",
								  JOptionPane.INFORMATION_MESSAGE);
	setMenusEnabled(true);

  }


  void jmnuUtility_Autocreate_actionPerformed(ActionEvent e) {
	mFileCreation create = new mFileCreation(anchor.outputFileNames, userData.getLastOutputDir(),
											 anchor.allIndices);

  }


  void jmnuPlots_New_actionPerformed(ActionEvent e) {
	CreatePlot frame = new CreatePlot(anchor);
  }


  public void setMenusEnabled(boolean enable) {
	jmnuPlots.setEnabled(enable);
	jmnuPlots_New.setEnabled(enable);
	jmnuUtility_Autocreate.setEnabled(enable);
	jmnuUtility.setEnabled(enable);

  }


  public class DefaultFileFilter extends FileFilter {

	String ext;

	String description;

	public DefaultFileFilter(String extension, String description) {
	  this.ext = extension;
	  this.description = description;
	}


	//
	public boolean accept(File f) {
	  if (f.isDirectory()) {
		return true;
	  }
	  if (f.getName().endsWith(ext)) {
		return true;
	  }
	  else {
		return false;
	  }
	}


	//The description of this filter
	public String getDescription() {
	  return description;
	}
  }


  void jmnuPlots_CreateScript_actionPerformed(ActionEvent e) {

	//Create a file chooser
	final JFileChooser fc = new JFileChooser();
	fc.setCurrentDirectory(userData.getLastOutputDir());
	fc.setDialogTitle("Save matlab script as: ");
	fc.setFileFilter(new DefaultFileFilter("m", "Matlab Script Files"));
	fc.setMultiSelectionEnabled(false);
//In response to a button click:
	int returnVal = fc.showSaveDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	  File file = fc.getSelectedFile();
	  if (file != null) {
		if (!file.getName().endsWith(".m")) {
		  file = new File(file.getAbsolutePath(), file.getName() + ".m");
		}
		userData.setLastOutputDir(file.getParentFile());
		PlotWriter writer = new PlotWriter(anchor.getPlots(), file);
	  }
	}

  }


  public void addPlotToMenu(final PlotSet plot) {
	JMenuItem item = new JMenuItem(plot.getTitle());
	plot.setMenu(item);
	item.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		CreatePlot frame = new CreatePlot(anchor, plot);
	  }
	});
	jmnuPlots.add(item, jmnuPlots.getItemCount() - 2);
	if (!jmnuPlots_Copy.isEnabled()) {
	  jmnuPlots_Copy.setEnabled(true);
	}
  }


  void jmnuPlots_Copy_actionPerformed(ActionEvent e) {
	PlotCopyDialog diag = new PlotCopyDialog(new JFrame(), "Pick a Plot to copy from", true, anchor);

  }


  void plotChanged() {
	for (int i = 0; i < jmnuPlots.getItemCount(); i++) {
	  JMenuItem item = jmnuPlots.getItem(i);

	}
  }


  void jmiFile_SaveAs_actionPerformed(ActionEvent e) {

//Create a file chooser
	final JFileChooser fc = new JFileChooser();
	fc.setCurrentDirectory(userData.getLastSaveDir());
	fc.setDialogTitle("Save session as: ");
	fc.setFileFilter(new DefaultFileFilter("ccscript", "Matlab Conversion Files"));
	fc.setMultiSelectionEnabled(false);
//In response to a button click:
	int returnVal = fc.showSaveDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	  File file = fc.getSelectedFile();
	  if (file != null) {
		if (!file.getName().endsWith(".ccscript")) {
		  file = new File(file.getParent(), file.getName() + ".ccscript");
		}
		anchor.setSaveFile(file);
		userData.setLastSaveDir(file.getParentFile());
		try {
		  ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		  out.writeObject(anchor);
		  out.close();
		  userData.putRecent(file);
		  anchor.setChanged(false);

		}
		catch (IOException ex) {
		  ex.printStackTrace();
		}

	  }
	}

  }


  void jmiFile_Save_actionPerformed(ActionEvent e) {
	if (anchor.getSaveFile() == null) {
	  jmiFile_SaveAs_actionPerformed(null);
	  return;
	}
	else {
	  try {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(anchor.getSaveFile()));
		out.writeObject(anchor);
		out.close();
		userData.putRecent(anchor.getSaveFile());
		anchor.setChanged(false);
	  }
	  catch (IOException ex) {
		ex.printStackTrace();
	  }

	}
  }


  void jmiFile_Open_actionPerformed(ActionEvent e) {
	final JFileChooser fc = new JFileChooser();
	fc.setCurrentDirectory(userData.getLastOpenDir());
	fc.setDialogTitle("Open session: ");
	fc.setFileFilter(new DefaultFileFilter("ccscript", "Controls Conversion Files"));
	fc.setMultiSelectionEnabled(false);
//In response to a button click:
	int returnVal = fc.showOpenDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	  File file = fc.getSelectedFile();
	  openFile(file);
	}

  }


  void jmiFile_New_actionPerformed(ActionEvent e) {
	if (anchor.isChanged()) {
	  //Custom button text
	  Object[] options = {
		"Yes, save", "No, just create new", "Cancel Action"};
	  int n = JOptionPane.showOptionDialog(new Frame(),
		"There are unsaved changes, would you like to save them before exiting?",
										   "Save your changes?", JOptionPane.YES_NO_CANCEL_OPTION,
										   JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	  if (n == JOptionPane.YES_OPTION) {
		jmiFile_Save_actionPerformed(null);
	  }
	  else if (n == JOptionPane.CANCEL_OPTION) {
		return;
	  }
	}

	anchor.clearForNew();

    this.readList.clear();
    this.writeList.clear();
	jmnuPlots.setEnabled(false);
	jmnuUtility.setEnabled(false);
	jmnu_File_Add_actionPerformed(null);
  }

  void jmiHelp_Tutorial_actionPerformed(ActionEvent e) {
    JFrame frame = new TutorialFrame();
    frame.pack();
    frame.show();
  }


}