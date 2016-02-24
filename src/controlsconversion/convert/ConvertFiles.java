package controlsconversion.convert;


import java.util.ArrayList;
import java.io.File;
import java.io.*;
import controlsconversion.FrameMain;
import java.util.Hashtable;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Geoff Granum
 * @version 1.0
 */

public class ConvertFiles {



  public ConvertFiles(ArrayList from, ArrayList to, File outputDir, FrameMain main) {
	String outPath = outputDir.getPath();
    ArrayList indices;
    Hashtable allIndx = new Hashtable();
    int val = 0;
    for (int i = 0; i < from.size(); i++) {
      indices = writeFile((File)from.get(i), (String)to.get(i) + ".m", outPath);
      if(indices != null){
        allIndx.put(to.get(i), indices);
      }
    }
    main.doneConverting(allIndx);
  }

  public static void writeFiles(ArrayList from, ArrayList to, File outputDir){
	String outPath = outputDir.getPath();

	for (int i = 0; i < from.size(); i++) {
	  writeFile( (File) from.get(i), (String) to.get(i) + ".m", outPath);
	}

  }

  public static ArrayList writeFile(File from, String to, String outPath) {
	try {
      ArrayList indices = new ArrayList();
	  BufferedReader in = new BufferedReader(new FileReader(from));
	  PrintWriter out = new PrintWriter(new FileWriter(outPath + File.separator + to));
	  String line;
	  out.println("function x = " + to.substring(0, to.indexOf('.') - 1) + "(input)");

	  out.println("if input == 1");
	  out.print(" x = {");

	  // get top deal: e.g.
	  //  Sample   Time     Commanded Pos   Encoder 1 Pos   Encoder 3 Pos   Control Effort
	  // assume each block is seperated by at least TWO spaces
	  String[] index = null;
	  while ( (line = in.readLine()) != null) {
		if (!line.trim().equals("")) {
		  index = line.split("  ");
		  break;
		}
	  }
	  if (index == null) {
		return null;
	  }
	  for (int i = 0; i < index.length; i++) {
        line = index[i].trim();
        if(!line.equals("")){
		  out.print("'" + line + "' ");
          indices.add(line);
		}
	  }
	  out.println("};");
      out.println("x = char(x);");
      out.println("end\n");

	  while ( (line = in.readLine()) != null) {
		if (!line.trim().equals("")) {
		  break;
		}
	  }

	  out.println(" x = " + line);

	  while ( (line = in.readLine()) != null) {
		if (line.trim().endsWith("]")) {
		  out.println(line + ';');
		}
		else {
		  out.println(line);
		}

	  }
      out.close();
      in.close();
      return indices;

	}
	catch (Exception ex) {
	  ex.printStackTrace();
	}
    return null;
  }

}