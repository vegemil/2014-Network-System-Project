package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Timetable extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable);

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		ImageButton homeButton = (ImageButton)findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});
		//스피너 에러중
//		Spinner classSpinner = (Spinner)findViewById(R.id.classSpinner);
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.class_array, R.layout.support_simple_spinner_dropdown_item);
//		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//		classSpinner.setAdapter(adapter);
//		classSpinner.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				TextView selectText = (TextView)findViewById(R.id.classTextView);
//				
//			}
//		});
	}
}
