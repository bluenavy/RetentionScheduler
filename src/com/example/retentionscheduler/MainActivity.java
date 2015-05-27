/*â€œThis is a course requirement for CS 192 Software Engineering II under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science, College of Engineering, University of the Philippines, Diliman for the AY 2014-2015â€�.
 Neil Jonathan A. Joaquin
 David Relao
 Aldrin Simpao*/
/*Code History:
Initial Code Authored by: Neil Jonathan A. Joaquin, David Relao
Changes made authored by David Relao: added intent functionality, used arraylists, calendarView methods*/
/* File Creation Date: CS 191 days
    Development Group: Blue Navy Inc.
    Client Group: Purple McShort Shorts
    Purpose of file: Main Executable Android File.
*/
package com.example.retentionscheduler;
//import java.util.Date;

//import android.app.Activity;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.annotation.SuppressLint;
//import android.widget.Toast;
import android.content.Intent;
//import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
//import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.view.MenuItem;


@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements OnClickListener {
     CalendarView calendarView;
	private Button button_manage;
	private Button button_viewevents;
	String name = "", dates = "";
	ArrayList<String> titles;
	ArrayList<String> dating;
	DataAccessObject dao = new DataAccessObject(this);

      /*
     Method name: onCreate
     Purpose: Sets the xml. Creates the clickable buttons
     Calling Arguments:Bundle savedInstanceState
     Required Files:
     Database Tables:
     Return value: None
     */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		button_manage = (Button)findViewById(R.id.manage);
		button_manage.setOnClickListener(this);
		button_viewevents = (Button)findViewById(R.id.viewevents);
		button_viewevents.setOnClickListener(this);
		
		final Intent intent = getIntent();
		name = intent.getStringExtra("eventTitle");
		dates = intent.getStringExtra("dates");
		titles = intent.getStringArrayListExtra("titleArray");
		dating = intent.getStringArrayListExtra("dateArray");

		calendarView=(CalendarView) findViewById(R.id.calendarView1);
		calendarView.setOnDateChangeListener(new OnDateChangeListener() {
			long selected = calendarView.getDate();
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {

				String selecteddate="";
				if(selected != view.getDate()){
						selected=view.getDate();
						String temp="";
						String temp1="";
						String temp2="";
						String month1="";
						String day1="";
						String year1="";
						int c=0;
						try{
							String data=dao.readFile("database");
							for(int a=0;a<data.length()-1;a++){
								if(data.charAt(a)!='\n'){
									temp=temp+data.charAt(a);
								}else {
									temp1=dao.readFile(temp);
									for(int b=0;b<temp1.indexOf('\n');b++){
										temp2=temp2+temp1.charAt(b);
									}
									for(int b=0;b<temp2.indexOf('A');b++){
										month1=month1+temp2.charAt(b);
										c=b;
									}
									for(int b=temp2.indexOf('A')+1;b<temp2.indexOf('A',temp2.indexOf('A')+1);b++){
										day1=day1+temp2.charAt(b);
										c=b;
									}
									for(int b=temp2.indexOf('A',temp2.indexOf('A')+1)+1;b<temp2.length();b++){
										year1=year1+temp2.charAt(b);
									}
									if((month+1)==(Integer.parseInt(month1)) && (dayOfMonth)==(Integer.parseInt(day1)) && (year)==(Integer.parseInt(year1))){
										selecteddate=selecteddate+temp+"\n";
									}
									temp="";
									temp2="";
									day1="";
									month1="";
									year1="";
								}
								
							}
							
							
						}catch(Exception e){}
						Intent intent1=new Intent(MainActivity.this, viewInfo.class);
						intent1.putExtra("selecteddate", selecteddate);
						//finish();
						startActivity(intent1);
				}
			}
		});
	}

      /*
     Method name: onClick
     Purpose: Links the clickable buttons to their associated activity
     Calling Arguments: View v
     Required Files:
     Database Tables:
     Return value: None
     */

	public void onClick(View v){
		//respond to click
		if(v.getId()==button_manage.getId()){
			//the button was clicked
			Intent intent = new Intent(this, ManageActivity.class);
			startActivity(intent);
		}
		else if(v.getId()==button_viewevents.getId()) {
			Intent intent = new Intent(this, viewAll.class);
			startActivity(intent);
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}