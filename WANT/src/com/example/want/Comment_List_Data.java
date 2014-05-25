package com.example.want;

public class Comment_List_Data {
	private String context;
	private String writer;
	private String date;

	public Comment_List_Data(String context, String writer, String date) {
		this.context = context;
		this.writer = writer;
		this.date = date;
	}

	public String getContext() {
		return context;
	}

	public String getWriter() {
		return writer;
	}

	public String getDate() {
		return date;
	}

}
