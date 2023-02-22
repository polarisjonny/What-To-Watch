package com.team2.wtw.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class ReviewManage {
	
	static JdbcTemplate jt = new JdbcTemplate();
	
	 public void printReviewList() {
	        try {
	            Connection conn = jt.getConnection();
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM REVIEW");
	            
	            // 전제 행 출력
	            while (rs.next()) {
	                int reviewNo = rs.getInt("REVIEW_NO");
	                int contentsNo = rs.getInt("CONTENTS_NO");
	                int memberNo = rs.getInt("MEMBER_NO");
	                String reviewContent = rs.getString("REVIEW_CONTENT");
	                double reviewScore = rs.getDouble("REVIEW_SCORE");
	                Timestamp writeDate = rs.getTimestamp("WRITE_DATE");
	                String deleteYn = rs.getString("DELETE_YN");
	                
	                System.out.println(reviewNo + "\t" + contentsNo + "\t" + memberNo + "\t" + reviewContent + "\t" + reviewScore + "\t" + writeDate + "\t" + deleteYn);
	            }
	            
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}