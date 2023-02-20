package whatToWatch.app.joinLoginFunc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserView {
	Scanner scanner = new Scanner(System.in);
	
	//회원가입 입력받기
	public UserData GetjoinInfo() {
		UserData data = new UserData();
		
		System.out.print("아이디를 입력하세요 : ");
		String userId = scanner.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String userPwd = scanner.nextLine();
		System.out.print("닉네임을 입력하세요 : ");
		String userNick = scanner.nextLine();
		System.out.print("이메일을 입력하세요 : ");
		String userEmail = scanner.nextLine();
		System.out.print("핸드폰번호를 입력하세요 : ");
		String userPhoneNumber = scanner.nextLine();
		showSecurityQ();
		System.out.print("원하는 보안 질문을 선택해 주세요 : ");
		String userSecurityQ = scanner.nextLine();
		System.out.println("보안 질문에 대한 답을 작성해 주세요 : ");
		String userSecurityA = scanner.nextLine();
		
		
		data.setUserId(userId);
		data.setUserPwd(userPwd);
		data.setUserNick(userNick);
		data.setUserEmail(userEmail);
		data.setUserPhoneNumber(userPhoneNumber);
		data.setUserSecurityQ(userSecurityQ);
		data.setUserSecurityA(userSecurityA);
		
		return data;
	}
	
	
	//보안질문 보여주기
	public void showSecurityQ() {
		System.out.println("1. 태어난 지역은?");
		System.out.println("2. 제일 좋아하는 음식은?");
		System.out.println("3. 당신의 인생 좌우명은?");
	}
	
	//로그인 입력받기
	public UserData GetLoginInfo() {
		UserData data = new UserData();
		
		System.out.print("아이디를 입력하세요 : ");
		String userId = scanner.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String userPwd = scanner.nextLine();
		
		data.setUserId(userId);
		data.setUserPwd(userPwd);
		
		return data;
	}
	
	//회원정보 출력 입력받기
	public UserData GetSearchMemberInfo() {
		UserData data = new UserData();
		
		System.out.print("회원정보를 출력할 아이디를 입력하세요 : ");
		String userId = scanner.nextLine();
		System.out.print("회원정보를 출력할 비밀번호를 입력하세요 : ");
		String userPwd = scanner.nextLine();
		
		data.setUserId(userId);
		data.setUserPwd(userPwd);
		
		return data;
	}
	
	//회원정보 수정 입력받기
	public UserData getModifiedMemberInfo() {
		UserData data = new UserData();
		
		System.out.print("회원정보를 수정하시겠습니까?(네/아니오) : ");
		String answer = scanner.nextLine();
		
		if(answer.equals("네")) {
			System.out.print("회원정보를 수정할 아이디를 입력하세요 : ");
			String userId = scanner.nextLine();
			data.setUserId(userId);
			System.out.print("회원정보를 수정할 비밀번호를 입력하세요 : ");
			String userPwd = scanner.nextLine();
			data.setUserPwd(userPwd);
			
			System.out.print("닉네임을 입력하세요 : ");
			String userNick = scanner.nextLine();
			data.setUserNick(userNick);
				
				
			 
			System.out.print("이메일을 입력하세요 : ");
			String userEmail = scanner.nextLine();
			data.setUserEmail(userEmail);
			
			System.out.print("핸드폰번호를 입력하세요 : ");
			String userPhoneNumber = scanner.nextLine();
			data.setUserPhoneNumber(userPhoneNumber);
		
			return data;
		}
		
		return null;
	}
	
	public UserData withdrawMemberInfo() {
		UserData data = new UserData();
		
		System.out.print("탈퇴하시겠습니까?(네/아니오) : ");
		String answer = scanner.nextLine();
		
		if(answer.equals("네")) {
			System.out.print("탈퇴할 아이디를 입력하세요 : ");
			String userId = scanner.nextLine();
			System.out.print("탈퇴할 비밀번호를 입력하세요 : ");
			String userPwd = scanner.nextLine();
			data.setUserId(userId);
			data.setUserPwd(userPwd);
			
			return data;
		} 
		
		return null;
	}
	
	public UserData findIdInfo() {
		UserData data = new UserData();
		
		System.out.print("핸드폰번호를 입력하세요 : ");
		String userPhoneNumber = scanner.nextLine();
		
		data.setUserPhoneNumber(userPhoneNumber);
		
		return data;
	}
	
	//비밀번호 찾기 첫번째 단계(아이디, 핸드폰 번호, 이메일로 찾기)
	public UserData findPwdInfoOneStep() {
		UserData data = new UserData();
		
		System.out.print("아이디를 입력하세요 : ");
		String userId = scanner.nextLine();
		data.setUserId(userId);
		
		System.out.print("핸드폰 번호를 입력하세요 : ");
		String userPhoneNumber = scanner.nextLine();
		data.setUserPhoneNumber(userPhoneNumber);
		
		System.out.print("이메일을 입력하세요 : ");
		String userEmail = scanner.nextLine();
		data.setUserEmail(userEmail);
		
		return data;
	}
	
	
	public UserData findPwdInfoTwoStep() {
		UserData data = new UserData();
		
		System.out.print("아이디를 입력하세요 : ");
		String userId = scanner.nextLine();
		String securityQ = "";
		String securityA = "";
		data.setUserId(userId);
		
		try {
			Connection conn = JdbcTemplate.getConnection();
			
			String sql = "SELECT SECURITY_Q, SECURITY_A FROM PWD_QUESTION P JOIN MEMBER M ON P.SECURITY_Q_NO = M.SECURITY_Q_NO WHERE MEMBER_ID = ?";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				securityQ = rs.getString("SECURITY_Q");
				data.setUserSecurityQ(securityQ);
				securityA = rs.getString("SECURITY_A");
				data.setUserSecurityA(securityA);
			}
			
			System.out.print(securityQ);
			String tempA = scanner.nextLine();
			
			data.setTempSecurityA(tempA);
			
			conn.close();
		} catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
