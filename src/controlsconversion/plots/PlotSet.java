package controlsconversion.plots;


import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class PlotSet implements Serializable {


  protected String title;

  protected String xLabel;

  protected String yLabel;

  protected String altYLabel;

  protected ArrayList yLines = new ArrayList();

  //protected LinkedList altYLines = new LinkedList();


  protected boolean usesAlternateAxis = false;

  protected transient JMenuItem menuItem;


  public PlotSet(String title, String xLabel, String yLabel, String altYLabel) {
	this.title = title;
	this.xLabel = xLabel;
	this.yLabel = yLabel;
	this.altYLabel = altYLabel;
  }


  public PlotSet(String title, String xLabel, String yLabel, String altYLabel,
				 ArrayList xyLinePlots, boolean usesAlternateAxis) {
	this.title = title;
	this.xLabel = xLabel;
	this.yLabel = yLabel;
	this.altYLabel = altYLabel;
	this.yLines = xyLinePlots;
	this.usesAlternateAxis = usesAlternateAxis;
  }


  public PlotSet copyPlot() {
	PlotSet set = new PlotSet(title, xLabel, yLabel, altYLabel, new ArrayList (),
							  usesAlternateAxis);
	for (int i = 0; i < yLines.size(); i++) {
	  set.addLinePlot( ( (XYLine) yLines.get(i)).copyXYLine());
	}
	return set;
  }


  public String toString() {
	return this.title;
  }


  /* Sample matlab code
   %Plot auto-created by 364L Controls conversion code written by Geoff Granum
   figure(1)
   hold on;
   titleVal = 'PIV Step Input Kp = 0.0898, Kd = 0.0152';
   title(titleVal)
   yLimit_a = 0;yLimit_b = 0;data = stepkp0898kd0152(0);
   x_0 = data(:,2);
   y_0_a = data(:,4);
   maxVal = max(y_0_a);
   if maxVal > yLimit_a
   yLimit_a = maxVal;
   end
   y_0_b = data(:,6);
   maxVal = max(y_0_b);
   if maxVal > yLimit_b
   yLimit_b = maxVal;
   end
   [AX,H1,H2] = plotyy(x_0,y_0_a,x_0,y_0_b,'plot');
   set(get(AX(1),'Ylabel'),'String','Encoder 1 Pos (Counts)');
   set(get(AX(2),'Ylabel'),'String','Control Effort (Volts)');
   set(H1, 'LineStyle' , ' -')
   set(H2, 'LineStyle' , ':')
   set(H1, 'color','k')
   set(H2, 'color','b')
   set(AX(1), 'ylim', [-yLimit_a, yLimit_a]);
   set(AX(2), 'ylim', [-yLimit_b, yLimit_b]);
   set(AX(1), 'ytick', [-yLimit_a:yLimit_a/5:yLimit_a]);
   set(AX(2), 'ytick', [-yLimit_b:yLimit_b/5:yLimit_b]);
   set(AX(1), 'ygrid', 'on');
   set(AX(1), 'xgrid', 'on');
   set(AX(2), 'ygrid', 'on');
   set(AX(2), 'xgrid', 'on');
   legend([H1, H2], 'Encoder 1 Pos', 'Control Effort');
   print( gcf, '-djpeg', strcat(titleVal, '.jpg'))
   */

/*
  public String getMatlabScript(int figureNumber) {
	if (usesAlternateAxis) {
	  return getMatlabScriptAltY(figureNumber);
	}

	StringBuffer buf = new StringBuffer();

	buf.append(" %Plot auto-created by 364L Controls conversion code written by Geoff Granum\n");
	buf.append("   figure(" + figureNumber + ")\n");
	buf.append("   hold on;\n");
	buf.append("   titleVal = '" + title + "';\n");
	buf.append("   title(titleVal)\n");

	for (int i = 0; i < yLines.size(); i++) {
	  XYLine plot = (XYLine) yLines.get(i);
	  buf.append("data = " + plot.getFile() + "(0);\n");
	  buf.append("x_" + i + " = data(:," + plot.getXIndx() + ");\n");
	  buf.append("y_" + i + "_a = data(:," + plot.getYIndx() + ");\n");
	  buf.append("maxVal = max(y_" + i + "_a);\n\n");
	  buf.append("if maxVal > yLimit_a\n");
	  buf.append("yLimit_a = maxVal;\n");
	  buf.append("end\n\n");
	}
	for (int i = 0; i < yLines.size(); i++) {
	  XYLine plot = (XYLine) yLines.get(i);
	  String xStr = "x_" + i;
	  String yStrA = "y_" + i + "_a";
	  String yStrB = "y_" + i + "_b";

	  // no secondary y axis
	  // plot(x_i, y_i_a);
	  buf.append("plot(" + xStr + ", " + yStrA + ", '" + plot.getLineColor() + plot.getLineMarker() +
				 plot.getLineStyle() + "');\n");

	}
	buf.append("\n\n\n");
	buf.append(getLegend(0));
	return buf.toString();
  }
*/

/*
  public String getMatlabScriptAltY(int figureNumber) {
	StringBuffer buf = new StringBuffer();
	buf.append("\n%Plot auto-created by 364L Controls conversion code written by Geoff Granum\n");
	buf.append("figure(" + figureNumber + ")\n");
	buf.append("hold on;\n");
	buf.append("title('" + title + "')\n");
	buf.append("yLimit_a = 0;\n");
	buf.append("yLimit_b = 0;\n");
	int hc = 0; // handleCount

	// This for loop writes a function to find the limits on the graph window
	for (int i = 0; i < yLines.size(); i++) {
	  XYLine plot = (XYLine) yLines.get(i);
	  buf.append("data = " + plot.getFile() + "(0);\n");
	  buf.append("x_" + i + " = data(:," + plot.getXIndx() + ");\n");
	  buf.append("y_" + i + "_a = data(:," + plot.getYIndx() + ");\n");
	  buf.append("maxVal = max(y_" + i + "_a);\n\n");
	  buf.append("if maxVal > yLimit_a\n");
	  buf.append("yLimit_a = maxVal;\n");
	  buf.append("end\n\n");
	  if (i + 1 < yLines.size() && ( (XYLine) yLines.get(i + 1)).isAlternateYAxis()) {
		XYLine altPlot = (XYLine) yLines.get(++i);
		buf.append("x_" + i + "_b = data(:," + altPlot.getXIndx() + ");\n");
		buf.append("y_" + i + "_b = data(:," + altPlot.getYIndx() + ");\n");
		buf.append("maxVal = max(y_" + i + "_b);\n");
		buf.append("if maxVal > yLimit_b\n");
		buf.append("yLimit_b = maxVal;\n");
		buf.append("end\n");
	  }
	}
	buf.append("if(yLimit_a == 0)\n");
	buf.append("    yLimit_a = 1;\n");
	buf.append("end\n");
	buf.append("if(yLimit_b == 0)\n");
	buf.append("    yLimit_b = 1;\n");
	buf.append("end\n");

	for (int i = 0; i < yLines.size(); i++) {
	  XYLine plot = (XYLine) yLines.get(i);

	  String xStr = "x_" + i;
	  String yStrA = "y_" + i + "_a";
	  String yStrB = "y_" + i + "_b";
	  if (i + 1 < yLines.size() && ( (XYLine) yLines.get(i + 1)).isAlternateYAxis()) {
        XYLine altPlot = (XYLine)yLines.get(++i);
        buf.append("[AX,H" + (hc++) + ",H" + (hc++) + "] = plotyy(" + xStr + ',' + yStrA + ',' +
				   xStr + ',' + yStrB + ",'plot');\n");
		buf.append("set(H" + (hc - 2) + ", 'LineStyle' , ' " + plot.getLineStyle() + "')\n");
		buf.append("set(H" + (hc - 1) + ", 'LineStyle' , '" + altPlot.getLineStyle() + "')\n");
		buf.append("set(H" + (hc - 2) + ", 'color','" + plot.getLineColor() + "')\n");
		buf.append("set(H" + (hc - 1) + ", 'color','" + altPlot.getLineColor() + "')\n");
		buf.append("\n");

	  }
	  else {
		// no secondary y axis
		// plot(x_i, y_i_a);
		buf.append("H" + (hc++) + " = plot(" + xStr + ", " + yStrA + ", '" + plot.getLineColor() +
				   plot.getLineMarker() + plot.getLineStyle() + "');\n");
		buf.append("\n\n\n");

	  }

	}
	buf.append("set(get(AX(1),'Ylabel'),'String','" + this.yLabel + "');\n");
	buf.append("set(get(AX(2),'Ylabel'),'String','" + this.altYLabel + "');\n");
	buf.append("set(AX(1), 'ylim', [-yLimit_a, yLimit_a]);\n");
	buf.append("set(AX(2), 'ylim', [-yLimit_b, yLimit_b]);\n");
	buf.append("set(AX(1), 'ytick', [-yLimit_a:yLimit_a/5:yLimit_a]);\n");
	buf.append("set(AX(2), 'ytick', [-yLimit_b:yLimit_b/5:yLimit_b]);\n");
	buf.append("set(AX(1), 'ygrid', 'on');\n");
	buf.append("set(AX(1), 'xgrid', 'on');\n");
	buf.append("set(AX(2), 'ygrid', 'on');\n");
	buf.append("set(AX(2), 'xgrid', 'on');\n");
	buf.append(getLegend(hc));
	buf.append("print( gcf, '-djpeg', strcat('" + title + "', '.jpg'))");

	return buf.toString();
  }


  protected String getLegend(int handleCount) {
	StringBuffer buf = new StringBuffer();
	int hc = 0;
	  buf.append("legend([H0");
	  while (hc++ < handleCount) {
		buf.append(" , H" + hc);
	  }
	  buf.append("]");
	  for (int i = 0; i < yLines.size(); i++) {
		buf.append(",");
		buf.append('\'');
		buf.append( ( (XYLine) yLines.get(i)).getLegend());
		buf.append('\'');

	  }

	buf.append(");\n");
	return buf.toString();
  }

*/
  public void addLinePlot(XYLine linePlot) {
	yLines.add(linePlot);
  }


  public String getTitle() {
	return title;
  }


  public String getXLabel() {
	return xLabel;
  }


  public String getYLabel() {
	return yLabel;
  }


  public String getAltYLabel() {
	return altYLabel;
  }


  public ArrayList getPlots() {
	return yLines;
  }


  public void setTitle(String title) {
	this.title = title;
	if (menuItem != null) {
	  menuItem.setText(title);
	}
  }


  public JMenuItem getMenu() {
	return this.menuItem;
  }


  public void setUsesAlternateAxis(boolean altYAxis) {
	this.usesAlternateAxis = altYAxis;
  }




  public void setXLabel(String xLabel) {
	this.xLabel = xLabel;
  }


  public void setYLabel(String yLabel) {
	this.yLabel = yLabel;
  }


  public void setAltYLabel(String altYLabel) {
	this.altYLabel = altYLabel;
  }


  public void setMenu(JMenuItem menuItem) {
	this.menuItem = menuItem;
  }
  public boolean isUsesAlternateAxis() {
    return usesAlternateAxis;
  }

  public ArrayList getYLines(){
    return this.yLines;
  }

  public void setYLines(ArrayList yLines){
    this.yLines = yLines;
  }


}
