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
	   final String ����="053400053E682C";
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
    	
			 System.out.println("�����ͺ��̽��� ���ῡ �����Ͽ����ϴ�.");
       System.out.println("����");
       while(true){
    	   try{
       clientSocket = serverSocket.accept();
       System.out.println("Ŭ���̾�Ʈ ����, IP = "+clientSocket.getInetAddress());
    
    
          out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream(),
                      "UTF-8")), true);
          in = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream(), "UTF-8"));
          tagid=in.readLine(); //¦���� ����          
          id=in.readLine();
          time=in.readLine();
          
          System.out.println("id : " + id);
          System.out.println("tagid : " + tagid);
          System.out.println("time : " + time);
          
          out.println(tagid); //Ŭ���̾�Ʈ�� �����°�, ��ÿ� ������� ���������� �⼮ó�� �Ǿ����ϴ�.�� Ŭ���̾�Ʈ�� ���������.
          

          out.flush();
          
          if(tagid.equals(����)){ //������ �����ϴ���, ���� ���� �����Ͱ����Ұ����� �츮����~
        	  subject="����";
        	  System.out.println( subject);
              }else if(tagid.equals(CPP)){
              	System.out.println(time+":CPP:" + id+"�� �⼮�ϼ̽��ϴ�.");
            }
              else if(tagid.equals(COMGOO)){
              	System.out.println(time+":COMGOO:" + id+"�� �⼮�ϼ̽��ϴ�.");
            }
              else if(tagid.equals(AND)){
              	System.out.println(time+":AND:" + id+"�� �⼮�ϼ̽��ϴ�.");
            }
              else if(tagid.equals(SINHO)){
              	System.out.println(time+":SINHO:" + id+"�� �⼮�ϼ̽��ϴ�.");
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
          //���� ó�����ִ� �κ�
          String query ="insert into subject_network(tagid,id,grade,name) values('"+subject+"','"+id+"','"+grade+"','"+name+"')";
          //�����Ͱ� �ִµ� �����Ϸ��� update
          //�����Ͱ� ������ ���� insert ������ ���ָ� ��
          
          
          
          // ��ȸ select * from table where password='1010';
          // ���� insert into table(id,password) values(1,2);
          // ���� delete
          // ���� update
          
         
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