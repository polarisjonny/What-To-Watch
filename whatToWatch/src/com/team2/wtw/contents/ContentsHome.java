package com.team2.wtw.contents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.team2.wtw.template.JdbcConncetionTemplate;
import com.team2.wtw.template.PrintTemplate;

public class ContentsHome {

	public void showContents() {

		// 추천 컨텐츠 출력
		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			// 최신순
			String newest_sql = "SELECT CONTENTS_TITLE \"TITLE\"\r\n"
					+ "    , TO_CHAR(RELEASE_DATE, 'YY/MM/DD') \"RELEASE_DATE\"\r\n" + "FROM\r\n" + "    (\r\n"
					+ "    SELECT ROWNUM AS \"순위\", CONTENTS_TITLE, RELEASE_DATE\r\n" + "    FROM CONTENTS\r\n"
					+ "    )\r\n" + "WHERE \"순위\" <= 5\r\n";

			PreparedStatement newest_pstmt = conn.prepareStatement(newest_sql);

			ResultSet newest_ResultSet = newest_pstmt.executeQuery();

			System.out.println("최신작품");
			int i = 1;
			while (newest_ResultSet.next() || i <= 5) {
				System.out.printf("%d. %s    %s\n", i, newest_ResultSet.getString("TITLE"),
						newest_ResultSet.getString("RELEASE_DATE"));
				i++;
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // method | showContents

}
