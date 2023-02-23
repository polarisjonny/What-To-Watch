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
	            ResultSet rs = stmt.executeQuery("SELECT R.REVIEW_NO, M.MEMBER_NO, M.MEMBER_NICK, R.REVIEW_CONTENT, TO_CHAR(WRITE_DATE, 'YY/MM/DD') AS \"WRITE_DAY\"\r\n"
	            		+ "FROM REVIEW R\r\n"
	            		+ "    JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO");
	            
	            System.out.println("리뷰번호 | 회원번호 | 작성자 닉네임 | 리뷰 내용 | 작성일");
	            
	            // 전제 행 출력
	            while (rs.next()) {
	                int reviewNo = rs.getInt("REVIEW_NO");
	                int memberNo = rs.getInt("MEMBER_NO");
	                String memberNick = rs.getString("MEMBER_NICK");
	                String reviewContent = rs.getString("REVIEW_CONTENT");
	                String writeDate = rs.getString("WRITE_DAY");
	                
	                System.out.println(reviewNo + "\t" + memberNo + "\t" + memberNick + "\t" + reviewContent + "\t" +  writeDate + "\t");
	            }
	            
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}

