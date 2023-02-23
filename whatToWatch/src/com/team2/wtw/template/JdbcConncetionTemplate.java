package com.team2.wtw.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConncetionTemplate {

	public Connection getJdbcConnection() {
		// DB 연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pw = "KH";
		
		try {
			return DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
