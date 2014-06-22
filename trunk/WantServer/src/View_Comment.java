import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View_Comment {

	PrintWriter out = null;

	String[] writer = new String[100];
	String[] commenttext = new String[100];
	String[] date = new String[100];

	public void getCommentList(Socket clientSocket, String grade, String textNum) {
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF-8")), true);

			String Query = "SELECT name, commenttext, date FROM commentdb" + grade
					+ " WHERE communitytextindex=" + textNum
					+ " ORDER BY date desc";

			DBManager.GetInstance().SendQuery(Query);

			ResultSet rs = DBManager.GetInstance().GetResult();
			int count = 0;

			while (rs.next()) {
				writer[count] = rs.getNString("name");
				commenttext[count] = rs.getNString("commenttext");
				date[count] = rs.getTimestamp("date").toString();

				System.out.println("writer : " + writer[count]);
				System.out.println("comment text : " + commenttext[count]);
				System.out.println("date : " + date[count]);
				System.out.println("------------------");

				count++;
			}

			out.println(count);

			if (count != 0) {
				for (int i = 0; i < count; i++) {
					out.println(writer[i]);
					out.println(commenttext[i]);
					out.println(date[i]);
				}
			}
			out.flush();

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
