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

		TextView majorintro = (TextView) findViewById(R.id.majorintro);

		majorintro
				.setText("�츮 �а��� �������\r\n���ϰ� ��Ȱ�� ��\r\n�ֵ���, �׷� ����\r\n���� ���� �� ����\r\n�� �ֵ��� ������ ���縦\r\n"
						+ "�缺�ϴµ� ������ ��ǥ�� �ΰ� �ֽ��ϴ�.\r\n\r\n���α׷��� ���, ��Ʈ��ũ, ��ǻ�ͱ׷��Ƚ�, �������α׷���, ��Ƽ�̵��ó�� ���� �پ��� ����������"
						+ " �л��鿡�� �����ϰ� ������ �л����� �������� ���� �ǹ� �ɷ��� ����ϱ� ���� ������Ʈ ���� �� ȸ�縦 �̸� �����غ� �� �ִ� ���忬�� ������ �����ϰ� �ֽ��ϴ�."
						+ "\r\n\r\n�츮�� �̷� �������� ������� ����� ���� ����ϰ� �ֽ��ϴ�.\r\n\r\n������ �����Ͻʽÿ�.\r\n\r\n\r\n\r\n                          - ����ȸ���б� ��ǻ�Ͱ��а�");

	}

}
