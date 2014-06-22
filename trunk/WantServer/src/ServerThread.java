import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 개별 클라이언트와 메세지를 주고 받는 스레드 Socket: 멤버변수, 생성자를 통해서 TestServer에서 할당 받는다. 1.
 * extends Thread 2. run() 클라이언트의 메세지를 주고 받는 비즈니스(Socket)
 */

public class ServerThread extends Thread {
	// 멤버변수로 선언
	private Socket socket = null;
	private String userIP = null;
	private BufferedReader in = null;

	ServerThread(Socket s) {
		this.socket = s;
		userIP = socket.getInetAddress().toString();
	}

	public void run() {
		try {
			System.out.println("ServerThread Run!");
			service();
			closeAll();
		} catch (IOException e) {
			System.out.println("**" + userIP + "접속 종료.");
		}
	}

	private void service() throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream(),
				"UTF-8"));

		String client_message = null;
		while (true) {
			client_message = in.readLine();
			if (client_message == null || client_message.equals("")) {
				System.out.println(userIP + "님이 연결을 종료했습니다.");
			}

			String grade;
			String textNum;
			String name;
			String strNow;
			String id;
			String text = null;
			String password;

			switch (client_message) {
			case "1": // 커뮤니티 글 목록 불러오기
				System.out.println("커뮤니티 글 목록 출력");

				grade = in.readLine();

				View_CommunityList communityList = new View_CommunityList();
				communityList.getCommunityList(socket, grade);

				break;

			case "2": // 커뮤니티 글 보여주기
				System.out.println("커뮤니티 글 출력");

				grade = in.readLine();
				textNum = in.readLine();

				View_CommunityBody communityBody = new View_CommunityBody();
				communityBody.GetCommunityBody(socket, grade, textNum);
				break;

			case "3": // 커뮤니티 글 쓰기
				System.out.println("커뮤니티 글 쓰기");

				String title = in.readLine();
				name = in.readLine();
				id = in.readLine();
				String date = in.readLine();
				grade = in.readLine();
				String lineCount = in.readLine();
				int Count = Integer.parseInt(lineCount);
				String[] body = new String[Count];

				for (int i = 0; i < Count; i++) {
					body[i] = in.readLine();

					if (i == 0)
						text = body[i];
					else
						text += "\r\n" + body[i];
				}
				Write_Community write = new Write_Community();
				write.WriteCommunity(socket, title, id, name, date, grade, text);

				break;

			case "4": // 커뮤니티 댓글 보여주기
				System.out.println("커뮤니티 댓글 출력");

				grade = in.readLine();
				textNum = in.readLine();

				View_Comment comment = new View_Comment();
				comment.getCommentList(socket, grade, textNum);
				break;

			case "5": // 커뮤니티 댓글쓰기
				System.out.println("커뮤니티 댓글 쓰기");

				String index = in.readLine();
				id = in.readLine();
				text = in.readLine();
				name = in.readLine();
				strNow = in.readLine();
				grade = in.readLine();

				Write_Comment write_Comment = new Write_Comment();
				write_Comment.WriteComment(socket, index, id, text, name,
						strNow, grade);
				break;

			case "6": // 로그인
				System.out.println("로그인");

				Login login = new Login();

				id = in.readLine();
				password = in.readLine();

				login.DoLogin(socket, id, password);

				break;

			default:
				break;
			}

			System.out.println("");
		}
	}

	public void closeAll() throws IOException {

		if (in != null)
			in.close();
		if (socket != null)
			socket.close();
	}
}