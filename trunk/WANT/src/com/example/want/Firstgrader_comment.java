package com.example.want;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class Firstgrader_comment extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstgrader_comment);
		
		// �׼ǹ� ����
				ActionBar actionBar = getSupportActionBar();
				actionBar.hide();
	}

}
