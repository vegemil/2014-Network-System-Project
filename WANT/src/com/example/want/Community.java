package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Community extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.community);

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

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

		ImageButton firstgraderButton = (ImageButton) findViewById(R.id.firstgraderButton);
		firstgraderButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (StudentInfo.getGrade().equals("1")) {
					Intent intent = new Intent(getApplicationContext(),
							View_noticelist_firstgrader.class);
					startActivity(intent);
				} else
					Toast.makeText(getApplicationContext(),
							"학년이 달라 입장할 수 없습니다", Toast.LENGTH_SHORT).show();
			}
		});

		ImageButton secondgraderButton = (ImageButton) findViewById(R.id.secondgraderButton);
		secondgraderButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (StudentInfo.getGrade().equals("2")) {
					Intent intent = new Intent(getApplicationContext(),
							View_noticelist_secondgrader.class);
					startActivity(intent);
				} else
					Toast.makeText(getApplicationContext(),
							"학년이 달라 입장할 수 없습니다", Toast.LENGTH_SHORT).show();
			}
		});

		ImageButton thirdgraderButton = (ImageButton) findViewById(R.id.thirdgraderButton);
		thirdgraderButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (StudentInfo.getGrade().equals("3")) {
					Intent intent = new Intent(getApplicationContext(),
							View_noticelist_thirdgrader.class);
					startActivity(intent);
				} else
					Toast.makeText(getApplicationContext(),
							"학년이 달라 입장할 수 없습니다", Toast.LENGTH_SHORT).show();
			}
		});

		ImageButton fourthgraderButton = (ImageButton) findViewById(R.id.fourthgraderButton);
		fourthgraderButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (StudentInfo.getGrade().equals("4")) {
					Intent intent = new Intent(getApplicationContext(),
							View_noticelist_fourthgrader.class);
					startActivity(intent);
				} else
					Toast.makeText(getApplicationContext(),
							"학년이 달라 입장할 수 없습니다", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
