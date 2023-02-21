package com.team2.wtw.freeboard;

import com.team2.wtw.main.Main;

public class FreeBoardMain {

	FreeBoardService fs = new FreeBoardService();
	
	public void boardMain() throws Exception {

		System.out.println("====자유게시판====");

		System.out.println("1. 게시판 작성");
		System.out.println("2. 게시판 조회");
		System.out.println("3. 게시판 수정");
		System.out.println("4. 게시판 삭제");

		// 게시판 목록 ~~~
		
		m01();
	}
	
	public void m01() throws Exception {
			
		String pick = Main.SC.nextLine();
		
		switch(pick) {
		case "1" :  fs.BoardWrite();    break;
		case "2" :  fs.BoardSearch();      break;
		case "3" :  fs.edit();      break;
		case "4" :  fs.boardDelect();      break;
		default :  System.out.println("잘못입력하였습니다");
		}
		}

}
