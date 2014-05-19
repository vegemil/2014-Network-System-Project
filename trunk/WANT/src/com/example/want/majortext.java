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
	    	String tgg3 = tgg2.replace("<!-- border-width:1px; border-color:white; border-style:dashed; -->", "<내용>");
	    	String tgg4 = tgg3.replace("&#039;", "");
	    	String tgg5 = tgg4.replace("&gt;", "");
	    	String tgg6 = tgg5.replace(" ","");
	    	
	    	tv.setText(tgg6);
	        
	    	
	    	
	    	
	    	/*BufferedReader in = new BufferedReader(new InputStreamReader(aURL.openStream(),"utf-8"));
	    	
	    	String inputLine;
	    	
	    	while((inputLine = in.readLine()) !=null)
	    		System.out.println(inputLine);
	    	in.close();*/
	    	
	    }catch(IOException e){
	    	System.out.println("잘못된 URL 입니다.");
	    	
	    }   
	    
	    
	    
	    
	    
	}
}















	    /*
	    HttpClient client = new DefaultHttpClient();
	    String url = "http://www.naver.com";
	    HttpGet get = new HttpGet(url);
	    HttpResponse response = null;
	    try{
	    	response = client.execute(get);	    	
	    } catch (ClientProtocolException e){
	    	e.printStackTrace();
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
	    HttpEntity resEntity = response.getEntity();
	    String sRes = "";
	    if(resEntity !=null){
	    	try{
	    		sRes = EntityUtils.toString(resEntity);
	    		Log.w("SNSApp","response:"+sRes);
	    		Toast.makeText(getBaseContext(), sRes, Toast.LENGTH_SHORT).show();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       

	    	}*/
	
	    //tv.setText(it.getStringExtra("text"));
	    //StringBuilder content = new StringBuilder();
	    
	   /* StringBuffer sb = new StringBuffer("http://mse.skhu.ac.kr/news/");
	    tv.setText(it.getStringExtra("text"));
	    sb.append(it.getStringExtra("text"));
	    System.out.println(sb);*/	     
	
	    







	  /*  try{
	URL aURL = new URL("http://www.nate.com");
	BufferedReader in = new BufferedReader(new InputStreamReader(aURL.openStream()));
	
	String inputLine;
	
	while((inputLine = in.readLine()) !=null)
		System.out.println(inputLine);
	in.close();
}catch(IOException e){
	System.out.println("URL에서 데이터를 읽는 중 오류 발생");
}*/
	/*public static void main(String url){
		try{
			URL aURL = new URL("http://www.nate.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(aURL.openStream()));
			
			String inputLine;
			
			while((inputLine = in.readLine()) !=null)
				System.out.println(inputLine);
			in.close();
		}catch(IOException e){
			System.out.println("URL에서 데이터를 읽는 중 오류 발생");
		}
			
		
	}*/
	

	
