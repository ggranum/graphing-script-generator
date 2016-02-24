package controlsconversion.io;

import java.io.File;
import controlsconversion.plots.PlotSet;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.*;
import controlsconversion.output.MatLabScriptGeneration;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class PlotWriter {
  public PlotWriter() {
  }

  public PlotWriter(PlotSet[] plots, File outFile){
	try {
	  PrintWriter out = new PrintWriter(new FileWriter(outFile));
      out.println(getHeaderText());
      for (int i = 0; i < plots.length; i++) {
        out.println(MatLabScriptGeneration.getMatlabScript(plots[i], i+1));
      }



      out.close();
	}
	catch (IOException ex) {
      ex.printStackTrace();
	}

  }


  protected String getHeaderText() {
  StringBuffer buf = new StringBuffer();
  buf.append("%Auto generated matlab code for Purdue 364 lab\n");
  buf.append("%Java conversion utility created by Geoff Granum\n");

  buf.append("\n\n\n");
  buf.append("clc\n");
  buf.append("clear all;\n");
  buf.append("close all;\n");
buf.append("\n\n\n\n");

  return buf.toString();
}


}