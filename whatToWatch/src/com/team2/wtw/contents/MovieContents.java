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

public class MovieContents {

	private String[] movieList = new String[10];

	public void processMovie() {

		// 총 드라마 컨텐츠 수 ( 페이지에 이용 )
		int count = getCount();
		// 선택한 컨텐츠의 CONTENTS_NO. 리뷰로 넘길용도.
		String choice = "";

		// 컨텐츠가 없을리 없지만 없다면...
		if (count == 0) {
			System.out.println("컨텐츠 count 0개...");
			// 옝화 컨텐츠 목록 출력 (10개씩)
		} else {
			showMovieContents(count);
		}
		// 세부 보기한 컨텐츠의 CONTENTS_NO. choice에 저장.
		choice = showMovieDetail();

		// 리뷰 기능으로
		new Review().processReview(choice);
	}

	// 총 옝화 컨텐츠 수 가져오기
	private int getCount() {
		String result = "";
		int count = 0;

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String getCount_sql = "SELECT COUNT(CONTENTS_NO) \"COUNT\"\r\n" + "FROM CONTENTS C1\r\n"
				+ "    JOIN CATEGORY C2 ON C1.CATEGORY_NO = C2.CATEGORY_NO\r\n" + "WHERE C2.CATEGORY_NAME = '영화'";

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

	// 영화 목록 출력하기
	public void showMovieContents(int count) {

		int start = 0;
		int end = 10;

		String input = "";

		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			while (!(input.equals("C"))) {

				String movieList_sql = "SELECT RANK, CONTENTS_NO, CONTENTS_TITLE, SUBSTR(SYNOPSIS, 1, 10) SYNOPSIS, TO_CHAR(RELEASE_DATE, 'YY/MM/DD') RELEASEDATE\r\n"
						+ "FROM\r\n" + "(\r\n"
						+ "    SELECT RANK() OVER (ORDER BY RELEASE_DATE DESC) RANK, CONTENTS_NO, CONTENTS_TITLE, SYNOPSIS, RELEASE_DATE\r\n"
						+ "    FROM CONTENTS C1\r\n" + "        JOIN CATEGORY C2 ON C1.CATEGORY_NO = C2.CATEGORY_NO\r\n"
						+ "    WHERE CATEGORY_NAME = '영화'\r\n" + ")\r\n" + "WHERE RANK > ? AND RANK <= ?";

				PreparedStatement movieList_pstmt = conn.prepareStatement(movieList_sql);
				movieList_pstmt.setInt(1, start);
				movieList_pstmt.setInt(2, end);
				ResultSet movieList_ResultSet = movieList_pstmt.executeQuery();

				PrintTemplate.printFloor();
				
				System.out.println("[영화]");
				int i = 0;
				while (movieList_ResultSet.next()) {
					movieList[i] = movieList_ResultSet.getString("CONTENTS_NO");
					System.out.printf("%d. %s \t\t %s \t\t %s\n", i + 1, movieList_ResultSet.getString("CONTENTS_TITLE")
							, movieList_ResultSet.getString("SYNOPSIS") , movieList_ResultSet.getString("RELEASEDATE"));
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

	} // method | showMovieContents
	

	// 선택한 옝화 세부 정보 보기
	public String showMovieDetail() {

		System.out.print("세부 정보를 볼 영화의 번호를 입력해주세요 : ");
		String input = Main.SC.nextLine();

		PrintTemplate.printFloor();
		
		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

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
			System.out.print(contents_title);
			
			// 리뷰 평균
			String avgScore = getAvgScore(movieList[Integer.parseInt(input) - 1]);
			System.out.println("\t\t\t\t 평균 리뷰평점 : " + avgScore);
			
			
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

		return movieList[Integer.parseInt(input) - 1];

	} // method | showMovieDetail

	
	// 리뷰 평균 평점 구하기
	public String getAvgScore(String contentsNo) {
		
		String avgScore = "";
		
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		String getAvg_sql = "SELECT AVG(REVIEW_SCORE)\r\n"
				+ "FROM REVIEW\r\n"
				+ "WHERE CONTENTS_NO = ?";
		
		try {
			
			PreparedStatement getAvg_pstmt = conn.prepareStatement(getAvg_sql);
			getAvg_pstmt.setString(1, contentsNo);
			
			ResultSet avgResultSet = getAvg_pstmt.executeQuery();
			
			avgResultSet.next();
			
			avgScore = avgResultSet.getString("AVG(REVIEW_SCORE)");
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return avgScore;
	}
	
} // class | MovieContents
