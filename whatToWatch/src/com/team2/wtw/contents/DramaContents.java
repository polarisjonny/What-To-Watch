package com.team2.wtw.contents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.wtw.main.Main;
import com.team2.wtw.review.Review;
import com.team2.wtw.template.JdbcConncetionTemplate;
import com.team2.wtw.template.PrintTemplate;

public class DramaContents {

	private String[] dramaList = new String[10];

	public void processDrama() {

		// 총 드라마 컨텐츠 수 ( 페이지에 이용 )
		int count = getCount();
		// 선택한 컨텐츠의 CONTENTS_NO. 리뷰로 넘길용도.
		String choice = "";

		// 컨텐츠가 없을리 없지만 없다면...
		if (count == 0) {
			System.out.println("컨텐츠 count 0개...");
			// 드라마 컨텐츠 목록 출력 (10개씩)
		} else {
			showDramaContents(count);
		}
		// 세부 보기한 컨텐츠의 CONTENTS_NO. choice에 저장.
		choice = showDramaDetail();
		
		// 리뷰 기능으로
		new Review().processReview(choice);
	}

	// 총 드라마 컨텐츠 수 가져오기
	private int getCount() {
		String result = "";
		int count = 0;

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String getCount_sql = "SELECT COUNT(CONTENTS_NO) \"COUNT\"\r\n" + "FROM CONTENTS C1\r\n"
				+ "    JOIN CATEGORY C2 ON C1.CATEGORY_NO = C2.CATEGORY_NO\r\n" + "WHERE C2.CATEGORY_NAME = '드라마'";

		try {
			PreparedStatement getCount_pstmt = conn.prepareStatement(getCount_sql);
			ResultSet countResultSet = getCount_pstmt.executeQuery();

			countResultSet.next();
			result = countResultSet.getString("COUNT");
			count = Integer.parseInt(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		count = ((count + 9) / 10) * 10;

		return count;
	} // method | getCount

	// 드라마 목록 출력하기
	public void showDramaContents(int count) {

		int start = 0;
		int end = 10;

		String input = "";

		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			while (!(input.equals("C"))) {
				String dramaList_sql = "SELECT RANK, CONTENTS_NO, CONTENTS_TITLE, SUBSTR(SYNOPSIS, 1, 10) SYNOPSIS, TO_CHAR(RELEASE_DATE, 'YY/MM/DD') RELEASEDATE\r\n"
						+ "FROM\r\n" + "(\r\n"
						+ "    SELECT RANK() OVER (ORDER BY RELEASE_DATE DESC) RANK, CONTENTS_NO, CONTENTS_TITLE, SYNOPSIS, RELEASE_DATE\r\n"
						+ "    FROM CONTENTS C1\r\n" + "        JOIN CATEGORY C2 ON C1.CATEGORY_NO = C2.CATEGORY_NO\r\n"
						+ "    WHERE CATEGORY_NAME = '드라마'\r\n" + ")\r\n" + "WHERE RANK > ? AND RANK <= ?";

				PreparedStatement dramaList_pstmt = conn.prepareStatement(dramaList_sql);

				dramaList_pstmt.setInt(1, start);
				dramaList_pstmt.setInt(2, end);
				ResultSet dramaList_ResultSet = dramaList_pstmt.executeQuery();

				PrintTemplate.printFloor();
				
				System.out.println("[드라마]");
				int i = 0;
				while (dramaList_ResultSet.next()) {
					dramaList[i] = dramaList_ResultSet.getString("CONTENTS_NO");
					System.out.printf("%d. %s \t\t %s \t\t %s\n", i + 1, dramaList_ResultSet.getString("CONTENTS_TITLE"),
							dramaList_ResultSet.getString("SYNOPSIS"), dramaList_ResultSet.getString("RELEASEDATE"));
					i++;
				}

				PrintTemplate.printFloor();

				if (start == 0 && end == count) {
					// c
					System.out.println("선택 | 현재페이지 : C");
					System.out.print("입력 : ");
					input = Main.SC.nextLine();

				} else if (start == 0 && end < count) {
					// n , c
					System.out.println("선택 | 다음페이지 : N, 현재페이지 : C");
					System.out.print("입력 : ");
					input = Main.SC.nextLine();
					if (input.equals("N")) {
						start += 10;
						end += 10;
					} else if (input.equals("C")) {

					}
				} else if (start >= 10 && end < count) {
					// p, n, c
					System.out.println("선택 | 이전페이지 : P, 다음페이지 : N, 현재페이지 : C");
					System.out.print("입력 : ");
					input = Main.SC.nextLine();
					if (input.equals("P")) {
						start -= 10;
						end -= 10;
					} else if (input.equals("N")) {
						start += 10;
						end += 10;
					} else if (input.equals("C")) {

					}
				} else if (start >= 10 && end == count) {
					// p, c
					System.out.println("선택 | 이전페이지 : P, 현재페이지 : C");
					System.out.print("입력 : ");
					input = Main.SC.nextLine();
					if (input.equals("P")) {
						start -= 10;
						end -= 10;
					} else if (input.equals("C")) {

					}
				}

			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // method | showDramaContents

	// 선택한 드라마 세부 정보 보기
	public String showDramaDetail() {

		System.out.print("세부 정보를 볼 드라마의 번호를 입력해주세요 : ");
		String input = Main.SC.nextLine();

		PrintTemplate.printFloor();
		
		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			String dramaDetail_sql = "SELECT CONTENTS_NO\r\n" + "    ,DIRECTOR_NO\r\n" + "    ,COUNTRY_NO\r\n"
					+ "    ,CATEGORY_NO\r\n" + "    ,CONTENTS_TITLE\r\n" + "    ,SYNOPSIS\r\n"
					+ "    ,TO_CHAR(RELEASE_DATE, 'YY/MM/DD') AS RELEASE_DATE\r\n" + "FROM CONTENTS\r\n"
					+ "WHERE CONTENTS_NO = " + dramaList[Integer.parseInt(input) - 1];

			PreparedStatement dramaDetail_pstmt = conn.prepareStatement(dramaDetail_sql);
			ResultSet dramaDetail_ResultSet = dramaDetail_pstmt.executeQuery();

			dramaDetail_ResultSet.next();
			String contents_no = dramaDetail_ResultSet.getString("CONTENTS_NO");
			String director_no = dramaDetail_ResultSet.getString("DIRECTOR_NO");
			String country_no = dramaDetail_ResultSet.getString("COUNTRY_NO");
			String category_no = dramaDetail_ResultSet.getString("CATEGORY_NO");
			String contents_title = dramaDetail_ResultSet.getString("CONTENTS_TITLE");
			String synopsis = dramaDetail_ResultSet.getString("SYNOPSIS");
			String release_date = dramaDetail_ResultSet.getString("RELEASE_DATE");

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

			System.out.print("출연진 :");
			while (getStarring_ResultSet.next())
				System.out.print(getStarring_ResultSet.getString("STARRING_NAME") + " ");
			System.out.println();

			// 줄거리
			System.out.println(synopsis);

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dramaList[Integer.parseInt(input) - 1];

	} // method | chooseDrama

}
