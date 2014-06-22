import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View_CommunityBody {

	PrintWriter out = null;

	public void GetCommunityBody(Socket clientSocket, String grade,
			String textNum) {
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF-8")), true);

			String Query = "SELECT title, context FROM community" + grade
					+ " WHERE textnum=" + textNum;

			DBManager.GetInstance().SendQuery(Query);

			ResultSet rs = DBManager.GetInstance().GetResult();

			String title = null, context = null;

			while (rs.next()) {
				title = rs.getNString("title");
				context = rs.getNString("context");

				System.out.println("title : " + title);
				System.out.println("context : " + context);
				System.out.println("------------------");
			}

			String[] body = context.split("\r\n");

			out.println(title);
			out.println(body.length);

			for (int i = 0; i < body.length; i++)
				out.println(body[i]);
			
			out.flush();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
