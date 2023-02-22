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
	
	

}
