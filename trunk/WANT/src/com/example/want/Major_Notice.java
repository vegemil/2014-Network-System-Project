package com.example.want;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;



public class Major_Notice extends ActionBarActivity {
	
	private ArrayList<HashMap<String, String>> data;	
	private ListView list;
	private SimpleAdapter sa;
	private Major_Notice2 hp;
		
	private final Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			listUpdate();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.major_notice);
			
		    data = new ArrayList<HashMap<String, String>>();	        
	        list = (ListView)findViewById(R.id.listView1);	        
	        hp = new Major_Notice2(Major_Notice.this, handler, data);	        
	        sa = new SimpleAdapter(Major_Notice.this, data, R.layout.major_notice2,
	        		new String[]{"title","writer","day","test","text"}, new int[]{R.id.tv_title, R.id.tv_writer, R.id.tv_day,R.id.tv_test,R.id.tv_major});
	        
	        
	        list.setAdapter(sa);
	        
	        list.setOnItemClickListener(new OnItemClickListener() {
				
	        	
	        	@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
	        		HashMap<String, String> item = (HashMap<String, String>)parent.getItemAtPosition(position);
	        		Intent it = new Intent(Major_Notice.this, majortext.class);
	        		it.putExtra("text", item.get("text"));
	        		startActivity(it);
	        		
					
				}
			});
	      
	       	       
		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		ImageButton homeButton = (ImageButton)findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});
		hp.open();
	}
	
	
	  //업데이트하기
	    private void listUpdate()
	    {
	    	sa.notifyDataSetChanged();
	    }
}
