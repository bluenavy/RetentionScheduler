/*â€œThis is a course requirement for CS 192 Software Engineering II under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science, College of Engineering, University of the Philippines, Diliman for the AY 2014-2015â€�.
 Neil Jonathan A. Joaquin
 David Relao
 Aldrin Simpao*/
/*Code History:
Initial Code Authored by: Neil Jonathan A. Joaquin, David Relao
Changes made authored by David Relao: added methods, used arraylists, edited readFile()*/
/* File Creation Date: CS 191 days
    Development Group: Blue Navy Inc.
    Client Group:
    Purpose of file: Object that writes and reads from a text file
*/
package com.example.retentionscheduler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;
//import android.util.Log;
import android.widget.EditText;
//import android.util.Log;

public class DataAccessObject{
	
     static Context fileContext;
     private static ArrayList<String> titles = new ArrayList<String>();
 	private static ArrayList<String> dating = new ArrayList<String>();
	
	DataAccessObject (Context fileContext) {
		DataAccessObject.fileContext = fileContext;
	}
	
	 /*
     Method name: onClick
     Purpose: Creates the clickable buttons
     Calling Arguments:
     Required Files:
     Database Tables:
     Return value: None
     */

	public String readFile(String name) throws FileNotFoundException{
		InputStream inputStream = fileContext.openFileInput(name+".txt");
		String current="";

		try {
			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();
				while (((receiveString = bufferedReader.readLine()) != null)) {
					stringBuilder.append(receiveString+"\n");
				}
				inputStream.close();
				current = stringBuilder.toString();
			}
		}
		catch(Exception e){
			return "error";
		}
		current += "\n";
		return current;
	}

     /*
     Method name: writeFile
     Purpose: Writes the data on a textfile
     Calling Arguments:EditTex
     Required Files:
     Database Tables:
     Return value: None
     */

	public String writeFile(String name, String description, String date, String time) {
		try {
			File file = fileContext.getFileStreamPath("database.txt");
			if(!file.exists()){
				FileOutputStream fs1 = fileContext.openFileOutput("database.txt", Context.MODE_PRIVATE);
				OutputStreamWriter myOutWriter1 = new OutputStreamWriter(fs1);
			
				myOutWriter1.append(name);
				myOutWriter1.append("\n");
				myOutWriter1.close();
				fs1.close();
			}else{	
				FileOutputStream fs1 = new FileOutputStream(file, true);
				OutputStreamWriter myOutWriter1 = new OutputStreamWriter(fs1);
			
				String str = readFile("database");
				if (!str.toLowerCase().contains(name.toLowerCase())) {
					myOutWriter1.append(name);
					myOutWriter1.append("\n");
				}
				myOutWriter1.close();
				fs1.close();
			}
			FileOutputStream fos = fileContext.openFileOutput(name+".txt", Context.MODE_PRIVATE);
			OutputStreamWriter myOutWriter = new OutputStreamWriter(fos);
				
			myOutWriter.append(date);
			myOutWriter.append("\n");
			
			myOutWriter.append(name);
			myOutWriter.append("\n");
	
			myOutWriter.append(description);
			myOutWriter.append("\n");
	
			myOutWriter.append(time);
			myOutWriter.append("\n");		
				
			myOutWriter.close();
			fos.close();
			System.out.println("Write SUCCESS!");	
		}
		catch (Exception e) {

		};
		
		
		return date;
	}
}


