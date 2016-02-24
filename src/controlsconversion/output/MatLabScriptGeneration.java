package controlsconversion.output;

import controlsconversion.plots.PlotSet;
import java.util.ArrayList;
import controlsconversion.plots.XYLine;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class MatLabScriptGeneration {
  public MatLabScriptGeneration() {
  }


 public static String getMatlabScript(PlotSet set, int figureNumber) {
   StringBuffer buf = new StringBuffer();
   buf.append("\n%Plot auto-created by 364L Controls conversion code written by Geoff Granum\n");
   buf.append("figure(" + figureNumber + ")\n");
   buf.append("hold on;\n");
   buf.append("title('" + set.getTitle() + "')\n");
   buf.append("yLimit_a = 0;\n");
   buf.append("yLimit_b = 0;\n");
   int hc = 0; // handleCount
   ArrayList yLines = set.getYLines();

   // This for loop writes a function to find the limits on the graph window
   for (int i = 0; i < yLines.size(); i++) {
     XYLine plot = (XYLine) yLines.get(i);
     int hcA = hc++;
     buf.append("data = " + plot.getFile() + "(0);\n");
     buf.append("x_" + hcA + " = data(:," + (plot.getXIndx()+1) + ");\n");
     buf.append("y_" + hcA + " = data(:," + (plot.getYIndx()+1) + ");\n");
     buf.append("maxVal_a = max(y_" + hcA + ");\n\n");
     buf.append("if maxVal_a > yLimit_a\n");
     buf.append("yLimit_a = maxVal_a;\n");
     buf.append("end\n\n");
     if (i + 1 < yLines.size() && ( (XYLine) yLines.get(i + 1)).isAlternateYAxis()) {
       XYLine altPlot = (XYLine) yLines.get(++i);
       int hcB = hc++;
       buf.append("x_" + hcB + " = data(:," + (altPlot.getXIndx()+1) + ");\n");
       buf.append("y_" + hcB + " = data(:," + (altPlot.getYIndx()+1) + ");\n");
       buf.append("maxVal_b = max(y_" + hcB + ");\n");
       buf.append("if maxVal_b > yLimit_b\n");
       buf.append("yLimit_b = maxVal_b;\n");
       buf.append("end\n");
     }
   }
   buf.append("if(yLimit_a == 0)\n");
   buf.append("    yLimit_a = 1;\n");
   buf.append("end\n");
   buf.append("if(yLimit_b == 0)\n");
   buf.append("    yLimit_b = 1;\n");
   buf.append("end\n");
hc = 0;
   for (int i = 0; i < yLines.size(); i++) {
     XYLine plot = (XYLine) yLines.get(i);

     String xStrA = "x_" + hc;
     String yStrA = "y_" + hc;
     int hcA = hc++;

     if (i + 1 < yLines.size() && ( (XYLine) yLines.get(i + 1)).isAlternateYAxis()) {
       String xStrB = "x_" + hc;
       String yStrB = "y_" + hc;
       int hcB = hc++;

       XYLine altPlot = (XYLine)yLines.get(++i);
       buf.append("[AX,H" + (hcA) + ",H" + (hcB) + "] = plotyy(" + xStrA + ',' + yStrA + ',' +
                  xStrB + ',' + yStrB + ",'plot');\n");
       buf.append("set(H" + (hcA) + ", 'LineStyle' , ' " + plot.getLineStyle() + "')\n");
       buf.append("set(H" + (hcB) + ", 'LineStyle' , '" + altPlot.getLineStyle() + "')\n");
       buf.append("set(H" + (hcA) + ", 'color','" + plot.getLineColor() + "')\n");
       buf.append("set(H" + (hcB) + ", 'color','" + altPlot.getLineColor() + "')\n");
       buf.append("\n");

     }
     else {
       // no secondary y axis
       // plot(x_i, y_i_a);
       buf.append("H" + (hcA) + " = plot(" + xStrA + ", " + yStrA + ", '" + plot.getLineColor() +
                  plot.getLineMarker() + plot.getLineStyle() + "');\n");
       buf.append("\n");

     }

   }
   buf.append("set(get(AX(1),'Ylabel'),'String','" + set.getYLabel() + "');\n");
   buf.append("set(AX(1), 'ylim', [-yLimit_a, yLimit_a]);\n");
   buf.append("set(AX(1), 'ygrid', 'on');\n");
   buf.append("set(AX(1), 'xgrid', 'on');\n");
   buf.append("set(AX(1), 'ytick', [-yLimit_a:yLimit_a/5:yLimit_a]);\n");

   buf.append("set(get(AX(2),'Ylabel'),'String','" + set.getAltYLabel() + "');\n");
   buf.append("set(AX(2), 'ylim', [-yLimit_b, yLimit_b]);\n");
   buf.append("set(AX(2), 'ytick', [-yLimit_b:yLimit_b/5:yLimit_b]);\n");
   buf.append("set(AX(2), 'ygrid', 'on');\n");
   buf.append("set(AX(2), 'xgrid', 'on');\n");
   buf.append(getLegend(set, hc));
   buf.append("print( gcf, '-djpeg', strcat('" + set.getTitle().replaceAll(" ", "_") + "', '.jpg'))");

   return buf.toString();
 }


 protected static String getLegend(PlotSet set, int handleCount) {
   StringBuffer buf = new StringBuffer();
   ArrayList yLines = set.getYLines();
   int hc = 0;
     buf.append("legend([H0");
     while (hc < handleCount) {
       buf.append(" , H" + hc++);
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



}