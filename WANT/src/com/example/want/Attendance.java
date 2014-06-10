package com.example.want;

import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Attendance extends ActionBarActivity implements AsyncResponse {
	int count = 0;
	private TCPClient myTcpClient;
	
	static String[] serverMessage = new String[100];

	public class connectTask extends AsyncTask<String, String, String> {
		public AsyncResponse delegate = null;
		
		
		
		@Override
		protected String doInBackground(String... message) {
			
			Log.i("Hi", "im doin!!!!!!!!!!!!");
			 
			// we create a TCPClient object and
			myTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
				
				@Override
				// here the messageReceived method is implemented
				public void messageReceived(String message) {
					// this method calls the onProgressUpdate
					publishProgress(message);
					// serverMessage[0] = message;
										
					serverMessage[count] = message;
					//count++;
					Log.i("TAG", count + "서버에서 받은 값: " + serverMessage[count]);
					/*Log.i("TAG", "0번째받은 값: " + serverMessage[0]);
					Log.i("TAG", "1번째 받은 값: " + serverMessage[1]);*/
					count++;
				}
			}, 8888);
			myTcpClient.run();

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);

			Log.i("tag", "스레드 value : " + values[0]);

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendance1);

		// 서버접속 요청
		final connectTask connect = new connectTask();
		connect.executeOnExecutor(connectTask.THREAD_POOL_EXECUTOR);
		//connect.execute("");
		connect.delegate = this;
		Log.i("Hi", "im Att1!!!!!!!!!!!!");
		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		final TextView subjectText = (TextView) findViewById(R.id.subjectText);
		final TextView monthText = (TextView) findViewById(R.id.monthText);
		subjectText.setMinWidth(79);
		monthText.setMinWidth(50);

		Spinner monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
		ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,
				R.array.mon, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpinner.setAdapter(adapter1);
		monthSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String month = parent.getItemAtPosition(position).toString();
				monthText.setText(month);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

				monthText.setText("월선택");
			}
		});

		Spinner subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.sub, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		subjectSpinner.setAdapter(adapter2);
		subjectSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				String subject = parent.getItemAtPosition(position).toString();
				subjectText.setText(subject);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				subjectText.setText("과목명");
			}
		});

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (myTcpClient.isRunnable() == false && myTcpClient == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		HashMap<String, String> attendance = new HashMap<String, String>();

		for (int i = 0; i < Integer.parseInt(serverMessage[0]); i++) {			
			attendance.put("id", serverMessage[(2 * i) + 1]);
			Log.i("TestID", serverMessage[(2 * i) + 1]);
			attendance.put("time", serverMessage[2 * (i + 1)]);
			Log.i("Test", serverMessage[2 * (i + 1)]);
		}
		
		Log.i("쉰난다다아아", "넘어왔당");
		TextView firstweek1 = (TextView) findViewById(R.id.firstweek1);
		TextView firstweek2 = (TextView) findViewById(R.id.firstweek2);
		TextView firstweek3 = (TextView) findViewById(R.id.firstweek3);
		TextView firstweek4 = (TextView) findViewById(R.id.firstweek4);
		TextView firstweek5 = (TextView) findViewById(R.id.firstweek5);
		TextView firstweek6 = (TextView) findViewById(R.id.firstweek6);
		TextView firstweek7 = (TextView) findViewById(R.id.firstweek7);
		TextView firstweek8 = (TextView) findViewById(R.id.firstweek8);

		TextView id1 = (TextView) findViewById(R.id.gradenumber1);
		TextView id2 = (TextView) findViewById(R.id.gradenumber2);
		TextView id3 = (TextView) findViewById(R.id.gradenumber3);
		TextView id4 = (TextView) findViewById(R.id.gradenumber4);
		TextView id5 = (TextView) findViewById(R.id.gradenumber5);
		TextView id6 = (TextView) findViewById(R.id.gradenumber6);
		TextView id7 = (TextView) findViewById(R.id.gradenumber7);
		TextView id8 = (TextView) findViewById(R.id.gradenumber8);

		/*firstweek1.setText(attendance.get(id1));
		firstweek2.setText(attendance.get(id2));
		firstweek3.setText(attendance.get(id3));
		firstweek4.setText(attendance.get(id4));
		firstweek5.setText(attendance.get(id5));
		firstweek6.setText(attendance.get(id6));
		firstweek7.setText(attendance.get(id7));
		firstweek8.setText(attendance.get(id8));*/
		
		//myTcpClient.stopClient();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub

	}
}