package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class Major_Information extends ActionBarActivity {
	static final String[] inforlist = { "�а��Ұ�", "�����ԼҰ�", "����", "�а���������",
			"���ǽð�ǥ" };
	private ArrayAdapter<String> arrayAdapter;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.major_information);

		// �׼ǹ� ����
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, inforlist);

		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(arrayAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setOnItemClickListener(onItemClickListener);

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

	private OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()

	{

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id)

		{
			if (position == 0) {
				Intent intent = new Intent(Major_Information.this,
						Major_Introduction.class);
				startActivity(intent);
			}
			if (position == 1) {
				Intent intent = new Intent(Major_Information.this,
						Professor_Introduction.class);
				startActivity(intent);
			}
			if (position == 2) {
				Intent intent = new Intent(Major_Information.this, Major.class);
				startActivity(intent);
			}
			if (position == 3) {
				Intent intent = new Intent(Major_Information.this,
						Major_Notice.class);
				startActivity(intent);
			}
			if (position == 4) {
				Intent intent = new Intent(Major_Information.this,
						Timetable.class);
				startActivity(intent);
			}

		}

	};

}
