package com.team2.wtw.contents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieContents {

	private String[] movieList = new String[10];

	public void showMovieContents() {

		try {
			// DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "C##TEAM2TEST";
			String pw = "T2T";
			Connection conn = DriverManager.getConnection(url, id, pw);

			// 최신순
			String movieList_sql = "SELECT CONTENTS_NO, CONTENTS_TITLE, TO_CHAR(RELEASE_DATE, 'YY/MM/DD') AS RELEASE_DATE\r\n"
					+ "FROM\r\n" + "(\r\n" + "    SELECT ROWNUM \"NUM\", CONTENTS_NO, CONTENTS_TITLE, RELEASE_DATE\r\n"
					+ "    FROM CONTENTS T1\r\n" + "        JOIN CATEGORY T2 ON T1.CATEGORY_NO = T2.CATEGORY_NO \r\n"
					+ "    WHERE CATEGORY_NAME = '영화'\r\n" + "    ORDER BY RELEASE_DATE DESC\r\n" + ")\r\n"
					+ "WHERE NUM > ? AND NUM <= ?\r\n";

			PreparedStatement movieList_pstmt = conn.prepareStatement(movieList_sql);

			movieList_pstmt.setString(1, "0");
			movieList_pstmt.setString(2, "10");
			ResultSet movieList_ResultSet = movieList_pstmt.executeQuery();

			System.out.println("영화");
			int i = 1;
			while (movieList_ResultSet.next()) {
				movieList[i - 1] = movieList_ResultSet.getString("CONTENTS_NO");
				System.out.printf("%d. %-20s %s\n", i, movieList_ResultSet.getString("CONTENTS_TITLE"),
						movieList_ResultSet.getString("RELEASE_DATE"));
				i++;
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // method | showMovieContents

	public void chooseMovie() {

		String input = ContentsHome.SC.nextLine();

		try {
			// DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "C##TEAM2TEST";
			String pw = "T2T";
			Connection conn = DriverManager.getConnection(url, id, pw);

			String movieDetail_sql = "SELECT CONTENTS_NO\r\n" + "    ,DIRECTOR_NO\r\n" + "    ,COUNTRY_NO\r\n"
					+ "    ,CATEGORY_NO\r\n" + "    ,CONTENTS_TITLE\r\n" + "    ,SYNOPSIS\r\n"
					+ "    ,TO_CHAR(RELEASE_DATE, 'YY/MM/DD') AS RELEASE_DATE\r\n" + "FROM CONTENTS\r\n"
					+ "WHERE CONTENTS_NO = " + movieList[Integer.parseInt(input) - 1];

			PreparedStatement movieDetail_pstmt = conn.prepareStatement(movieDetail_sql);
			ResultSet movieDetail_ResultSet = movieDetail_pstmt.executeQuery();

			movieDetail_ResultSet.next();
			String contents_no = movieDetail_ResultSet.getString("CONTENTS_NO");
			String director_no = movieDetail_ResultSet.getString("DIRECTOR_NO");
			String country_no = movieDetail_ResultSet.getString("COUNTRY_NO");
			String category_no = movieDetail_ResultSet.getString("CATEGORY_NO");
			String contents_title = movieDetail_ResultSet.getString("CONTENTS_TITLE");
			String synopsis = movieDetail_ResultSet.getString("SYNOPSIS");
			String release_date = movieDetail_ResultSet.getString("RELEASE_DATE");

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

	} // method | chooseMovie

}
