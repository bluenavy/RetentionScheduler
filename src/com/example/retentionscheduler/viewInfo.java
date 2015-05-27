/*â€œThis is a course requirement for CS 192 Software Engineering II under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science, College of Engineering, University of the Philippines, Diliman for the AY 2014-2015â€�.
 Neil Jonathan A. Joaquin
 David Relao
 Aldrin Simpao*/
/*Code History:
Initial Code Authored by: Neil Jonathan A. Joaquin, David Relao
Changes made authored by David Relao: added intent functionality, used arraylists, dao implementations*/
/* File Creation Date: March 13, 2015
    Development Group: Blue Navy Inc.
    Client Group:
    Purpose of file: Displays the event you created
*/
package com.example.retentionscheduler;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
//import android.widget.TextView;
import android.widget.TextView;

public class viewInfo extends ActionBarActivity{

	DataAccessObject dao = new DataAccessObject(this);
     String name = "", month, dayOfMonth, year, dates;
     Button button;
     ArrayList<String> titles;
 	ArrayList<String> dating;

      /*
     Method name: onCreate
     Purpose: Sets the xml. Creates the clickable buttons
     Calling Arguments:Bundle savedInstanceState
     Required Files:
     Database Tables:
     Return value: None
     */
	
	protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.event_information);
          TextView view = (TextView) findViewById(R.id.info);
          try {
	          Intent intent = getIntent();
	          name = intent.getStringExtra("selecteddate");

	  	     if (name.length() > 0) {

	  	     	String temp="";
	  	     	String temp1="";
	  	     	String temp2="";
	  	     	for(int a=0;a<name.length();a++){
	  	     		if(name.charAt(a)!='\n'){
	  	     			temp=temp+name.charAt(a);
	  	     		}else{
	  	     			System.out.println(temp+""+a);
	  	     			temp1=temp1+dao.readFile(temp);
	  	     			for(int b=temp1.indexOf('\n')+1;b<temp1.length();b++){
	  	     				temp2=temp2+temp1.charAt(b);
	  	     			}
	  	     			temp="";
	  	     			temp1="";
	  	     		}
	  	     	}
	  	     	view.setText(temp2);
			}else{
				view.setText("***NO EVENT TO BE VIEWED***\n");
			}

          }
          catch (Exception e) {};
          view.setMovementMethod(new ScrollingMovementMethod());
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
