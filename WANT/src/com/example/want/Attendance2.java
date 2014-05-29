package com.example.want;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Attendance2 extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.attendancecheck);
	    
	    Button check;
	    Button check2;
	    check = (Button)findViewById(R.id.att_button1);
	    check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(Attendance2.this,Attendance.class);
				startActivity(intent);			
			}
		});
	    check2 = (Button)findViewById(R.id.att_button2);
	    check2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(Attendance2.this,AttendanceCheck.class);
				startActivity(intent);	
				
			}
		});
	    
	
	    // TODO Auto-generated method stub
	}

}
