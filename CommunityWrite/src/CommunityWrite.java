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

public class CommunityWrite {

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

		serverSocket = new ServerSocket(8888);
		System.out.println("--------Community Write Server Start!! -----");

		try {
			clientSocket = serverSocket.accept();
			System.out.println("클라이언트 연결");

			try {
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(clientSocket.getOutputStream(),
								"UTF-8")), true);
				in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream(), "UTF-8"));

				String title;
				String writer;
				String[] body;
				String id;
				String date;
				String grade;
				String lineCount;
				String text = null;

				title = in.readLine();
				writer = in.readLine();
				id = in.readLine();
				date = in.readLine();
				grade = in.readLine();

				lineCount = in.readLine();

				body = new String[Integer.parseInt(lineCount)];
				for (int i = 0; i < Integer.parseInt(lineCount); i++) {
					body[i] = in.readLine();
					// System.out.println(body[i]);

					if (i == 0)
						text = body[i];
					else
						//text += " \r\n "  + body[i];  줄바꿈 확인 근데 리스트 출력 고쳐야함
						text += " " + body[i];
				}

				System.out.println(title);
				System.out.println(writer);
				System.out.println(id);
				System.out.println(date);
				System.out.println(grade);
				System.out.println(text);

				String result;

				result = writeText(con, title, writer, text, id, date, grade);

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
			serverSocket.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static String writeText(Connection con, String title,
			String writer, String body, String id, String date, String grade) {
		// TODO Auto-generated method stub
		try {
			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			int textNum = getTextNum(con, grade);

			System.out.println("INSERT INTO community" + grade + " values('"
					+ writer + "', '" + id + "', '" + title + "', '" + body
					+ "', '" + date + "', " + textNum + ")");

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("INSERT INTO community" + grade + " values('"
					+ writer + "', '" + id + "', '" + title + "', '" + body
					+ "', '" + date + "', " + textNum + ")")) {
				rs = st.getResultSet();
			}

			return "SUCESS_INSERT";

		} catch (Exception e) {
			// TODO: handle exception
			return "FAIL_INSERT";
		}

	}

	private static int getTextNum(Connection con, String grade) {
		// TODO Auto-generated method stub
		try {
			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			int count = 0;

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT COUNT(*) FROM community" + grade)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				count = rs.getInt("COUNT(*)");
			}

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
}
