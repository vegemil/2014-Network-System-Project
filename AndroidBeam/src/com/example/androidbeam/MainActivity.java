package com.example.androidbeam;

import java.nio.charset.Charset;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

//import com.appstudio.android.sample.R;

public class MainActivity extends Activity 
                        implements CreateNdefMessageCallback,
                                 OnNdefPushCompleteCallback {
  NfcAdapter mNfcAdapter;
  
  private PendingIntent pendingIntent;
  private TextView tagDesc;
  
  //TextView mInfoText;
  private static final int MESSAGE_SENT = 1;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    tagDesc = (TextView)findViewById(R.id.tagDesc);
    
    mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
  }
  
    @Override
	protected void onPause() {
		if (mNfcAdapter != null) {
			mNfcAdapter.disableForegroundDispatch(this);
		}
		super.onPause();
	}

	/*@Override
	protected void onResume() {
		super.onResume();
		if (mNfcAdapter != null) {
			mNfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
		}
	}*/
    
 
    
    @Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
    Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
	if (tag != null) {
		byte[] tagId = tag.getId();
		tagDesc.setText("TagID: " + toHexString(tagId));
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
    


	private TextView mInfoText;{
	
    mInfoText = (TextView) findViewById(R.id.textView);
    // NFC 어댑터가 사용 가능한 디바이스인지 검사
    mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    if (mNfcAdapter == null) {
      mInfoText = (TextView) findViewById(R.id.textView);
      mInfoText.setText("NFC 誘몄????붾컮?댁뒪");
    }
    // NDEF 메세지 생성을 위한 콜백 함수 설정
    mNfcAdapter.setNdefPushMessageCallback(this, this);
    // 메세지 전송 완료 후 콜백 함수 설정
    mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
    
    
  }
  
  
  /**
   * CreateNdefMessageCallback interface 구현
   */
  @Override
  public NdefMessage createNdefMessage(NfcEvent event) {
    Time time = new Time();
    time.setToNow();
    String text = ("Beam me up!\n\n" +
                    "Beam Time: " + time.format("%H:%M:%S"));
    NdefMessage msg = new NdefMessage(new NdefRecord[] { 
        createMimeRecord(
            "application/com.appstudio.sample.android.beam", 
                                             text.getBytes())
        // AAR을 사용하려면 아래의 주석을 푼다.  
        // ,NdefRecord.createApplicationRecord(
        //                    "com.appstudio.sample.android")
    });
    return msg;
  }

  /**
   * 메세지 전송이 완료됐을 때 호출되는 콜백 
   */
  @Override
  public void onNdefPushComplete(NfcEvent arg0) {
    // UI 스레드와는 다른 별개의 스레드가 이 메소드 콜백 호출
    // UI 스레드로 전달
    mHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
  }

  /** 메세지 전송 완료시 실행*/
  private final Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case MESSAGE_SENT:
          Toast.makeText(getApplicationContext(), 
                     "메세지 전송 완료!", Toast.LENGTH_LONG).show();
          break;
      }
    }
  };

  @Override
  public void onResume() {
    super.onResume();
    // NFC 태그에 의해 기동됐는지를 먼저 확인한다.
    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(
                                  getIntent().getAction())) {
      processIntent(getIntent());
    }
  }

  /**
   * 태그 메시지를 받으면 해당 인텐드에서 NDEF 메세지를 추출
   */
  void processIntent(Intent intent) {
    Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                             NfcAdapter.EXTRA_NDEF_MESSAGES);
    // 한 번에 하나의 메시지만을 수신
    NdefMessage msg = (NdefMessage) rawMsgs[0];
    // 첫 번째 레코드는 마임 타입을 담고 있으며,
    // 두 번째 레코드가 있다면 ARR을 담고 있다.
    mInfoText.setText(new String(
                          msg.getRecords()[0].getPayload()));
  }

  /**
   * TNF_MIME_MEDIA 타입의 태그 레코드 생성
   *
   * @param mimeType
   */
  public NdefRecord createMimeRecord(String mimeType, 
                                            byte[] payload) {
    byte[] mimeBytes = mimeType.getBytes(
                                Charset.forName("US-ASCII"));
    NdefRecord mimeRecord = new NdefRecord(
                        NdefRecord.TNF_MIME_MEDIA, mimeBytes,
                                       new byte[0], payload);
    return mimeRecord;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // NFC를 미지원하면 이 옵션 메뉴는 필요없다.
    if (mNfcAdapter == null) {
      return super.onCreateOptionsMenu(menu);
    }
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        Intent intent = new Intent(
                        Settings.ACTION_NFCSHARING_SETTINGS);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  }
  

