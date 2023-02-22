package com.team2.wtw.faq;

import com.team2.wtw.main.Main;

public class PlayAllFunction {
	PlayStart ps = new PlayStart();
	
	
	public void goFunction() throws Exception {
		boolean ending = playAllFunction();
	}
	
	public boolean playAllFunction() throws Exception {
		
		String input = allFunctionMenu();
		if(input.equals("3")) {
			System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
			return true;
		}
		
		switch(input) {
		case "1" : 
			//FAQ게시판
			while(true) {
				boolean isFinish = ps.faqPlayStart();
				if(isFinish) {break;}
			}
			System.out.println("이전화면으로 돌아갑니다."); 
			playAllFunction();
			break;
		case "2" :
			//문의사항게시판
			while(true) {
				boolean isFinish = ps.qaPlayStart(); 
				if(isFinish) {break;}
			} 
			playAllFunction();
		}
		return false;
	}
	
	public String allFunctionMenu() {
		System.out.println();
		System.out.println("=================================================================");
		System.out.println("       1.자주묻는질문(FAQ)     |     2.문의사항           |      3.종료           ");
		System.out.println("=================================================================");
		String input = Main.SC.nextLine();
		
		return input;
	}
	
}
