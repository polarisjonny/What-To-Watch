package com.team2.wtw.user;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTemplate {

	public static Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "C##TEAM2";
		String password = "TEAM2";
		Connection conn = DriverManager.getConnection(url, username, password);

		return conn;
	}

}
