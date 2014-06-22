package com.example.want;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Major_Introduction extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.major_introduction);

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

		TextView majorintro = (TextView) findViewById(R.id.majorintro);

		majorintro
				.setText("우리 학과는 사람들이\r\n편리하게 생활할 수\r\n있도록, 그로 인해\r\n삶의 질을 더 높일\r\n수 있도록 소중한 인재를\r\n"
						+ "양성하는데 교육의 목표를 두고 있습니다.\r\n\r\n프로그래밍 언어, 네트워크, 컴퓨터그래픽스, 게임프로그래밍, 멀티미디어처리 등의 다양한 교과과정을"
						+ " 학생들에게 제공하고 있으며 학생들의 융합적인 사고와 실무 능력을 배양하기 위한 프로젝트 수업 및 회사를 미리 경험해볼 수 있는 현장연수 수업도 제공하고 있습니다."
						+ "\r\n\r\n우리는 미래 지향적인 인재들을 만들기 위해 노력하고 있습니다.\r\n\r\n여러분 도전하십시오.\r\n\r\n\r\n\r\n                          - 성공회대학교 컴퓨터공학과");

	}

}
