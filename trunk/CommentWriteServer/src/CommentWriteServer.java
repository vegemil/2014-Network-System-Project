import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommentWriteServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		Connection con = null;

		try {
			con = DriverManager
					.getConnection(
							"jdbc:mysql://network.cgdc8rvnyyii.ap-northeast-1.rds.amazonaws.com:3306?useUnicode=true&characterEncoding=euckr",
							"want", "32102637");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		serverSocket = new ServerSocket(9997);
		System.out.println("--------Community Write Server Start!! -----");

		while (true) {

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

					String communityTextIndex;
					String id;
					String comment;
					String name;
					String strNow;
					String grade;

					communityTextIndex = in.readLine();
					id = in.readLine();
					comment = in.readLine();
					name = in.readLine();
					strNow = in.readLine();
					grade = in.readLine();

					System.out.println(communityTextIndex);
					System.out.println(id);
					System.out.println(comment);
					System.out.println(name);
					System.out.println(strNow);
					System.out.println(grade);

					String result;

					result = writeComment(con, communityTextIndex, id, comment,
							name, strNow, grade);

					System.out.println(result);

					out.write(result);
					out.flush();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				out.close();
				in.close();
				clientSocket.close();
				//serverSocket.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	private static String writeComment(Connection con,
			String communityTextIndex, String id, String comment, String name,
			String strNow, String grade) {
		// TODO Auto-generated method stub
		try {
			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			System.out.println("INSERT INTO commentdb" + grade + " values("
					+ communityTextIndex + ", '" + id + "', '" + comment
					+ "', '" + name + "', '" + strNow + "')");

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("INSERT INTO commentdb" + grade + " values("
					+ communityTextIndex + ", '" + id + "', '" + comment
					+ "', '" + name + "', '" + strNow + "')")) {
				rs = st.getResultSet();
			}

			return "SUCESS_INSERT";

		} catch (Exception e) {
			// TODO: handle exception
			return "FAIL_INSERT";
		}
	}

}
