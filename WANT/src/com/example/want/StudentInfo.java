package com.example.want;

import android.util.Log;

public class StudentInfo {
	private static String id = "";
	private static String password = "";
	private static String grade = "";
	private static String name = "";

	public static void setID(String inputID) {
		id = inputID;
		Log.i("studentInfo", id);
	}

	public static String getID() {
		return id;
	}

	public static void setPassword(String inputPassword) {
		password = inputPassword;
		Log.i("studentInfo", password);
	}

	public static String getPassword() {
		return password;
	}

	public static void setGrade(String inputgrade) {
		grade = inputgrade;
		Log.i("studentInfo", grade);
	}

	public static String getGrade() {
		return grade;
	}

	public static void setName(String inputName) {
		name = inputName;
		Log.i("studentInfo", name);
	}

	public static String getName() {
		return name;
	}
}
