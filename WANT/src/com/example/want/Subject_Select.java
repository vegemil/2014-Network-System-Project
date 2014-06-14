package com.example.want;

//import java.util.HashMap;

//import com.example.want.Subject_Select.connectTask;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class Subject_Select extends ActionBarActivity {
	static final String[] inforlist = { "네트워크시스템프로젝트", "게임프로젝트" };
	private ArrayAdapter<String> arrayAdapter;
	private ListView listView;
	/*int count = 0;
	private TCPClient myTcpClient;
	
	static String[] serverMessage = new String[500];
	
	int _2nd = 0;
	int _3nd = 0;
	
	public class connectTask extends AsyncTask<String, String, String> {
		public Subject_Select delegate = null;
		
		
		
		@Override
		protected String doInBackground(String... message) {
			
			Log.i("Hi", "im doin!!!!!!!!!!!!");
			 
			// we create a TCPClient object and
			myTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
				
				@Override
				// here the messageReceived method is implemented
				public void messageReceived(String message) {
					// this method calls the onProgressUpdate
					publishProgress(message);
					// serverMessage[0] = message;
										
					serverMessage[count] = message;
					//count++;
					Log.i("TAG", count + "서버에서 받은 값: " + serverMessage[count]);
					Log.i("TAG", "0번째받은 값: " + serverMessage[0]);
					Log.i("TAG", "1번째 받은 값: " + serverMessage[1]);
					count++;
				}
			}, 8888);
			myTcpClient.run();

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);

			Log.i("tag", "스레드 value : " + values[0]);
		}

	}
*/
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_select);
		
		/*// 서버접속 요청
				final connectTask connect = new connectTask();
				connect.executeOnExecutor(connectTask.THREAD_POOL_EXECUTOR);
				//connect.execute("");
				connect.delegate = this;
				Log.i("Hi", "im Att1!!!!!!!!!!!!");*/

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, inforlist);

		listView = (ListView) findViewById(R.id.subjectlist);
		listView.setAdapter(arrayAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setOnItemClickListener(onItemClickListener);

		/*ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
			}
		});*/
		/*try {
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
		}*/

		/*HashMap<String, String> attendance = new HashMap<String, String>();

		for (int i = 0; i < Integer.parseInt(serverMessage[0]); i++) {
			Log.i("몇개찍히니??", serverMessage[0]);
			attendance.put("id", serverMessage[(2 * i) + 1]);
			Log.i("TestID", serverMessage[(2 * i) + 1]);
			attendance.put("time", serverMessage[2 * (i + 1)]);
			Log.i("Test", serverMessage[2 * (i + 1)]);
			_2nd++;
			_3nd++;
		}*/

		
	}

	

	private OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()

	{

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id)

		{
			if (position == 0) {
				Intent intent = new Intent(Subject_Select.this,
						Subject_Network.class);
				startActivity(intent);
			}
			if (position == 1) {
				Intent intent = new Intent(Subject_Select.this,
						Subject_Game.class);
				startActivity(intent);
			}
						
		}

	};

}
