package com.example.want;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class Major_Information extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.major_information);
		
		// �׼ǹ� ����
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
	}

}
