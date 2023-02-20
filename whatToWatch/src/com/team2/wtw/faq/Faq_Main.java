package com.team2.wtw.faq;

public class Faq_Main {

	PlayStart ps = new PlayStart();

	public static void main(String[] args) {

		// FAQ게시판
		while (true) {
			boolean isFinish = ps.faqPlayStart();
			if (isFinish) {
				break;
			}
		}
		System.out.println("사용을 종료합니다. 이용해 주셔서 감사합니다.");

	}

}
