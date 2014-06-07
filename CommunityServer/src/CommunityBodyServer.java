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

public class CommunityBodyServer {

	static String title ;
	static String context ;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		serverSocket = new ServerSocket(9998);
		System.out.println("--------Community Body Server Start!! -----");

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
					String textNum;

					grade = in.readLine();
					textNum = in.readLine();

					System.out.println("GRADE : " + grade);
					System.out.println("TEXTNUM : " + textNum);

					getBoardData(grade, textNum);
					String[] body = context.split("\r\n");
					
					out.println(title);
					out.println(body.length);
					out.println(context);
					
					out.flush();
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

	public static void getBoardData(String grade, String textNum) {

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

			System.out.println("SELECT title, context FROM community" + grade
					+ " WHERE textnum=" +textNum);

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT  title, context FROM community" + grade
					+ " WHERE textnum=" +textNum)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				title = rs.getNString("title");
				context = rs.getNString("context");

				System.out.println("title : " + title);
				System.out.println("context : " + context);
				System.out.println("------------------");
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

}