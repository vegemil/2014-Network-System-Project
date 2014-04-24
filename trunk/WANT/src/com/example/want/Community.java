package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class Community extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.community);
		
		//¾×¼Ç¹Ù ¼û±è
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
		
		ImageButton firstgraderButton = (ImageButton)findViewById(R.id.firstgraderButton);
		firstgraderButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Firstgrader_Notice.class);
				startActivity(intent);
			}
		});
		
		ImageButton secondgraderButton = (ImageButton)findViewById(R.id.secondgraderButton);
		secondgraderButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Secondgrader_Notice.class);
				startActivity(intent);
			}
		});
		
		ImageButton thirdgraderButton = (ImageButton)findViewById(R.id.thirdgraderButton);
		thirdgraderButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Thirdgrader_Notice.class);
				startActivity(intent);
			}
		});
		
		ImageButton fourthgraderButton = (ImageButton)findViewById(R.id.fourthgraderButton);
		fourthgraderButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Fourthgrader_Notice.class);
				startActivity(intent);
			}
		});
	}

}
