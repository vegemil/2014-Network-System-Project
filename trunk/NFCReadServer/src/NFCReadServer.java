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

	static String[] id = new String[500];
	static String[] first_check = new String[500];
	static String[] second_check = new String[500];
	static String[] third_check = new String[500];
	
	static int count = 0;
	static int count2 = 0;
	static int count3 = 0;
	
	
	static String[] week = new String[3];

	public static void main(String[] args) throws IOException, SQLException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		serverSocket = new ServerSocket(3939);
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

					
					out.println(count2);
					for (int i = 0; i < count2; i++) {
						out.println(id[i]);
						out.println(second_check[i]);
					}
					
					out.println(count3);
					for (int i = 0; i < count3; i++) {
						out.println(id[i]);
						out.println(third_check[i]);
					}							

					out.flush();

					count = 0;
					count2 = 0;
					count3 = 0;

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
			Statement st2 = null;
			ResultSet rs = null;
			ResultSet rs2 = null;
			
			Statement st3 = null;
			ResultSet rs3 = null;

			st = con.createStatement();
			rs = st.executeQuery("USE network");
			st2=con.createStatement();
			rs2 = st2.executeQuery("USE network");
			st3=con.createStatement();
			rs3 = st3.executeQuery("USE network");
			
			// 테이블리스트출력쿼리전송
			/*
			if (st.execute("SELECT id, time FROM subject_network")) {
				rs = st.getResultSet();
			}*/
			
					
		
			if (st.execute("SELECT id, time FROM subject_network where date_format(time, '%Y-%m-%d') = '2014-06-11'")) {
				rs = st.getResultSet();				
				while (rs.next()) {
					id[count] = rs.getNString("id");
					first_check[count] = rs.getTimestamp("time").toString(); // 1주차의시간을계산해
																			// time이일주차인것만
																			// 긁어와야함
					count++;
				}
			}
			
			//week[0] = "1st";
			
			
			if (st2.execute("SELECT id, time FROM subject_network where date_format(time, '%Y-%m-%d') = '2014-06-12'")) {
				rs2 = st2.getResultSet();						
				while (rs2.next()) {
					id[count2] = rs2.getNString("id");
					second_check[count2] = rs2.getTimestamp("time").toString(); // 2주차의시간을계산해
																				// time이일주차인것만
																			// 긁어와야함
					count2++;				
				}
			}
			

			if (st3.execute("SELECT id, time FROM subject_network where date_format(time, '%Y-%m-%d') = '2014-06-13'")) {
				rs3 = st3.getResultSet();						
				while (rs3.next()) {
					id[count3] = rs3.getNString("id");
					third_check[count3] = rs3.getTimestamp("time").toString(); // 3주차의시간을계산해
																				// time이일주차인것만
																			// 긁어와야함
					count3++;				
				}
			}
			
			
			for (int i = 0; i < count; i++) {
				System.out.println("id : " + id[i]);
				System.out.println("first_check : " + first_check[i]);				
				System.out.println("-------------------");

			}
			for(int b=0;b< count2;b++){
				System.out.println("id : " + id[b]);
				System.out.println("second_check : " + second_check[b]);
			}
			
			for (int c = 0; c < count3; c++) {
				System.out.println("id : " + id[c]);
				System.out.println("third_check : " + third_check[c]);				
				System.out.println("-------------------");

			}
			

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			System.out.println("-------------------");
		}

	}

}
