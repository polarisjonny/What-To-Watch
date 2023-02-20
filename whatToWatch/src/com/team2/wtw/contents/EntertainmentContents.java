package com.team2.wtw.contents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.wtw.main.Main;
import com.team2.wtw.template.JdbcConncetionTemplate;

public class EntertainmentContents {
	
	private String[] EntertainmentList = new String[10];

	public void showEnterContents() {

		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			// 최신순
			String entertainmentList_sql = "SELECT CONTENTS_NO, CONTENTS_TITLE, RELEASE_DATE\r\n" + "FROM\r\n" + "(\r\n"
					+ "    SELECT ROWNUM \"NUM\", CONTENTS_NO, CONTENTS_TITLE, RELEASE_DATE\r\n"
					+ "    FROM CONTENTS T1\r\n" + "        JOIN CATEGORY T2 ON T1.CATEGORY_NO = T2.CATEGORY_NO \r\n"
					+ "    WHERE CATEGORY_NAME = '예능'\r\n" + "    ORDER BY RELEASE_DATE DESC\r\n" + ")\r\n"
					+ "WHERE NUM > ? AND NUM <= ?\r\n";

			PreparedStatement entertainmentList_pstmt = conn.prepareStatement(entertainmentList_sql);

			entertainmentList_pstmt.setString(1, "0");
			entertainmentList_pstmt.setString(2, "10");
			ResultSet entertainmentList_pstmtList_ResultSet = entertainmentList_pstmt.executeQuery();

			System.out.println("예능");
			int i = 1;
			while (entertainmentList_pstmtList_ResultSet.next()) {
				System.out.printf("%d. %s\n", i, entertainmentList_pstmtList_ResultSet.getString("CONTENTS_TITLE"));
				i++;
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // method | showEnterContents

	public void chooseEnter() {

		String input = Main.SC.nextLine();

		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			String entertainmentDetail_sql = "SELECT CONTENTS_NO\r\n" + "    ,DIRECTOR_NO\r\n" + "    ,COUNTRY_NO\r\n"
					+ "    ,CATEGORY_NO\r\n" + "    ,CONTENTS_TITLE\r\n" + "    ,SYNOPSIS\r\n"
					+ "    ,TO_CHAR(RELEASE_DATE, 'YY/MM/DD') AS RELEASE_DATE\r\n" + "FROM CONTENTS\r\n"
					+ "WHERE CONTENTS_NO = " + EntertainmentList[Integer.parseInt(input) - 1];

			PreparedStatement entertainmentDetail_pstmt = conn.prepareStatement(entertainmentDetail_sql);
			ResultSet entertainmentDetail_ResultSet = entertainmentDetail_pstmt.executeQuery();

			entertainmentDetail_ResultSet.next();
			String contents_no = entertainmentDetail_ResultSet.getString("CONTENTS_NO");
			String director_no = entertainmentDetail_ResultSet.getString("DIRECTOR_NO");
			String country_no = entertainmentDetail_ResultSet.getString("COUNTRY_NO");
			String category_no = entertainmentDetail_ResultSet.getString("CATEGORY_NO");
			String contents_title = entertainmentDetail_ResultSet.getString("CONTENTS_TITLE");
			String synopsis = entertainmentDetail_ResultSet.getString("SYNOPSIS");
			String release_date = entertainmentDetail_ResultSet.getString("RELEASE_DATE");

			// 제목
			System.out.println(contents_title);

			// 장르
			String getGenre_sql = "SELECT GENRE_NAME\r\n" + "FROM CONTENTS_GENRE CG\r\n"
					+ "    JOIN GENRE G ON CG.GENRE_NO = G.GENRE_NO\r\n" + "WHERE CG.CONTENTS_NO = " + contents_no
					+ "\r\n";
			PreparedStatement getGenre_pstmt = conn.prepareStatement(getGenre_sql);
			ResultSet getGenre_ResultSet = getGenre_pstmt.executeQuery();

			System.out.print("장르 : ");
			while (getGenre_ResultSet.next())
				System.out.print(getGenre_ResultSet.getString("GENRE_NAME") + " ");
			System.out.print("\t");

			// 국가
			String getCountry_sql = "SELECT COUNTRY_NAME\r\n" + "FROM COUNTRY\r\n" + "WHERE COUNTRY_NO = " + country_no;
			PreparedStatement getCountry_pstmt = conn.prepareStatement(getCountry_sql);
			ResultSet getCountry_ResultSet = getCountry_pstmt.executeQuery();
			getCountry_ResultSet.next();
			System.out.print(getCountry_ResultSet.getString("COUNTRY_NAME") + "\t");

			// 개봉일

			System.out.println(release_date);

			// 감독
			String getDiretor_sql = "SELECT DIRECTOR_NAME\r\n" + "FROM DIRECTOR\r\n" + "WHERE DIRECTOR_NO = "
					+ director_no + "\r\n";
			PreparedStatement getDirector_pstmt = conn.prepareStatement(getDiretor_sql);
			ResultSet getDirector_ResultSet = getDirector_pstmt.executeQuery();

			getDirector_ResultSet.next();
			System.out.printf("감독 : %s\t", getDirector_ResultSet.getString("DIRECTOR_NAME"));

			// 출연진
			String getStarring_sql = "SELECT STARRING_NAME\r\n" + "FROM CONTENTS_STARRING CS\r\n"
					+ "    JOIN STARRING S ON CS.STARRING_NO = S.STARRING_NO\r\n" + "WHERE CS.CONTENTS_NO = "
					+ contents_no + "\r\n";
			PreparedStatement getStarring_pstmt = conn.prepareStatement(getStarring_sql);
			ResultSet getStarring_ResultSet = getStarring_pstmt.executeQuery();

			while (getStarring_ResultSet.next())
				System.out.print(getStarring_ResultSet.getString("STARRING_NAME") + " ");
			System.out.println();

			// 줄거리
			System.out.println(synopsis);

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // method | chooseDrama
	
}
