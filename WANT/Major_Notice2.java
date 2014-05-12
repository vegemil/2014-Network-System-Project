package com.example.want;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.AdapterView.OnItemClickListener;


public class Major_Notice2 {
	private String url;
	private Context context;
	private Handler handler;
	private ProgressDialog progressDialog;
	private Source source;
	private ArrayList<HashMap<String, String>> data;
	
	public Major_Notice2(Context context, Handler handler, ArrayList<HashMap<String, String>> data)
	{
		this.context = context;
		this.handler = handler;
		this.data = data;
	}
	
	public void open()
	{
		//파싱할 주소
		url = "http://mse.skhu.ac.kr/news/board.php";
		
		//처리하기
		try
		{
			process();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void process() throws IOException
	{
		//상태 Progress 띄우기 위해서 사용함!
		final Handler mHandler = new Handler();
		new Thread()
		{

			@Override
			public void run()
			{
				URL nURL;
				try
				{
					nURL = new URL(url);
					mHandler.post(new Runnable(){

						@Override
						public void run()
						{
							progressDialog = ProgressDialog.show(context, "", "Data loding...");
						}
					});
					
					//모든 데이터 초기화
					data.clear();
					
					InputStream html = nURL.openStream();
					//가져오는 HTML의 인코딩형식
					source = new Source(new InputStreamReader(html, "EUC-KR"));
					
					//테이블가져오기
					Element table = (Element) source.getAllElements(HTMLElementName.TABLE).get(3);
					
					System.out.println("테이블 개수!!!!" + source.getAllElements(HTMLElementName.TABLE).size());
					System.out.println("TR개수 " + table.getAllElements(HTMLElementName.TR).size());
					//테이블 안의 TR 개수
					int tr_count = table.getAllElements(HTMLElementName.TR).size();
					
                    Element tr = null;
                    Element td=null;
					
					HashMap<String, String> hm = null;
					
					for(int i=0; i<tr_count; i=i+2)
					{
						tr = (Element) table.getAllElements(HTMLElementName.TR).get(i);
						td= (Element) tr.getAllElements(HTMLElementName.TD).get(1);
						
						hm = new HashMap<String, String>();
						   
						hm.put("test", ((Element) tr.getAllElements(HTMLElementName.TD).get(0)).getContent().toString());
						hm.put("title", ((Element) td.getAllElements(HTMLElementName.SPAN).get(0)).getContent().toString());
						hm.put("writer", ((Element) tr.getAllElements(HTMLElementName.TD).get(2)).getContent().toString());
						hm.put("day", ((Element) tr.getAllElements(HTMLElementName.TD).get(3)).getContent().toString());
						hm.put("AAA", ((Element) td.getAllElements(HTMLElementName.A).get(0)).getContent().toString());
						data.add(hm);
					}
				
				
					
					
					mHandler.post(new Runnable()
					{
						public void run()
						{
							progressDialog.cancel();
							//업데이트 완료를 핸들러로 보내줌
							handler.sendEmptyMessage(0);
						}
					});
				}catch (MalformedURLException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
			
		}.start();
	}
}