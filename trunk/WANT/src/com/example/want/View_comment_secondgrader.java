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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class View_comment_secondgrader extends ActionBarActivity {

	private String[] serverMessage = new String[100];
	private String[] date = new String[100];
	private String[] writer = new String[100];
	private String[] context = new String[100];
	private int listCount = 0;
	private int count = 0;

	ListView list;
	Comment_Adapter adapter;
	ArrayList<Comment_List_Data> arrData;

	private TCPClient myTcpClient;

	public class connectTask extends AsyncTask<String, String, String> {

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
			});

			myTcpClient.run();
			Log.i("onCreate", "스레드 시작");

			return null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_comment_secondgrader);

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

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// 서버접속 요청
		final connectTask connect = new connectTask();
		connect.execute("");

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

		// 메세지 던짐
		myTcpClient.sendMessage("4");
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

		if (listCount != 0) {
			for (int i = 0; i < listCount; i++) {
				writer[i] = serverMessage[3 * i + 1];
				context[i] = serverMessage[3 * i + 2];
				date[i] = serverMessage[3 * (i + 1)];

				Log.i("onCreate 결과", i + " : " + writer[i] + ", " + context[i]
						+ ", " + date[i]);
				arrData.add(new Comment_List_Data(context[i], writer[i],
						date[i]));
			}
		} else
			arrData.add(new Comment_List_Data("댓글이 없습니다. 댓글을 작성해 주세요", "", ""));

		adapter = new Comment_Adapter(this, arrData);
		list = (ListView) findViewById(R.id.Secondgrader_comment_listview);
		list.setAdapter(adapter);

		Button commentInputButton = (Button) findViewById(R.id.commentInputButton);
		commentInputButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Write_comment_secondgrade.class);
				startActivity(intent);
			}
		});

		for (int i = 0; i < listCount + 1; i++)
			serverMessage[i] = null;
		count = 0;

		myTcpClient.stopClient();
	}

}