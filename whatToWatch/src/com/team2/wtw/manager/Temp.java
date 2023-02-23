package com.team2.wtw.manager;


public class Temp {

	QA_function qa = new QA_function();
	
	//Q&A게시판 실행
		public boolean qaPlayStart() throws Exception {
			//문의게시판 메뉴 보여주김
			qa.showQaMenu();
			
			//문의게시판 메뉴 선택
			String input = qa.selectQaMenu();
			if(input.equals("0")) {return true;}
			
			//선택에 따른 로직 제시
			qa.showQaList(input);
			return false;
		}
	
}
