package com.example.want;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.want.Login.connectTask;

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

public class Write_notice_secondgrade extends ActionBarActivity implements AsyncResponse {

	private TCPClient myTcpClient;
	
	String serverMessage;
	
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

					Log.i("tag", "서버에서 받은 값 : " + message);
				}
			}, 9995); //9995로 바꿀 예정
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
		setContentView(R.layout.write_notice_secondgrade);
		
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
		
		ImageButton okButton = (ImageButton)findViewById(R.id.okButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText titleEdit =(EditText)findViewById(R.id.titleEdit);
				EditText bodyEdit = (EditText)findViewById(R.id.bodyEdit);
				
				String title = titleEdit.getText().toString();
				String[] body = bodyEdit.getText().toString().split("\n");
				String name = StudentInfo.getName();
				String id = StudentInfo.getID();
				
				// 현재 시간을 msec으로 구한다.
				long now = System.currentTimeMillis();
				// 현재 시간을 저장 한다.
				Date date = new Date(now);
				// 시간 포맷으로 만든다.
				SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				String strNow = sdfNow.format(date);
				
				String grade = StudentInfo.getGrade();
				
				if (myTcpClient != null) {
					myTcpClient.sendMessage(title);
					myTcpClient.sendMessage(name);
					myTcpClient.sendMessage(id);
					myTcpClient.sendMessage(strNow);
					myTcpClient.sendMessage(grade);
					myTcpClient.sendMessage(String.valueOf(body.length));
					for(int i=0; i<body.length; i++)
					{
						myTcpClient.sendMessage(body[i]);
					}
				}
				
				while (serverMessage == null || serverMessage.isEmpty()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(serverMessage.equals("SUCESS_INSERT"))
				{
					Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
					myTcpClient.stopClient();
					serverMessage = null;
					finish();
				}
				else
					Toast.makeText(getApplicationContext(), "등록 실패 다시 입력하세요", Toast.LENGTH_SHORT).show();
				
			}
		});
	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		
	}

}
