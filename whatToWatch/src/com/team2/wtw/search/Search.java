package com.team2.wtw.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team2.wtw.main.Main;
import com.team2.wtw.review.Review;
import com.team2.wtw.template.JdbcConncetionTemplate;
import com.team2.wtw.template.PrintTemplate;

public class Search {

	private String searchScope = null;
	private String searchCategory = null;
	private String searchKeyword = null;

	private List<String> searchResultList = new ArrayList<String>();

	public void processSearch() {

		while (searchScope == null)
			selectSearchScope();
		while (searchCategory == null)
			selectSearchCategory();

		getKeyword();

		search();

		if (searchResultList.isEmpty()) {

		} else {
			String choice = showDetail();

			new Review().processReview(choice);
		}

	} // method | processSEarch

	private void selectSearchScope() {

		String scope = "";
		System.out.println("검색 범위 : 1. 통합검색,  2. 영화  3. 드라마  4. 예능");
		System.out.print("입력 : ");
		scope = Main.SC.nextLine();

		if (scope.equals("1")) {
			setSearchScope(scope);

		} else if (scope.equals("2")) {
			setSearchScope(scope);

		} else if (scope.equals("3")) {
			setSearchScope(scope);

		} else if (scope.equals("4")) {
			setSearchScope(scope);

		} else {
			System.out.println("잘못된 입력입니다.");

			setSearchScope(null);
		}

//		switch (scope) {
//		case "1":
//			setSearchScope(scope);
//			break;
//		case "2":
//			setSearchScope(scope);
//			break;
//		case "3":
//			setSearchScope(scope);
//			break;
//		case "4":
//			setSearchScope(scope);
//			break;
//		default:
//			System.out.println("잘못된 입력입니다..");
//			break;
//		}
//
//		setSearchScope(null);

	}

	private void selectSearchCategory() {

		String category = "";
		System.out.println("검색 카테고리 : 1. 제목,  2. 출연자  3. 감독  4. 장르");
		System.out.print("입력 : ");
		category = Main.SC.nextLine();

		if (category.equals("1")) {

			setSearchCategory(category);

		} else if (category.equals("2")) {

			setSearchCategory(category);

		} else if (category.equals("3")) {

			setSearchCategory(category);

		} else if (category.equals("4")) {

			setSearchCategory(category);

		} else {
			System.out.println("잘못된 입력입니다..");

			setSearchCategory(null);
		}

//		switch (category) {
//		case "1":
//			setSearchCategory(category);
//			break;
//		case "2":
//			setSearchCategory(category);
//			break;
//		case "3":
//			setSearchCategory(category);
//			break;
//		case "4":
//			setSearchCategory(category);
//			break;
//		default:
//			System.out.println("잘못된 입력입니다..");
//			break;
//		}
//
//		setSearchCategory(null);

	}

	private void getKeyword() {

		System.out.print("검색 :");
		setSearchKeyword(Main.SC.nextLine());

	}

	private void search() {

		if (searchCategory.equals("1")) {

			titleSearch();

		} else if (searchCategory.equals("2")) {

			directorSearch();

		} else if (searchCategory.equals("3")) {

			starringSearch();

		} else if (searchCategory.equals("4")) {

			genreSearch();

		}

	}

	private String addScope(String sql) {

		String addSql = "";

		if (searchScope.equals("1")) {

			addSql = sql;

		} else if (searchScope.equals("2")) {

			addSql = sql + "\r\n AND CATEGORY_NO = 1"; // 영화

		} else if (searchScope.equals("3")) {

			addSql = sql + "\r\n AND CATEGORY_NO = 2"; // 드라마

		} else if (searchScope.equals("4")) {

			addSql = sql + "\r\n AND CATEGORY_NO = 3"; // 예능

		}

		return addSql;
	}

	private void titleSearch() {
		searchResultList.clear();

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String search_sql = "SELECT C.CONTENTS_NO, CONTENTS_TITLE\r\n" + "FROM CONTENTS C\r\n"
				+ "WHERE CONTENTS_TITLE LIKE ?";

		search_sql = addScope(search_sql);

		try {
			PreparedStatement search_pstmt = conn.prepareStatement(search_sql);

			search_pstmt.setString(1, "%" + searchKeyword + "%");

			ResultSet searchResultSet = search_pstmt.executeQuery();

			if (searchResultSet.next()) {

				searchResultList.add(searchResultSet.getString("CONTENTS_NO"));
				System.out.printf("%d. %s\n", 1, searchResultSet.getString("CONTENTS_TITLE"));

			} else {
				System.out.println("검색 결과가 없습니다 ..");
			}

			int i = 2;
			while (searchResultSet.next()) {

				searchResultList.add(searchResultSet.getString("CONTENTS_NO"));
				System.out.printf("%d. %s\n", i, searchResultSet.getString("CONTENTS_TITLE"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void directorSearch() {
		searchResultList.clear();

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String search_sql = "SELECT C.CONTENTS_NO, CONTENTS_TITLE, DIRECTOR_NAME\r\n" + "FROM CONTENTS C\r\n"
				+ "    JOIN DIRECTOR D ON C.DIRECTOR_NO = D.DIRECTOR_NO\r\n" + "WHERE DIRECTOR_NAME LIKE ?";

		search_sql = addScope(search_sql);

		try {
			PreparedStatement search_pstmt = conn.prepareStatement(search_sql);

			search_pstmt.setString(1, "%" + searchKeyword + "%");

			ResultSet searchResultSet = search_pstmt.executeQuery();

			if (searchResultSet.next()) {

				searchResultList.add(searchResultSet.getString("CONTENTS_NO"));
				System.out.printf("%d. %s \t\t %s\n", 1, searchResultSet.getString("CONTENTS_TITLE"),
						searchResultSet.getString("DIRECTOR_NAME"));

			} else {
				System.out.println("검색 결과가 없습니다 ..");
			}

			int i = 2;
			while (searchResultSet.next()) {

				searchResultList.add(searchResultSet.getString("CONTENTS_NO"));
				System.out.printf("%d. %s \t\t %s\n", i, searchResultSet.getString("CONTENTS_TITLE"),
						searchResultSet.getString("DIRECTOR_NAME"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void starringSearch() {
		searchResultList.clear();

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String search_sql = "SELECT C.CONTENTS_NO, CONTENTS_TITLE, STARRING_NAME\r\n" + "FROM CONTENTS C\r\n"
				+ "    JOIN CONTENTS_STARRING CS ON C.CONTENTS_NO = CS.CONTENTS_NO\r\n"
				+ "    JOIN STARRING S ON CS.STARRING_NO = S.STARRING_NO\r\n" + "WHERE STARRING_NAME LIKE ?";

		search_sql = addScope(search_sql);

		try {
			PreparedStatement search_pstmt = conn.prepareStatement(search_sql);

			search_pstmt.setString(1, "%" + searchKeyword + "%");

			ResultSet searchResultSet = search_pstmt.executeQuery();

			if (searchResultSet.next()) {

				searchResultList.add(searchResultSet.getString("CONTENTS_NO"));
				System.out.printf("%d. %s \t\t %s\n", 1, searchResultSet.getString("CONTENTS_TITLE"),
						searchResultSet.getString("STARRING_NAME"));

			} else {
				System.out.println("검색 결과가 없습니다 ..");
			}

			int i = 2;
			while (searchResultSet.next()) {

				searchResultList.add(searchResultSet.getString("CONTENTS_NO"));
				System.out.printf("%d. %s \t\t %s\n", i, searchResultSet.getString("CONTENTS_TITLE"),
						searchResultSet.getString("STARRING_NAME"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void genreSearch() {
		searchResultList.clear();

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String search_sql = "SELECT C.CONTENTS_NO, CONTENTS_TITLE, GENRE_NAME\r\n" + "FROM CONTENTS C\r\n"
				+ "    JOIN CONTENTS_GENRE CG ON C.CONTENTS_NO = CG.CONTENTS_NO\r\n"
				+ "    JOIN GENRE G ON CG.GENRE_NO = G.GENRE_NO\r\n" + "WHERE GENRE_NAME LIKE ?";

		search_sql = addScope(search_sql);

		try {
			PreparedStatement search_pstmt = conn.prepareStatement(search_sql);

			search_pstmt.setString(1, "%" + searchKeyword + "%");

			ResultSet searchResultSet = search_pstmt.executeQuery();

			if (searchResultSet.next()) {

				searchResultList.add(searchResultSet.getString("CONTENTS_NO"));
				System.out.printf("%d. %s \t\t %s\n", 1, searchResultSet.getString("CONTENTS_TITLE"),
						searchResultSet.getString("GENRE_NAME"));

			} else {
				System.out.println("검색 결과가 없습니다 ..");
			}

			int i = 2;
			while (searchResultSet.next()) {

				searchResultList.add(searchResultSet.getString("CONTENTS_NO"));
				System.out.printf("%d. %s \t\t %s\n", i, searchResultSet.getString("CONTENTS_TITLE"),
						searchResultSet.getString("GENRE_NAME"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private String showDetail() {

		System.out.print("세부 정보를 볼 영화의 번호를 입력해주세요 : ");
		String input = Main.SC.nextLine();

		PrintTemplate.printFloor();

		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			String movieDetail_sql = "SELECT CONTENTS_NO\r\n" + "    ,DIRECTOR_NO\r\n" + "    ,COUNTRY_NO\r\n"
					+ "    ,CATEGORY_NO\r\n" + "    ,CONTENTS_TITLE\r\n" + "    ,SYNOPSIS\r\n"
					+ "    ,TO_CHAR(RELEASE_DATE, 'YY/MM/DD') AS RELEASE_DATE\r\n" + "FROM CONTENTS\r\n"
					+ "WHERE CONTENTS_NO = " + searchResultList.get(Integer.parseInt(input) - 1);

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
			String avgScore = getAvgScore(searchResultList.get(Integer.parseInt(input) - 1));
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

		return searchResultList.get(Integer.parseInt(input) - 1);

	}

	public String getAvgScore(String contentsNo) {

		String avgScore = "";

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String getAvg_sql = "SELECT AVG(REVIEW_SCORE)\r\n" + "FROM REVIEW\r\n" + "WHERE CONTENTS_NO = ?";

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

	public String getSearchScope() {
		return searchScope;
	}

	public void setSearchScope(String searchScope) {
		this.searchScope = searchScope;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

} // class | Search