package com.team2.wtw.manager;

import java.sql.Connection;
import java.util.Scanner;

import com.team2.wtw.user.UserData;
import com.team2.wtw.user.UserService;

public class MemberManage {
	
	Scanner sc = new Scanner(System.in);
	
	static JdbcTemplate jt = new JdbcTemplate();
	UserService us = new UserService();
	
	
	
	public void generalMember() {
		//회원 정보 조회
		us.searchMemberInfo();
		//회원 번호 선택 후 삭제, 수정 기능 선택
	}
	
	public void sanctionMember() {
		//제제회원 조회
		//제재회원 번호 선택 후 삭제, 수정, -> 이프 이퀄스 사용하기 
		
	}
	
	public void manager() {
		//관리자 조회
		//관리자 번호 선택 후 삭제, 수정, 추가
		
		
	}
	
