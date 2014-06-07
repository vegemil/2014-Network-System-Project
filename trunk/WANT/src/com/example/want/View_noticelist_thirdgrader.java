package com.example.want;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class View_noticelist_thirdgrader extends ActionBarActivity implements
		AsyncResponse {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_noticelist_thirdgrader);

		// ¾×¼Ç¹Ù ¼û±è
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		ImageButton writeButton = (ImageButton) findViewById(R.id.writeButton);
		writeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Write_notice_thirdgrade.class);
				startActivity(intent);
			}
		});

		ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
			}
		});

		
	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub

	}

}
