package com.team2.wtw.manager;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private String username;
    private Scanner sc = new Scanner(System.in);
    
    MemberManage mm = new MemberManage();

    public Menu(String username) {
        this.username = username;
    }

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

    public void choiceMenu() {
        while (true) {
            printMenu();
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                sc.next(); // 입력
                continue;
            }

            switch (choice) {
                case 0: /* 로그아웃 */ return;
                case 1: /* 홈화면 */ break;
                case 2: /* 회원 관리 */
                    int permissionNo = JdbcTemplate.getPermissionNo(username);
                    if (permissionNo == 1 || permissionNo == 2) {
                        memberManagementMenu();
                    } else {
                        System.out.println("접속 권한이 없습니다.");
                    }
                    break;
                case 3: /* 게시판 관리 */
                    permissionNo = JdbcTemplate.getPermissionNo(username);
                    if (permissionNo == 1 || permissionNo == 2 || permissionNo == 3) {
                        boardManagementMenu();
                    } else {
                        System.out.println("접속 권한이 없습니다.");
                    }
                    break;
                case 4: /* 리뷰 관리 */
                    permissionNo = JdbcTemplate.getPermissionNo(username);
	                if (permissionNo == 1 || permissionNo == 2 || permissionNo == 4) {
	                    reviewManagementMenu();
	                } else {
	                    System.out.println("접속 권한이 없습니다.");
	                }
	                break;
	            case 5: /*문의 관리*/
	                permissionNo = JdbcTemplate.getPermissionNo(username);
	                if (permissionNo == 1 || permissionNo == 6) {
	                    questionManagementMenu();
	                } else {
	                    System.out.println("접속 권한이 없습니다.");
	                }
	                break;
	            case 6: /*이벤트 관리*/
	                permissionNo = JdbcTemplate.getPermissionNo(username);
	                if (permissionNo == 1 || permissionNo == 7) {
	                    eventManagementMenu();
	                } else {
	                    System.out.println("접속 권한이 없습니다.");
	                }
	                break;
	            case 7: /*컨텐츠 관리*/ 
	                contentsManagement(); 
	                break;
	            default:
	                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
	        }
	    }
	}
    
    public void memberManagementMenu() {
        System.out.println("환영합니다 관리자님. 회원관리 페이지입니다.\n");
        System.out.println("0. 관리자 홈");
        System.out.println("1. 일반회원");
        System.out.println("2. 제재회원");
        System.out.println("3. 관리자");
        System.out.print("메뉴 선택 : ");
        int memberType = sc.nextInt();
        switch (memberType) {
	        case 0: 
	    		choiceMenu();
	    		break;
            case 1:
                mm.generalMember();
                break;
            case 2:
            	mm.sanctionMember();
                break;
            case 3:
                mm.manager();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
        }
    }
    
    public void boardManagementMenu() {
    	
    }
    
    public void reviewManagementMenu() {
    	
    }
	

	public void questionManagementMenu() {
		System.out.println("환영합니다 관리자님. 문의관리 페이지입니다.\n");
		System.out.println("0. 관리자 홈");
        System.out.println("1. 자주묻는질문");
        System.out.println("2. 문의게시판");
        System.out.print("메뉴 선택 : ");
        int questionType = sc.nextInt();
        
        switch(questionType) {
        	case 0: 
        		choiceMenu();
        		break;
        	case 1:
        		//자주묻는질문 수정/추가/삭제
        		break;
        	case 2:
        		//문의게시판 확인/답변
        }
		
	}
	
	public void eventManagementMenu() {
		
	}
	
	public void contentsManagement() {
		
	}
	
	

}
