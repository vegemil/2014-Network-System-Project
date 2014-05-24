package com.example.want;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class Login extends ActionBarActivity implements AsyncResponse {

	String name;
	String grade;
	String id;
	String password;

	static String[] serverMessage = new String[3];
	static String serverName;
	static String serverGrade;

	static int count = 0;

	private TCPClient myTcpClient;

	String result;
	SharedPreferences sp;
	Editor edit;
	// StudentInfo myStudentInfo;

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
					// serverMessage[0] = message;
					serverMessage[count] = message;
					count++;
					Log.i("tag", "서버에서 받은 값 : " + message);
				}
			}, 5555);
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

		setContentView(R.layout.login);
		
		sp= (SharedPreferences)getSharedPreferences("pref", 0); //pref.xml파일을 불러오기 , 파일이 없으면 생성댐 
		edit=sp.edit();
		// 서버접속 요청
		final connectTask connect = new connectTask();
		connect.execute("");
		connect.delegate = this;

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

		ImageButton joinButton = (ImageButton) findViewById(R.id.joinButton);
		joinButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Join.class);
				startActivity(intent);
			}
		});

		final EditText idEdit = (EditText) findViewById(R.id.loginidedit);
		final EditText passwordEdit = (EditText) findViewById(R.id.loginpasswordedit);

		ImageButton okButton = (ImageButton) findViewById(R.id.loginokButton);
		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				id = idEdit.getText().toString();
				password = passwordEdit.getText().toString();

				Log.i("tag", "id : " + id);
				Log.i("tag", "password : " + password);

				result = login(id, password);

				if (!result.equals("LOGIN_SUCESS")) {
					idEdit.setText("");
					passwordEdit.setText("");
						
					
					if(result.equals("PASSWORD_WRONG"))
					Toast.makeText(getApplicationContext(), "잘못된 패스워드를 입력하셨습니다.",
							Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(getApplicationContext(), "없는 아이디 입니다.", Toast.LENGTH_SHORT).show();
					
				} else {
					StudentInfo.setID(id);
					StudentInfo.setPassword(password);
					StudentInfo.setName(serverMessage[1]);
					StudentInfo.setGrade(serverMessage[2]);

					Toast.makeText(getApplicationContext(),
							StudentInfo.getName() + " 로그인 하였습니다.",
							Toast.LENGTH_SHORT).show();
					edit.putString("id", StudentInfo.getID());
					edit.commit();
					myTcpClient.stopClient();
					finish();
				}
				
				count = 0;
				for(int i = 0; i <3; i++)
					serverMessage[i] = null;
				
			}
		});

	}

	public String login(String id, String password) {
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

		return serverMessage[0];
	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		serverMessage[0] = output;
		Log.i("tag", "processFinish result : " + serverMessage[0]);
	}
}
