package com.example.want;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class Write_Secondgrade extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_secondgrade);

		// �׼ǹ� ����
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
		
		ImageButton okButton = (ImageButton)findViewById(R.id.okButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText titleEdit =(EditText)findViewById(R.id.titleEdit);
				EditText bodyEdit = (EditText)findViewById(R.id.bodyEdit);
				
				String title = titleEdit.getText().toString();
				String body = bodyEdit.getText().toString();
				String name = StudentInfo.getName();
				String id = StudentInfo.getID();
				
				// ���� �ð��� msec���� ���Ѵ�.
				long now = System.currentTimeMillis();
				// ���� �ð��� ���� �Ѵ�.
				Date date = new Date(now);
				// �ð� �������� �����.
				SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				String strNow = sdfNow.format(date);
				
				
			}
		});
	}

}
