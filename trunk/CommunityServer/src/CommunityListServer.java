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

public class CommunityListServer {

	static String[] writer = new String[100];
	static String[] id = new String[100];
	static String[] title = new String[100];;
	static String[] context = new String[100];
	static String[] date = new String[100];
	static String[] textnum = new String[100];

	static int count = 0;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		serverSocket = new ServerSocket(9999);
		System.out.println("--------Community Server Start!! -----");

		// getBoardData("2");

		System.out.println("----------------------------------<<");

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

					String grade;

					grade = in.readLine();

					System.out.println("GRADE : " + grade);

					getBoardData(grade);

					out.println(count);
					System.out.println(count);

					for (int i = 0; i < count; i++) {
						out.println(writer[i]);
						out.println(title[i]);
						out.println(date[i]);
						out.println(context[i]);
						out.println(textnum[i]);
					}

					out.flush();
					count =0;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					clientSocket.close();
					out.close();
					in.close();
					System.out.println("client close");
					System.out.println("Server Continue");
					System.out.println("");
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//serverSocket.close();
	}

	public static void getBoardData(String grade) {

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

			System.out.println("SELECT * FROM community" + grade
					+ " ORDER BY date desc");

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM community" + grade
					+ " ORDER BY date desc")) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				writer[count] = rs.getNString("name");
				id[count] = rs.getNString("id");
				title[count] = rs.getNString("title");
				context[count] = rs.getNString("context");
				date[count] = rs.getTimestamp("date").toString();
				textnum[count] = rs.getNString("textnum");

				System.out.println("writer : " + writer[count]);
				System.out.println("id : " + id[count]);
				System.out.println("title : " + title[count]);
				System.out.println("context : " + context[count]);
				System.out.println("date : " + date[count]);
				System.out.println("textnum : " + textnum[count]);
				System.out.println("------------------");
				count++;
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

}