package com.team2.wtw.manager;

import com.team2.wtw.main.Main;
import com.team2.wtw.qa.Afunction;
import com.team2.wtw.qa.MyQa;
import com.team2.wtw.qa.Qfunction;

public class QA_function {

	// 메뉴보여주기
	public void showQaMenu() {
		System.out.println(
				"================================================================================================");
		System.out.println(
				" 0. 돌아가기      1.문의답변 작성     ");
		System.out.println(
				"================================================================================================ ");
	}

	// 메뉴선택받기
	public String selectQaMenu() {
		String input = Main.SC.nextLine();

		return input;
	}

	// 선택에 따른 로직젯
	public void showQaList(String input) throws Exception {
		Qfunction q = new Qfunction();
		Afunction a = new Afunction();
		MyQa mq = new MyQa();
		q.qaBoardList();
		a.answerList();
	}

}
