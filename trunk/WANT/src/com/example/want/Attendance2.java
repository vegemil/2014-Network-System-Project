package com.example.want;

import android.R.string;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

public class Attendance2 extends ActionBarActivity {
	

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.attendance2);
      
     
  	
      // 액션바 숨김
      ActionBar actionBar = getSupportActionBar();
      actionBar.hide();
      
      Intent intent = getIntent();
      String id=intent.getExtras().getString("id").toString();
      String time=intent.getExtras().getString("time").toString();
      Log.i("아이디!!!!!!!!", "받은id: "+id);
      Log.i("시간!!!!!","받은시간: "+ time);
      
      
     // TableLayout tl= (TableLayout)findViewById(R.id.firstweek1);
      

      if(intent.getExtras().getString("id").toString().equals("200731029")){
    	  TextView firstweek2 = (TextView)findViewById(R.id.firstweek1);
          firstweek2.setText(time.toString());  
      }

      else if(intent.getExtras().getString("id").toString().equals("200831028")){
    	  TextView firstweek2 = (TextView)findViewById(R.id.firstweek3);
          firstweek2.setText(time);  
      }
      else if(intent.getExtras().getString("id").toString().equals("200931032")){
    	  TextView firstweek2 = (TextView)findViewById(R.id.firstweek4);
          firstweek2.setText(time);  
      }
      else if(intent.getExtras().getString("id").toString().equals("201031006")){
    	  TextView firstweek2 = (TextView)findViewById(R.id.firstweek5);
          firstweek2.setText(time);  
      }
      else if(intent.getExtras().getString("id").toString().equals("201131010")){
    	  TextView firstweek2 = (TextView)findViewById(R.id.firstweek6);
          firstweek2.setText(time);  
      }
      else if(intent.getExtras().getString("id").toString().equals("201131025")){
    	  TextView firstweek2 = (TextView)findViewById(R.id.firstweek7);
          firstweek2.setText(time);  
      }
      else if(intent.getExtras().getString("id").toString().equals("201131035")){
    	  TextView firstweek2 = (TextView)findViewById(R.id.firstweek8);
          firstweek2.setText(time);  
      }
      
    
      
      
      final TextView subjectText = (TextView) findViewById(R.id.subjectText);
      final TextView monthText = (TextView) findViewById(R.id.monthText);
      subjectText.setMinWidth(79);
      monthText.setMinWidth(50);

      Spinner monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
      ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,
            R.array.mon, android.R.layout.simple_spinner_item);
      adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      monthSpinner.setAdapter(adapter1);
      monthSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

         @Override
         public void onItemSelected(AdapterView<?> parent, View view,
               int position, long id) {
            // TODO Auto-generated method stub
            String month = parent.getItemAtPosition(position).toString();
            monthText.setText(month);
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub

            monthText.setText("월선택");
         }
      });

      Spinner subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
      ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
            R.array.sub, android.R.layout.simple_spinner_item);
      adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      subjectSpinner.setAdapter(adapter2);
      subjectSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

         @Override
         public void onItemSelected(AdapterView<?> parent, View view,
               int position, long id) {
            // TODO Auto-generated method stub

            String subject = parent.getItemAtPosition(position).toString();
            subjectText.setText(subject);
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
            subjectText.setText("과목명");
         }
      });

   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {

      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
}