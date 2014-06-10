import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NFCReadServer {

	static String[] id = new String[100];
	static String[] first_check = new String[100];

	static int count = 0;

	public static void main(String[] args) throws IOException, SQLException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		serverSocket = new ServerSocket(9990);
		System.out.println("--------NFCRead Server Start!! -----");

		// getAttendance();

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

					getAttendance();

					out.println(count);
					System.out.println(count);

					for (int i = 0; i < count; i++) {
						out.println(id[i]);
						out.println(first_check[i]);
					}

					out.flush();

					count = 0;

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				out.close();
				in.close();
				clientSocket.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	private static void getAttendance() throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;

		con = DriverManager
				.getConnection(
						"jdbc:mysql://network.cgdc8rvnyyii.ap-northeast-1.rds.amazonaws.com:3306?useUnicode=true&characterEncoding=euckr",
						"want", "32102637");

		try {
			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			// 테이블리스트출력쿼리전송
			if (st.execute("SELECT id, time FROM subject_network")) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				id[count] = rs.getNString("id");
				first_check[count] = rs.getTimestamp("time").toString(); // 1주차의시간을계산해
																			// time이일주차인것만
																			// 긁어와야함
				count++;
			}

			for (int i = 0; i < count; i++) {
				System.out.println("id : " + id[i]);
				System.out.println("first_check : " + first_check[i]);
				System.out.println("-------------------");

			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

	}

}
