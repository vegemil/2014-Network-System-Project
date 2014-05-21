package com.example.want;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private NfcAdapter nfcAdapter;
	private PendingIntent pendingIntent;
	static String[] serverMessage = new String[3];
	static int count = 0;

	private TCPClient myTcpClient;

	// StudentInfo myStudentInfo;

	public class connectTask extends AsyncTask<String, String, String> {
		public MainActivity delegate = null;

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
					count++;

					message = serverMessage[0];
					Log.i("Tag ID", "서버에서 받은 값 : " + message);
				}
			}, 7777);
			myTcpClient.run();
			return null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		//nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		// Intent intent = new Intent(this,
		// getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		// pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

		// final connectTask connect = new connectTask();
		// connect.execute("");
		// connect.delegate = this;

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		ImageButton logoutButton = (ImageButton) findViewById(R.id.loginoutButton);
		logoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Login.class);
				startActivity(intent);
			}
		});

		ImageButton attendanceButton = (ImageButton) findViewById(R.id.attendanceButton);
		attendanceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Attendance.class);
				startActivity(intent);
			}
		});

		ImageButton communityButton = (ImageButton) findViewById(R.id.communityButton);
		communityButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Community.class);
				startActivity(intent);
			}
		});

		ImageButton majorinformationButton = (ImageButton) findViewById(R.id.majorinformationButton);
		majorinformationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Major_Information.class);
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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	protected void onPause() {
		if (nfcAdapter != null) {
			nfcAdapter.disableForegroundDispatch(this);
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (nfcAdapter != null) {
			nfcAdapter
					.enableForegroundDispatch(this, pendingIntent, null, null);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

//		Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//		if (tag != null) {
//			byte[] tagId = tag.getId();
//
//			serverMessage[0] = toHexString(tagId); // tagid서버로 전송
//
//			Toast.makeText(getApplicationContext(), "태그되었습니다.",
//					Toast.LENGTH_SHORT).show();
//
//			if (myTcpClient != null) {
//				myTcpClient.sendMessage(serverMessage[0]);
//			}
//
//			while (serverMessage[0] == null || serverMessage[0].isEmpty()) {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//			myTcpClient.stopClient();
//		}
	}

	public static final String CHARS = "0123456789ABCDEF";

	public static String toHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; ++i) {
			sb.append(CHARS.charAt((data[i] >> 4) & 0x0F)).append(
					CHARS.charAt(data[i] & 0x0F));
		}
		return sb.toString();
	}

}
