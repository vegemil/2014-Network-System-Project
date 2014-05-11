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

		String result = null;

		serverSocket = new ServerSocket(4321);
		System.out.println("--------Join Server Start!! -----");

		try {
				clientSocket = serverSocket.accept();
				System.out.println("Ŭ���̾�Ʈ ����");

				try {
					out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(
									clientSocket.getOutputStream(), "UTF-8")),
							true);
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

					System.out.println("NAME : " + name);
					System.out.println("GRADE : " + grade);
					System.out.println( "ID : " +id);
					System.out.println("PASSWORD : " + password);

					result = findID(id, password, grade, name);
					System.out.println("result : " + result);
					out.println(result);
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

	public static String findID(String id, String password, String grade, String name) {

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
			
			if(rs.next())
			{
				result = "���̵� �ߺ��Դϴ�. �ٽ� �����ϼ���";
			}
			else
			{
				System.out.println("INSERT INTO studentdb VALUES ('" + id +"', " + password + ", '" + name + "', " + grade +")");
				st.execute("INSERT INTO studentdb VALUES ('" + id +"', " + password + ", '" + name + "', " + grade +")");
				result = "ȸ������ �Ϸ�";
			}

		} catch (SQLException sqex) {

			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());

		}
		return result;
	}
}
