package com.example.want;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.want.Write_comment_secondgrade.connectTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Write_comment_thirdgrade extends ActionBarActivity implements
AsyncResponse {
	
	private TCPClient myTcpClient;

	private String serverMessage;
	
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

					Log.i("tag", "�������� ���� �� : " + message);
				}
			}, 9997);
			myTcpClient.run();

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);

			Log.i("tag", "������ value : " + values[0]);

		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_comment_thirdgrader);
		
		final connectTask connect = new connectTask();
		connect.execute("");
		connect.delegate = this;

		// �׼ǹ� ����
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		Button okButton = (Button) findViewById(R.id.okButton);
		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText commentEdit = (EditText) findViewById(R.id.comment);

				String communityTextIndex = Community_Text_Data.getTextNum();
				String id = StudentInfo.getID();
				String comment = commentEdit.getText().toString();
				String name = StudentInfo.getName();
				String grade = StudentInfo.getGrade();

				// ���� �ð��� msec���� ���Ѵ�.
				long now = System.currentTimeMillis();
				// ���� �ð��� ���� �Ѵ�.
				Date date = new Date(now);
				// �ð� �������� �����.
				SimpleDateFormat sdfNow = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				String strNow = sdfNow.format(date);

				if (myTcpClient != null) {
					myTcpClient.sendMessage(communityTextIndex);
					myTcpClient.sendMessage(id);
					myTcpClient.sendMessage(comment);
					myTcpClient.sendMessage(name);
					myTcpClient.sendMessage(strNow);
					myTcpClient.sendMessage(grade);
				}

				while (serverMessage == null || serverMessage.isEmpty()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (serverMessage.equals("SUCESS_INSERT")) {
					Toast.makeText(getApplicationContext(), "��ϵǾ����ϴ�.",
							Toast.LENGTH_SHORT).show();

					myTcpClient.stopClient();
					finish();
				} else
					Toast.makeText(getApplicationContext(), "��� ���� �ٽ� �Է��ϼ���",
							Toast.LENGTH_SHORT).show();

				serverMessage = null;
			}
		});
	}

	@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		
	}
}
