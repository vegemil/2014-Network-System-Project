package com.example.want;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;

public class Firstgrader_Notice extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstgrader_notice);
		
		// �׼ǹ� ����
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
	}

}
