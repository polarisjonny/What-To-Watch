package com.team2.wtw.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.wtw.main.Main;
import com.team2.wtw.template.JdbcConncetionTemplate;

public class UserService {

	// 회원가입 기능
	public void join() {
		// 유저한테 회원가입 보여주고 입력받기
		UserView uv = new UserView();
		UserData data = uv.GetjoinInfo();

		try {
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
			String sql = "INSERT INTO MEMBER(MEMBER_NO, SECURITY_Q_NO, STATE_CODE, MEMBER_ID, MEMBER_PWD, MEMBER_NICK, EMAIL, PHONE_NUMBER, SECURITY_A) VALUES(MEMBER_NO.NEXTVAL, ?, 1, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			int securityQNum = Integer.parseInt(data.getUserSecurityQ());
			pstmt.setInt(1, securityQNum);
			pstmt.setString(2, data.getUserId());
			pstmt.setString(3, data.getUserPwd());
			pstmt.setString(4, data.getUserNick());
			pstmt.setString(5, data.getUserEmail());
			pstmt.setString(6, data.getUserPhoneNumber());
			pstmt.setString(7, data.getUserSecurityA());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("회원가입 성공");
			} else {
				System.out.println("회원가입 실패");
			}

			conn.close();

		} catch (SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그인 기능
	public void login() {
		UserView uv = new UserView();
		UserData data = uv.GetLoginInfo();

		try {
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
			String sql = "SELECT MEMBER_NO, MEMBER_ID, MEMBER_PWD, MEMBER_NICK FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getUserId());
			pstmt.setString(2, data.getUserPwd());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int memberNum = rs.getInt("MEMBER_NO");
				String nick = rs.getString("MEMBER_NICK");

				Main.userData.setUserNum(memberNum);
				
				System.out.println(nick + "님 환영합니다:)");

			} else {
				System.out.println("로그인 실패");
			}

			conn.close();

		} catch (SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 아이디 찾기
	public void findId() {
		UserView uv = new UserView();
		UserData data = uv.findIdInfo();

		try {
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			String sql = "SELECT MEMBER_ID, MEMBER_NICK FROM MEMBER WHERE PHONE_NUMBER = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getUserPhoneNumber());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String nick = rs.getString("MEMBER_NICK");
				String userId = rs.getString("MEMBER_ID");

				System.out.println(nick + "님의 아이디는 " + userId + " 입니다.");
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 비밀번호 찾기
	public void findPwd() {
		UserView uv = new UserView();
		UserData data = uv.findPwdInfoOneStep();

		try {
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getUserId());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String tempEmail = data.getUserEmail();
				String email = rs.getString("EMAIL");
				String tempPhoneNumber = data.getUserPhoneNumber();
				String phoneNumber = rs.getString("PHONE_NUMBER");
				String nick = rs.getString("MEMBER_NICK");
				String password = rs.getString("MEMBER_PWD");

				if (tempEmail.equals(email) && tempPhoneNumber.equals(phoneNumber)) {
					System.out.println(nick + "님의 비밀번호는 " + password + " 입니다.");
				} else {
					System.out.println("비밀번호 찾기 실패");
					findPwd2();
				}

			}

			conn.close();

		} catch (SQLException se) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 비밀번호 찾기(보안질문으로)
	public void findPwd2() {
		UserView uv = new UserView();
		UserData data = uv.findPwdInfoTwoStep();

		try {
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getUserId());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String nick = rs.getString("MEMBER_NICK");
				String userPwd = rs.getString("MEMBER_PWD");
				String tempA = data.getTempSecurityA();
				String securityA = data.getUserSecurityA();

				if (tempA.equals(securityA)) {
					System.out.println(nick + "님의 비밀번호는 " + userPwd + " 입니다.");
				} else {
					System.out.println("비밀번호 찾기 실패");
				}
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 회원정보 조회
	public void searchMemberInfo() {
		UserView uv = new UserView();
		UserData data = uv.GetSearchMemberInfo();

		try {
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
			String sql = "SELECT MEMBER_ID, MEMBER_PWD, MEMBER_NICK, SIGN_UP_DATE, EMAIL, PHONE_NUMBER FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getUserId());
			pstmt.setString(2, data.getUserPwd());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String memberId = rs.getString("MEMBER_ID");
				String memberPwd = rs.getString("MEMBER_PWD");
				String memberNick = rs.getString("MEMBER_NICK");
				String signUpDate = rs.getString("SIGN_UP_DATE");
				String memberEmail = rs.getString("EMAIL");
				String memberPhoneNumber = rs.getString("PHONE_NUMBER");

				System.out.println(
						"+-------------+------------------+-----------+-------------------------+--------------+----------------------------+");
				System.out.println(
						"| ID          | Password         | Nickname  | Email                   | Phone Number | Sign-up Date               |");
				System.out.println(
						"+-------------+------------------+-----------+-------------------------+--------------+----------------------------+");
				System.out.format("| %-11s | %-16s | %-9s | %-23s | %-12s | %-20s     |\n", memberId, memberPwd,
						memberNick, memberEmail, memberPhoneNumber, signUpDate);
				System.out.println(
						"+-------------+------------------+-----------+-------------------------+--------------+----------------------------+");

			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("1. 2.3.");
	}

	// 회원정보 수정
	public void modifyMemberInfo() {
		UserView uv = new UserView();
		UserData data = uv.getModifiedMemberInfo();

		try {
			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			String sql = "UPDATE MEMBER SET MEMBER_NICK = ?, EMAIL = ?, PHONE_NUMBER = ? WHERE MEMBER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getUserNick());
			pstmt.setString(2, data.getUserEmail());
			pstmt.setString(3, data.getUserPhoneNumber());
			pstmt.setString(4, data.getUserId());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("회원정보 수정 성공");
			} else {
				System.out.println("회원정보 수정 실패");
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 탈퇴
	public void withdrawUser() {
		UserView uv = new UserView();
		UserData data = uv.withdrawMemberInfo();

		try {
			String sql = "UPDATE MEMBER SET STATE_CODE = 3 WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";

			Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getUserId());
			pstmt.setString(2, data.getUserPwd());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("회원 탈퇴 성공");
			} else {
				System.out.println("회원 탈퇴 실패");
			}

			conn.close();

		} catch (SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//회원 전체 목록 조회
	public void getAllUser() {
		try {
			String sql = "SELECT MEMBER_NO, MEMBER_ID, MEMBER_PWD, MEMBER_NICK FROM MEMBER";
			
			Connection conn = JdbcTemplate.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memberPwd = rs.getString("MEMBER_PWD");
				String memberNick = rs.getString("MEMBER_NICK");
				
				System.out.println(memberNo+" | "+memberId+" | "+memberPwd+" | "+memberNick);
			}
		} catch(SQLException e) {
			System.out.println("값을 잘못 입력하셨습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
