package com.example.want;

public class Community_Data {
	private String title;
	private String writer;
	private String date;
	
	public Community_Data(String title, String writer, String date)
	{
		this.title = title;
		this.writer = writer;
		this.date = date;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getWriter()
	{
		return writer;
	}
	
	public String getDate()
	{
		return date;
	}
	
}
