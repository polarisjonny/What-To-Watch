package com.team2.wtw.template;

import com.team2.wtw.main.Main;

public class PrintTemplate {

	private String[] headerMenu = new String[10];
	
	//헤드 메뉴
	public void printHeaderMenu() {

		printCeiling();
		menuBranch(headerMenu);
		printHeaderMenu(headerMenu);
		printFloor();
		printHowToUse();
		printFloor();

	} // method | printHeaderMenu

	private void printCeiling() {
		
		for (int i = 0; i < 123; i++)
			System.out.print("=");
		System.out.println();
		
	} // method | printCeiling

	public static void printFloor() {
		
		for (int i = 0; i < 123; i++)
			System.out.print("-");
		System.out.println();
		
	} // method | printFloor

	private void printHeaderMenu(String[] headerMenu) {
		
		for (int i = 0; i < headerMenu.length; i++)
			System.out.printf("%-11s", headerMenu[i]);
		System.out.println();
		
	} // method | printHeaderMenu

	private void menuBranch(String[] headerMenu) {

		if (Main.userData.getUserNum() == 0) {
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
		} else if (Main.userData.getUserNum() != 0) {
			headerMenu[0] = "1. 내정보";
			headerMenu[1] = "2. 로그아웃";
			headerMenu[2] = "3. 검 색";
			headerMenu[3] = "4. 영 화";
			headerMenu[4] = "5. 드라마";
			headerMenu[5] = "6. 예 능";
			headerMenu[6] = "7. 자유게시판";
			headerMenu[7] = "8. 이벤트";
			headerMenu[8] = "9. 문의게시판";
			headerMenu[9] = "10. FAQ";
		}
		
	} // method | menuBranch

	public void printHowToUse() {
		System.out.println("위의 헤더 메뉴를 사용할 때는 / 후 숫자 또는 메뉴명을 입력해 주세요.(ex. /2, /로그인)");
		System.out.println("프로그램을 종료 하시려면 /exit를 입력해주세요.");
	} // method | printHowToUse
	
} // class | PrintTemplate
