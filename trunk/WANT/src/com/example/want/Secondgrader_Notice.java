package com.example.want;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Secondgrader_Notice extends ActionBarActivity implements
		AsyncResponse {

	static String[] serverMessage = new String[100];
	static String[] title = new String[100];
	static String[] date = new String[100];
	static String[] writer = new String[100];
	static int listCount = 0;
	static int count = 0;
	
	ListView list;
	Community_Adapter adapter;
	ArrayList<Community_Data> arrData;
	

	private TCPClient myTcpClient;

	public class connectTask extends AsyncTask<String, String, String> {
		public AsyncResponse delegate = null;

		@Override
		protected String doInBackground(String... message) {

			// we create a TCPClient object and
			myTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {

				@Override
				// here the messageReceived method is implemented
				public void messageReceived(String message) {
					// this method calls the onProgressUpdate
					publishProgress(message);

					serverMessage[count] = message;
					Log.i("server에서 받은 값", serverMessage[count]);
					count++;
				}
			}, 7123);

			myTcpClient.run();

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);

			Toast.makeText(getApplicationContext(), "로딩중입니다.",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondgrader_notice);

		// 서버접속 요청
		final connectTask connect = new connectTask();
		connect.execute("");
		connect.delegate = this;
		Log.i("onCreate", "스레드 시작");

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		ImageButton writeButton = (ImageButton) findViewById(R.id.writeButton);
		writeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Write_Secondgrade.class);
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

		while (myTcpClient == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 메세지 던짐
		myTcpClient.sendMessage("2");
		Log.i("onCreate", "메세지 던짐");

		while (serverMessage[0] == null || serverMessage[0].isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		listCount = Integer.parseInt(serverMessage[0]);
		Log.i("onCreate", "listCount : " + listCount);
		
		 
		 arrData = new ArrayList<Community_Data>();
		 
		 for (int i = 0; i < listCount; i++) {
		 writer[i] = serverMessage[3 * i + 1];
		 title[i] = serverMessage[3 * i + 2];
		 date[i] = serverMessage[3 * (i + 1)];
		
		 Log.i("onCreate 결과", i +" : " + writer[i] + ", " + title[i] + ", " + date[i]);
		 arrData.add(new Community_Data(title[i], writer[i], date[i]));
		 }
		 		
		 adapter = new Community_Adapter(this, arrData);
		 list = (ListView)findViewById(R.id.listView1);
		 list.setAdapter(adapter);
		 
	}
	
	
	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		serverMessage[0] = output;
		Log.i("tag", "processFinish result : " + serverMessage[0]);

	}

}
