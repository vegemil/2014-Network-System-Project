package com.example.want;

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
import android.widget.Toast;

public class Login extends ActionBarActivity implements AsyncResponse{

		String name;
		String grade;
		String id;
		String password;

		static String[] serverMessage = new String[3];
		static String serverName;
		static String serverGrade;
		
		static int count = 0;

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
						//serverMessage[0] = message;
						serverMessage[count] = message;
						count++;
						Log.i("tag", "서버에서 받은 값 : " + message);
					}
				}, 6666);
				myTcpClient.run();

				return null;
			}

			@Override
			protected void onProgressUpdate(String... values) {
				super.onProgressUpdate(values);
				
				Log.i("tag","스레드 value : " + values[0]);
				
				if(serverMessage[0].equals("로그인 성공"))
					Toast.makeText(getApplicationContext(), serverMessage[1] + " 로그인 하였습니다.", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(), serverMessage[0], Toast.LENGTH_SHORT).show();
				
			}

		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		
		// 액션바 숨김
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
				
				while (serverMessage[0] == null || serverMessage[0].isEmpty()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Log.i("tag", "result : " + serverMessage[0]);
				
				if(serverMessage[0].equals("로그인 성공")  )
				{
					while (serverMessage[2] == null || serverMessage[2].isEmpty()) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					name =serverMessage[1];
					grade = serverMessage[2];

					Log.i("tag", "name : " + name);
					Log.i("tag", "grade : " + grade );
					
					finish();
					
				}
				
				else if(serverMessage[0].equals("잘못된 패스워드 입니다."))
				{
					idEdit.setText("");
					passwordEdit.setText("");
					
				}
				//나중에 영어로 다바꿀것 공백없이 
								
			}
		});

	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		serverMessage[0] = output;
		Log.i("tag", "processFinish result : " + serverMessage[0]);
	}
}
