package com.example.want;

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
import android.widget.TextView;

public class View_noticebody_secondgrader extends ActionBarActivity implements
		AsyncResponse {

	private TCPClient myTcpClient;
	static String[] serverMessage = new String[100];
	static String title;
	static String body;
	static int count = 0;

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
					Log.i("server���� ���� ��", serverMessage[count]);
					count++;
				}
			}, 9998);

			myTcpClient.run();
			Log.i("onCreate", "������ ����");

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
		setContentView(R.layout.view_noticebody_secondgrader);

		// �������� ��û
		final connectTask connect = new connectTask();
		connect.execute("");
		connect.delegate = this;

		// �׼ǹ� ����
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		Button comment = (Button) findViewById(R.id.commentButton);
		comment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						View_comment_secondgrader.class);
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
		
		myTcpClient.sendMessage("2");
		myTcpClient.sendMessage(Community_Text_Data.getTextNum());
		Log.i("SecondGrader_Notice", "�޼��� ����");

		while (serverMessage[0] == null || serverMessage[0].isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		TextView title = (TextView) findViewById(R.id.titleText);
		TextView context = (TextView) findViewById(R.id.bodyText);

		title.setText(serverMessage[0]);
		int lineCount = Integer.parseInt(serverMessage[1]);
		for(int i=0; i<lineCount; i++)
		{
			if(i ==0)
				body = serverMessage[i+2];
			else
				body += "\n" + serverMessage[i+2];
			
		}
		
		context.setText(body);
		for(int i=0; i<lineCount +1; i++)
			serverMessage[i] = "";
		myTcpClient.stopClient();
	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub

	}
}
