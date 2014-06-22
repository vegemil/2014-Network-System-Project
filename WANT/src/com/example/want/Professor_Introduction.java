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

		// �׼ǹ� ����
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

		kim.setText("���� : ���м�\r\n���� : ����\r\n������ : �������а� 6320ȣ\r\n��ȭ��ȣ : 02-2610-4347\r\n�̸��� : hskim@skhu.ac.kr"
				+ " \r\n�з»��� : �������б�\r\n                  ���ڰ��а�\r\n���ɺо� : �������, �������,\r\n                  ����ó��");

		jung.setText("���� : ������\r\n���� : �α���\r\n������ : �������а� 6314ȣ\r\n��ȭ��ȣ : 02-2610-4726\r\n�̸��� : ysjeong@skhu.ac.kr"
				+ " \r\n�з»��� : �������б�\r\n                  ���ڰ��а�\r\n���ɺо� : Wireless Networks and Mobile Computing");

		lim.setText("���� : �����\r\n���� : ������\r\n������ : �������а� 6308ȣ\r\n��ȭ��ȣ : 02-2610-4313\r\n�̸��� : cglim@skhu.ac.kr"
				+ " \r\n�з»��� : Louisiana State\r\n                  University\r\n                  Computer Science\r\n���ɺо� : ��ǻ�� �׷��Ƚ�\r\n                  ��ǻ�� ����, �𵨸�");

	}

}
