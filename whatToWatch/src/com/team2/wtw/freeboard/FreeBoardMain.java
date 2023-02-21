package com.team2.wtw.freeboard;

import com.team2.wtw.main.Main;

public class FreeBoardMain {

	FreeBoardService fs = new FreeBoardService();
	
	public void boardMain() throws Exception {

		// 게시판 목록 ~~~
		MainService ms = new MainService();
		while(true) {
			boolean isFinish = ms.startProgram();
				if(isFinish) {break;}
		}
	}
	
	public boolean m01() throws Exception {
			
		String pick = Main.SC.nextLine();
		
		switch(pick) {
		case "1" :  fs.BoardWrite();    break;
		case "2" :  fs.BoardSearch();      break;
		case "3" :  fs.edit();      break;
		case "4" :  fs.boardDelect();    break;
		case "5" :  return true;
		default :  System.out.println("잘못입력하였습니다");
		}
		
		return false;
		}

}
