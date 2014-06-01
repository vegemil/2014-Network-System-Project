package com.example.want;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.util.Log;

public class TCPClient {

	private String serverMessage;
	
	public static final String SERVERIP = "192.168.123.158"; // your computer IP address

	public static int SERVERPORT ;
	private OnMessageReceived mMessageListener = null;
	private boolean mRun = false;

	PrintWriter out;
	BufferedReader in;

	/**
	 * constructor of the class. OnMessageReceived listens for the messages
	 * received from server
	 */
	public TCPClient(OnMessageReceived listener, int serverPort) {
		mMessageListener = listener;
		SERVERPORT = serverPort;
	}

	/**
	 * Sends the message entered by client to the server
	 * 
	 * @param message
	 *            text entered by client
	 */
	public void sendMessage(String message) {
		if (out != null && !out.checkError()) {
			out.println(message);
			out.flush();
		}
	}

	public void stopClient() {
		mRun = false;
	}

	public void run() {
		mRun = true;

		try { 
			// here you must put your computer's IP address.
			InetAddress serverAddr = InetAddress.getByName(SERVERIP);

			Log.e("TCP Client", "C: Connecting...");

			// create a socket to make the connection with the server
			Socket socket = new Socket(serverAddr, SERVERPORT);

			try {
				// send the message to the server
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream(),
								"UTF-8")), true);

				Log.e("TCP Client", "C: Sent.");
				Log.e("TCP Client", "C: Done.");

				// receive the message which the server sends back
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream(), "UTF-8"));
       
				// in this while the client listens for the messages send by the
				// server
				while (mRun) {
					serverMessage = in.readLine();
					System.out.println(serverMessage);
					if (serverMessage != null && mMessageListener != null) {
						// call the method messageReceived from MyActivity class
						Log.e("RESPONSE FROM SERVER", "S: Received Message: '"
								+ serverMessage + "'");
						mMessageListener.messageReceived(serverMessage);
					}
					serverMessage = null;		
				}


			} catch (Exception e) {

				Log.e("TCP", "S: Error", e);

			} finally {
				// the socket must be closed. It is not possible to reconnect to
				// this socket
				// after it is closed, which means a new socket instance has to
				// be created.
				socket.close();
			}
		} catch (Exception e) {
			Log.e("TCP", "C:Error", e);
		}
	}

	/*
	 * Declare the interface. The method messageReceived(String message must be
	 * implemented in the MyActivity class at on asynckTask doInBackground
	 */
	public interface OnMessageReceived {
		public void messageReceived(String message);
	}
	
	public boolean isRunnable()
	{
		if(mRun == true)
			return true;
		else
			return false;
	}


}