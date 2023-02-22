package com.team2.wtw.freeboard;

import com.team2.wtw.main.Main;

public class MainService {
	
	FreeBoardService fs = new FreeBoardService();
	
	
	//선택지 보여주기 
	public void showMenu() throws Exception {
		
		System.out.println("\n\n\n\n\n");
		System.out.println("===============================자유게시판===============================");
		
		System.out.print("1. 게시판 작성");
		System.out.print("  2. 게시판 조회");
		System.out.print("  3. 게시판 수정");
		System.out.print("  4. 게시판 삭제");
		System.out.println("  5. 홈페이지로 돌아가기");
		
		System.out.println("=====================================================================");
		
		fs.BoardList();
		System.out.println("=====================================================================");
		System.out.println("위의 헤더 메뉴를 사용할때는 숫자를 입력해주세요 . (ex. 1 enter , 3 enter )");
		System.out.println("=====================================================================");
		
		System.out.println("\n\n\n\n\n");
		
		
		
	}//선택지 보여주기 
	
	public void boardStart(String pick) throws Exception {
		
		
		switch(pick) {
		
		case "1" : 
					if(Main.userData.getUserNum()==0) 
						{
							System.out.println("*******로그인하고 이용하세요 ㅜㅜ********");
						}else{fs.BoardWrite();}   break;
					
		case "2" :  
					fs.BoardSearch();      break;
		case "3" :
					if(Main.userData.getUserNum()==0) 
						{
							System.out.println("********로그인하고 이용하세요 ㅜㅜ********");
						}else{fs.edit();}   break;
					
		case "4" : 
					if(Main.userData.getUserNum()==0) 
						{
							System.out.println("********로그인하고 이용하세요 ㅜㅜ********");
						}else {fs.boardDelect();}    break;
		default :  System.out.println("잘못입력하였습니다");
		}
		
	}//게시글 프로그램
	
	
	public boolean startProgram() throws Exception {
		
		showMenu();
		
		
		String pick = Main.SC.nextLine();
		
		if(pick.equals("5")) {return true;}
		
		boardStart(pick);
		
		return false;
	}//메인화면
	
	//2초지연
	public void sleep() throws Exception {
		
		for(int i=0 ; i<3 ; i++) {
			Thread.sleep(1000);
			System.out.println();
		}
	}

}
