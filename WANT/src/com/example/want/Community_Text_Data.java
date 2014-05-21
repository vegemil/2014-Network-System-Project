package com.example.want;

public class Community_Text_Data {
	
	private static String writer;
	private static String id; 
	private static String context;
	private static String date;
	private static int textnum;
	
	public static String getWriter()
	{
		return writer;
	}
	
	public static void setWriter(String InputWriter)
	{
		writer = InputWriter;
	}
	
	public static String getID()
	{
		return id;
	}
	
	public static void setID(String InputID)
	{
		id = InputID;
	}

	public static String getContext()
	{
		return context;
	}
	
	public static void setContext(String InputContext)
	{
		context = InputContext;
	}
	
	public static String getDate()
	{
		return date;
	}
	
	public static void setDate(String InputDate)
	{
		date = InputDate;
	}
	
	public static int getTextNum()
	{
		return textnum;
	}
	
	public static void setTextNum(int InputTextNum)
	{
		textnum = InputTextNum;
	}
}
