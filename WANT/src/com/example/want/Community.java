package com.example.want;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;


public class Community extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.community);
		
		//�׼ǹ� ����
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
	
	}

}
