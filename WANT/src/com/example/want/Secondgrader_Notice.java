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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Secondgrader_Notice extends ActionBarActivity implements
		AsyncResponse {

	static String[] serverMessage = new String[100];
	static String[] title = new String[100];
	static String[] date = new String[100];
	static String[] writer = new String[100];
	static String[] context = new String[100];
	static String[] textnum = new String[100];
	static int listCount = 0;
	static int count = 0;
	
	ListView list;
	Community_Adapter adapter;
	ArrayList<Community_List_Data> arrData;
	

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
		
		while(myTcpClient == null)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		myTcpClient.sendMessage("2");
				
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
		
		 
		 arrData = new ArrayList<Community_List_Data>();
		 
		 for (int i = 0; i < listCount; i++) {
		 writer[i] = serverMessage[5 * i + 1];
		 title[i] = serverMessage[5 * i + 2];
		 date[i] = serverMessage[5 * i + 3];
		 context[i] = serverMessage[5 * i +4];
		 textnum[i] = serverMessage[5 * (i + 1)];
		
		 Log.i("onCreate 결과", i +" : " + writer[i] + ", " + title[i] + ", " + date[i]);
		 Log.i("onCreate 결과", context[i]);
		 Log.i("onCreate 결과","" + textnum[i]);
		 arrData.add(new Community_List_Data(title[i], writer[i], date[i]));
		 }
		 		
		 adapter = new Community_Adapter(this, arrData);
		 list = (ListView)findViewById(R.id.listView1);
		 list.setAdapter(adapter);
		 
		 list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Community_Text_Data.setContext(context[position]);
				Community_Text_Data.setTitle(title[position]);
				Community_Text_Data.setTextNum(textnum[position]);
				Community_Text_Data.setDate(date[position]);
				Community_Text_Data.setWriter(writer[position]);
				
				Log.i("Community text data", Community_Text_Data.getContext());
				Log.i("Community text data", Community_Text_Data.getDate());
				Log.i("Community text data", Community_Text_Data.getTextNum());
				Log.i("Community text data", Community_Text_Data.getTitle());
				Log.i("Community text data", Community_Text_Data.getWriter());
				Intent intent = new Intent(getApplicationContext(),
						Secondgrader_NoticeBody.class);
				startActivity(intent);
			}
		});
		 
		 myTcpClient.stopClient();
	}
	
	
	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		serverMessage[0] = output;
		Log.i("tag", "processFinish result : " + serverMessage[0]);

	}

}