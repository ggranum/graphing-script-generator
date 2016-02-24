package controlsconversion.plots;

import java.io.Serializable;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class XYLine implements Serializable{


  protected String file;

  protected String xAxis;

  protected String yAxis;

  protected int xIndx = -1;

  protected int yIndx = -1;


  protected String legend = "";

  protected String lineColor;

  protected String lineMarker;

  protected String lineStyle;


  boolean alternateYAxis = false;


  public XYLine() {
  }


  public XYLine(String file, String legend, String xAxis, int xIndx, String yAxis, int yIndx,
				String lineColor, String lineMarker, String lineStyle, boolean alternateYAxis) {
	this.file = file;
	this.legend = legend;
	this.xAxis = xAxis;
	this.xIndx = xIndx;
	this.yAxis = yAxis;
	this.yIndx = yIndx;
	this.lineColor = lineColor;
	this.lineMarker = lineMarker;
	this.lineStyle = lineStyle;
	this.alternateYAxis = alternateYAxis;
  }


  public XYLine copyXYLine(){
    return new XYLine(file, legend, xAxis, xIndx, yAxis, yIndx, lineColor, lineMarker, lineStyle, alternateYAxis);
  }


  public String toString() {
	StringBuffer buf = new StringBuffer(legend);
	buf.append("  ");
	buf.append(lineColor);
	buf.append(lineStyle);
	buf.append(lineMarker);
	return buf.toString();
  }


  public int getXIndx() {
	return this.xIndx;
  }


  public int getYIndx() {
	return this.yIndx;
  }

  public void setXIndx(int xIndx) {
    this.xIndx = xIndx;
  }


  public void setYIndx(int yIndx) {
    this.yIndx = yIndx;
  }


  public boolean isAlternateYAxis() {
	return alternateYAxis;
  }


  public String getLegend() {
	return legend;
  }


  public String getLineColor() {
	return lineColor;
  }


  public String getLineMarker() {
	return lineMarker;
  }


  public String getLineStyle() {
	return lineStyle;
  }


  public String getXAxis() {
	return xAxis;
  }


  public String getYAxis() {
	return yAxis;
  }


  public void setYAxis(String yAxis) {
	this.yAxis = yAxis;
  }


  public void setXAxis(String xAxis) {
	this.xAxis = xAxis;
  }


  public void setLineStyle(String lineStyle) {
	this.lineStyle = lineStyle;
  }


  public void setLineMarker(String lineMarker) {
	this.lineMarker = lineMarker;
  }


  public void setLineColor(String lineColor) {
	this.lineColor = lineColor;
  }


  public void setLegend(String legend) {
	this.legend = legend;
  }


  public void setAlternateYAxis(boolean alternateYAxis) {
	this.alternateYAxis = alternateYAxis;
  }


  public String getFile() {
	return file;
  }


  public void setFile(String file) {
	this.file = file;
  }


}