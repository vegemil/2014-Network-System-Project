import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JoinServer extends Thread {
	public static final int SERVERPORT = 4444;
	private PrintWriter Out;

	public void sendMessage(String message) {
		if (Out != null && !Out.checkError()) {
			Out.println(message);
			Out.flush();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		try {
			System.out.println("S : Connecting");

			ServerSocket serverSocket = new ServerSocket(SERVERPORT);

			Socket client = serverSocket.accept();
			System.out.println("S : Receving");

			try {
				// 스트링 전송
				Out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(client.getOutputStream())), true);

				// 스트링 받음
				BufferedReader in = new BufferedReader(new InputStreamReader(
						client.getInputStream()));

				String name = in.readLine();
				String grade = in.readLine();
				String id = in.readLine();
				String password = in.readLine();
				
				System.out.println("name : " + name);
				System.out.println("grade : " + grade);
				System.out.println("id : " + id);
				System.out.println("password : "+ password);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("S : Error");
				e.printStackTrace();
			}
			finally{
				client.close();
				System.out.println("S : Done");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("S : Error");
			e.printStackTrace();
		}
	}

}
