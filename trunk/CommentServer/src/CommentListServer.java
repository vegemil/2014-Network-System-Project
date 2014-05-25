import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommentListServer {

	static String[] writer = new String[100];
	static String[] context = new String[100];
	static String[] date = new String[100];

	static int count = 0;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		serverSocket = new ServerSocket(5345);
		System.out.println("--------Comment Server Start!! -----");
		
		//getBoardData("2", "1");

		try {
			clientSocket = serverSocket.accept();
			System.out.println("클라이언트 연결");

			try {
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(clientSocket.getOutputStream(),
								"UTF-8")), true);
				in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream(), "UTF-8"));

				String grade;
				String textnum;

				grade = in.readLine();
				textnum = in.readLine();

				System.out.println("GRADE : " + grade);
				System.out.println("TEXTNUM : " + textnum);

				getBoardData(grade, textnum);

				out.println(count);
				System.out.println(count);

				for (int i = 0; i < count; i++) {
					out.println(writer[i]);
					out.println(date[i]);
					out.println(context[i]);
				}

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

	public static void getBoardData(String grade, String textnum) {

		try {
			Connection con = null;

			con = DriverManager
					.getConnection(
							"jdbc:mysql://network.cgdc8rvnyyii.ap-northeast-1.rds.amazonaws.com:3306?useUnicode=true&characterEncoding=euckr",
							"want", "32102637");

			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			System.out.println("SELECT * FROM commentdb" + grade
					+ " WHERE communitytextindex=" + textnum
					+ " ORDER BY date desc");

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM commentdb" + grade
					+ " WHERE communitytextindex=" + textnum
					+ " ORDER BY date desc")) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				writer[count] = rs.getNString("name");
				context[count] = rs.getNString("commenttext");
				date[count] = rs.getTimestamp("date").toString();

				System.out.println("writer : " + writer[count]);
				System.out.println("context : " + context[count]);
				System.out.println("date : " + date[count]);
				System.out.println("------------------");
				count++;
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

}
