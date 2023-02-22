package com.team2.wtw.controller;

import com.team2.wtw.contents.DramaContents;
import com.team2.wtw.contents.EntertainmentContents;
import com.team2.wtw.contents.MovieContents;
import com.team2.wtw.freeboard.FreeBoardMain;
import com.team2.wtw.main.Main;
import com.team2.wtw.manager.Menu;
import com.team2.wtw.search.Search;
import com.team2.wtw.template.PrintTemplate;
import com.team2.wtw.user.UserService;

public class PageController {

	public void controllPage() {

		String userInput = "";
		UserService us = new UserService();
		

		while ( !(userInput.equals("/exit")) ) {
			new PrintTemplate().printHeaderMenu();

			userInput = Main.SC.nextLine();

			// 회원 가입
			if (Main.userData.getUserNum() == 0 && (userInput.equals("/1") || userInput.equals("/회원가입"))) {

				us.join();

			}
			// 회원정보
			else if (Main.userData.getUserNum() != 0 && (userInput.equals("/1") || userInput.equals("/내정보"))) {

				us.searchMemberInfo();

			}
			// 로그인
			else if (Main.userData.getUserNum() == 0 && (userInput.equals("/2") || userInput.equals("/로그인"))) {

				
				us.login();

			}
			// 로그아웃
			else if (Main.userData.getUserNum() != 0 && (userInput.equals("/2") || userInput.equals("/로그아웃"))) {

				Main.userData.setUserNum(0);

			} 
			// 검색
			else if (userInput.equals("/3") || userInput.equals("/검 색")) {

				Search s = new Search();
				s.processSearch();
				
			}
			// 영화
			else if (userInput.equals("/4") || userInput.equals("/영 화")) {

				MovieContents mc = new MovieContents();
				mc.processMovie();
				

			}
			// 드라마
			else if (userInput.equals("/5") || userInput.equals("/드라마")) {

				DramaContents dc = new DramaContents();
				dc.processDrama();
				

			}
			// 예 능
			else if (userInput.equals("/6") || userInput.equals("/예 능")) {

				EntertainmentContents ec = new EntertainmentContents();
				ec.processEnt();
				

			}
			// 자유게시판
			else if (userInput.equals("/7") || userInput.equals("/자유게시판")) {

				FreeBoardMain fbm = new FreeBoardMain();
				try {
					fbm.boardMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			// 이벤트
			else if (userInput.equals("/8") || userInput.equals("/이벤트")) {


			}
			// 문의게시판
			else if (userInput.equals("/9") || userInput.equals("/문의게시판")) {

				
				
			}
			// FAQ
			else if (userInput.equals("/10") || userInput.equals("/FAQ")) {

				

			}
			//설정(관리자)
			else if (userInput.equals("/11") || userInput.equals("/관리자")) {
				
			}

		} // while( !(userInput.equals("/exit")) )

	} // method | controllPage

} // class | PageController
