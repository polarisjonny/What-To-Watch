package faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbctemplate.JdbcTemplate;
import main.Main;
import playstart.PlayStart;

//자주묻는질문
public class Faq {
	
	int good;
	int bad;
	int sumGood;
	int sumBad;
	
	//메뉴보여주기
	public void showFaqMenu() {
		System.out.println();
		System.out.println("		=========================================");
		System.out.println("		1.로그인이 안돼요");
		System.out.println("		2.계정 정보 변경은 어떻게 하나요?");
		System.out.println("		3.계정을 삭제하고 싶습니다");
		System.out.println("		4.이벤트에 참여했는데 점수가 갱신되지 않아요");
		System.out.println("		5.이벤트에 당첨됐는데 상품은 언제주나요?");
		System.out.println("		6.컨텐츠 추가를 요청합니다/컨텐츠 정보 수정을 요청합니다");
		System.out.println("		0.이전");
		System.out.println("		=========================================");
		
		System.out.println();
		System.out.print("입력 __ ");
	}
	
	//메뉴선택받기
	public String selectFaqMenu() {
		String input = Main.SC.nextLine();
		
		return input;
	}
	
	//선택에 따른 로직제시
	public void showSolution(String input) throws Exception {
		
		switch(input) {
		case "1" :
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡS O L U T I O Nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
			System.out.println("	◈ 아이디, 비밀번호를 확인하라는 메세지가 뜰 경우 >>");
			System.out.println("	➥  아이디 및 비밀번호 정보를 확인한 후 잊어버린 경우 비밀번호를 변경해야 합니다.\n");
			goodBad();
			break;
		case "2" :
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡS O L U T I O Nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
			System.out.println("	◈ 이메일, 닉네임, 비밀번호 등 개인정보를 변경하고 싶은 경우 >>");
			System.out.println("	➥ 회원정보 관리 페이지에 들어가 변경하려는 계정 정보를 확인 후 변경하면 됩니다.\n");
			goodBad();
			break;
		case "3" :
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡS O L U T I O Nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
			System.out.println("	◈ 회원탈퇴를 하고 싶은 경우 >>");
			System.out.println("	➥ 회원정보 관리 페이지에 들어가 하단에 있는 회원탈퇴를 누른 뒤 계정 삭제를 진행하면 됩니다.\n");
			goodBad();
			break;
		case "4" :
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡS O L U T I O Nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
			System.out.println("	◈ 이벤트 점수 오류가 발생한 경우 >>");
			System.out.println("	➥ 불법프로그램을 사용하여 이벤트에 참여한 경우 점수가 갱신되지 않습니다. 해당 문제가 발생한 경우 고객센터 02-123-4567 로 자세한 문의 바랍니다.\n");
			goodBad();
			break;
		case "5" :
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡS O L U T I O Nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
			System.out.println("	◈ 이벤트 참여 후 상품지급에 대해 문의할 경우 >>");
			System.out.println("	➥ 상품 지급은 이벤트 점수 확인 후 주말(공휴일 포함)제외 최대 일주일 정도 소요됩니다. 일정 기간이 지난 후 상품지급이 되지 않았을 경우 고객센터 문의 바랍니다.\n");
			goodBad();
			break;
		case "6" :
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡS O L U T I O Nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
			System.out.println("	◈ 컨텐츠의 정보수정 및 추가를 요청할 경우 >>");
			System.out.println("	➥ 컨텐츠 추가/정보 수정에 대한 요청은 문의게시판의 컨텐츠관련문의 카테고리를 이용해주시면 됩니다.\n");
			goodBad();
			break;
		default : System.out.println("문의 번호를 다시 확인해 주세요.");
		}
	}
	
	public void goodBad() throws Exception {
		Connection conn = JdbcTemplate.jdbcTemplate();
		
		//기존에 있던 좋아요&싫어요 수 받아와서 계속 누적
		String s = "SELECT FAQ_NO, ADMIN_NO, FAQ_TITLE, FAQ_CONTENT, FAQ_SOLUTION, GOOD_COUNT, BAD_COUNT FROM FAQ WHERE FAQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, PlayStart.Input);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			good = rs.getInt("GOOD_COUNT");
			bad = rs.getInt("BAD_COUNT");
		}
		
		System.out.println("		답변이 도움이 되었나요?");
		System.out.println("		1.도움됨 2.도움안됨");
	
		String gbInput = Main.SC.nextLine();
		
		switch(gbInput) {
		case "1" :
			good++;
			System.out.println("		도움됨( " + good + " )/" + "도움안됨( " + bad+ " )");
			goodbadConnection();
			break;
		case "2" :
			bad++;
			System.out.println("		도움됨( " + good + " )/" + "도움안됨( " + bad + " )");
			goodbadConnection();
			break;
		}
			
	}
	
	//좋아요 싫어요 DB쿼리문
	public void goodbadConnection() throws Exception {
		Connection conn = JdbcTemplate.jdbcTemplate();
		
		String s = "UPDATE FAQ SET GOOD_COUNT = ?, BAD_COUNT = ? WHERE FAQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, good);
		pstmt.setInt(2, bad);
		pstmt.setString(3, PlayStart.Input);
		int result = pstmt.executeUpdate();
		
		conn.close();
	}
	
}
	