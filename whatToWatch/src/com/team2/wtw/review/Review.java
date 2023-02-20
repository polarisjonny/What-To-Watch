package com.team2.wtw.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.wtw.contents.ContentsHome;
import com.team2.wtw.main.Main;
import com.team2.wtw.template.JdbcConncetionTemplate;

public class Review {

	public void showReview(String contentsNo) {

		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			String showReview_sql = "SELECT REVIEW_NO, REVIEW_CONTENT, REVIEW_SCORE, WRITE_DATE, MEMBER_NO\r\n"
					+ "FROM REVIEW R\r\n"
					+ "    JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO\r\n"
					+ "WHERE R.CONTENTS_NO = " + contentsNo;
			
			PreparedStatement reviewList_pstmt = conn.prepareStatement(showReview_sql);
			ResultSet reviewResultSet = reviewList_pstmt.executeQuery();
			
			while(reviewResultSet.next()) {
				
				
				
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // method | showReview

	public void writeReviwe(String contentsNo) {

		System.out.print("리뷰 작성 : ");
		String reviewContents = Main.SC.nextLine();
		System.out.print("평점 : ");
		double reviewScore = Main.SC.nextDouble();

		try {
			// DB 연결
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			// 최신순
			String writeReview_sql = "INSERT INTO REVIEW\r\n" + "VALUES ( REVIEW_NO_SEQ.NEXTVAL\r\n" + "        , "
					+ contentsNo + "\r\n" + "        , " + Main.userData.getUserNum() + "\r\n" + "        , "
					+ reviewContents + "\r\n" + "        , " + reviewScore + "\r\n" + "        , SYSDATE\r\n"
					+ "        , DEFAULT)";
			
			
			
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // method | writeReviwe

}
