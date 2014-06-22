package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;

import android.widget.TextView;

public class Professor_Introduction extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.professor_introduction);

		// 액션바 숨김
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
			}
		});

		TextView kim = (TextView) findViewById(R.id.kim);
		TextView jung = (TextView) findViewById(R.id.jung);
		TextView lim = (TextView) findViewById(R.id.lim);

		kim.setText("성명 : 김학수\r\n직위 : 교수\r\n연구실 : 정보과학관 6320호\r\n전화번호 : 02-2610-4347\r\n이메일 : hskim@skhu.ac.kr"
				+ " \r\n학력사항 : 연세대학교\r\n                  전자공학과\r\n관심분야 : 영상통신, 영상압축,\r\n                  영상처리");

		jung.setText("성명 : 정연식\r\n직위 : 부교수\r\n연구실 : 정보과학관 6314호\r\n전화번호 : 02-2610-4726\r\n이메일 : ysjeong@skhu.ac.kr"
				+ " \r\n학력사항 : 연세대학교\r\n                  전자공학과\r\n관심분야 : Wireless Networks and Mobile Computing");

		lim.setText("성명 : 임충규\r\n직위 : 조교수\r\n연구실 : 정보과학관 6308호\r\n전화번호 : 02-2610-4313\r\n이메일 : cglim@skhu.ac.kr"
				+ " \r\n학력사항 : Louisiana State\r\n                  University\r\n                  Computer Science\r\n관심분야 : 컴퓨터 그래픽스\r\n                  컴퓨터 게임, 모델링");

	}

}
