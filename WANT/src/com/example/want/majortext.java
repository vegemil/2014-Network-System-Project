package com.example.want;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;



import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;




public class majortext extends Activity {
	TextView tv;
	Intent it;
	String tgg;
	private Source source;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.majortext);
	    
	    android.app.ActionBar actionBar = getActionBar();
		actionBar.hide();
	    it=getIntent();
	    tv = (TextView)findViewById(R.id.tv_major);
	    
	    StringBuffer sb = new StringBuffer("http://mse.skhu.ac.kr/news/");
	    //tv.setText(it.getStringExtra("text"));
	    sb.append(it.getStringExtra("text"));
	    System.out.println(sb);
	     
	    
	    if(android.os.Build.VERSION.SDK_INT > 9) {   //스레드 문제를 해결하기 위해 사용

	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy);
	    }
	    
	    try{
	    	URL aURL = new URL(sb.toString());
	    	InputStream html = aURL.openStream(); //가져오는 HTML 인코딩형식
	    	
	    	source = new Source(new InputStreamReader(html,"EUC-KR"));
	    	
	    	Element table = (Element)source.getAllElements(HTMLElementName.TABLE).get(5);
	    	Element tr = (Element)table.getAllElements(HTMLElementName.TABLE).get(0);
	    	
	    	
	    	System.out.println("테이블의 개수: "+source.getAllElements(HTMLElementName.TABLE).size());
	    	System.out.println("TR의 개수: "+table.getAllElements(HTMLElementName.TR).size());
	    	System.out.println("TD의 개수: "+tr.getAllElements(HTMLElementName.TD).size());
	    	
	    	tgg = ((Element) tr.getAllElements(HTMLElementName.TD).get(0)).getContent().toString();
	    	String tgg2 = tgg.replace("<br>", "");
	    	String tgg3 = tgg2.replace("<!-- border-width:1px; border-color:white; border-style:dashed; -->", "<내용>\n");
	    	String tgg4 = tgg3.replace("&#039;", "");
	    	String tgg5 = tgg4.replace("&gt;", "");
	    	String tgg6 = tgg5.replaceAll("\n", "");
	    	String tgg7 = tgg6.replaceAll(" ","");
	    	
	    	
	    	tv.setText(tgg6);
	    	tv.setMovementMethod(ScrollingMovementMethod.getInstance());
	     
	    }catch(IOException e){
	    	System.out.println("잘못된 URL 입니다.");
	    	
	    }   
	       
    
	}
}



	
