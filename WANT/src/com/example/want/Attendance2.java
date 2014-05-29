package com.example.want;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Attendance2 extends ActionBarActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.attendance);	    
	    
		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
	    
	    final TextView subjectText = (TextView) findViewById(R.id.subjectText);
		final TextView monthText = (TextView) findViewById(R.id.monthText);
		subjectText.setMinWidth(79);
		monthText.setMinWidth(50);

		Spinner monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
		monthSpinner.setPrompt("월 선택");
		ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,
				R.array.mon, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		monthSpinner.setAdapter(adapter1);
		monthSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String month = parent.getItemAtPosition(position).toString();
				monthText.setText(month);
		
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

				monthText.setText("월선택");
			}
		});
		
		
		
		Spinner subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.sub, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		subjectSpinner.setAdapter(adapter2);
		
		subjectSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				String subject = parent.getItemAtPosition(position).toString();
				subjectText.setText(subject);
			
				switch(position){
					case 1:
						Attendance2.this.setContentView(R.layout.attendance);
						
					case 2:
						
						Attendance2.this.setContentView(R.layout.attendance2);					
				}			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				subjectText.setText("과목명");
			}
		});
	    // TODO Auto-generated method stub
	}

}
