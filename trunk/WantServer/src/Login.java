import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	private PrintWriter out = null;

	private String result = null;
	private String name = null;
	private String grade = null;

	public String DoLogin(Socket clientSocket, String id, String password) {
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF-8")), true);

			String Query = "SELECT * FROM studentdb WHERE id= " + id;

			DBManager.GetInstance().SendQuery(Query);

			ResultSet rs = DBManager.GetInstance().GetResult();

			if (!rs.next()) {
				result = "NOT_EXIST_ID";
			} else {
				// ���̵� ������, �н����带 üũ�Ѵ�.
				Query = "SELECT * FROM studentdb WHERE id= '" + id
						+ "' AND password= '" + password + "'";
				DBManager.GetInstance().SendQuery(Query);
				rs = DBManager.GetInstance().GetResult();

				if (rs.next()) {
					// �α��� ����!
					result = "LOGIN_SUCESS";

					Query = "SELECT grade, name FROM studentdb WHERE id= " + id;
					DBManager.GetInstance().SendQuery(Query);
					rs = DBManager.GetInstance().GetResult();

					if (rs.next()) {
						grade = rs.getString("grade");
						name = rs.getString("name");
					}

				} else {
					// �н����� Ʋ��!
					result = "PASSWORD_WRONG";
				}

				out.println(result);
				out.println(name);
				out.println(grade);
				out.flush();

			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
