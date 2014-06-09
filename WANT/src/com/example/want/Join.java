package com.example.want;

import com.example.want.TCPClient;

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

public class Join extends ActionBarActivity implements AsyncResponse {

	String name;
	String grade;
	String id;
	String password;

	static String serverMessage;

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

					serverMessage = message;
				}
			}, 4321);
			myTcpClient.run();

			return serverMessage;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join);

		// connect to the server
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

		final EditText nameEdit = (EditText) findViewById(R.id.joinnameedit);
		final EditText gradeEdit = (EditText) findViewById(R.id.joingradeedit);
		final EditText idEdit = (EditText) findViewById(R.id.joinidedit);
		final EditText passwordEdit = (EditText) findViewById(R.id.joinpasswordedit);

		ImageButton okButton = (ImageButton) findViewById(R.id.joinokButton);

		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				name = nameEdit.getText().toString();
				grade = gradeEdit.getText().toString();
				id = idEdit.getText().toString();
				password = passwordEdit.getText().toString();

				Log.i("tag", "name : " + name);
				Log.i("tag", "grade : " + grade);
				Log.i("tag", "id : " + id);
				Log.i("tag", "password : " + password);

				// sends the message to the server
				if (myTcpClient != null) {
					myTcpClient.sendMessage(name);
					myTcpClient.sendMessage(grade);
					myTcpClient.sendMessage(id);
					myTcpClient.sendMessage(password);
				}

				// 데이터 값이 넘어올때까지 기다림
				while (serverMessage == null || serverMessage.isEmpty()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				Log.i("tag", "result : " + serverMessage);
				Toast.makeText(getApplicationContext(), serverMessage,
						Toast.LENGTH_SHORT).show();

				if (serverMessage.equals("회원가입 완료")) {
					finish();
				} else if (serverMessage.equals("아이디 중복입니다. 다시 가입하세요")) {
					nameEdit.setText("");
					gradeEdit.setText("");
					idEdit.setText("");
					passwordEdit.setText("");
				} else {
					Log.i("tag", "회원가입 에러");
				}

			}
		});
		serverMessage = null;

	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		serverMessage = output;
		Log.i("tag", "processFinish result : " + serverMessage);
	}

}