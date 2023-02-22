package com.team2.wtw.faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team2.wtw.main.Main;
import com.team2.wtw.template.JdbcConncetionTemplate;

public class MyQa {

	public static String Qno;
	public static String dQno;
	
	//문의 수정하기
	public void updateQ() throws Exception {
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		System.out.print("수정할 문의사항의 문의번호를 입력해주세요 : ");
		Qno = Main.SC.nextLine();
		
		String s = "SELECT Q_NO, ADMIN_NO, MEMBER_NO, CATEGORY_NAME, DETAIL_CATEGORY_CODE, Q_TITLE, Q_CONTENT, TO_CHAR(Q_DATE, 'YYYY/MM/DD HH24:MI') AS Q_DATE, A_NO, A_TITLE, A_CONTENT, TO_CHAR(A_DATE, 'YYYY/MM/DD HH24:MI') AS A_DATE, DELETE_YN, NVL(MORE_Q_NO, '0') AS MORE_Q_NO FROM QA JOIN QA_CATEGORY ON QA.CATEGORY_CODE = QA_CATEGORY.CATEGORY_CODE WHERE Q_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, Qno);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println();
		System.out.println("		=============== 수정할 게시글 ===============");
		if(rs.next()) {
			Qno = rs.getString("Q_NO");
			String title = rs.getString("Q_TITLE");
			String content = rs.getString("Q_CONTENT");
			
			System.out.println("		문의번호:" + Qno + " | 제목:" + title);
			System.out.println("		내용:" + content);
		}
		
		System.out.print("\n수정할 내용 작성 : ");
		String content = Main.SC.nextLine();
		
		String sql = "UPDATE QA SET Q_CONTENT = ? WHERE Q_NO = ?";
		PreparedStatement pstmt2 = conn.prepareStatement(sql);
		pstmt2.setString(1, content);
		pstmt2.setString(2, Qno);
		int result = pstmt2.executeUpdate();
		
		if(result == 1) {
			System.out.println("수정이 완료되었습니다!");
		}else {
			System.out.println("게시글 수정이 안되었습니다.");
		}
		conn.close();
	}
	
	//문의 삭제하기
	public void deleteQ() throws Exception {
		PlayAllFunction paf = new PlayAllFunction();
		
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		System.out.println();
		System.out.print("삭제할 게시글의 번호를 입력하세요 : ");
		dQno = Main.SC.nextLine();
	
		String s = "SELECT Q_NO, ADMIN_NO, MEMBER_NO, CATEGORY_NAME, DETAIL_CATEGORY_CODE, Q_TITLE, Q_CONTENT, TO_CHAR(Q_DATE, 'YYYY/MM/DD HH24:MI') AS Q_DATE, A_NO, A_TITLE, A_CONTENT, TO_CHAR(A_DATE, 'YYYY/MM/DD HH24:MI') AS A_DATE, DELETE_YN, NVL(MORE_Q_NO, '0') AS MORE_Q_NO FROM QA JOIN QA_CATEGORY ON QA.CATEGORY_CODE = QA_CATEGORY.CATEGORY_CODE WHERE Q_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, dQno);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println();
		System.out.println("		=============== 삭제게시글 ===============");
		if(rs.next()) {
			dQno = rs.getString("Q_NO");
			String title = rs.getString("Q_TITLE");
			String content = rs.getString("Q_CONTENT");
			
			System.out.println("		문의번호:" + dQno + " | 제목:" + title);
			System.out.println("		내용:" + content);
		}
		
		System.out.println("정말 삭제 하시겠습니까?");
		System.out.println("1.삭제하기 2.취소");
		
		String real = Main.SC.nextLine();
		
		switch(real) {
		case "1" : 
			realDelete(); 
			break;
		case "2" : 
			System.out.println("처음화면으로 돌아갑니다.");
		}
		conn.close();
	}
	
	public void realDelete() throws Exception {
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		String s = "DELETE QA WHERE Q_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, dQno);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("게시글이 삭제되었습니다!");
		}else {
			System.out.println("삭제에 실패하였습니다.");
		}
		conn.close();
	}
	
	//내 문의 보기
	public void myQuestion() throws Exception {
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		System.out.print("회원번호를 입력하세요(숫자) : ");
		Qfunction.memberNo = Main.SC.nextLine();
		
		String s = "SELECT Q_NO, ADMIN_NO, MEMBER_NO, CATEGORY_NAME, DETAIL_CATEGORY_CODE, Q_TITLE, Q_CONTENT, TO_CHAR(Q_DATE, 'YYYY/MM/DD HH24:MI') AS Q_DATE, A_NO, A_TITLE, A_CONTENT, TO_CHAR(A_DATE, 'YYYY/MM/DD HH24:MI') AS A_DATE, DELETE_YN, NVL(MORE_Q_NO, '0') AS MORE_Q_NO FROM QA JOIN QA_CATEGORY ON QA.CATEGORY_CODE = QA_CATEGORY.CATEGORY_CODE WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, Qfunction.memberNo);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println();
		System.out.println("		================================");
		while(rs.next()) {
			int no = rs.getInt("Q_NO");
			int dbMemberNo = rs.getInt("MEMBER_NO");
			String category = rs.getString("CATEGORY_NAME");
			String title = rs.getString("Q_TITLE");
			String content = rs.getString("Q_CONTENT");
			String answer = rs.getString("A_CONTENT");
			
			System.out.println("		문의번호:" + no + " | 회원번호:" + Qfunction.memberNo);
			System.out.println("		" + category + " | 제목:" + title);
			System.out.println("		내용 : " + content);
			System.out.println("		관리자답변:" + answer);
			System.out.println();
		}
		System.out.println("		================================");
		conn.close();
	}
	
}
