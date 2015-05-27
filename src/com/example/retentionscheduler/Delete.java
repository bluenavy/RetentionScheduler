/*This is a course requirement for CS 192 Software Engineering II under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science, College of Engineering, University of the Philippines, Diliman for the AY 2014-2015â€�.
 Neil Jonathan A. Joaquin
 David Relao*/

/*Code History:
Initial Code Authored by: Neil Jonathan A. Joaquin
Changes made authored by David Relao: edited the delete format implementation; added SpannableString and ClickableSpan features; added popup windows; deleting from DDMS*/

/* File Creation Date: April 20, 2015
    Development Group: Blue Navy Inc.
    Client Group: Purple McShort Shorts
    Purpose of file: Deletes a specific event from the view and from the database.
*/

package com.example.retentionscheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Delete extends ActionBarActivity implements OnClickListener{
	DataAccessObject dao = new DataAccessObject(this);
	Button button;
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		TextView view = (TextView) findViewById(R.id.textView2);
		try{
			String data=dao.readFile("database");
			SpannableString str = new SpannableString(data);
			final String[] state = data.split("\n");
			LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
  			for (int i = 0; i < state.length; i++) {
  				
  				final String word = state[i];
  			        Button btnTag = new Button(this);
  			        btnTag.setText(state[i]);
  			        btnTag.setId(i);
  			      btnTag.setBackgroundColor(Color.parseColor("#81dedc"));
  			        layout.addView(btnTag);
  			      ((Button)findViewById(i)).setOnClickListener(new OnClickListener()
  			      {
  			     	 public void onClick(View v) {
  			     		final Dialog dialog = new Dialog(context);
						dialog.setContentView(R.layout.popup);
						dialog.setTitle("Are you sure?");
						Button deleteBut = (Button) dialog.findViewById(R.id.delete);
						deleteBut.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								try {
									FileOutputStream fs1 = context.openFileOutput("database.txt", Context.MODE_PRIVATE);
									OutputStreamWriter myOutWriter1 = new OutputStreamWriter(fs1);
									for (final String recopy : state) {
										if (!recopy.equals(word)) {
											myOutWriter1.append(recopy);
											myOutWriter1.append("\n");
										}
									}
									myOutWriter1.close();
									fs1.close();
								
									File file = new File("/data/data/com.example.retentionscheduler/files/"+word+".txt");
									boolean deleted = file.delete();	
								
								} catch(Exception e){}
								dialog.dismiss();
								finish();
								startActivity(getIntent());
							}
						});
						Button cancelBut = (Button) dialog.findViewById(R.id.cancel);
						cancelBut.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
						dialog.show();
  			     	 } 
  			     });
  			     }
			/*for (final String word : state) {
				ClickableSpan clickableSpan = new ClickableSpan() {
					@Override
					public void onClick(View textView) {
						//use word here to make a decision
						final Dialog dialog = new Dialog(context);
						dialog.setContentView(R.layout.popup);
						dialog.setTitle("Are you sure?");
						Button deleteBut = (Button) dialog.findViewById(R.id.delete);
						deleteBut.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								try {
									FileOutputStream fs1 = context.openFileOutput("database.txt", Context.MODE_PRIVATE);
									OutputStreamWriter myOutWriter1 = new OutputStreamWriter(fs1);
									for (final String recopy : state) {
										if (!recopy.equals(word)) {
											myOutWriter1.append(recopy);
											myOutWriter1.append("\n");
										}
									}
									myOutWriter1.close();
									fs1.close();
								
									File file = new File("/data/data/com.example.retentionscheduler/files/"+word+".txt");
									boolean deleted = file.delete();	
								
								} catch(Exception e){}
								dialog.dismiss();
								finish();
								startActivity(getIntent());
							}
						});
						Button cancelBut = (Button) dialog.findViewById(R.id.cancel);
						cancelBut.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
						dialog.show();
					}
				};
				str.setSpan(clickableSpan, data.indexOf(word), data.indexOf(word) + word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}*/
			//view.setText(str);
			//view.setMovementMethod(LinkMovementMethod.getInstance());
		}catch(Exception e){}
	}
		
	public void onClick(View v){
		
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
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
