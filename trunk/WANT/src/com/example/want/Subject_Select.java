package com.example.want;

//import java.util.HashMap;

//import com.example.want.Subject_Select.connectTask;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class Subject_Select extends ActionBarActivity {
	static final String[] inforlist = { "네트워크시스템프로젝트", "게임프로젝트" };
	private ArrayAdapter<String> arrayAdapter;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_select);
		
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
		
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, inforlist);

		listView = (ListView) findViewById(R.id.subjectlist);
		listView.setAdapter(arrayAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setOnItemClickListener(onItemClickListener);

	}

	

	private OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()

	{
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position, long id)
		{
			if (position == 0) {
				Intent intent = new Intent(Subject_Select.this,
						Subject_Network.class);
				startActivity(intent);
			}
			if (position == 1) {
				Intent intent = new Intent(Subject_Select.this,
						Subject_Game.class);
				startActivity(intent);
			}
						
		}

	};

}
