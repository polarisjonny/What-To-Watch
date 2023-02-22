package com.team2.wtw.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.wtw.template.JdbcConncetionTemplate;

public class Admin {

	

	//관리자 목록 보여주기
    public static void AdiminList() {
        try (Connection conn = new JdbcConncetionTemplate().getJdbcConnection();) {
            // SELECT 문을 생성하여 관리자 목록 검색
            String sql = "SELECT * FROM ADMIN";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        // 값 가져오기 
                        int adminNo = rs.getInt("ADMIN_NO");
                        int permissionNo = rs.getInt("PERMISSION_NO");
                        String adminId = rs.getString("ADMIN_ID");
                        String adminPwd = rs.getString("ADMIN_PWD");
                        String adminName = rs.getString("ADMIN_NAME");
                        
                        // 값 출력하기 
                        System.out.printf("%d, %d, %s, %s, %s%n", adminNo, permissionNo, adminId, adminPwd, adminName);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("데이터베이스 오류 : " + ex.getMessage());
        }
    }
    
    // 권한번호 삭제, 추가, 수정 
    public static void managePermission(int adminNo, int permissionNo, String action) {
        try {
        	Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
            PreparedStatement ps;
            switch (action) {
                case "삭제":
                    ps = conn.prepareStatement("DELETE FROM ADMIN WHERE ADMIN_NO = ? AND PERMISSION_NO = ?");
                    ps.setInt(1, adminNo);
                    ps.setInt(2, permissionNo);
                    ps.executeUpdate();
                    System.out.println("\n" + adminNo + "번 ID를 가진 관리자에 대한 사용 권한을 삭제했습니다.");
                    break;
                case "수정":
                    ps = conn.prepareStatement("UPDATE ADMIN SET PERMISSION_NO = ? WHERE ADMIN_NO = ?");
                    ps.setInt(1, permissionNo);
                    ps.setInt(2, adminNo);
                    ps.executeUpdate();
                    System.out.println("\n" + adminNo + "번 ID를 가진 관리자에 대한 사용 권한을 수정했습니다.");
                    break;
                case "추가":
                    ps = conn.prepareStatement("INSERT INTO ADMIN (ADMIN_NO, PERMISSION_NO) VALUES (?, ?)");
                    ps.setInt(1, adminNo);
                    ps.setInt(2, permissionNo);
                    ps.executeUpdate();
                    System.out.println("\n" + adminNo + "번 ID를 가진 관리자에 대한 사용 권한을 추가했습니다.");
                    break;
                default:
                    System.out.println("잘못된 작업이 지정되었습니다.");
                    break;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("사용 권한 관리 오류 : " + e.getMessage());
        }
    }

}
