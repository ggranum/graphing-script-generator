package controlsconversion;


import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import controlsconversion.plots.PlotSet;
import java.io.Serializable;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class Anchor implements Serializable {

  protected boolean changed = false;

  protected ArrayList inputFiles = new ArrayList();

  protected ArrayList outputFiles = new ArrayList();

  protected ArrayList outputFileNames = new ArrayList();

  protected Hashtable allIndices = new Hashtable();

  protected File saveFile = null;

  protected transient FrameMain main;


  protected ArrayList plots = new ArrayList();


  public Anchor(FrameMain main) {
	this.main = main;
  }


  public Anchor(ArrayList inputFiles, ArrayList outputFiles, Hashtable allIndices) {
	this.changed = true;
    this.inputFiles = inputFiles;
	this.outputFiles = outputFiles;
	this.allIndices = allIndices;

  }


  public void addPlotSet(PlotSet plot) {
	this.changed = true;
    main.addPlotToMenu(plot);
	this.plots.add(plot);
  }


  public PlotSet[] getPlots() {
	PlotSet[] plotsAry = new PlotSet[plots.size()];
	for (int i = 0; i < plotsAry.length; i++) {
	  plotsAry[i] = (PlotSet) plots.get(i);
	}
	return plotsAry;
  }

  public void clearForNew(){
    changed = false;
     inputFiles.clear();
     outputFiles.clear();
     outputFileNames.clear();
     allIndices.clear();
     saveFile = null;
     plots.clear();
  }


  public File getSaveFile() {
	return saveFile;
  }


  public void setSaveFile(File saveFile) {
	this.changed = true;
    this.saveFile = saveFile;
  }


  public FrameMain getMain() {
	return main;
  }


  public void setMain(FrameMain main) {
	this.changed = true;
    this.main = main;
  }


  public Hashtable getAllIndices() {
	return allIndices;
  }


  public ArrayList getInputFiles() {
	return inputFiles;
  }

  public ArrayList getOutputFileNames() {
	return outputFileNames;
  }


  public ArrayList getOutputFiles() {
	return outputFiles;
  }


  public void setOutputFiles(ArrayList outputFiles) {
    this.changed = true;
	this.outputFiles = outputFiles;
  }


  public void setOutputFileNames(ArrayList outputFileNames) {
    this.changed = true;
	this.outputFileNames = outputFileNames;
  }



  public void setInputFiles(ArrayList inputFiles) {
    this.changed = true;
	this.inputFiles = inputFiles;
  }


  public void setAllIndices(Hashtable allIndices) {
	this.allIndices = allIndices;
  }
  public boolean isChanged() {
    return changed;
  }
  public void setChanged(boolean changed) {
    this.changed = changed;
  }


}