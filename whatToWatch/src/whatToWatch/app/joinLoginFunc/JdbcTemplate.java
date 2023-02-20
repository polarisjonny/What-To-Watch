package whatToWatch.app.joinLoginFunc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTemplate {
	public static Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "C##KH";
		String password = "KH";
		Connection conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	}
}
