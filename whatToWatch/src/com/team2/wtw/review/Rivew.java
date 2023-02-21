package com.team2.wtw.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.wtw.main.Main;
import com.team2.wtw.template.JdbcConncetionTemplate;
import com.team2.wtw.template.PrintTemplate;

public class Rivew {

	public void processReview(String choice) {
		String contentsNo = choice;
		String input = "";
		
		
		showReview(contentsNo);
		
		System.out.println("1. 돌아가기,  2. 리뷰쓰기");
		System.out.print("입력 : ");
		input = Main.SC.nextLine();
		
		if(input.equals("1")) {
			
			
			
		}
		else if(input.equals("2")) {
			
			writeReview(contentsNo);
			
		}
		
	}
	
	public void showReview(String contentsNo) {

		PrintTemplate.printFloor();

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String getReviewList_sql = "SELECT REVIEW_CONTENT, M.MEMBER_NICK, TO_CHAR(WRITE_DATE, 'YY/MM/DD') WRITE_DAY, WRITE_DATE\r\n"
				+ "FROM REVIEW R\r\n" + "    JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO\r\n" + "WHERE R.CONTENTS_NO = "
				+ contentsNo + "ORDER BY WRITE_DATE DESC";
		try {
			PreparedStatement riviewList_pstmt = conn.prepareStatement(getReviewList_sql);
			ResultSet reviewList_ResultSet = riviewList_pstmt.executeQuery();

			int i = 0;
			System.out.println("[리뷰]");
			while (reviewList_ResultSet.next()) {
				System.out.printf("%d. %s \t\t\t %s \t %s\n", i + 1, reviewList_ResultSet.getString("REVIEW_CONTENT"),
						reviewList_ResultSet.getString("MEMBER_NICK"), reviewList_ResultSet.getString("WRITE_DAY"));
				i++;
			}

			PrintTemplate.printFloor();

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // method | showReview

	public void writeReview(String contentsNo) {

		String riviewContent = "";
		String riviewScore = "";
		
		System.out.println(Main.userData.getUserNum());
		
		System.out.print("리뷰 : ");
		riviewContent = Main.SC.nextLine();
		System.out.print("평점 : ");
		riviewScore = Main.SC.nextLine();
		
		PrintTemplate.printFloor();

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String writeReview_sql = "INSERT INTO REVIEW\r\n"
				+ "VALUES( REVIEW_NO_SEQ.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT)";
		
		try {
			
			PreparedStatement riviewWrite_pstmt = conn.prepareStatement(writeReview_sql);
			riviewWrite_pstmt.setString(1, contentsNo);
			riviewWrite_pstmt.setString( 2, Integer.toString(Main.userData.getUserNum()) );
			riviewWrite_pstmt.setString(3, "'" + riviewContent + "'");
			riviewWrite_pstmt.setString(4, riviewScore);
			
			int result = riviewWrite_pstmt.executeUpdate();
			
			if(result == 0) {
				
				System.out.println("리뷰 작성에 실패했습니다..");
				
			}
			else if (result == 1) {
				
				System.out.println("리뷰 작성 성공했습니다.");
				
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	} // method | writeReview

} // class | Rivew
