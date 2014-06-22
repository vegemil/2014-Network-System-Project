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
import android.util.Log;

public class Major_Notice2 {
	private String url;
	private String url2;
	private String url3;
	private String url4;
	private Context context;
	private Handler handler;
	private ProgressDialog progressDialog;
	private Source source;
	private Source source2;
	private Source source3;
	private Source source4;
	private ArrayList<HashMap<String, String>> data;

	public Major_Notice2(Context context, Handler handler,
			ArrayList<HashMap<String, String>> data) {
		this.context = context;
		this.handler = handler;
		this.data = data;
	}

	public void open() {
		// 파싱할 주소
		url = "http://mse.skhu.ac.kr/news/board.php";
		url2 = "http://mse.skhu.ac.kr/news/board.php?&page=2";
		url3 = "http://mse.skhu.ac.kr/news/board.php?&page=3";
		url4 = "http://mse.skhu.ac.kr/news/board.php?&page=4";

		// 처리하기
		try {
			process();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process() throws IOException {
		// 상태 Progress 띄우기 위해서 사용함!
		final Handler mHandler = new Handler();
		new Thread() {

			@Override
			public void run() {
				URL nURL;
				URL nURL2;
				URL nURL3;
				URL nURL4;
				try {
					nURL = new URL(url);
					nURL2 = new URL(url2);
					nURL3 = new URL(url3);
					nURL4 = new URL(url4);
					mHandler.post(new Runnable() {

						@Override
						public void run() {
							progressDialog = ProgressDialog.show(context, "",
									"Data loding...");
						}
					});

					// 모든 데이터 초기화
					data.clear();

					InputStream html = nURL.openStream();
					InputStream html2 = nURL2.openStream();
					InputStream html3 = nURL3.openStream();
					InputStream html4 = nURL4.openStream();
					// 가져오는 HTML의 인코딩형식
					source = new Source(new InputStreamReader(html, "EUC-KR"));
					source2 = new Source(new InputStreamReader(html2, "EUC-KR"));
					source3 = new Source(new InputStreamReader(html3, "EUC-KR"));
					source4 = new Source(new InputStreamReader(html4, "EUC-KR"));

					// 테이블가져오기
					Element table = (Element) source.getAllElements(
							HTMLElementName.TABLE).get(3);
					Element table2 = (Element) source2.getAllElements(
							HTMLElementName.TABLE).get(3);
					Element table3 = (Element) source3.getAllElements(
							HTMLElementName.TABLE).get(3);
					Element table4 = (Element) source4.getAllElements(
							HTMLElementName.TABLE).get(3);

					System.out.println("테이블 개수!!!!"
							+ source.getAllElements(HTMLElementName.TABLE)
									.size());
					System.out.println("TR개수 "
							+ table.getAllElements(HTMLElementName.TR).size());
					System.out.println("A개수 "
							+ table.getAllElements(HTMLElementName.A).size());
					// 테이블 안의 TR 개수
					int tr_count = table.getAllElements(HTMLElementName.TR)
							.size();

					Element tr = null;
					Element td = null;
					Element aa = null;

					Element tr2 = null;
					Element td2 = null;
					Element aa2 = null;

					Element tr3 = null;
					Element td3 = null;
					Element aa3 = null;

					Element tr4 = null;
					Element td4 = null;
					Element aa4 = null;

					HashMap<String, String> hm = null;

					for (int i = 0; i < tr_count; i = i + 2) {
						tr = (Element) table.getAllElements(HTMLElementName.TR)
								.get(i);
						td = (Element) tr.getAllElements(HTMLElementName.TD)
								.get(1);
						aa = (Element) td.getAllElements(HTMLElementName.A)
								.get(0);

						hm = new HashMap<String, String>();

						hm.put("test",
								((Element) tr
										.getAllElements(HTMLElementName.TD)
										.get(0)).getContent().toString());
						hm.put("title",
								((Element) td.getAllElements(
										HTMLElementName.SPAN).get(0))
										.getContent().toString());
						hm.put("writer",
								((Element) tr
										.getAllElements(HTMLElementName.TD)
										.get(2)).getContent().toString());
						hm.put("day",
								((Element) tr
										.getAllElements(HTMLElementName.TD)
										.get(3)).getContent().toString());
						hm.put("text",
								(aa.getAttributeValue("href").toString()));
						Log.i("text",
								((Element) tr
										.getAllElements(HTMLElementName.TD)
										.get(1)).getContent().toString());
						data.add(hm);
					}

					for (int i = 0; i < tr_count; i = i + 2) {
						tr2 = (Element) table2.getAllElements(
								HTMLElementName.TR).get(i);
						td2 = (Element) tr2.getAllElements(HTMLElementName.TD)
								.get(1);
						aa2 = (Element) td2.getAllElements(HTMLElementName.A)
								.get(0);

						hm = new HashMap<String, String>();

						hm.put("test",
								((Element) tr2.getAllElements(
										HTMLElementName.TD).get(0))
										.getContent().toString());
						hm.put("title",
								((Element) td2.getAllElements(
										HTMLElementName.SPAN).get(0))
										.getContent().toString());
						hm.put("writer",
								((Element) tr2.getAllElements(
										HTMLElementName.TD).get(2))
										.getContent().toString());
						hm.put("day",
								((Element) tr2.getAllElements(
										HTMLElementName.TD).get(3))
										.getContent().toString());
						hm.put("text",
								(aa2.getAttributeValue("href").toString()));
						Log.i("text",
								((Element) tr2.getAllElements(
										HTMLElementName.TD).get(1))
										.getContent().toString());
						data.add(hm);
					}

					for (int i = 0; i < tr_count; i = i + 2) {
						tr3 = (Element) table3.getAllElements(
								HTMLElementName.TR).get(i);
						td3 = (Element) tr3.getAllElements(HTMLElementName.TD)
								.get(1);
						aa3 = (Element) td3.getAllElements(HTMLElementName.A)
								.get(0);

						hm = new HashMap<String, String>();

						hm.put("test",
								((Element) tr3.getAllElements(
										HTMLElementName.TD).get(0))
										.getContent().toString());
						hm.put("title",
								((Element) td3.getAllElements(
										HTMLElementName.SPAN).get(0))
										.getContent().toString());
						hm.put("writer",
								((Element) tr3.getAllElements(
										HTMLElementName.TD).get(2))
										.getContent().toString());
						hm.put("day",
								((Element) tr3.getAllElements(
										HTMLElementName.TD).get(3))
										.getContent().toString());
						hm.put("text",
								(aa3.getAttributeValue("href").toString()));
						Log.i("text",
								((Element) tr3.getAllElements(
										HTMLElementName.TD).get(1))
										.getContent().toString());
						data.add(hm);
					}

					for (int i = 0; i < tr_count; i = i + 2) {
						tr4 = (Element) table4.getAllElements(
								HTMLElementName.TR).get(i);
						td4 = (Element) tr4.getAllElements(HTMLElementName.TD)
								.get(1);
						aa4 = (Element) td4.getAllElements(HTMLElementName.A)
								.get(0);

						hm = new HashMap<String, String>();

						hm.put("test",
								((Element) tr4.getAllElements(
										HTMLElementName.TD).get(0))
										.getContent().toString());
						hm.put("title",
								((Element) td4.getAllElements(
										HTMLElementName.SPAN).get(0))
										.getContent().toString());
						hm.put("writer",
								((Element) tr4.getAllElements(
										HTMLElementName.TD).get(2))
										.getContent().toString());
						hm.put("day",
								((Element) tr4.getAllElements(
										HTMLElementName.TD).get(3))
										.getContent().toString());
						hm.put("text",
								(aa4.getAttributeValue("href").toString()));
						// hm.put("text", (aa.getAttributes().toString()));
						data.add(hm);
					}

					mHandler.post(new Runnable() {
						public void run() {
							progressDialog.cancel();
							// 업데이트 완료를 핸들러로 보내줌
							handler.sendEmptyMessage(0);
						}
					});
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}.start();
	}
}