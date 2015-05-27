package com.example.retentionscheduler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class TestButton  extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_button);
		LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
		for (int i = 1; i < 20; i++) {
		        Button btnTag = new Button(this);
		        btnTag.setText("Button " + i);
		        btnTag.setId(i);
		        layout.addView(btnTag);
		        ((Button)findViewById(i)).setOnClickListener(this);
		    }
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


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
