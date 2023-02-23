package com.team2.wtw.eventboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.wtw.main.Main;
import com.team2.wtw.template.JdbcConncetionTemplate;

public class EventBoardService {
	
	//이벤트 게시물 목록만 보여주기
	public void printEventBoardList() {
		System.out.println("================== 이벤트 게시판 ===================");
		
		try {
			String sql = "SELECT EVENT_NO, EVENT_TITLE FROM EVENTBOARD";
			
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int eventNumber = rs.getInt("EVENT_NO");
				String eventTitle = rs.getString("EVENT_TITLE");
				
				System.out.printf("%d || %s\n", eventNumber, eventTitle);
				
			}
			
			printEventBoardDetail();
			
			conn.close();
			
		} catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//관리자용 이벤트 게시판 목록 조회
	public void printEventBoardListAdmin() {
		System.out.println("================== 이벤트 게시판 ===================");
		
		try {
			String sql = "SELECT EVENT_NO, EVENT_TITLE FROM EVENTBOARD";
			
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int eventNumber = rs.getInt("EVENT_NO");
				String eventTitle = rs.getString("EVENT_TITLE");
				
				System.out.printf("%d || %s\n", eventNumber, eventTitle);
				
			}
			conn.close();
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//이벤트 게시판 내용 상세
	public void printEventBoardDetail() {
		EventView ev = new EventView();
		try {
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
			
			String sql = "SELECT EVENT_NO, EVENT_TITLE, EVENT_CONTENT, START_DATE, END_DATE FROM EVENTBOARD WHERE EVENT_NO = ?";
			
			int choice = ev.showEventBoard();
			
			//1번 게시판 상세를 보여줄 때
			switch(choice) {
			case 1 : 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, 1);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int eventNum = rs.getInt("EVENT_NO");
					String eventTitle = rs.getString("EVENT_TITLE");
					String eventContent = rs.getString("EVENT_CONTENT");
					String startDate = rs.getString("START_DATE");
					String endDate = rs.getString("END_DATE");
					
					System.out.println(eventNum);
					System.out.println("-----------------------------");
					System.out.println(eventTitle);
					System.out.println("-----------------------------");
					System.out.println(eventContent);
					System.out.println();
					
					System.out.println("시작일 | "+startDate);
					System.out.println("종료일 | "+endDate);
					
					startEvent1();
				}
				
				conn.close();
				break;
			
			//2번 게시판 상세를 보여줄 때
			case 2 : 
				PreparedStatement pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, 2);
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					int eventNum = rs2.getInt("EVENT_NO");
					String eventTitle = rs2.getString("EVENT_TITLE");
					String eventContent = rs2.getString("EVENT_CONTENT");
					String startDate = rs2.getString("START_DATE");
					String endDate = rs2.getString("END_DATE");
					
					
					System.out.println(eventNum);
					System.out.println("-----------------------------");
					System.out.println(eventTitle);
					System.out.println("-----------------------------");
					System.out.println(eventContent);
					System.out.println();
					
					System.out.println("시작일 | "+startDate);
					System.out.println("종료일 | "+endDate);
					
					startEvent2();
				}
				
				conn.close();
				break;
			}

		} catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	public void startEvent1() {
		EventView ev = new EventView();
		String input = ev.viewIfWonEvent1();
		
		try {
			if(input.equals("GO") || input.equals("go")) {
				String sql = "SELECT COUNT(REVIEW_NO) 갯수 FROM REVIEW WHERE MEMBER_NO = ?";
				Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, Main.userData.getUserNum());
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int score = rs.getInt("갯수");
					
					if(score > 10) {
						//축하 메시지 보여주기
						ev.showCelebrationMessage1();
						
						//당첨 쿠폰 보여주기
						ev.showMovieCoupon();
						
						
					} else {
						System.out.println("아쉽게도 리뷰 수가 부족합니다... 좀 더 분발해 주세요~!!");
						
						
					}
				}
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startEvent2() {
		EventView ev = new EventView();
		String input = ev.viewEvent2();
		
		try {
			if(input.equals("START") || input.equals("start")) {
				Event2Game game = new Event2Game();
				int credit = game.playGame();
				
				//게임에 승리했을 경우
				if(credit > 500) {
					//축하 메시지 보여주기
					ev.showCelebrationMessage2();
					
					//팝콘음료쿠폰 보여주기
					ev.showPopcornCoupon();
					
					
					
				} 
				//게임에서 패배했을 경우
				else {
					System.out.println("아쉽게도 게임에서 패배하셨습니다.. 다음 기회를 노리세요!!");
					
					
				}
				
				String sql = "UPDATE EVENT2_ENTRANT_SCORE SET EVENT1_SCORE = ? WHERE MEMBER_NO = ?";
				
				Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, credit);
				
				pstmt.setInt(2, Main.userData.getUserNum());
				
				conn.close();
				
			}    
		} catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
