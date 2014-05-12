package com.example.want;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class majortext extends Activity {
TextView tv;
Intent it;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.majortext);
	    it=getIntent();
	    tv = (TextView)findViewById(R.id.tv_major);
	    tv.setText(it.getStringExtra("text"));
	    // TODO Auto-generated method stub
	}

}
