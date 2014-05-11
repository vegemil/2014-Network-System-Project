package com.example.want;

import android.util.Log;

public class StudentInfo {
	private String id;
	private String password;
	private String grade;
	private String name;

	public void setID(String inputID) {
		id = inputID;
		Log.i("studentInfo", id);
	}

	public String getID() {
		return id;
	}

	public void setPassword(String inputPassword) {
		password = inputPassword;
		Log.i("studentInfo", password);
	}

	public String getPassword() {
		return password;
	}

	public void setGrade(String inputgrade) {
		grade = inputgrade;
		Log.i("studentInfo", grade);
	}

	public String getGrade() {
		return grade;
	}

	public void setName(String inputName) {
		name = inputName;
		Log.i("studentInfo", name);
	}

	public String getName() {
		return name;
	}
}
