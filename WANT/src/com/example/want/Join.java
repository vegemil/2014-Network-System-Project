package com.example.want;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;




import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class Join extends ActionBarActivity {

	String name;
	String grade;
	String id;
	String password;



	static String SERVERIP = "172.30.4.76";
	static int PORT = 44444;
	
	byte[] result;



	PrintWriter out;
	BufferedReader in;

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

				
				tcp_client tc = new tcp_client();
				tc.execute("name", "grade", "id", "password");
			}
		});

	}


	public class tcp_client extends android.os.AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				InetAddress serverAddr = InetAddress.getByName(SERVERIP);
				Socket sock = new Socket(serverAddr, PORT);
				

				DataInputStream input = new DataInputStream(
						sock.getInputStream());
				DataOutputStream output = new DataOutputStream(
						sock.getOutputStream());

				try {
					for (String data : params)
					{
						Log.i("tag", data);
						output.writeChars(data);
						output.flush();
					}
					
					result = null;
					
					input.read(result);
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.i("tag", "message isn't work");
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return null;
		}

	}
}