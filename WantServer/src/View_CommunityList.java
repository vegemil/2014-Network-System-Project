import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View_CommunityList {

	PrintWriter out = null;

	String[] writer = new String[100];
	String[] title = new String[100];
	String[] date = new String[100];
	String[] textnum = new String[100];

	public void getCommunityList(Socket clientSocket, String grade) {
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF-8")), true);

			String Query = "SELECT name, title, date, textnum FROM community"
					+ grade + " ORDER BY date desc ";

			DBManager.GetInstance().SendQuery(Query);

			ResultSet rs = DBManager.GetInstance().GetResult();
			int count = 0;

			while (rs.next()) {
				writer[count] = rs.getNString("name");
				title[count] = rs.getNString("title");
				date[count] = rs.getTimestamp("date").toString();
				textnum[count] = rs.getNString("textnum");

				System.out.println("writer : " + writer[count]);
				System.out.println("title : " + title[count]);
				System.out.println("date : " + date[count]);
				System.out.println("textnum : " + textnum[count]);
				System.out.println("------------------");

				count++;
			}

			out.println(count);

			if (count != 0) {
				for (int i = 0; i < count; i++) {
					out.println(writer[i]);
					out.println(title[i]);
					out.println(date[i]);
					out.println(textnum[i]);
				}
			}
			out.flush();

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
