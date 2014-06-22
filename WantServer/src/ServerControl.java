import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerControl {

	/**
	 * �ټ��� Ŭ���̾�Ʈ�� �����Ͽ� ��� ����ϴ� ���� ���α׷� step 
	 * 1. Server Socket 
	 * 2. while loop���� accept()�Ͽ� ���� ��ȯ�޴´�. 
	 * 3. ������ �Ҵ��ϴ� ServerThread�� ���� 
	 * 4. start()
	 */

	public void go() throws IOException {
		ServerSocket server_socket = null;
		Socket client_socket = null;

		try {
			server_socket = new ServerSocket(65535);
			System.out.println("**���� ����**");
			
			// �ټ��� Ŭ���̾�Ʈ�� ����ϱ� ���� loop
			while (true) {
				client_socket = server_socket.accept(); // Ŭ���̾�Ʈ ���ӽ� ���ο� ������ ����
				System.out.println(client_socket.getInetAddress() + "�� ����");
				
				ServerThread st = new ServerThread(client_socket);
				st.start();
		
			}
			
		} 
		finally {
			if (client_socket != null)
				client_socket.close();
			if (server_socket != null)
				server_socket.close();
			System.out.println("**���� ����**");
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
