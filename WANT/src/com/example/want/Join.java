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

public class Join extends ActionBarActivity {

	String name;
	String grade;
	String id;
	String password;

	String result;

	private TCPClient myTcpClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join);

		// ¾×¼Ç¹Ù ¼û±è
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

		// connect to the server
		new connectTask().execute("");

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

			}
		});
				
	}

	public class connectTask extends AsyncTask<String, String, TCPClient> {

		@Override
		protected TCPClient doInBackground(String... message) {

			// we create a TCPClient object and
			myTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {

				@Override
				// here the messageReceived method is implemented
				public void messageReceived(String message) {
					// this method calls the onProgressUpdate
					publishProgress(message);
				}
			});
			myTcpClient.run();

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			result = values[0];
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT)
					.show();
		}
	}

}