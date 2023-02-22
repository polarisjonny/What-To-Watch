package com.team2.wtw.manager;

import com.team2.wtw.main.Main;

public class ManagerView {
	//관리자 번호는 1000번
	
	//관리자 초기화면(아이디, 비밀번호 입력) 보여주기
	public ManagerData showManagerLogin() {
		ManagerData data = new ManagerData();
		
		System.out.println("==================================================");
		System.out.println("           관    리    자    페    이    지          ");
		System.out.println("--------------------------------------------------");
		System.out.println();
		System.out.print("아이디를 입력하세요 : ");
		String managerId = Main.SC.nextLine();
		data.setAdminId(managerId);
		
		System.out.print("비밀번호를 입력하세요 : ");
		String managerPwd = Main.SC.nextLine();
		data.setAdminPwd(managerPwd);
		
		return data;
	}
	
	//관리자 로그인 한 후의 화면 보여주기
	public void showManagerMenu() {
		 System.out.println("0. 로그아웃");
	     System.out.println("1. 홈화면");
	     System.out.println("2. 회원 관리");
	     System.out.println("3. 게시판 관리");
	     System.out.println("4. 리뷰 관리");
	     System.out.println("5. 문의 관리");
	     System.out.println("6. 이벤트 관리");
	     System.out.println("7. 컨텐츠 관리");
	     System.out.print("메뉴 선택 : ");
	     String menuNum = Main.SC.nextLine();
	}
	
	
}
