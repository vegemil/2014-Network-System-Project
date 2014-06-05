package com.example.want;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetworkManager {
	public static final String SERVERIP = "192.168.0.13"; 
	public static int SERVERPORT = 3412;
	private static Socket socket;
	static PrintWriter out;
	BufferedReader in;
	
	public NetworkManager() throws IOException {
		// TODO Auto-generated constructor stub
		// send the message to the server
		out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream(),
						"UTF-8")), true);
		in = new BufferedReader(new InputStreamReader(
				socket.getInputStream(), "UTF-8"));
	}
	
	
	public static Socket getSocket() throws IOException
	{
		if(socket == null)
			socket = new Socket();
		
		if(socket != null)
			socket.connect(new InetSocketAddress(SERVERIP, SERVERPORT));
		return socket;
	}
	
	public static void closeSocket() throws IOException
	{
		if(socket !=null)
			socket.close();
	}
	
	public static void sendMsg(String message) throws IOException
	{
		if (out != null && !out.checkError()) {
			out.println(message);
			out.flush();
		}
	}
}
