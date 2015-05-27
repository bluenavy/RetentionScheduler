/*This is a course requirement for CS 192 Software Engineering II under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science, College of Engineering, University of the Philippines, Diliman for the AY 2014-2015â€�.
 Neil Jonathan A. Joaquin
 David Relao*/

/*Code History:
Initial Code Authored by: Neil Jonathan A. Joaquin
Changes made authored by David Relao: edited the time format into non-military*/

/* File Creation Date: CS 191 days
    Development Group: Blue Navy Inc.
    Client Group: Purple McShort Shorts
    Purpose of file: Creates the Event, now with Date and Time picker, and save it in a text file.
*/
package com.example.retentionscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TimePicker;
import android.widget.Toast;

public class Create1 extends ActionBarActivity implements OnClickListener {
	CalendarView calendarView;
	TimePicker timePicker;
	String name = "",description="";
	String time="";
	String calendar="";
	Button button;
	DataAccessObject dao = new DataAccessObject(this);
	Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	/*
     Method name: onCreate
     Purpose: Sets the xml. Creates the view format of this activity
     Calling Arguments: Bundle savedInstanceState
     Required Files:
     Database Tables:
     Return value: None
     */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create1);
		
		final Intent intent = getIntent();
		name = intent.getStringExtra("eventTitle");
		description = intent.getStringExtra("description");
		button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(this);
		
		calendarView=(CalendarView) findViewById(R.id.calendarView1);
		calendarView.setDate(Calendar.getInstance().getTimeInMillis(),false,true);
		calendarView.setOnDateChangeListener(new OnDateChangeListener() {
			long selected = calendarView.getDate();
			long something = selected;
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				
				if(selected != view.getDate()) {
						
						selected=view.getDate();
						
						if (something <= selected) {
							month=month+1;
							//Toast.makeText(getApplicationContext(), something+" ? "+selected, Toast.LENGTH_LONG).show();
							calendar=month+"A"+dayOfMonth+"A"+year;
						}
						else {
							calendar = "none";
						}
				}
			}
		});
		
		DateFormat dateFormat1 = new SimpleDateFormat("MM");
		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String month = (dateFormat1.format(date)); 
		String day = (dateFormat2.format(date));
		String year = (dateFormat3.format(date));
		if(month.charAt(0)=='0'){
			month=""+month.charAt(1);
		}
		if(day.charAt(0)=='0'){
			day=""+day.charAt(1);
		}
		calendar=month+"A"+day+"A"+year;
		System.out.println(calendar);
		c.setTimeZone(TimeZone.getTimeZone("Asia/Manila"));
		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		 int hour = timePicker.getCurrentHour();
		 int min = timePicker.getCurrentMinute();
		 timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			int hour = timePicker.getCurrentHour();
			 int min = timePicker.getCurrentMinute();
	            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
	                if(hour!= timePicker.getCurrentHour() || min !=timePicker.getCurrentMinute()){
	               	 hour = timePicker.getCurrentHour();
	               	 min = timePicker.getCurrentMinute();
	               	 if (min > 9) {
	               		 if (hour >= 12) {
	               			 time=(hour-12)+":"+min+" PM";
	               		 }
	               		 else {
	               			 time=hour+":"+min+" AM";
	               		 }
	               	 }
	               	 else {
	               		 if (hour >= 12) {
	               			 time=(hour-12)+":0"+min+" PM";
	               		 }
	               		 else {
	               			 time=hour+":0"+min+" AM";
	               		 }
	               	 }
	               	 
	                }
	            }
	        });
		 if (min > 9) {
     		 if (hour >= 12) {
     			 time=(hour-12)+":"+min+" PM";
     		 }
     		 else {
     			 time=hour+":"+min+" AM";
     		 }
     	 }
     	 else {
     		 if (hour >= 12) {
     			 time=(hour-12)+":0"+min+" PM";
     		 }
     		 else {
     			 time=hour+":0"+min+" AM";
     		 }
     	 }
	}
	@Override
	public void onClick(View v){
          //respond to click
		if (v.getId() == R.id.button1) {
			try {
				if (!calendar.equals("none")) {
					//if (!time.equals("none")) {
						dao.writeFile(name, description, calendar, time);
						Toast.makeText(getApplicationContext(), "Event Saved!", Toast.LENGTH_LONG).show();
						//Toast.makeText(getApplicationContext(), c.get(Calendar.MINUTE)+"", Toast.LENGTH_LONG).show();
					//}
					//else {
					//	Toast.makeText(getApplicationContext(), "Time Selected Invalid!"+time.length(), Toast.LENGTH_LONG).show();
					//}
				}
				else {
					Toast.makeText(getApplicationContext(), "Date Selected Invalid!", Toast.LENGTH_LONG).show();
				}
			}catch(Exception e){}
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.create1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
