/*This is a course requirement for CS 192 Software Engineering II under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science, College of Engineering, University of the Philippines, Diliman for the AY 2014-2015â€�.
 Neil Jonathan A. Joaquin
 David Relao*/

/*Code History:
Initial Code Authored by: Neil Jonathan A. Joaquin, David Relao
Changes made authored by David Relao: added intent functionality, dao implementations
Changes made authored by David Relao: edited the filing*/

/* File Creation Date: CS 191 days
    Development Group: Blue Navy Inc.
    Client Group: Purple McShort Shorts
    Purpose of file: Creates the Event and save it in a text file.
*/
package com.example.retentionscheduler;

//import android.content.Context;
import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class CreateEvent extends ActionBarActivity implements OnClickListener {
	
	Button theButton, addButton1;
	Button assocButt;
	EditText name;
	EditText description;
	EditText date;
	EditText time;	
	StringBuilder stringBuilder = new StringBuilder();
	String dates;
	Context context = this;
	
	
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
          setContentView(R.layout.create_event);
          name = (EditText) findViewById(R.id.event_name);
		description = (EditText) findViewById(R.id.description_name);
		
          theButton = (Button)findViewById(R.id.add_events);
          theButton.setOnClickListener(this);
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
		if (v.getId() == R.id.add_events) {
			try {
				int count=0;
				int max=0;
				if (name.length() != 0) {
					String some = dao.readFile("database");
					if (!some.equals("error")) {
						String[] data = some.split("\n");
						for (int i=0; i<data.length; i++) {
							if (data[i].charAt(data[i].length()-1) == ')') {
								if (data[i].substring(0,data[i].length()-3).length() == name.length()) {
									if (data[i].contains(name.getText().toString())) {
										count++;
										if(max<Character.getNumericValue(data[i].charAt(data[i].length()-2))){
											max=Character.getNumericValue(data[i].charAt(data[i].length()-2));
										}
									}
								}
							}
							else {
								if (data[i].length() == name.length()) {
									if (data[i].contains(name.getText().toString())) {
										count++;
									}
								}
							}
						}
					}
					if(max>=count+1){
						count=max;
					}
					Intent intent = new Intent(this, Create1.class);
					//Toast.makeText(getApplicationContext(),  count+"", Toast.LENGTH_LONG).show();
					if (count > 0) {
						intent.putExtra("eventTitle", name.getText().toString()+"("+(count+1)+")");
					}
					else if (count == 0){
						intent.putExtra("eventTitle", name.getText().toString());
					}
					intent.putExtra("description", description.getText().toString());
					startActivity(intent);
				}
				else {
					Toast.makeText(getApplicationContext(), "Invalid Event name input!", Toast.LENGTH_LONG).show();
				}
			}catch (Exception e) {
				System.out.println(Log.getStackTraceString(e));
			}
		}
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
