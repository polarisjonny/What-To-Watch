package faq;

public class FaqStart {
	
	import faq.Faq;
	import qa.QandA;

	public class PlayStart {
		Faq faq = new Faq();
		QandA qa = new QandA();
		
	//FAQ게시판 실행
	public boolean faqPlayStart() {
		//FAQ 메뉴보여주기
		faq.showFaqMenu();
		
		//메뉴 선택
		String input = faq.selectFaqMenu();
		if(input.equals("0")) {
		return true;	
		}
		
		//선택에 따른 로직제시
		faq.showSolution(input);
		return false;
	}

}
