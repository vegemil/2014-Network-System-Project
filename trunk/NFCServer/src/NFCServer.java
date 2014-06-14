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
	   final String NProject="04729EB2422B80";
	   final String GProject="046A6F1A8D3280";
	   /*final String COMGOO="053400053E682C";
	   final String AND="053400053E682C";
	   final String SINHO="053400053EF73D";
	   */
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
          
          out.println(id);
          out.println(time);
         // out.println(tagid); //Ŭ���̾�Ʈ�� �����°�, ��ÿ� ������� ���������� �⼮ó�� �Ǿ����ϴ�.�� Ŭ���̾�Ʈ�� ���������.
           
          out.flush();
          
          if(tagid.equals(NProject)){ //������ �����ϴ���, ���� ���� �����Ͱ����Ұ����� �츮����~
        	 /* subject="����";
        	  System.out.println( subject);*/
        	  System.out.println(time+"��"+" ��Ʈ��ũ�ý���������Ʈ " +id+"�� �⼮�ϼ̽��ϴ�.");
              }else if(tagid.equals(GProject)){
              	System.out.println(time+"��"+" ����������Ʈ " +id+"�� �⼮�ϼ̽��ϴ�.");
            }
              /*else if(tagid.equals(COMGOO)){
              	System.out.println(time+":COMGOO:" + id+"�� �⼮�ϼ̽��ϴ�.");
            }
              else if(tagid.equals(AND)){
              	System.out.println(time+":AND:" + id+"�� �⼮�ϼ̽��ϴ�.");
            }
              else if(tagid.equals(SINHO)){
              	System.out.println(time+":SINHO:" + id+"�� �⼮�ϼ̽��ϴ�.");
            }*/
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
		if(tagid.equals(NProject)){
	          String query1 ="insert into subject_network(tagid,id,grade,name,time) values('"+tagid+"','"+id+"','"+grade+"','"+name+"','"+time+"')";
			 st.execute(query1);
			 }
			 else if(tagid.equals(GProject)){
				 String query2 ="insert into subject_game(tagid,id,grade,name,time) values('"+tagid+"','"+id+"','"+grade+"','"+name+"','"+time+"')";
		     st.execute(query2);
			 }
         // String query ="insert into subject_network(tagid,id,grade,name,time) values('"+tagid+"','"+id+"','"+grade+"','"+name+"','"+time+"')";
          //�����Ͱ� �ִµ� �����Ϸ��� update
          //�����Ͱ� ������ ���� insert ������ ���ָ� ��
                   
          
          
          // ��ȸ select * from table where password='1010';
          // ���� insert into table(id,password) values(1,2);
          // ���� delete
          // ���� update
          
         
          
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