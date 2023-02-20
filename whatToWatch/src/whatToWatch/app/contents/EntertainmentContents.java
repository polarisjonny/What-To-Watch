package whatToWatch.app.contents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntertainmentContents {

public void showEnterContents() {
		
		try {
			// DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "C##TEAM2TEST";
			String pw = "T2T";
			Connection conn = DriverManager.getConnection(url, id, pw);

			// 최신순
			String entertainmentList_sql = "SELECT CONTENTS_NO, CONTENTS_TITLE, RELEASE_DATE\r\n"
					+ "FROM\r\n"
					+ "(\r\n"
					+ "    SELECT ROWNUM \"NUM\", CONTENTS_NO, CONTENTS_TITLE, RELEASE_DATE\r\n"
					+ "    FROM CONTENTS T1\r\n"
					+ "        JOIN CATEGORY T2 ON T1.CATEGORY_NO = T2.CATEGORY_NO \r\n"
					+ "    WHERE CATEGORY_NAME = '예능'\r\n"
					+ "    ORDER BY RELEASE_DATE DESC\r\n"
					+ ")\r\n"
					+ "WHERE NUM > ? AND NUM <= ?\r\n";
			
			PreparedStatement entertainmentList_pstmt = conn.prepareStatement(entertainmentList_sql);

			entertainmentList_pstmt.setString(1, "0");
			entertainmentList_pstmt.setString(2, "10");
			ResultSet entertainmentList_pstmtList_ResultSet = entertainmentList_pstmt.executeQuery();

			System.out.println("예능");
			int i = 1;
			while (entertainmentList_pstmtList_ResultSet.next()) {
				System.out.printf("%d. %s\n", i, entertainmentList_pstmtList_ResultSet.getString("CONTENTS_TITLE"));
				i++;
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // method | showEnterContents
	
} // class | EntertainmentContents
