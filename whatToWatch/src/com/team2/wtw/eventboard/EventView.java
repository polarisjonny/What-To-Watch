package com.team2.wtw.eventboard;

import com.team2.wtw.main.Main;

public class EventView {
	public void showMovieCoupon() {
		System.out.println("CGV 5000원 할인쿠폰에 당첨되셨습니다!");
		System.out.println();
		System.out.println("<>================================<>");     
		System.out.println("||     			CGV               ||");    
		System.out.println("||                                ||");
		System.out.println("||			   할인쿠폰             ||");
		System.out.println("||                                ||");
		System.out.println("||             5000원              ||");
		System.out.println("||                                ||");
		System.out.println("<>================================<>");		
	}
	
	
	public void showPopcornCoupon() {
		System.out.println("CGV 팝콘 음료세트 쿠폰에 당첨되셨습니다!");
		System.out.println();
		System.out.println(".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("|                                 |");
		System.out.println("|                CGV              |");
		System.out.println("|                                 |");
		System.out.println("|          팝콘 + 음료 세트          |");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

	}
	
	public int showEventBoard() {
		//게시물 목록만 보여주고
		
		//상세 조회하려면 번호를 입력하세요
		System.out.print("조회하고 싶은 이벤트 게시판의 번호를 입력하세요 : ");
		String input = Main.SC.nextLine();
		int inputNum = Integer.parseInt(input);
		
		return inputNum;
	}
}
