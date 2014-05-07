package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Join extends ActionBarActivity {

	String name;
	int grade;
	int id;
	int password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join);

		// ¾×¼Ç¹Ù ¼û±è
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
		
		final EditText nameEdit = (EditText) findViewById(R.id.joinnameedit);
		final EditText gradeEdit = (EditText) findViewById(R.id.joingradeedit);
		final EditText idEdit = (EditText) findViewById(R.id.joinidedit);
		final EditText passwordEdit = (EditText) findViewById(R.id.joinpasswordedit);

		ImageButton okButton = (ImageButton) findViewById(R.id.joinokButton);

		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name = nameEdit.getText().toString();
				grade = Integer.parseInt(gradeEdit.getText().toString());
				id = Integer.parseInt(idEdit.getText().toString());
				password = Integer.parseInt(passwordEdit.getText().toString());

				Log.i("tag", "name : " + name);
				Log.i("tag", "grade : " + grade);
				Log.i("tag", "id : " + id);
				Log.i("tag", "password : " + password);
			}
		});

	}
}
