package com.example.want;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class View_comment_thirdgrader extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_comment_thirdgrader);
		
		// �׼ǹ� ����
				ActionBar actionBar = getSupportActionBar();
				actionBar.hide();
	}

}
