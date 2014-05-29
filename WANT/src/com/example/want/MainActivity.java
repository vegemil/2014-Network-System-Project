package com.example.want;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	// private NfcAdapter nfcAdapter;
	// private PendingIntent pendingIntent;
	static String[] serverMessage = new String[3];
	static int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		// 현재 시간을 msec으로 구한다.
		long now = System.currentTimeMillis();
		// 현재 시간을 저장 한다.
		Date date = new Date(now);
		// 시간 포맷으로 만든다.
		SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		String strNow = sdfNow.format(date);
		
		Log.i("time", strNow);

		ImageButton loginoutButton = (ImageButton) findViewById(R.id.loginoutButton);
		loginoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (StudentInfo.getID().equals("")
						|| StudentInfo.getGrade().equals("")
						|| StudentInfo.getName().equals("")
						|| StudentInfo.getPassword().equals("")) {
					Intent intent = new Intent(getApplicationContext(),
							Login.class);
					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(),
							StudentInfo.getName() + " 로그아웃 되었습니다.",
							Toast.LENGTH_SHORT).show();
					StudentInfo.setGrade("");
					StudentInfo.setID("");
					StudentInfo.setName("");
					StudentInfo.setPassword("");
				}
			}
		});

		ImageButton attendanceButton = (ImageButton) findViewById(R.id.attendanceButton);
		attendanceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Attendance.class);
				startActivity(intent);
			}
		});

		ImageButton communityButton = (ImageButton) findViewById(R.id.communityButton);
		communityButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Community.class);
				startActivity(intent);
			}
		});

		ImageButton majorinformationButton = (ImageButton) findViewById(R.id.majorinformationButton);
		majorinformationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Major_Information.class);
				startActivity(intent);
			}
		});

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
