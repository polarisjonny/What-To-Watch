package com.team2.wtw.freeboard;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {

	public static Connection conn() throws Exception {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##SI";
		String pwd = "SI";

		Connection conn = DriverManager.getConnection(url, id, pwd);

		return conn;
	}

}
