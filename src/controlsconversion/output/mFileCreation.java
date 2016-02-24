package controlsconversion.output;


import java.util.ArrayList;
import java.io.*;
import controlsconversion.*;
import java.util.Hashtable;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class mFileCreation {

  File mFile;

  ArrayList plotData = new ArrayList();

  public mFileCreation(ArrayList to, File outputDir, Hashtable allIndx) {

	PrintWriter out;

	mFile = new File(outputDir, "AutoCreate.m");
	try {
	  out = new PrintWriter(new FileWriter(mFile));
	  out.print(getHeaderText());
	  for (int i = 0; i < to.size(); i++) {
        String fileName = (String)to.get(i);
        out.print(getPlotFlags(fileName, (ArrayList)allIndx.get(fileName)));
	  }
      out.print("\n\n\n");

      for(int i = 0; i < plotData.size(); i++){
        out.print(getPlotString((ArrayList)plotData.get(i)));
      }

      out.close();
	}
	catch (IOException ex) {
	  ex.printStackTrace();
	}


  }


  protected String getPlotString(ArrayList plotDataSet){
    String fileName = (String)plotDataSet.get(0);
    String xLabel = (String)plotDataSet.get(1);
    int xVal = ((Integer)plotDataSet.get(2)).intValue();
    String yLabel = (String)plotDataSet.get(3);
    int yVal = ((Integer)plotDataSet.get(4)).intValue();
    String plotName = (String)plotDataSet.get(5);


    StringBuffer buf = new StringBuffer();
    buf.append("if " + plotName + " == ON");
    buf.append('\n');
    buf.append("figure(PLOT_INDEX)");
    buf.append('\n');
    buf.append("data = " + fileName + "(0);");
    buf.append('\n');
    buf.append("plot(data(:,"+ xVal +"), data(:," + yVal +"), 'b--');");
    buf.append('\n');
    buf.append("title('" + yLabel + " versus " + xLabel + "');");
    buf.append('\n');
    buf.append("xlabel('" + xLabel +"');");
    buf.append('\n');
    buf.append("ylabel('" + yLabel + "');");
    buf.append('\n');
    buf.append("grid on");
    buf.append('\n');
    buf.append("PLOT_INDEX = PLOT_INDEX + 1;");
    buf.append('\n');
    buf.append("end\n");
    buf.append("\n\n\n");
    return buf.toString();
  }


  protected String getPlotFlags(String fileName, ArrayList indices) {
    // [fileName, index_X, index_x (int), index_Y, index_Y (int), plotName, ]
    ArrayList plotDataSet = new ArrayList();

	String time = (String) indices.get(1);
    time = time.replaceAll(" ", "_");


	String val = "plot__" + fileName + "__";
	String val2 = "__VS__" + time;
	String plotName;
	StringBuffer buf = new StringBuffer();
    buf.append("%Begin ");
    buf.append(fileName);
    buf.append('\n');


// cant forget the zero based index BS
    for (int i = 2; i < indices.size(); i++) {
      plotDataSet.add(fileName);
      plotDataSet.add(time);
      plotDataSet.add(new Integer(1+1));

      plotName = (String) indices.get(i);
	  plotDataSet.add(plotName);
      plotDataSet.add(new Integer(i+1));
      plotName = val + plotName.replaceAll(" ", "_") + val2;
      plotDataSet.add(plotName);
	  buf.append(plotName);
	  buf.append(" = ON;\n");

      plotData.add(plotDataSet);
      plotDataSet = new ArrayList();
	}
    buf.append("%End ");
    buf.append(fileName);
    buf.append('\n');

    return buf.toString();
  }


  protected String getHeaderText() {
	StringBuffer buf = new StringBuffer();
	buf.append("%Auto generated matlab code for Purdue 364 lab\n");
	buf.append("%If you do not want a plot, then set the value of that plot to zero\n");
	buf.append("%Java conversion utility created by Geoff Granum\n");

	buf.append("\n\n\n");
    buf.append("clc\n");
    buf.append("clear all;\n");
    buf.append("close all;\n");
	buf.append("ON = 1;\n");
	buf.append("OFF = 0;\n");
    buf.append("PLOT_INDEX = 1;\n");


	buf.append("%Plot Flags\n");

	return buf.toString();
  }
  public ArrayList getPlotData() {
    return plotData;
  }


}