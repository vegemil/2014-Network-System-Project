import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Write_Community {
	PrintWriter out = null;

	public void WriteCommunity(Socket socket, String title, String id,
			String name, String date, String grade, String body) {
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), "UTF-8")), true);
			int textNum = DBManager.GetInstance().CountDB("community" + grade);

			String Query = "INSERT INTO community" + grade + " values('" + name
					+ "', '" + id + "', '" + title + "', '" + body + "', '"
					+ date + "', " + textNum + ")";

			DBManager.GetInstance().SendQuery(Query);

			out.println("SUCESS_INSERT");
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("FAIL_INSERT");
		}

		out.flush();
	}
}
