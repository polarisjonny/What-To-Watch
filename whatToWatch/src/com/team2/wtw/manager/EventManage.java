package com.team2.wtw.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team2.wtw.eventboard.EventBoardService;

public class EventManage {
    
    static JdbcTemplate jt = new JdbcTemplate();
    static Connection conn;
    Scanner sc = new Scanner(System.in);
    EventBoardService ebs = new EventBoardService();
 
    
    // 새 이벤트 추가
    public void addEvent(String eventTitle, String eventContent) throws SQLException {
        String sql = "INSERT INTO EVENTBOARD (EVENT_NO, ADMIN_NO, EVENT_TITLE, EVENT_CONTENT, START_DATE, END_DATE) " +
                     "VALUES (EVENT_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, NULL)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, 7); // 실제 관리자 번호
            stmt.setString(2, eventTitle);
            stmt.setString(3, eventContent);
            stmt.executeUpdate();
        }
    }
    
    // 이벤트 수정
    public void modifyEvent(int eventNo, String eventTitle, String eventContent) throws SQLException {
        String sql = "UPDATE EVENTBOARD SET EVENT_TITLE = ?, EVENT_CONTENT = ? WHERE EVENT_NO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventTitle);
            stmt.setString(2, eventContent);
            stmt.setInt(3, eventNo);
            stmt.executeUpdate();
        }
    }
    
    // 이벤트 삭제
    public void deleteEvent(int eventNo) throws SQLException {
        String sql = "DELETE FROM EVENTBOARD WHERE EVENT_NO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventNo);
            stmt.executeUpdate();
        }
    }
    
    // 이벤트 시작일 현재시간으로 설정
    public void setStartDate(int eventNo) throws SQLException {
        String sql = "UPDATE EVENTBOARD SET START_DATE = ? WHERE EVENT_NO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(2, eventNo);
            stmt.executeUpdate();
        }
    }
    
    // 점수 조회
    public static List<String> getEventScores(int eventId) {
        List<String> scores = new ArrayList<>();
        try (
            PreparedStatement stmt = conn.prepareStatement("SELECT MEMBER_ID, SCORE FROM EVENT_ATTENDANCE WHERE EVENT_ID = ?")) {
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int memberId = rs.getInt("MEMBER_ID");
                int score = rs.getInt("SCORE");
                scores.add("회원 아이디 : " + memberId + ", 점수 : " + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }
    

    //이벤트 추가 입력
    public void inputAddEvent() throws Exception {
    	System.out.println("이벤트 제목 입력 : ");
    	String eventTitle = sc.nextLine();
    	System.out.println("이벤트 내용 입력 : ");
    	String eventContent = sc.nextLine();
    	addEvent(eventTitle, eventContent);
    }

    
    //이벤트 수정 입력
    public void inputModifyEvent() throws Exception {
    	System.out.println("수정할 이벤트 게시글 번호 입력 : ");
    	int eventId = Integer.parseInt(sc.nextLine());
    	System.out.println("이벤트 제목 입력 : ");
    	String eventTitle = sc.nextLine();
    	System.out.println("이벤트 내용 입력 : ");
    	String eventContent = sc.nextLine();
    	modifyEvent(eventId, eventTitle, eventContent);
    	
    }

    
    //이벤트 삭제 입력
    public void inputDeleteEvent() throws Exception {
    	System.out.println("삭제할 이벤트 게시글 번호 입력 : ");
    	int eventId = Integer.parseInt(sc.nextLine());
    	deleteEvent(eventId);
    }

    //이벤트 별 점수 목록 조회
    public void printEventScore() {
    	ebs.printEventBoardList();
    	System.out.println("점수 조회할 이벤트 게시글 번호 입력 : ");
    	int eventId = Integer.parseInt(sc.nextLine());
    	List<String> eventScore = getEventScores(eventId);
    	System.out.println("이벤트 점수 : " + eventScore);
    	
    	System.out.println("전체 이벤트 점수 :");
    	getEventScores(eventId).forEach(System.out::println);
    	
    }
    
    public void eventManage() throws Exception {
    	ebs.printEventBoardList();
		System.out.println("\n이벤트 관리 - 다음을 선택하십시오");
		System.out.println("1. 이벤트 수정");
		System.out.println("2. 이벤트 삭제");
		System.out.println("3. 이벤트 추가");
		int eventType = sc.nextInt();
		        
        switch(eventType) {
        	case 1: 
        		inputModifyEvent();
        		break;
        	case 2:
        		inputDeleteEvent();
        		break;
        	case 3:
        		inputAddEvent();
        		break;
        }
    	
    }
}