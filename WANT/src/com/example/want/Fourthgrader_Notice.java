package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Fourthgrader_Notice extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fourthgrader_notice);

		// �׼ǹ� ����
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		ImageButton writeButton = (ImageButton) findViewById(R.id.writeButton);
		writeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Write_Fourthgrade.class);
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

}