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

public class Secondgrader_comment extends ActionBarActivity implements
		AsyncResponse {

	static String[] serverMessage = new String[100];
	static String[] date = new String[100];
	static String[] writer = new String[100];
	static String[] context = new String[100];
	static int listCount = 0;
	static int count = 0;

	ListView list;
	Comment_Adapter adapter;
	ArrayList<Comment_List_Data> arrData;

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
			}, 5345);

			myTcpClient.run();
			Log.i("onCreate", "스레드 시작");

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondgrader_comment);

		// 서버접속 요청
		final connectTask connect = new connectTask();
		connect.execute("");
		connect.delegate = this;

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		try {
			Thread.sleep(2000);
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

		// 메세지 던짐
		myTcpClient.sendMessage("2");
		myTcpClient.sendMessage(Community_Text_Data.getTextNum());
		Log.i("SecondGraderComment", "메세지 던짐");
		
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

		arrData = new ArrayList<Comment_List_Data>();

		for (int i = 0; i < listCount; i++) {
			writer[i] = serverMessage[3 * i + 1];
			date[i] = serverMessage[3 * i + 2];
			context[i] = serverMessage[3 * (i + 1)];

			Log.i("onCreate 결과", i + " : " + writer[i] + ", " + context[i]
					+ ", " + date[i]);
			arrData.add(new Comment_List_Data(context[i], writer[i], date[i]));
		}

		adapter = new Comment_Adapter(this, arrData);
		list = (ListView) findViewById(R.id.Secondgrader_comment_listview);
		list.setAdapter(adapter);

		myTcpClient.stopClient();
	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		//serverMessage[0] = output;
		// Log.i("tag", "processFinish result : " + serverMessage[0]);

	}

}