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

import com.mysql.jdbc.Statement;


public class NFCServer {
	 public static final int ServerPort = 6666;
	 public static final String ServerIP = "172.30.4.76"; 

   public static void main(String[] args) throws IOException {
	   final String 데통="053400053E682C";
	   final String CPP="053400053E682C";
	   final String COMGOO="053400053E682C";
	   final String AND="053400053E682C";
	   final String SINHO="053400053E682C";
	   
	   String subject=null;
      // TODO Auto-generated method stub
      ServerSocket serverSocket = null;
      Socket clientSocket = null;
      PrintWriter out = null;
      BufferedReader in = null;

      String result = null;
      String tagid = null;
      String id=null;
      String time=null;
      String grade =null;
      String name=null;
      
      String[] serverMessage ;
      
      serverSocket = new ServerSocket(7777);
      Connection con = null;
      ResultSet rs;
			

      try {
    	  con = DriverManager
					.getConnection(
							"jdbc:mysql://network.cgdc8rvnyyii.ap-northeast-1.rds.amazonaws.com:3306?useUnicode=true&characterEncoding=euckr",
							"want", "32102637");
    	 java.sql.Statement st = con.createStatement();
    	
			 System.out.println("데이터베이스의 연결에 성공하였습니다.");
       System.out.println("서버");
       while(true){
    	   try{
       clientSocket = serverSocket.accept();
       System.out.println("클라이언트 연결, IP = "+clientSocket.getInetAddress());
    
    
          out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream(),
                      "UTF-8")), true);
          in = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream(), "UTF-8"));
          tagid=in.readLine(); //짝수를 맞춰          
          id=in.readLine();
          time=in.readLine();
          
          System.out.println("id : " + id);
          System.out.println("tagid : " + tagid);
          System.out.println("time : " + time);
          
          out.println(tagid); //클라이언트에 보내는것, 몇시에 어느과목에 정상적으로 출석처리 되었습니다.를 클라이언트에 보내줘야함.
          

          out.flush();
          
          if(tagid.equals(데통)){ //넣을때 구분하던지, 빼고 나서 데이터가공할것인지 우리자유~
        	  subject="데통";
        	  System.out.println( subject);
              }else if(tagid.equals(CPP)){
              	System.out.println(time+":CPP:" + id+"님 출석하셨습니다.");
            }
              else if(tagid.equals(COMGOO)){
              	System.out.println(time+":COMGOO:" + id+"님 출석하셨습니다.");
            }
              else if(tagid.equals(AND)){
              	System.out.println(time+":AND:" + id+"님 출석하셨습니다.");
            }
              else if(tagid.equals(SINHO)){
              	System.out.println(time+":SINHO:" + id+"님 출석하셨습니다.");
            }
          rs=null;
           st.executeQuery("USE network");
      	if (st.execute("SELECT * FROM studentdb WHERE id= " + id)) {
			rs = st.getResultSet();
		}

		if (rs.next()) {
			grade = rs.getString("grade");
			name = rs.getString("name");
			
			
		} else {
			result = "FAIL_GETDATA";
		}
          //쿼리 처리해주는 부분
          String query ="insert into subject_network(tagid,id,grade,name) values('"+subject+"','"+id+"','"+grade+"','"+name+"')";
          //데이터가 있는데 수정하려면 update
          //데이터가 없으면 삽입 insert 위에꺼 해주면 ㄳ
          
          
          
          // 조회 select * from table where password='1010';
          // 삽입 insert into table(id,password) values(1,2);
          // 삭제 delete
          // 수정 update
          
         
          st.execute(query);
          out.flush();}
    	   catch (Exception e) {
               // TODO: handle exception
               e.printStackTrace();
            }finally{
            	clientSocket.close();
				System.out.println("client close");
				System.out.println("Server Continue");
				System.out.println(" ");
            }
      }
         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
         }
      
         out.close();
         in.close();
         clientSocket.close();
         serverSocket.close();

   
   }

}