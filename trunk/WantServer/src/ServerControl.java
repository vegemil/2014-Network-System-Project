import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerControl {

	/**
	 * 다수의 클라이언트가 접속하여 계속 통신하는 서버 프로그램 step 
	 * 1. Server Socket 
	 * 2. while loop에서 accept()하여 소켓 반환받는다. 
	 * 3. 소켓을 할당하는 ServerThread를 생성 
	 * 4. start()
	 */

	public void go() throws IOException {
		ServerSocket server_socket = null;
		Socket client_socket = null;

		try {
			server_socket = new ServerSocket(65535);
			System.out.println("**서버 실행**");
			
			// 다수의 클라이언트와 통신하기 위해 loop
			while (true) {
				client_socket = server_socket.accept(); // 클라이언트 접속시 새로운 소켓이 리턴
				System.out.println(client_socket.getInetAddress() + "님 입장");
				
				ServerThread st = new ServerThread(client_socket);
				st.start();
		
			}
			
		} 
		finally {
			if (client_socket != null)
				client_socket.close();
			if (server_socket != null)
				server_socket.close();
			System.out.println("**서버 종료**");
		}
	}

	public static void main(String[] args) {
		ServerControl server = new ServerControl();
		try {
			server.go();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
