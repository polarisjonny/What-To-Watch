package q_a;

public class QandA_BasicFunction {
	
	//메뉴보여주기
	public void showQaMenu() {
		System.out.println();
		System.out.println("1.문의사항 작성");
		System.out.println("2.문의게시판목록");
		System.out.println("3.나의 문의");
		System.out.println("4.답변 작성");
	}
	
	//메뉴선택받기
	public String selectQaMenu() {
		String input = Main.SC.nextLine();
		
		return input;
	}
	
	//선택에 따른 로직젯
	public void showQaList(String input) {
		Qfunction q = new Qfunction();
		Afunction a = new Afunction();
		
		switch(input) {
		case "1" : q.writeQ();
		case "2" :
		case "3" :
		case "4" :
		}
	}

}
