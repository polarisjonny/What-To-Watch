package com.team2.wtw.freeboard.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team2.wtw.freeboard.FreeBoardService;
import com.team2.wtw.freeboard.GetConnection;
import com.team2.wtw.main.Main;

public class CommentService {
	int commentNo = 0;
	
	public void writeComment() throws Exception {
		
		Connection conn = GetConnection.conn();
		
		String sql = "INSERT INTO \"COMMENT\" (COMMENT_NO,BOARD_NO,MEMBER_NO,C_CHAT)VALUES (SEQ_COMMENT_COMMENT_NO.NEXTVAL,?,?,?)";
		
		System.out.print("댓글 :");
		String comment = Main.SC.nextLine();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,FreeBoardService.BoardNo);
		pstmt.setInt(2,Main.userData.getUserNum()); //유저 넘버
		pstmt.setString(3,comment);
		int result = pstmt.executeUpdate();
		if(result==1) {
			System.out.println("댓글 작성완료");
		}else {
			System.out.println("댓글 작성 실패");
		}
		
		conn.close();
	} ///댓글 쓰기
	//댓글 출력
	public void readComment() throws Exception {
		
		Connection conn = GetConnection.conn();
		String sql = "SELECT C_CHAT,MEMBER_NICK,COMMENT_NO FROM \"COMMENT\"  C JOIN MEMBER M ON C.MEMBER_NO = M.MEMBER_NO WHERE BOARD_NO=? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,FreeBoardService.BoardNo);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String chat = rs.getString("C_CHAT");
			String nick = rs.getString("MEMBER_NICK");
			commentNo = rs.getInt("COMMENT_NO");
			
			System.out.println("*****************");		
			System.out.println("작성자 : "+nick);
			System.out.println("댓글 : "+chat);
			System.out.println("댓글번호 : "+commentNo);
			System.out.println("*****************");		
			
		}
		
		conn.close();
		
		
	}//댓글 출력
	
	//댓글 삭제 
	public void deleteComment() throws Exception {
		
		Connection conn = GetConnection.conn();
		
		
		System.out.println("삭제할 댓글 번호를 입력하시오");
		System.out.print("댓글번호 : ");
		String pickNo = Main.SC.nextLine();

		String Sql="DELETE \"COMMENT\" WHERE COMMENT_NO="+pickNo+" AND BOARD_NO="+FreeBoardService.BoardNo+" AND MEMBER_NO="+Main.userData.getUserNum();
		//사용자
		
		
		PreparedStatement pstmt = conn.prepareStatement(Sql);
		int result = pstmt.executeUpdate();
		
		if(result==1) {
			System.out.println("삭제완료");
		}else {
			System.out.println("댓글번호가 잘못되었습니다");
		}
		
		
		
		
	}
	
	
}
