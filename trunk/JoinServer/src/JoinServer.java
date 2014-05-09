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

public class JoinServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		serverSocket = new ServerSocket(5555);

		try {
			clientSocket = serverSocket.accept();
			System.out.println("클라이언트 연결");

			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF-8")), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream(), "UTF-8"));

			String name;
			String grade;
			String id;
			String password;

			name = in.readLine();
			grade = in.readLine();
			id = in.readLine();
			password = in.readLine();

			System.out.println(name);
			System.out.println(grade);
			System.out.println(id);
			System.out.println(password);

			out.println("회원가입 성공");
			out.flush();

			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void getStudentInfoFromDB(String id) {
		try {
			Connection con = null;

			con = DriverManager
					.getConnection(
							"jdbc:mysql://network.cgdc8rvnyyii.ap-northeast-1.rds.amazonaws.com:3306",
							"want", "32102637");

			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SHOW TABLES")) {

				rs = st.getResultSet();
			}

			while (rs.next()) {

				String str = rs.getNString(1);

				// rs에서 해당 필드의 값을 가져와 출력
				System.out.println(str);

			}

		} catch (SQLException sqex) {

			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());

		}
	}
}
