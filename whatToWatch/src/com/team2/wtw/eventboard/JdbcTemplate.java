package com.team2.wtw.eventboard;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTemplate {
	public static Connection getConnection() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "C##KH";
			String password = "KH";
			Connection conn = DriverManager.getConnection(url, username, password);
			
			return conn;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
