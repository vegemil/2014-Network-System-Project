package com.example.want;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Attendance extends ActionBarActivity  implements OnItemSelectedListener{
	TextView selection;
	TextView selections;

	String[] items = { "네트워크프로젝트", "게임프로젝트" };
	String[] itemss = { "3월", "4월", "5월", "6월", "9월", "10월", "11월", "12월" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendance);

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		selection = (TextView) findViewById(R.id.textView2);
		selections = (TextView) findViewById(R.id.textView3);

		Spinner spin = (Spinner) findViewById(R.id.spinner2);
		spin.setOnItemSelectedListener(this);

		Spinner spins = (Spinner) findViewById(R.id.spinner1);
		spins.setOnItemSelectedListener((OnItemSelectedListener) this);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		ArrayAdapter<String> bb = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itemss);

		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);

		bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spins.setAdapter(bb);

	}

	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		selection.setText(items[position]);
		selections.setText(itemss[position]);
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		selection.setText("");
		selections.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}