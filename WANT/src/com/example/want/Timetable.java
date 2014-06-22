package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Timetable extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable);

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

		Spinner classSpinner = (Spinner) findViewById(R.id.classSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.class_array,
				android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		classSpinner.setAdapter(adapter);

		classSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView classEdit = (TextView) findViewById(R.id.classTextView);
				classEdit.setText("강의실 이름 : "
						+ parent.getItemAtPosition(position).toString());

				TextView monday1 = (TextView) findViewById(R.id.monday1);
				TextView tuesday1 = (TextView) findViewById(R.id.tuesday1);
				TextView wednesday1 = (TextView) findViewById(R.id.wednesday1);
				TextView thursday1 = (TextView) findViewById(R.id.thursday1);

				TextView thursday4 = (TextView) findViewById(R.id.thursday4);

				TextView monday6 = (TextView) findViewById(R.id.monday7);
				TextView tuesday6 = (TextView) findViewById(R.id.tuesday7);
				TextView wednesday6 = (TextView) findViewById(R.id.wednesday7);
				TextView thursday6 = (TextView) findViewById(R.id.thursday7);

				TableRow tableRow2 = (TableRow) findViewById(R.id.tableRow2);
				TableRow tableRow5 = (TableRow) findViewById(R.id.tableRow5);

				TextView fourth_period = (TextView) findViewById(R.id.fourth_period);
				TextView fifth_period = (TextView) findViewById(R.id.fifth_period);

				tableRow5.setMinimumHeight(tableRow2.getHeight());
				fourth_period.setHeight(tableRow2.getHeight() / 2);
				fifth_period.setHeight(tableRow2.getHeight() / 2);

				switch (position) {
				case 0:
					monday1.setText("3D\r\n그래픽스 입문\r\n김명하");
					tuesday1.setText(" ");
					wednesday1.setText("네트워크\r\n프로그래밍\r\n정연식");
					thursday1.setText("JAVA\r\n프로그래밍\r\n정연식");
					monday6.setText("C++\r\n프로그래밍\r\n임충규");
					tuesday6.setText("C\r\n프로그래밍\r\n임충규");
					wednesday6.setText(" ");
					thursday6.setText("컴퓨터\r\n그래픽스\r\n임충규");
					break;

				case 1:
					monday1.setText(" ");
					tuesday1.setText(" ");
					wednesday1.setText(" ");
					thursday1.setText("C\r\n프로그래밍\r\n실습\r\n임충규");
					thursday4.setText("게임프로그래밍\r\n프로젝트\r\n임충규");
					monday6.setText("네트워크\r\n시스템\r\n프로그래밍\r\n정연식");
					tuesday6.setText("ARM\r\n프로세서\r\n정연식");
					wednesday6.setText(" ");
					thursday6.setText(" ");
					break;

				default:
					break;
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
	}
}
