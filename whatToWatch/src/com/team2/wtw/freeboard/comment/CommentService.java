package com.team2.wtw.freeboard.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team2.wtw.freeboard.FreeBoardService;
import com.team2.wtw.freeboard.GetConnection;
import com.team2.wtw.main.Main;

public class CommentService {
	
	public void writeComment() throws Exception {
		
		Connection conn = GetConnection.conn();
		
		String sql = "INSERT INTO \"COMMENT\" (COMMENT_NO,BOARD_NO,MEMBER_NO,C_CHAT)VALUES (SEQ_COMMENT_COMMENT_NO.NEXTVAL,?,?,?)";
		
		System.out.println("댓글 :");
		String comment = Main.SC.nextLine();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,FreeBoardService.BoardNo);
		pstmt.setInt(2,Main.userData.getUserNum());
		pstmt.setString(3,comment);
		int result = pstmt.executeUpdate();
		if(result==1) {
			System.out.println("댓글 작성완료");
		}
		
		
	} ///댓글 쓰기
	
	public void readComment() throws Exception {
		
		Connection conn = GetConnection.conn();
		
		String sql = "SELECT C_CHAT,MEMBER_NICK FROM \"COMMENT\"  C JOIN MEMBER M ON C.MEMBER_NO = M.MEMBER_NO WHERE BOARD_NO=? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,FreeBoardService.BoardNo);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String chat = rs.getString("C_CHAT");
			String nick = rs.getString("MEMBER_NICK");
			
			System.out.println("----------------");		
			System.out.println("댓글 : "+chat);
			System.out.println("작성자 : "+nick);
			System.out.println("----------------");		
		}
		
		
	}

}
