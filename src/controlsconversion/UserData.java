package controlsconversion;

import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class UserData implements Serializable{

  protected File[] recent = new File[4];
  protected File lastSaveDir;
  protected File lastOpenDir;
  protected File lastOutputDir;



  public UserData() {
    this.lastSaveDir = new File(System.getProperty("user.home"));
    this.lastOpenDir = lastSaveDir;
  }
  public File getLastOpenDir() {
    return lastOpenDir;
  }
  public File getLastSaveDir() {
    return lastSaveDir;
  }
  public void setLastSaveDir(File lastSaveDir) {
    this.lastSaveDir = lastSaveDir;
  }
  public void setLastOpenDir(File lastOpenDir) {
    this.lastOpenDir = lastOpenDir;
  }


  public File[] getRecent() {
    return recent;
  }

  public void putRecent(File file){
    for (int i = 0; i < recent.length; i++) {
      if(recent[i] == null){
		recent[i] = file;
		return;
	  }
      else if(recent[i] == file){
        return;
      }
    }
    for (int i = recent.length - 1; i < 1; i--) {
      recent[i] = recent[i-1];
    }
    recent[0] = file;
  }
  public File getLastOutputDir() {
    return lastOutputDir;
  }
  public void setLastOutputDir(File lastOutputDir) {
    this.lastOutputDir = lastOutputDir;
  }






}