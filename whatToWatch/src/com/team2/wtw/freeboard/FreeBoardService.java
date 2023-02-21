package com.team2.wtw.freeboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.team2.wtw.freeboard.comment.CommentService;
import com.team2.wtw.main.Main;

public class FreeBoardService {

	public ArrayList boardNoList = new ArrayList();
	
	public static int BoardNo=0;
	
	
	public void BoardList() throws Exception {

		Connection conn = GetConnection.conn();
		String sql = "SELECT TITLE,MEMBER_NICK,BOARD_NO FROM FREEBOARD B JOIN MEMBER M ON B.MEMBER_NO=M.MEMBER_NO WHERE DELETE_YN='N'";
		// 게시물 목록 조회
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {

			String title = rs.getString("TITLE");
			String writer = rs.getString("MEMBER_NICK");
			boardNoList.add("BOARD_NO");
			
			System.out.println("-------------------");
			System.out.println("제목 : " + title);
			System.out.println("작성자 : " + writer);
			System.out.println("-------------------");
		}

		conn.close();
	}// bl

	// 게시물 작성
	public void BoardWrite() throws Exception {

		Connection conn = GetConnection.conn();
		String sql = "INSERT INTO FREEBOARD(BOARD_NO,TITLE,CONTENT,MEMBER_NO) VALUES(SEQ_BOARD_BOARD_NO.NEXTVAL,?,?,?)";// 마지막
																														// 물음표는
																														// 작성자

		System.out.print("제목 : ");
		String title = Main.SC.nextLine();

		System.out.print("내용 : ");
		String content = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setInt(3,1 );//작성자
		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println("게시물 작성 성공 !!!");
		} else {
			System.out.println("게시물 작성 실패...");
		}

		conn.close();

	}// bw
		
	// 게시물 조회
	public void BoardSearch() throws Exception {

		int nick= 1; // 로그인시 계정 번호
		String sql = "SELECT TITLE,CONTENT,MEMBER_NICK,ENROLL_DATE,BOARD_NO FROM FREEBOARD B JOIN MEMBER M ON B.MEMBER_NO = M.MEMBER_NO WHERE TITLE = ?";
		System.out.println("제목을 검색하시오. ");
		System.out.println("제목 : ");
		String title = Main.SC.nextLine();

		Connection conn = GetConnection.conn();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.executeQuery();

		 if(rs.next()) {
			
			String  title2 = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writer = rs.getString("MEMBER_NICK");
			String date = rs.getString("ENROLL_DATE");
			BoardNo = rs.getInt("BOARD_NO");
			
			System.out.println("-------------------");
			System.out.println("제목 : " + title2);
			System.out.println("내용 : " + content);
			System.out.println("작성자 : " + writer);
			System.out.println("작성일자 : " + date);
			System.out.println("-------------------");
			
			conn.close();//
			
			CommentService cs = new CommentService();
			cs.readComment();
			System.out.println(BoardNo);
			System.out.println("1. 댓글 작성 ..");
			System.out.println("2. 댓글 삭제");
			System.out.println("3. 돌아가기");
			String pick = Main.SC.nextLine();
			
			if(pick.equals("1")) {
				
				cs.writeComment();
				
				System.out.println("-------------------");
				System.out.println("제목 : " + title2);
				System.out.println("내용 : " + content);
				System.out.println("작성자 : " + writer);
				System.out.println("작성일자 : " + date);
				System.out.println("-------------------");
				
				cs.readComment();
				
			}else if (pick.equals("2")) {
				cs.deleteComment();
			}else {
			}
		} 

	}// boardsearch

	// 게시물 수정
	public void edit() throws Exception {

		// sql이용하여 어떤 게시물 수정할지 컨택
		int nick = 1 ;// 로그인시 계정 번호
		String sql = "SELECT TITLE,CONTENT,MEMBER_NICK,ENROLL_DATE,BOARD_NO FROM FREEBOARD B JOIN MEMBER M ON B.MEMBER_NO = M.MEMBER_NO WHERE TITLE = ? AND M.MEMBER_NO="
				+ nick;

		System.out.println("수정할 게시물의 제목을 검색하시오. ");
		System.out.println("제목 : ");
		String title = Main.SC.nextLine();

		Connection conn = GetConnection.conn();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.executeQuery();
		
		
		// 수정할 게시물 보여주고
		if (rs.next()) {
			String title2 = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writer = rs.getString("MEMBER_NICK");
			String date = rs.getString("ENROLL_DATE");
			BoardNo = rs.getInt("BOARD_NO");
			
			System.out.println("-------------------");
			System.out.println("제목 : " + title2);
			System.out.println("내용 : " + content);
			System.out.println("작성자 : " + writer);
			System.out.println("작성일자 : " + date);
			System.out.println("-------------------");

		} else {
			System.out.println("검색결과가 없습니다");
		}

		// 게시물 수정
		String reSql = "UPDATE FREEBOARD SET TITLE = ? ,CONTENT  = ? WHERE MEMBER_NO =" + nick + " AND BOARD_NO =?";

		System.out.println("제목을 수정하시오 . . .");
		System.out.println("제목 : ");
		String reTitle = Main.SC.nextLine();

		System.out.println("내용을 수정하시오 . . .");
		System.out.println("내용 : ");
		String reContent = Main.SC.nextLine();

		PreparedStatement rePstmt = conn.prepareStatement(reSql);
		rePstmt.setString(1, reTitle);
		rePstmt.setString(2, reContent);
		rePstmt.setInt(3, BoardNo );
		
		int result = rePstmt.executeUpdate();

		if (result == 1) {
			System.out.println("게시물 수정 완료!!");
		} else {
			System.out.println("실패...");
		}

		conn.close();
	}// EDIT

	// 게시물 삭제
	public void boardDelect() throws Exception {
		// sql이용하여 어떤 게시물 수정할지 컨택
		int nick = 1;// 로그인시 계정번호
		String sql = "SELECT TITLE,CONTENT,MEMBER_NICK,ENROLL_DATE,BOARD_NO FROM FREEBOARD B JOIN MEMBER M ON B.MEMBER_NO = M.MEMBER_NO WHERE TITLE = ? AND DELETE_YN ='N' AND M.MEMBER_NO="+nick;

		System.out.println("삭제할 게시물의 제목을 검색하시오. ");
		System.out.print("제목 : ");
		String title = Main.SC.nextLine();

		Connection conn = GetConnection.conn();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.executeQuery();

		// 삭제할 게시물 보여주고
		if (rs.next()) {
			String title2 = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writer = rs.getString("MEMBER_NICK");
			String date = rs.getString("ENROLL_DATE");
			BoardNo = rs.getInt("BOARD_NO"); 
			
			System.out.println("-------------------");
			System.out.println("제목 : " + title2);
			System.out.println("내용 : " + content);
			System.out.println("작성자 : " + writer);
			System.out.println("작성일자 : " + date);
			System.out.println("-------------------");

		} else {
			System.out.println("검색결과가 없습니다");
		}

		String delectSql = "UPDATE FREEBOARD SET DELETE_YN = 'Y' WHERE MEMBER_NO =? AND TITLE = ? AND BOARD_NO="+BoardNo;

		System.out.println("이 게시물을 삭제 하시겠습니까??");
		System.out.println("1.예 2.아니오");
		int choise = Main.SC.nextInt();

		if (choise == 1) {
			PreparedStatement deletePstmt = conn.prepareStatement(delectSql);
			deletePstmt.setInt(1,nick);
			deletePstmt.setString(2, title);
			deletePstmt.executeUpdate();

			System.out.println("삭제 완료 되었습니다.");
			
		} else if (choise == 2) {
			System.out.println("");
		} 
		
		conn.close();

	}// 게시물 삭제

}
