package com.team2.wtw.manager;

import java.sql.Connection;
import java.util.Scanner;

import com.team2.wtw.sanction.SanctionMember;
import com.team2.wtw.user.UserData;
import com.team2.wtw.user.UserService;

public class MemberManage {
	
	Scanner sc = new Scanner(System.in);

	UserService us = new UserService();
	SanctionMember sm = new SanctionMember();


	
	public void sanctionMember() {
		//제제회원 조회
		sm.getSanctionInfo();
		//제재회원 번호 선택 후 종료일 재설정 
		sm.inputExpirationDate();
		
	}
	
	public void manager() {
		//전체 관리자 목록 조회

		AdminManage.AdiminList();
		Admin.AdiminList();
		//관리자 번호 선택 후 삭제, 수정, 추가
        System.out.print("\n관리자 번호 선택 : ");
        int adminNo = sc.nextInt();
        System.out.print("권한 번호 선택 : ");
        int permissionNo = sc.nextInt();
        System.out.print("다음 작업 중 선택 (삭제, 수정, 추가): ");
        String action = sc.next();
        AdminManage.managePermission(adminNo, permissionNo, action);


		
	}
	
}