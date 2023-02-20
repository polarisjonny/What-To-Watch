package com.team2.wtw.eventboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventBoardService {
	
	//이벤트 게시물 목록만 보여주기
	public void printEventBoardList() {
		System.out.println("================== 이벤트 게시판 ===================");
		
		try {
			String sql = "SELECT EVENT_NO, EVENT_TITLE FROM EVENTBOARD";
			
			Connection conn = JdbcTemplate.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int eventNumber = rs.getInt("EVENT_NO");
				String eventTitle = rs.getString("EVENT_TITLE");
				
				System.out.printf("%d || %s\n", eventNumber, eventTitle);
				
			}
			
			conn.close();
			
		} catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//이벤트 게시판 내용 상세
	public void printEventBoardDetail() {
		EventView ev = new EventView();
		try {
			Connection conn = JdbcTemplate.getConnection();
			
			String sql = "SELECT EVENT_NO, EVENT_TITLE, EVENT_CONTENT, START_DATE, END_DATE FROM EVENTBOARD WHERE EVENT_NO = ?";
			
			int choice = ev.showEventBoard();
			
			switch(choice) {
			case 1 : 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, choice);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int eventNum = rs.getInt("EVENT_NO");
					String eventTitle = rs.getString("EVENT_TITLE");
					String eventContent = rs.getString("EVENT_CONTENT");
					String startDate = rs.getString("START_DATE");
					String endDate = rs.getString("END_DATE");
					
					System.out.println(eventNum+" | "+eventTitle+" | "+eventContent + " | "+startDate+" | "+endDate);
		
				}
				
				conn.close();
				break;
				
			case 2 : 
			}
			
			
		} catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//event1
}
