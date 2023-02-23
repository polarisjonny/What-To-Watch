package com.team2.wtw.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerService {
	ManagerView view = new ManagerView();
	ManagerData data = new ManagerData();
	
	//관리자인지 확인해서 관리자일 경우 홈 화면 보여주기
	public boolean checkIfManager() {
		data = view.showManagerLogin();
		
		try {
			Connection conn = JdbcTemplate.getConnection();
			String sql = "SELECT * FROM ADMIN WHERE ADMIN_ID = ? AND ADMIN_PWD = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getAdminId());
			pstmt.setString(2,  data.getAdminPwd());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Menu.username = rs.getString("ADMIN_ID");
				String adminPwd = rs.getString("ADMIN_PWD");
				String adminName = rs.getString("ADMIN_NAME");
				int permissionNum = rs.getInt("PERMISSION_NO");
				
				
				if(permissionNum >= 1 && permissionNum <= 7) {
					System.out.println("\n"+adminName+" 관리자님, 환영합니다:)");
					
					return true;
					
				}
			}
			
		}catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("권한이 없습니다.");
		}
		return false;
	}
	
	
}
