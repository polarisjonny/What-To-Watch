package com.team2.wtw.sanction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.wtw.manager.JdbcTemplate;

public class SanctionMember {
	
//	JdbcTemplate jt = new JdbcTemplate();
//	SanctionMemberData smd = new SanctionMemberData();
	
	
	
	
    private SanctionMemberData smd;
    private JdbcTemplate jt;
    
    public SanctionMember() {
        smd = new SanctionMemberData();
        jt = new JdbcTemplate();
    }

    public void getSanctionInfo(int memberNo) {
        Connection conn = null;
        try {
            conn = jt.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        
        // 제재회원 정보 검색
        String query = "SELECT * FROM MEMBER_SANCTION WHERE MEMBER_NO = ?";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, memberNo);
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
            // Close the database connection
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



}
