package com.team2.wtw.faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team2.wtw.main.Main;
import com.team2.wtw.template.JdbcConncetionTemplate;

public class Afunction {
public static int QuestionNo;
	
	//답변할 문의내용보기
	public void answerList() throws Exception {
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		System.out.println();
		System.out.print("답변할 문의번호 : ");
		String questionNo = Main.SC.nextLine();
		
		String s = "SELECT Q_NO, ADMIN_NO, MEMBER_NO, CATEGORY_NAME, DETAIL_CATEGORY_NAME, Q_TITLE, Q_CONTENT, TO_CHAR(Q_DATE, 'YYYY/MM/DD HH24:MI') AS Q_DATE, A_NO, A_TITLE, A_CONTENT, TO_CHAR(A_DATE, 'YYYY/MM/DD HH24:MI') AS A_DATE, DELETE_YN, NVL(MORE_Q_NO, '0') AS MORE_Q_NO FROM QA JOIN QA_CATEGORY ON QA.CATEGORY_CODE = QA_CATEGORY.CATEGORY_CODE JOIN DETAIL_CATEGORY ON QA.DETAIL_CATEGORY_CODE = DETAIL_CATEGORY.DETAIL_CATEGORY_CODE WHERE Q_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, questionNo);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println();
		System.out.println("		==============================================");
		if(rs.next()) {
			QuestionNo = rs.getInt("Q_NO");
			int memberNo = rs.getInt("MEMBER_NO");
			String category = rs.getString("DETAIL_CATEGORY_NAME");
			String title = rs.getString("Q_TITLE");
			String content = rs.getString("Q_CONTENT");
			
			System.out.println("		문의 번호:" + QuestionNo + " | 이전문의번호:" + Qfunction.moreQ + " | 회원번호:" + Qfunction.memberNo);
			System.out.println("		세부카테고리:"+ category + " | 제목:" + title);
			System.out.println("		문의 내용:"+ content);
		}else {
			System.out.println("				없는 문의번호 입니다. 처음으로 돌아갑니다."); 
			System.out.println("		========================================================="); return;
		}
		System.out.println("		========================================================");
		conn.close();
		
		System.out.println("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ답변을 작성해 주세요 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ");
		writeA();
	}

	//답변작성
	public void writeA() throws Exception {
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		System.out.print("답변제목 : ");
		String title = Main.SC.nextLine();
		
		System.out.print("답변내용 : ");
		String content = Main.SC.nextLine();
		
		String s = "UPDATE QA SET A_TITLE = ?, A_CONTENT = ? WHERE Q_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setInt(3, QuestionNo);
		ResultSet rs = pstmt.executeQuery();
		
		conn.close();
		
		checkAnswer();
	}
	
	//답변확인
	public void checkAnswer() throws Exception {
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		String s = "SELECT Q_NO, ADMIN_NO, MEMBER_NO, CATEGORY_NAME, DETAIL_CATEGORY_NAME, Q_TITLE, Q_CONTENT, TO_CHAR(Q_DATE, 'YYYY/MM/DD HH24:MI') AS Q_DATE, A_NO, A_TITLE, A_CONTENT, TO_CHAR(A_DATE, 'YYYY/MM/DD HH24:MI') AS A_DATE, DELETE_YN, NVL(MORE_Q_NO, '0') AS MORE_Q_NO FROM QA JOIN QA_CATEGORY ON QA.CATEGORY_CODE = QA_CATEGORY.CATEGORY_CODE JOIN DETAIL_CATEGORY ON QA.DETAIL_CATEGORY_CODE = DETAIL_CATEGORY.DETAIL_CATEGORY_CODE WHERE Q_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);		
		pstmt.setInt(1, QuestionNo);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("\n		ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ답변 목록ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		if(rs.next()) {
			int aNo = rs.getInt("A_NO");
			int qNo = rs.getInt("Q_NO");
			String category = rs.getString("DETAIL_CATEGORY_NAME");
			String title = rs.getString("Q_TITLE");
			String content = rs.getString("A_CONTENT");
			
			System.out.println();
			System.out.println("		답변번호:" + aNo + " | 문의번호:" + qNo + " | 이전문의번호:" + Qfunction.moreQ );
			System.out.println("		세부카테고리:" + category + " | 제목:" + title);
			System.out.println("		답변내용:" + content);
		}
		System.out.println("		==============================================");
	}
}
