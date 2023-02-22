package com.team2.wtw.faq;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTemplate {
	
	public static Connection jdbcTemplate() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##PROJECT01";
		String pwd = "PROJECT01";
		
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		return conn;
	}

}
