import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private static DBManager mInstance = null;
	private static Connection con = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	private DBManager() {

	}

	static public DBManager GetInstance() {
		if (mInstance == null) {
			mInstance = new DBManager();

			try {
				mInstance.ConnectDB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return mInstance;
	}

	private void ConnectDB() throws SQLException {

		con = DriverManager
				.getConnection(
						"jdbc:mysql://network.cgdc8rvnyyii.ap-northeast-1.rds.amazonaws.com:3306?useUnicode=true&characterEncoding=euckr",
						"want", "32102637");
		st = con.createStatement();
		rs = st.executeQuery("USE network");

	}

	public void SendQuery(String Query) {
		System.out.println(Query);
		// 테이블리스트 출력 쿼리 전송
		try {
			if (st.execute(Query)) {
				rs = st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet GetResult() {
		return rs;
	}

	public int CountDB(String DBName) {
		// TODO Auto-generated method stub
		try {
			int count = 0;

			// 테이블리스트 출력 쿼리 전송
			if (st.execute("SELECT COUNT(*) FROM " + DBName)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				count = rs.getInt("COUNT(*)");
			}

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
}
