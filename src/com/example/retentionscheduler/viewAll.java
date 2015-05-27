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

import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
//import android.widget.TextView;
import android.widget.TextView;

public class viewAll extends ActionBarActivity implements OnClickListener{

	DataAccessObject dao = new DataAccessObject(this);
     String name = "", month, dayOfMonth, year, dates;
     Button button;
     final Context context = this;
     Color color;
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
	          String data = dao.readFile("database");

	  	     if (data.length() > 0) {

	  	     	SpannableString str = new SpannableString(data);
	  	     	String[] state = data.split("\n");
	  	     	LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
	  			for (int i = 0; i < state.length; i++) {
	  				final String word = state[i];
	  			        Button btnTag = new Button(this);
	  			        btnTag.setText(state[i]);
	  			        btnTag.setId(i);
	  			        btnTag.setBackgroundColor(Color.parseColor("#81dedc"));
	  			        //btnTag.setHeight(150);
	  			        layout.addView(btnTag);
	  			        ((Button)findViewById(i)).setOnClickListener(new OnClickListener()
	  			      {
	  			     	     @Override
	  			     	     public void onClick(View v) {
	  			     	     	Dialog dialog = new Dialog(context);
		  	     				dialog.setContentView(R.layout.view_all);
		  	     				dialog.setTitle(word);
								try {
									TextView text = (TextView) dialog.findViewById(R.id.info1);
									String data1 = dao.readFile(word);
									String[] state1 = data1.split("\n");
			  	     				StringBuilder strB = new StringBuilder();
			  	     				for (int i=0; i<state1.length; i++) {
			  	     					if(i!=1){
				  	     					if (i==0) {
				  	     						String newState = state1[i].replaceAll("A", "/");
				  	     						strB.append(newState);
				  	     						strB.append("\n");
				  	     					}
				  	     					else {
				  	     						strB.append(state1[i]);
					  	     					strB.append("\n");
				  	     					}
			  	     					}
			  	     				}
			  	     				text.setText(strB.toString());
								} catch (FileNotFoundException e) {}
								dialog.show();
	  			     	     }
	  			     	}
	  			    );
	  			}
	  	     	/*
	  	     	for (final String word : state) {
	  	     		ClickableSpan clickableSpan = new ClickableSpan() {
	  	     			@Override
	  	     			public void onClick(View textView) {
	  	     				Dialog dialog = new Dialog(context);
	  	     				dialog.setContentView(R.layout.view_all);
	  	     				dialog.setTitle(word);
							try {
								TextView text = (TextView) dialog.findViewById(R.id.info1);
								String data1 = dao.readFile(word);
								String[] state1 = data1.split("\n");
		  	     				StringBuilder strB = new StringBuilder();
		  	     				for (int i=0; i<state1.length; i++) {
		  	     					if (i==0) {
		  	     						String newState = state1[i].replaceAll("A", "/");
		  	     						strB.append(newState);
		  	     						strB.append("\n");
		  	     					}
		  	     					else {
		  	     						strB.append(state1[i]);
			  	     					strB.append("\n");
		  	     					}
		  	     				}
		  	     				text.setText(strB.toString());
							} catch (FileNotFoundException e) {}
							dialog.show();
	  	     			}
	  	     		};
	  	     		str.setSpan(clickableSpan, data.indexOf(word), data.indexOf(word) + word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	  	     	}*/
	  	     	//view.setText(str);
				//view.setMovementMethod(LinkMovementMethod.getInstance());
			}else{
				view.setText("***NO EVENT TO BE VIEWED***\n");
			}

          }
          catch (Exception e) {};
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
