package com.team2.wtw.eventboard;

import com.team2.wtw.main.Main;

public class EventView {
	public int showEventMenu() {
		System.out.println();
		System.out.println("1. 이벤트 게시판 목록");
		System.out.println("2. 홈으로 돌아가기");
		System.out.print("메뉴를 선택하세요 : ");
		String num = Main.SC.nextLine();
		
		int number = Integer.parseInt(num);
		
		return number;
	}
	
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
	
	//1번 게시판의 당첨 여부확인
	public String viewIfWonEvent1() {
		System.out.println();
		System.out.print("당첨여부를 확인하러 가기(GO를 입력하세요) ->");
		String input = Main.SC.nextLine();
		
		return input;
	}
	
	//2번 게시판 게임 시작하기
	public String viewEvent2() {
		System.out.println();
		System.out.print("게임 시작하기(START를 입력하세요) ->");
		String input = Main.SC.nextLine();
		
		return input;
	}
	
	//1번 게시판 이벤트 당첨 축하 메시지 보여주기
	public void showCelebrationMessage1() {
		System.out.println("☆★☆★☆★☆★☆★☆★ 축하드립니다! 리뷰 10개 이상을 남겨 이벤트에 당첨되셨습니다~ ☆★☆★☆★☆★");
	}
	
	//2번 게시판 이벤트 당첨 축하 메시지 보여주기
	public void showCelebrationMessage2() {
		System.out.println("☆★☆★☆★☆★☆★☆★ 축하드립니다! 크레딧 500점을 넘겨 게임에 승리하였습니다~ ☆★☆★☆★☆★☆★");
	}
	
	
}
