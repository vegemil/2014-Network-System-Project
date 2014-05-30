package com.example.want;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class Attendance2 extends ActionBarActivity {

	private NfcAdapter nfcAdapter;
	private PendingIntent pendingIntent;
	static String[] clientMessage = new String[3];
	static String[] serverMessage = new String[3];
	static int count = 0;
	private TCPClient myTcpClient;
	private SharedPreferences sp; //추가한 부분
    
	
	
	public class connectTask extends AsyncTask<String, String, String> {
		public Attendance2 delegate = null;
		

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
	    

	    protected void onPause() {
			if (nfcAdapter != null) {
				nfcAdapter.disableForegroundDispatch(this);
			}
			myTcpClient.stopClient();
			super.onPause();
		}

		@Override
		protected void onResume() {
			super.onResume();
			if (nfcAdapter != null) {
				nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
			}
		}

		@Override
		protected void onNewIntent(Intent intent) {
			super.onNewIntent(intent);
		
			Calendar cal=new GregorianCalendar();
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			if (tag != null) {
				byte[] tagId = tag.getId();
				
				clientMessage[0] = toHexString(tagId); // tagid서버로 전송		 
				clientMessage[1] = sp.getString("id", "null");
				clientMessage[2] = String.format("%d/%d/%d/%d:%d", cal.get(Calendar.YEAR)
     					, cal.get(Calendar.MONTH)+1
     					, cal.get(Calendar.DAY_OF_MONTH)
     					, cal.get(Calendar.HOUR_OF_DAY)
     					, cal.get(Calendar.MINUTE));	
				Toast.makeText(getApplicationContext(), "태그되었습니다.", Toast.LENGTH_SHORT).show();
	
				if (myTcpClient != null) {
					myTcpClient.sendMessage(clientMessage[0]);
					myTcpClient.sendMessage(clientMessage[1]);
					myTcpClient.sendMessage(clientMessage[2]);
				}
				
				
				while (clientMessage[0] == null || clientMessage[0].isEmpty()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}			
			
		}
			

		public static final String CHARS = "0123456789ABCDEF";
		
		public static String toHexString(byte[] data) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < data.length; ++i) {
				sb.append(CHARS.charAt((data[i] >> 4) & 0x0F))
					.append(CHARS.charAt(data[i] & 0x0F));
			}
			return sb.toString();
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendance);
		//nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		sp = (SharedPreferences)getSharedPreferences("pref", 0); //1
		
		final connectTask connect = new connectTask();
		connect.execute("");
		
		connect.delegate = this;
		
		
		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}