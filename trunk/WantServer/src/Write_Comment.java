import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Write_Comment {

	PrintWriter out = null;

	public void WriteComment(Socket socket, String communityTextIndex,
			String id, String text, String name, String strNow, String grade) {
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), "UTF-8")), true);

			String Query = "INSERT INTO commentdb" + grade + " values("
					+ communityTextIndex + ", '" + id + "', '" + text + "', '"
					+ name + "', '" + strNow + "')";

			DBManager.GetInstance().SendQuery(Query);

			out.println("SUCESS_INSERT");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("FAIL_INSERT");
		}
	}

}
