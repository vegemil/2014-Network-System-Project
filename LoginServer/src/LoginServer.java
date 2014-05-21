import java.io.BufferedReader;
import java.io.BufferedWriter;
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

public class LoginServer implements Runnable {
	public static final int ServerPort = 6666;
	public static final String ServerIP = "172.30.4.76";

	PrintWriter out = null;
	BufferedReader in = null;

	String result = null;
	String name = null;
	String grade = null;

	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("S: Connecting...");
			ServerSocket serverSocket = new ServerSocket(ServerPort);

			Connection con = null;

			con = DriverManager
					.getConnection(
							"jdbc:mysql://network.cgdc8rvnyyii.ap-northeast-1.rds.amazonaws.com:3306?useUnicode=true&characterEncoding=euckr",
							"want", "32102637");

			while (true) {
				Socket client = serverSocket.accept();
				System.out.println("S: Receiving...");

				try {
					while (true) {
						out = new PrintWriter(new BufferedWriter(
								new OutputStreamWriter(
										client.getOutputStream(), "UTF-8")),
								true);
						in = new BufferedReader(new InputStreamReader(
								client.getInputStream(), "UTF-8"));

						String id;
						String password;

						id = in.readLine();
						password = in.readLine();

						System.out.println(id);
						System.out.println(password);

						result = checkLogin(con, id, password);

						System.out.println("result : " + result);
						out.println(result);

						if (result.equals("LOGIN_SUCESS")) {
							name = getName(con, id);
							grade = getGrade(con, id);
							out.println(name);
							out.println(grade);
							break;
						}
						out.flush();
					}
				} catch (Exception e) {
					System.out.println("S: Error");
					e.printStackTrace();
				} finally {
					client.close();
					System.out.println("S: Done.");
					System.out.println("Server Continue");
					System.out.println(" ");
				}
			}
			
		} catch (Exception e) {
			System.out.println("S: Error");
			e.printStackTrace();
		}
	}

	private String checkLogin(Connection con, String id, String password) {
		// TODO Auto-generated method stub
		String result = null;

		try {
			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM studentdb WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (!rs.next()) {
				result = "NOT_EXIST_ID";
			} else {
				// 아이디가 있으면, 패스워드를 체크한다.
				st.execute("SELECT * FROM studentdb WHERE id= '" + id
						+ "' AND password= '" + password + "'");
				rs = st.getResultSet();
				if (rs.next()) {
					// 로그인 성공!
					result = "LOGIN_SUCESS";
				} else {
					// 패스워드 틀림!
					result = "PASSWORD_WRONG";
				}

			}
		} catch (SQLException sqex) {

			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());

		}
		return result;
	}

	private String getGrade(Connection con, String id) {
		// TODO Auto-generated method stub
		String result = null;

		try {

			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM studentdb WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				result = rs.getString("grade");
			} else {
				result = "FAIL_GETGRADE";
			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		return result;
	}

	private String getName(Connection con, String id) {
		// TODO Auto-generated method stub
		String result = null;

		try {

			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("USE network");

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM studentdb WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				result = rs.getString("name");
			} else {
				result = "FAIL_GETNAME";
			}

		} catch (SQLException sqex) {

			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());

		}

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread desktopServerThread = new Thread(new LoginServer());
		desktopServerThread.start();
	}
}