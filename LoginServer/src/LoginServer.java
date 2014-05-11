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

public class LoginServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		String result = null;
		String name = null;
		String grade = null;

		serverSocket = new ServerSocket(6666);

		try {

			clientSocket = serverSocket.accept();
			System.out.println("Ŭ���̾�Ʈ ����");

			try {
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(clientSocket.getOutputStream(),
								"UTF-8")), true);
				in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream(), "UTF-8"));

				String id;
				String password;

				id = in.readLine();
				password = in.readLine();

				System.out.println(id);
				System.out.println(password);

				result = findID(id);
				System.out.println("result : " + result);
				out.println(result);

				if (result.equals("��й�ȣ Ȯ���Ϸ� ���")) {
					name = getName(id);
					grade = getGrade(id);
					out.println(name);
					out.println(grade);
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

	public static String findID(String id) {

		String result = null;

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

			// ���̺���Ʈ ��� ���� ����
			if (st.execute("SELECT * FROM studentdb WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				result = "��й�ȣ Ȯ���Ϸ� ���";
			} else {
				result = "���� ���̵� �Դϴ�.";
			}

		} catch (SQLException sqex) {

			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());

		}
		return result;
	}

	public static String getName(String id) {
		String result = null;

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

			// ���̺���Ʈ ��� ���� ����
			if (st.execute("SELECT * FROM studentdb WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				result = rs.getString("name");
			} else {
				result = "�̸��� ���� �� �� �����ϴ�.";
			}

		} catch (SQLException sqex) {

			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());

		}

		return result;

	}

	public static String getGrade(String id) {
		String result = null;

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

			// ���̺���Ʈ ��� ���� ����
			if (st.execute("SELECT * FROM studentdb WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				result = rs.getString("grade");
			} else {
				result = "�г��� ������ �� �����ϴ�.";
			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		return result;
	}
}
