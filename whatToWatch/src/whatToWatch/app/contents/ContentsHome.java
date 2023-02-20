package whatToWatch.app.contents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ContentsHome {

	public static final Scanner SC = new Scanner(System.in);
	
	public void showContents() {

		// 헤더 메뉴 출력
		for (int i = 0; i < 123; i++)
			System.out.print("=");
		System.out.println();

		String[] headerMenu = new String[10];
		headerMenu[0] = "1. 회원가입";
		headerMenu[1] = "2. 로그인";
		headerMenu[2] = "3. 검 색";
		headerMenu[3] = "4. 영 화";
		headerMenu[4] = "5. 드라마";
		headerMenu[5] = "6. 예 능";
		headerMenu[6] = "7. 자유게시판";
		headerMenu[7] = "8. 이벤트";
		headerMenu[8] = "9. 문의게시판";
		headerMenu[9] = "10. FAQ";

		for (int i = 0; i < headerMenu.length; i++)
			System.out.printf("%-11s", headerMenu[i]);
		System.out.println();

		for (int i = 0; i < 123; i++)
			System.out.print("-");
		System.out.println();

		// 추천 컨텐츠 출력
		try {
			// DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "C##TEAM2TEST";
			String pw = "T2T";
			Connection conn = DriverManager.getConnection(url, id, pw);

			// 최신순
			String newest_sql = "SELECT CONTENTS_TITLE \"TITLE\"\r\n"
					+ "    , TO_CHAR(RELEASE_DATE, 'YY/MM/DD') \"RELEASE_DATE\"\r\n" + "FROM\r\n" + "    (\r\n"
					+ "    SELECT ROWNUM AS \"순위\", CONTENTS_TITLE, RELEASE_DATE\r\n" + "    FROM CONTENTS\r\n"
					+ "    )\r\n" + "WHERE \"순위\" <= 5\r\n";

			PreparedStatement newest_pstmt = conn.prepareStatement(newest_sql);

			ResultSet newest_ResultSet = newest_pstmt.executeQuery();

			System.out.println("최신작품");
			int i = 1;
			while (newest_ResultSet.next() || i <= 5) {
				System.out.printf("%d. %s    %s\n", i, newest_ResultSet.getString("TITLE"),
						newest_ResultSet.getString("RELEASE_DATE"));
				i++;
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // method | showContents

} // class | Contents
