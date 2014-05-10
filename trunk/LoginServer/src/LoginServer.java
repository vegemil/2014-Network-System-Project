import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class LoginServer {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		String result = null;

		serverSocket = new ServerSocket(5555);

		try {

				clientSocket = serverSocket.accept();
				System.out.println("클라이언트 연결");

				try {
					out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(
									clientSocket.getOutputStream(), "UTF-8")),
							true);
					in = new BufferedReader(new InputStreamReader(
							clientSocket.getInputStream(), "UTF-8"));

					String id;
					String password;

					id = in.readLine();
					password = in.readLine();

					System.out.println(id);
					System.out.println(password);

					
					System.out.println("result : " + result);
					out.println(result);
					out.flush();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} 
				out.close();
				in.close();
				clientSocket.close();
				serverSocket.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		

}
