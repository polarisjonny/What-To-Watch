package com.team2.wtw.eventboard;

public class EventMain {
	public void startEventBoard() {
		EventBoardService service = new EventBoardService();
		EventView view = new EventView();
		boolean isFinished = true;
		
		while(isFinished) {
			int choice = view.showEventMenu();
			
			switch(choice) {
			//이벤트 목록 보여주기
			case 1 : service.printEventBoardList();
					 break;
			
			//메인 페이지(홈)으로 돌아가기
			case 2 : isFinished = false;
					 break;
					
			default : 
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}	
	}
}
