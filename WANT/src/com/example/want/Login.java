package com.example.want;

import com.example.want.Join.connectTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class Login extends ActionBarActivity implements AsyncResponse{

		String name;
		String grade;
		String id;
		String password;

		static String serverMessage;
		static String serverName;
		static String serverGrade;

		private TCPClient myTcpClient;
		private StudentInfo myStudent;

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

						//serverMessage = message;
					}
				});
				myTcpClient.run();

				return serverMessage;
			}

			@Override
			protected void onProgressUpdate(String... values) {
				super.onProgressUpdate(values);
				serverMessage = values[0];
				serverName = values[1];
				serverGrade = values[2];
			}

		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		
		// ¾×¼Ç¹Ù ¼û±è
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		ImageButton homeButton = (ImageButton)findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});
		
		ImageButton joinButton = (ImageButton)findViewById(R.id.joinButton);
		joinButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Join.class);
				startActivity(intent);
			}
		});
		
		final EditText idEdit = (EditText)findViewById(R.id.loginidedit);
		final EditText passwordEdit = (EditText)findViewById(R.id.loginpasswordedit);
		
		final connectTask connect = new connectTask();
		connect.execute("");
		connect.delegate = this;
		
		ImageButton okButton = (ImageButton) findViewById(R.id.loginokButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				id = idEdit.getText().toString();
				password = passwordEdit.getText().toString();
				
				Log.i("tag", "id : " + id);
				Log.i("tag", "password : " + password);
				
				if (myTcpClient != null) {
					myTcpClient.sendMessage(id);
					myTcpClient.sendMessage(password);
				}
				
				while (serverMessage == null || serverMessage.isEmpty()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Log.i("tag", "name : " + name);
				Log.i("tag", "grade : " + grade );
			}
		});
	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		serverMessage = output;
		Log.i("tag", "processFinish result : " + serverMessage);
	}
}
