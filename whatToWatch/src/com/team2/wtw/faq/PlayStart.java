package com.team2.wtw.faq;

public class PlayStart {
	Faq faq = new Faq();
	QandA qa = new QandA();
	public static String Input;
	
	//FAQ게시판 실행
	public boolean faqPlayStart() throws Exception {
		//FAQ 메뉴보여주기
		faq.showFaqMenu();
		
		//메뉴 선택
		Input = faq.selectFaqMenu();
		if(Input.equals("0")) {
		return true;	
		}
		
		//선택에 따른 로직제시
		faq.showSolution(Input);
		return false;
	}
	
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
