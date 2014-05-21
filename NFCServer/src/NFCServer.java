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


public class NFCServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		

		String result = null;
		//String name = null;
		//String grade = null;
		String clientName=null;
		String tagid = null;
		String[] serverMessage ;
		
		serverSocket = new ServerSocket(7777);

		try {
			System.out.println("서버");
			clientSocket = serverSocket.accept();
			System.out.println("클라이언트 연결, 클라이언트 IP = "+clientSocket.getInetAddress());
		
			try{
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(clientSocket.getOutputStream(),
								"UTF-8")), true);
				in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream(), "UTF-8"));
				/*clientName = in.readLine();	
				out.println("ㅎ2 client"+clientName);
				System.out.println("ㅎ2 client"+clientName);*/
				
				tagid=in.readLine();
				System.out.println("tagid:");
				//System.out.println(tagid);
				//result = tagid.toString();
				out.println(tagid);
				
				
				/*if (tagid.equals("태그 id 읽기 성공")) {

					
					//result = servermessage(tagid);					

					name = getName(id);
					grade = getGrade(id);
					out.println(name);
					out.println(grade);
				}

				out.flush();*/
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
	

	/*private static String servermessage(String tagid) {
		// TODO Auto-generated method stub
		
		tagid = tagid.toString();
		return null;
	}
		*/	
}
		
	//DB에서 데이터 추출
	/*public static String getTagid(String id) {
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

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM subject_network WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				result = rs.getString("tagid");
			} else {
				result = "강의실 정보를 가져올 수 없습니다.";
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

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM subject_network WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				result = rs.getString("name");
			} else {
				result = "이름을 가져 올 수 없습니다.";
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

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM subject_network WHERE id= " + id)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				result = rs.getString("grade");
			} else {
				result = "학년을 가져올 수 없습니다.";
			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		return result;
	}
}*/
	



			/*try {
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

				result = checkLogin(id, password);
				
				System.out.println("result : " + result);
				out.println(result);

				if (result.equals("로그인 성공")) {

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
		}*/
	

	/*public static String checkLogin(String id, String password) {

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

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT * FROM studentdb WHERE id= " + id )) {
				rs = st.getResultSet();
			}
			
			if(!rs.next())
			{
				result =  "없는 아이디입니다.";
			}
			else
			{
				// 아이디가 있으면, 패스워드를 체크한다.
				st.execute("SELECT * FROM studentdb WHERE id= '" + id + "' AND password= '" + password +"'");
				rs = st.getResultSet();
				if(rs.next())
				{
					// 로그인 성공!
					result = "로그인 성공";
				}
				else
				{
					// 패스워드 틀림!
					result = "잘못된 패스워드 입니다.";
				}
				
			}
		} catch (SQLException sqex) {

			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());

		}
		return result;
	}*/
	

