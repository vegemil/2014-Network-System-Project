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
      String tagid = null;
      String[] serverMessage ;
      
      serverSocket = new ServerSocket(7777);

      try {
         System.out.println("서버");
         clientSocket = serverSocket.accept();
         System.out.println("클라이언트 연결, IP = "+clientSocket.getInetAddress());
      
         try{
            out = new PrintWriter(new BufferedWriter(
                  new OutputStreamWriter(clientSocket.getOutputStream(),
                        "UTF-8")), true);
            in = new BufferedReader(new InputStreamReader(
                  clientSocket.getInputStream(), "UTF-8"));
            tagid=in.readLine();
            System.out.println("tagid : " + tagid);
            out.println(tagid);

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

}