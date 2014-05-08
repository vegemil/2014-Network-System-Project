package com.example.want;

import java.io.BufferedReader;
import java.io.PrintWriter;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Join extends ActionBarActivity {

	static String inputname;
	static String inputgrade;
	static String inputid;
	static String inputpassword;

	private static final int JOIN = 1;

	private String serverMessage;
	public static final String SERVERIP = " ";
	public static final int SERVERPORT = 4444;

	PrintWriter out;
	BufferedReader in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join);

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

		ImageButton okButton = (ImageButton) findViewById(R.id.joinokButton);

		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment newFragment = new FireMissilesDialogFragment();
			    newFragment.show(this.getSupportFragmentManager(), "missiles");
			}
		});

	}

	 public class FireMissilesDialogFragment extends DialogFragment {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("회원가입하시겠습니까?")
					.setPositiveButton("취소",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									dialog.cancel();
								}
							})
					.setNegativeButton("확인",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									EditText nameEdit = (EditText)findViewById(R.id.joinnameedit);
									EditText gradeEdit = (EditText)findViewById(R.id.joingradeedit);
									EditText idEdit = (EditText)findViewById(R.id.joinidedit);
									EditText passwordEdit = (EditText)findViewById(R.id.joinpasswordedit);

									inputname = nameEdit.getText().toString();
									inputgrade = gradeEdit.getText().toString();
									inputid = idEdit.getText().toString();
									inputpassword = passwordEdit.getText().toString();
								}
							});
			// Create the AlertDialog object and return it
			return builder.create();
		}
	}

}
