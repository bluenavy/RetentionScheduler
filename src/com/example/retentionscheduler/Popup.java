package com.example.retentionscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Popup extends ActionBarActivity implements OnClickListener { 
	
	Button theButton;
	EditText file1;
	EditText file2;
	EditText file3;	
	StringBuilder stringBuilder = new StringBuilder();
	
	@Override public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.files); 
		theButton = (Button) findViewById(R.id.btn_close_popup);
		theButton.setOnClickListener(this);
		file1 = (EditText) findViewById(R.id.file);
		file2 = (EditText) findViewById(R.id.file1);
		file3 = (EditText) findViewById(R.id.file2);
		//init(); 
		//popupInit(); 
	} 
	
	/*public void init() {
		popupButton = (Button) findViewById(R.id.popupbutton); 
		popupText = new TextView(this); 
		insidePopupButton = new Button(this); 
		layoutOfPopup = new LinearLayout(this);
		insidePopupButton.setText("OK");
		popupText.setText("This is Popup Window.press OK to dismiss it.");
		popupText.setPadding(0, 0, 0, 20); 
		layoutOfPopup.setOrientation(1);
		layoutOfPopup.addView(popupText); 
		layoutOfPopup.addView(insidePopupButton); 
	}
	
	public void popupInit() { 
		popupButton.setOnClickListener(this);
		insidePopupButton.setOnClickListener(this);
		popupMessage = new PopupWindow(layoutOfPopup, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
		popupMessage.setContentView(layoutOfPopup); 
	} 
	
	@Override public void onClick(View v) {
		if (v.getId() == R.id.popupbutton) {
			popupMessage.showAsDropDown(popupButton, 0, 0); 
		} 
		else { 
			popupMessage.dismiss();
		} 
	}*/
	public void onClick(View v){
        //respond to click
		if (v.getId() == theButton.getId()) {
			stringBuilder.append(file1.getText().toString());
			stringBuilder.append("\n");
			stringBuilder.append(file1.getText().toString());
			stringBuilder.append("\n");
			stringBuilder.append(file1.getText().toString());
			stringBuilder.append("\n");
			Intent intent = new Intent(this, viewInfo.class);
			startActivity(intent);
		}
    }
	
	public String getString() {
		return this.stringBuilder.toString();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}