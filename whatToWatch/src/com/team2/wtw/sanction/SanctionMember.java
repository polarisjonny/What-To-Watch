package com.team2.wtw.sanction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import com.team2.wtw.manager.JdbcTemplate;
import com.team2.wtw.template.JdbcConncetionTemplate;

public class SanctionMember {
	
//	JdbcTemplate jt = new JdbcTemplate();
//	SanctionMemberData smd = new SanctionMemberData();
	
	
	
	
	 	private SanctionMemberData smd;
	    private static JdbcTemplate jt;


	    public SanctionMember() {
	        smd = new SanctionMemberData();
	        jt = new JdbcTemplate();
	    }


	    // 제재 회원 조회
	    public void getSanctionInfo() {
	        Connection conn = null;
	        conn = new JdbcConncetionTemplate().getJdbcConnection();
	        
	        
	        // 제재회원 정보 검색
	        String query = "SELECT * FROM MEMBER_SANCTION";
	        
	        try {
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                smd.setMemberNo(rs.getInt("MEMBER_NO"));
	                smd.setMemberId(rs.getString("MEMBER_ID"));
	                smd.setSancType(rs.getString("SANC_TYPE"));
	                smd.setSancCatName(rs.getString("SANC_CAT_NAME"));
	                smd.setSanctionDate(rs.getTimestamp("SANCTION_DATE"));
	                smd.setExpirationDate(rs.getTimestamp("EXPIRATION_DATE"));
	                smd.setAdminNo(rs.getInt("ADMIN_NO"));
	                
	                // 정보 출력
	                System.out.printf("%-10s%-15s%-15s%-25s%-20s%-20s%-10s%n",
	                        "MEMBER_NO", "MEMBER_ID", "SANC_TYPE", "SANC_CAT_NAME", "SANCTION_DATE", "EXPIRATION_DATE", "ADMIN_NO");
	                System.out.printf("%-10d%-15s%-15s%-25s%-20s%-20s%-10d%n",
	                        smd.getMemberNo(), smd.getMemberId(), smd.getSancType(), smd.getSancCatName(), smd.getSanctionDate(), smd.getExpirationDate(), smd.getAdminNo());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    //종료일 설정
	    public void updateSanctionExpirationDate(Connection conn, int memberNo, LocalDate sanctionDate, LocalDate newExpirationDate) throws SQLException {
	        // 회원에 대한 기존 기록 및 제재일 확인
	        String selectSql = "SELECT * FROM MEMBER_SANCTION WHERE MEMBER_NO = ? AND SANCTION_DATE = ?";
	        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
	            selectStmt.setInt(1, memberNo);
	            selectStmt.setObject(2, sanctionDate);
	            try (ResultSet rs = selectStmt.executeQuery()) {
	                if (rs.next()) {
	                    // 수정
	                    String updateSql = "UPDATE MEMBER_SANCTION SET EXPIRATION_DATE = ? WHERE MEMBER_NO = ? AND SANCTION_DATE = ?";
	                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
	                        updateStmt.setObject(1, newExpirationDate);
	                        updateStmt.setInt(2, memberNo);
	                        updateStmt.setObject(3, sanctionDate);
	                        int rowsUpdated = updateStmt.executeUpdate();
	                        System.out.println("Updated " + rowsUpdated + " rows");
	                    }
	                } else { 
	                    // 추가
	                    String insertSql = "INSERT INTO MEMBER_SANCTION (MEMBER_NO, SANCTION_DATE, EXPIRATION_DATE) VALUES (?, ?, ?)";
	                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
	                        insertStmt.setInt(1, memberNo);
	                        insertStmt.setObject(2, sanctionDate);
	                        insertStmt.setObject(3, newExpirationDate);
	                        int rowsInserted = insertStmt.executeUpdate();
	                        System.out.println("Inserted " + rowsInserted + " rows");
	                    }
	                }
	            }
	        }
	    }
	    
	    
	    //관리자가 제재일 수정하기
	    public void inputExpirationDate() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("\n-------제재회원 종료일 설정-------");
	        System.out.print("제재 회원 번호 선택 : ");
	        int memberNo = sc.nextInt();
	        System.out.println("제재 종료일 입력 (yyyy-MM-dd): ");
	        String newDateStr = sc.next();

	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date newDate = null;
	        try {
	            newDate = dateFormat.parse(newDateStr);
	        } catch (ParseException e) {
	            System.out.println("잘못된 날짜 형식입니다. ('yyyy-MM-dd' format.)");
	            return;
	        }

	        try {
	            Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
	            updateSanctionExpirationDate(conn, memberNo, new java.sql.Date(newDate.getTime()).toLocalDate(), LocalDate.parse(newDateStr));
	            System.out.println("제재 종료일시가 성공적으로 업데이트 되었습니다.");
	        } catch (SQLException e) {
	            System.out.println("제재 종료일시 업데이를 실패했습니다.");
	            e.printStackTrace();
	        }
	    }
	    
	    
	  //회원번호 입력해서 제재하기
	    public static void inputSanction() {
	        Connection conn = null;
	        conn = new JdbcConncetionTemplate().getJdbcConnection();
	        
	        Scanner sc = new Scanner(System.in);
	        System.out.println("\n-------회원 제재-------");
	        System.out.print("회원 번호 입력 : ");
	        int memberNo = sc.nextInt();
	        System.out.print("제재 유형 입력(리뷰/게시글) : ");
	        String sancType = sc.nextLine();
	        System.out.print("제재 사유 번호 선택 : ");
	        int sancCatName = sc.nextInt();
	        System.out.print("제재 기간 입력(영구제재일 경우 9999-12-31 입력) : ");
	        String expirationDateStr = sc.next();
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date expirationDate = null;
	        try {
	            expirationDate = dateFormat.parse(expirationDateStr);
	        } catch (ParseException e) {
	            System.out.println("잘못된 날짜 형식입니다. ('yyyy-MM-dd' format.)");
	            return;
	        }
	        System.out.print("관리자 번호 입력 : ");
	        int adminNo = sc.nextInt();

	        try {
	            String query = "INSERT INTO MEMBER_SANCTION (MEMBER_NO, SANC_TYPE, SANC_CAT_NAME, SANCTION_DATE, EXPIRATION_DATE, ADMIN_NO) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setInt(1, memberNo);
	            pstmt.setString(2, sancType);
	            pstmt.setInt(3, sancCatName);
	            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
	            pstmt.setTimestamp(5, new Timestamp(expirationDate.getTime()));
	            pstmt.setInt(6, adminNo);
	            pstmt.executeUpdate();
	            System.out.println("제재가 성공적으로 처리되었습니다.");
	        } catch (SQLException e) {
	            System.out.println("제재 처리를 실패했습니다.");
	            e.printStackTrace();
	        } finally {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    
	    //게시판 제재 목록 조회
	    public static void printBoardSanctions(Connection conn) throws SQLException {
	        String query = "SELECT * FROM BOARD_SANCTION";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            int boardSanctionNo = rs.getInt("BOARD_SANC_NO");
	            int boardNo = rs.getInt("BOARD_NO");
	            int sancCatNo = rs.getInt("SANC_CAT_NO");
	            
	            System.out.println("Board Sanction No: " + boardSanctionNo);
	            System.out.println("Board No: " + boardNo);
	            System.out.println("Sanction Category No: " + sancCatNo);
	            System.out.println("--------------------");
	        }
	        
	        rs.close();
	        stmt.close();
	    }

	    
	    //리뷰 제재 목록 조회
	    public static void printReviewSanctions(Connection conn) throws SQLException {
	        String query = "SELECT * FROM REVIEW_SANCTION";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            int reviewSanctionNo = rs.getInt("REVIEW_SANC_NO");
	            int reviewNo = rs.getInt("REVIEW_NO");
	            int sancCatNo = rs.getInt("SANC_CAT_NO");
	            
	            System.out.println("Review Sanction No: " + reviewSanctionNo);
	            System.out.println("Review No: " + reviewNo);
	            System.out.println("Sanction Category No: " + sancCatNo);
	            System.out.println("--------------------");
	        }
	        
	        rs.close();
	        stmt.close();
	    }






}