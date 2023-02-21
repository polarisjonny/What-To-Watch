package q_a;

public class Qfunction {
		
	String category;
	String detail;
	public static String moreQ = "0";
	public static String memberNo;
	public static String memberNick;
	
	//문의사항
	public void writeQ() throws Exception {
		category = selectCategory();
		
		detail = detailCategory(category);
		
		writeQuestion();
	}
	
	//카테고리 선택
	public String selectCategory() {
		
		System.out.println("		==================");
		System.out.println("		카테고리를 선택해주세요");
		System.out.println("		1.컨텐츠관련문의");
		System.out.println("		2.이벤트관련문의");
		System.out.println("		3.회원관련문의");
		System.out.println("		4.오류/에러관련문의");
		System.out.println("		5.기타문의");
		System.out.println("		==================");
		System.out.print("선택 __ ");
		category = Main.SC.nextLine();
		
		return category;
		
	}
	
	//세부카테고리 제시
	public String detailCategory(String category) {
		switch(category) {
		case "1" :
			System.out.println("		=============");
			System.out.println("		101.컨텐츠추가");
			System.out.println("		102.컨텐츠삭제");
			System.out.println("		103.컨텐츠수정");
			System.out.println("		=============");
			System.out.print("선택 __ ");
			detail = Main.SC.nextLine();
			break;
		case "2" :
			System.out.println("		=============");
			System.out.println("		201.상품지급");
			System.out.println("		202.점수관련");
			System.out.println("		=============");
			System.out.print("선택 __ ");
			detail = Main.SC.nextLine();
			break;
		case "3" :
			System.out.println("		=============");
			System.out.println("		301.로그인문제");
			System.out.println("		302.개인정보관련");
			System.out.println("		303.제재관련");
			System.out.println("		=============");
			System.out.print("선택 __ ");
			detail = Main.SC.nextLine();
			break;
		case "4" :
			System.out.println("		==================");
			System.out.println("		401.홈페이지 오류/에러");
			System.out.println("		402.접속 오류/에러");
			System.out.println("		==================");
			System.out.print("선택 __ ");
			detail = Main.SC.nextLine();
			break;
		case "5" :
			System.out.println("		========");
			System.out.println("		501.기타");
			System.out.println("		========");
			System.out.print("선택 __ ");
			detail = Main.SC.nextLine();
			break;
		default : System.out.println("올바른 카테고리를 설정해 주세요.");
		}
		
		return detail;
	}
	
	//카테고리 선택 후 문의작성
	public void writeQuestion() throws Exception {
		Connection conn = JdbcTemplate.jdbcTemplate();
		
		System.out.println();
		System.out.print("제목 : ");
		String title = Main.SC.nextLine();
		
		System.out.print("내용 : ");
		String content = Main.SC.nextLine();
		
		System.out.print("회원번호 : ");
		memberNo = Main.SC.nextLine();
		
		System.out.print("이전문의번호(없으면 0입력) : ");
		moreQ = Main.SC.nextLine();
		
		String s = "INSERT INTO QA VALUES(SEQ_Q_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE, SEQ_A_NO.NEXTVAL, ?, ?, SYSDATE, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, 1);
		pstmt.setString(2, memberNo);
		pstmt.setString(3, category);
		pstmt.setString(4, detail);
		pstmt.setString(5, title);
		pstmt.setString(6, content);
		pstmt.setString(7, "답변없음");
		pstmt.setString(8, "답변없음");
		pstmt.setString(9, "N");
		pstmt.setString(10, moreQ);
		int result = pstmt.executeUpdate();
		
		if(result ==1) {
			System.out.println("문의사항이 접수되었습니다. 빠른 시일 내로 답변해 드리겠습니다.");
		}else {
			System.out.println("게시글 등록에 실패하였습니다.");
		}
		conn.close();
	}
	
	//문의게시판 목록
	public void qaBoardList() throws Exception {
		Connection conn = JdbcTemplate.jdbcTemplate();
		
		String s = "SELECT Q_NO, ADMIN_NO, MEMBER_NO, CATEGORY_NAME, DETAIL_CATEGORY_CODE, Q_TITLE, Q_CONTENT, TO_CHAR(Q_DATE, 'YYYY/MM/DD HH24:MI') AS Q_DATE, A_NO, A_TITLE, A_CONTENT, TO_CHAR(A_DATE, 'YYYY/MM/DD HH24:MI') AS A_DATE, DELETE_YN, NVL(MORE_Q_NO, '0') AS MORE_Q_NO FROM QA JOIN QA_CATEGORY ON QA.CATEGORY_CODE = QA_CATEGORY.CATEGORY_CODE";
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println();
		System.out.println("		================================");
		while(rs.next()) {
			int no = rs.getInt("Q_NO");
			memberNo = rs.getString("MEMBER_NO");
			String category = rs.getString("CATEGORY_NAME");
			String title = rs.getString("Q_TITLE");
			
			System.out.println("		문의번호:" + no + " | 회원번호:" + memberNo);
			System.out.println("		" + category + " | 제목:" + title);
			System.out.println();
		}
		System.out.println("		================================");
		conn.close();
	}
	
	//나의문의 세부게시판 로직 
	public void myQuestionList() {
		
	}
	
	//나의문의
	public void myQuestion() throws Exception {
		Connection conn = JdbcTemplate.jdbcTemplate();
		
		System.out.print("회원번호를 입력하세요(숫자) : ");
		memberNo = Main.SC.nextLine();
		
		String s = "SELECT Q_NO, ADMIN_NO, MEMBER_NO, CATEGORY_NAME, DETAIL_CATEGORY_CODE, Q_TITLE, Q_CONTENT, TO_CHAR(Q_DATE, 'YYYY/MM/DD HH24:MI') AS Q_DATE, A_NO, A_TITLE, A_CONTENT, TO_CHAR(A_DATE, 'YYYY/MM/DD HH24:MI') AS A_DATE, DELETE_YN, NVL(MORE_Q_NO, '0') AS MORE_Q_NO FROM QA JOIN QA_CATEGORY ON QA.CATEGORY_CODE = QA_CATEGORY.CATEGORY_CODE WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, memberNo);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println();
		System.out.println("		================================");
		while(rs.next()) {
			int no = rs.getInt("Q_NO");
			int dbMemberNo = rs.getInt("MEMBER_NO");
			String category = rs.getString("CATEGORY_NAME");
			String title = rs.getString("Q_TITLE");
			String answer = rs.getString("A_CONTENT");
			
			System.out.println("		문의번호:" + no + " | 회원번호:" + memberNo);
			System.out.println("		" + category + " | 제목:" + title);
			System.out.println("		관리자답변:" + answer);
			System.out.println();
		}
		System.out.println("		================================");
		conn.close();
	}


}
