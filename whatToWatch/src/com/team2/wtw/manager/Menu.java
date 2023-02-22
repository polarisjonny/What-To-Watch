package com.team2.wtw.manager;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.team2.wtw.contents.DramaContents;
import com.team2.wtw.contents.EntertainmentContents;
import com.team2.wtw.contents.MovieContents;
import com.team2.wtw.eventboard.EventBoardService;
import com.team2.wtw.faq.Afunction;
import com.team2.wtw.faq.Faq;
import com.team2.wtw.faq.PlayStart;
import com.team2.wtw.freeboard.FreeBoardService;
import com.team2.wtw.review.Review;
import com.team2.wtw.sanction.SanctionMember;

import com.team2.wtw.user.UserService;

public class Menu {

	public static String username;
	private Scanner sc = new Scanner(System.in);
	private static boolean isManager = false;
	
	
	MemberManage mm = new MemberManage();
	Review r = new Review();
	EventManage em = new EventManage();
	FreeBoardService fbs = new FreeBoardService();
	SanctionMember sm = new SanctionMember();
	JdbcTemplate jt = new JdbcTemplate();
	ContentsManage cm = new ContentsManage();
	DramaContents dc = new DramaContents();
	EntertainmentContents ec = new EntertainmentContents();
	MovieContents mc = new MovieContents();
	ReviewManage rm = new ReviewManage();
	Faq f = new Faq();
	UserService us = new UserService();
	PlayStart ps = new PlayStart();
	Afunction af = new Afunction();

	// 관리자페이지 메뉴판
	public void printMenu() {
		System.out.println("========== 관리자 페이지 ==========");
		System.out.println("0. 로그아웃");
		System.out.println("1. 홈화면");
		System.out.println("2. 회원 관리");
		System.out.println("3. 게시판 관리");
		System.out.println("4. 리뷰 관리");
		System.out.println("5. 문의 관리");
		System.out.println("6. 이벤트 관리");
		System.out.println("7. 컨텐츠 관리");
		System.out.print("메뉴 선택 : ");
	}

	// 권한에 따른 메뉴 선택 차별화
	public void choiceMenu() throws Exception {
		ManagerService service = new ManagerService();
		if(isManager) {
			
		}
		else {
			isManager = service.checkIfManager();
		}
		
		boolean isFinish = true;

		if (isManager) {
			while (isFinish) {
				printMenu();
				String choice;
				try {
					choice = sc.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
					sc.next(); // 입력
					continue;
				}

				switch (choice) {
				case "1":
					isFinish = false;
					break;
				case "2": /* 회원 관리 */
					int permissionNo = jt.getPermissionNo(username);
					if (permissionNo == 1 || permissionNo == 2) {
						memberManagementMenu();
					} else {
						System.out.println("\n접속 권한이 없습니다.");
					}
					break;
				case "3": /* 게시판 관리 */
					permissionNo = JdbcTemplate.getPermissionNo(username);
					if (permissionNo == 1 || permissionNo == 2 || permissionNo == 3) {
						boardManagementMenu();
					} else {
						System.out.println("\n접속 권한이 없습니다.");
					}
					break;
				case "4": /* 리뷰 관리 */
					permissionNo = JdbcTemplate.getPermissionNo(username);
					if (permissionNo == 1 || permissionNo == 2 || permissionNo == 4) {
						reviewManagementMenu();
					} else {
						System.out.println("\n접속 권한이 없습니다.");
					}
					break;
				case "5": /* 문의 관리 */
					permissionNo = JdbcTemplate.getPermissionNo(username);
					if (permissionNo == 1 || permissionNo == 6) {
						questionManagementMenu();
					} else {
						System.out.println("\n접속 권한이 없습니다.");
					}
					break;
				case "6": /* 이벤트 관리 */
					permissionNo = JdbcTemplate.getPermissionNo(username);
					if (permissionNo == 1 || permissionNo == 7) {
						eventManagementMenu();
					} else {
						System.out.println("\n접속 권한이 없습니다.");
					}
					break;
				case "7": /* 컨텐츠 관리 */
					contentsManagement();
					break;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
				}
			}

		}
	}

	// 회원관리 메뉴
	public void memberManagementMenu() throws Exception {
		System.out.println("\n환영합니다 관리자님. 회원관리 페이지입니다.\n");

		System.out.println("0. 관리자 홈");
		System.out.println("1. 일반회원 조회");
		System.out.println("2. 제재회원");
		System.out.println("3. 관리자");
		System.out.print("메뉴 선택 : ");
		String memberType = sc.nextLine();
		switch (memberType) {
		case "0":
			choiceMenu();
			break;
		case "1":
			us.getAllUser();
			break;
		case "2":
			mm.sanctionMember();
			break;
		case "3":
			mm.manager();
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
		}
	}

	// 게시판관리 메뉴
	public void boardManagementMenu() throws Exception {

		System.out.println("\n환영합니다 관리자님. 게시판관리 페이지입니다.");

		System.out.println("0. 관리자 홈");
		System.out.println("1. 게시판 관리");
		System.out.println("2. 제재 게시판 목록");
		String boardType = sc.nextLine();
		switch (boardType) {
		case "0":
			choiceMenu();
			break;
		case "1":
			// 게시판 목록 조회 후 작성자 번호 신고하기
			fbs.BoardList();
			sm.inputSanction();
			break;
		case "2":
			sm.printBoardSanctions(jt.getConnection());
			break;
		}
	}

	// 리뷰관리 메뉴
	public void reviewManagementMenu() throws Exception {

		System.out.println("\n환영합니다 관리자님. 리뷰관리 페이지입니다.");

		System.out.println("0. 관리자 홈");
		System.out.println("1. 리뷰 관리");
		System.out.println("2. 제재 리뷰 목록");
		String reviewType = sc.nextLine();
		switch (reviewType) {
		case "0":
			choiceMenu();
			break;
		case "1":
			rm.printReviewList();
			sm.inputSanction();
			break;
		case "2":
			sm.printReviewSanctions(jt.getConnection());
			break;
		}

	}

	// 문의관리 메뉴
	public void questionManagementMenu() throws Exception {

		System.out.println("\n환영합니다 관리자님. 문의관리 페이지입니다.\n");

		System.out.println("0. 관리자 홈");
		System.out.println("1. 자주묻는질문");
		System.out.println("2. 문의게시판");
		System.out.print("메뉴 선택 : ");
		String questionType = sc.nextLine();
		switch (questionType) {
		case "0":
			choiceMenu();
			break;
		case "1":
			ps.faqPlayStart();
			break;
		case "2":
			// 문의게시판 확인
			ps.qaPlayStart();
			// 문의게시판 답변
			af.answerList();
			af.writeA();
			af.checkAnswer();

		}

	}

	// 이벤트관리 메뉴
	public void eventManagementMenu() throws Exception {
		System.out.println("\n환영합니다 관리자님. 이벤트관리 페이지입니다.");

		System.out.println("0. 관리자 홈");
		System.out.println("1. 이벤트 추가/삭제/수정");
		System.out.println("2. 점수 조회");
		String eventType = sc.nextLine();

		switch (eventType) {
		case "0":
			choiceMenu();
			break;
		case "1":
			em.inputAddEvent();
			break;
		case "2":
			em.printEventScore();
			break;
		}

	}

	// 컨텐츠관리 메뉴
	public void contentsManagement() throws Exception {

		System.out.println("\n환영합니다 관리자님. 컨텐츠관리 페이지입니다.");

		System.out.println("0. 관리자 홈");
		System.out.println("1. 드라마 추가/수정/삭제");
		System.out.println("2. 예능 추가/수정/삭제");
		System.out.println("3. 영화 추가/수정/삭제");
		System.out.print("메뉴 선택 : ");
		String contentsType = sc.nextLine();

		switch (contentsType) {
		case "0":
			choiceMenu();
			break;
		case "1":
			dc.showDramaContents();
			cm.contentsManage();
			break;
		case "2":
			ec.showEntContents();
			cm.contentsManage();
			break;
		case "3":
			mc.showMovieContents();
			cm.contentsManage();
			break;
		}

	}

}