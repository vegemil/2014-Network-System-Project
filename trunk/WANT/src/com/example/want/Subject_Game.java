package com.example.want;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Subject_Game extends ActionBarActivity {

	int count = 0;
	private TCPClient myTcpClient;

	static String[] serverMessage = new String[500];
	/* static String[] serverMessage2 = new String[500]; */

	int _2nd = 0;
	int _3nd = 0;

	public class connectTask extends AsyncTask<String, String, String> {
		public Subject_Game delegate = null;

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

					// count++;
					Log.i("TAG", count + "서버에서 받은 값: " + serverMessage[count]);
					/*
					 * Log.i("TAG", "0번째받은 값: " + serverMessage[0]);
					 * Log.i("TAG", "1번째 받은 값: " + serverMessage[1]);
					 */
					count++;
				}
			});
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendance2);

		// 서버접속 요청
		final connectTask connect = new connectTask();
		connect.executeOnExecutor(connectTask.THREAD_POOL_EXECUTOR);
		// connect.execute("");
		connect.delegate = this;
		Log.i("Hi", "im SUB_Game!!!!!!!!!!!!");

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		final TextView monthText = (TextView) findViewById(R.id.monthText);
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
			Log.i("몇개찍히니??", serverMessage[0]);
			attendance.put("id", serverMessage[(2 * i) + 1]);
			Log.i("TestID", serverMessage[(2 * i) + 1]);
			attendance.put("time", serverMessage[2 * (i + 1)]);
			Log.i("Test", serverMessage[2 * (i + 1)]);
			_2nd++;
			_3nd++;
		}

		for (int a = 0; a < Integer.parseInt(serverMessage[0]); a++) {

			if (serverMessage[(2 * a) + 1].equals("200731029")) {

				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
				try {
					Date date1 = dateFormat.parse("2014-06-11 4:30");
					Date date2 = dateFormat.parse(serverMessage[2 * (a + 1)]);
					if (date1.after(date2)) {
						TextView f1 = (TextView) findViewById(R.id.w200731029);
						f1.setText("출석");
					} else if (date1.before(date2)) {
						TextView f1 = (TextView) findViewById(R.id.w200731029);
						f1.setText("지각");
					} else {
						TextView f1 = (TextView) findViewById(R.id.w200731029);
						f1.setText("결석");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (serverMessage[(2 * a) + 1].equals("201131010")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
				try {
					Date date1 = dateFormat.parse("2014-06-11 16:30");
					Date date2 = dateFormat.parse(serverMessage[2 * (a + 1)]);
					if (date1.after(date2)) {
						TextView f1 = (TextView) findViewById(R.id.w201131010);
						f1.setText("출석");
					} else if (date1.before(date2)) {
						TextView f1 = (TextView) findViewById(R.id.w201131010);
						f1.setText("지각");
					} else {
						TextView f1 = (TextView) findViewById(R.id.w201131010);
						f1.setText("결석");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (serverMessage[(2 * a) + 1].equals("201131025")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
				try {
					Date date1 = dateFormat.parse("2014-06-11 16:00 ");
					Date date2 = dateFormat.parse(serverMessage[2 * (a + 1)]);
					if (date1.after(date2)) {
						TextView f1 = (TextView) findViewById(R.id.w201131025);
						f1.setText("출석");
					} else if (date1.before(date2)) {
						TextView f1 = (TextView) findViewById(R.id.w201131025);
						f1.setText("지각");
					} else {
						TextView f1 = (TextView) findViewById(R.id.w201131025);
						f1.setText("결석");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (serverMessage[(2 * a) + 1].equals("201031013")) {
					Log.i("쩌루쩌ㅜ러", serverMessage[a]);
					TextView f3 = (TextView) findViewById(R.id.w201031013);
					f3.setText(serverMessage[2 * (a + 1)]);
				}
				/*
				 * if (serverMessage[(2 * a) + 1].equals("201131025")) {
				 * Log.i("쩌루쩌ㅜ러", serverMessage[a]); TextView f5 = (TextView)
				 * findViewById(R.id.w201131025); f5.setText(serverMessage[2 *
				 * (a + 1)]); }
				 */
				if (serverMessage[(2 * a) + 1].equals("201131026")) {
					Log.i("쩌루쩌ㅜ러", serverMessage[a]);
					TextView f6 = (TextView) findViewById(R.id.w201131026);
					f6.setText(serverMessage[2 * (a + 1)]);
				}
				if (serverMessage[(2 * a) + 1].equals("201131035")) {
					Log.i("쩌루쩌ㅜ러", serverMessage[a]);
					TextView f7 = (TextView) findViewById(R.id.w201131035);
					f7.setText(serverMessage[2 * (a + 1)]);
				}
				if (serverMessage[(2 * a) + 1].equals("201131037")) {
					Log.i("쩌루쩌ㅜ러", serverMessage[a]);
					TextView f8 = (TextView) findViewById(R.id.w201131037);
					f8.setText(serverMessage[2 * (a + 1)]);
				}

				_2nd++;
				Log.i("_2nd의 개수", ":" + _2nd);
				_3nd++;
			}

			/*
			 * int plus; plus = Integer.parseInt(serverMessage[_2nd + 1]) + _2nd
			 * / 2; //개수파악을 위해 사용 _2nd +1 홀수 위치에 값이 나온다. 뒤에 더해준 이유는 앞에까지의 값을 구하기
			 * 위한 것 Log.i("plus의 값은??", ":" + plus);
			 * 
			 * 
			 * for (int b = (_2nd/2)-1; b < plus; b++) {
			 * 
			 * if (serverMessage[2 * (b + 1)].equals("200931002")) {
			 * Log.i("너가?????????????", "그런거야 ?ㅠㅠㅠ"); TextView f9 = (TextView)
			 * findViewById(R.id.w2200931002); f9.setText(serverMessage[(2 * b)
			 * + 3]); } if (serverMessage[2 * (b + 1)].equals("200931032")) {
			 * Log.i("너가널이니??????????????", "그런거야 ?ㅠㅠㅠ"); Log.i("Erroe???",
			 * ":"+serverMessage[(2 * b) + 3]); TextView f2 =
			 * (TextView)findViewById(R.id.w2200931032);
			 * f2.setText(serverMessage[(2 * b)+3]); TextView f10 = (TextView)
			 * findViewById(R.id.w2200931032); f10.setText(serverMessage[(2 * b)
			 * + 3]); } if (serverMessage[2 * (b + 1)].equals("201131010")) {
			 * 
			 * TextView f2 = (TextView) findViewById(R.id.w2201131010);
			 * f2.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201031013")) {
			 * 
			 * TextView f3 = (TextView) findViewById(R.id.w2201031013);
			 * f3.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201131025")) {
			 * 
			 * TextView f5 = (TextView) findViewById(R.id.w2201131025);
			 * f5.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201131026")) {
			 * 
			 * TextView f6 = (TextView) findViewById(R.id.w2201131026);
			 * f6.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201131035")) {
			 * 
			 * TextView f7 = (TextView) findViewById(R.id.w2201131035);
			 * f7.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201131037")) {
			 * 
			 * TextView f7 = (TextView) findViewById(R.id.w201131037);
			 * f7.setText(serverMessage[(2 * b) + 3]); }
			 * 
			 * _3nd ++; Log.i("_3nd의 개수", ":" + _3nd); } }
			 * 
			 * int plus2; plus2 = Integer.parseInt(serverMessage[_3nd + 1]) +
			 * _3nd / 2; //개수파악을 위해 사용 _2nd +1 홀수 위치에 값이 나온다. 뒤에 더해준 이유는 앞에까지의
			 * 값을 구하기 위한 것 Log.i("plus의 값은??", ":" + plus2);
			 * 
			 * 
			 * for (int b = (_3nd/2)-1; b < plus2; b++) {
			 * 
			 * if (serverMessage[2 * (b + 1)].equals("200931002")) { TextView f9
			 * = (TextView) findViewById(R.id.w2200931002);
			 * f9.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("200931032")) { Log.i("너가널이니??????????????",
			 * "그런거야 ?ㅠㅠㅠ"); Log.i("Erroe???", ":"+serverMessage[(2 * b) + 3]);
			 * TextView f2 = (TextView)findViewById(R.id.w2200931032);
			 * f2.setText(serverMessage[(2 * b)+3]); TextView f10 = (TextView)
			 * findViewById(R.id.w2200931032); f10.setText(serverMessage[(2 * b)
			 * + 3]); } if (serverMessage[2 * (b + 1)].equals("201131010")) {
			 * 
			 * TextView f2 = (TextView) findViewById(R.id.w2201131010);
			 * f2.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201031013")) {
			 * 
			 * TextView f3 = (TextView) findViewById(R.id.w2201031013);
			 * f3.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201131025")) {
			 * 
			 * TextView f5 = (TextView) findViewById(R.id.w2201131025);
			 * f5.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201131026")) {
			 * 
			 * TextView f6 = (TextView) findViewById(R.id.w2201131026);
			 * f6.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201131035")) {
			 * 
			 * TextView f7 = (TextView) findViewById(R.id.w2201131035);
			 * f7.setText(serverMessage[(2 * b) + 3]); } if (serverMessage[2 *
			 * (b + 1)].equals("201131037")) {
			 * 
			 * TextView f7 = (TextView) findViewById(R.id.w201131037);
			 * f7.setText(serverMessage[(2 * b) + 3]); }
			 * 
			 * _3nd ++; } }
			 * 
			 * // myTcpClient.stopClient();
			 * 
			 * }
			 */}
	}
}
