package com.team2.wtw.main;

import java.util.Scanner;


import com.team2.wtw.controller.PageController;
import com.team2.wtw.manager.ManagerData;
import com.team2.wtw.template.PrintTemplate;
import com.team2.wtw.user.UserData;


public class Main {
	public static UserData userData = new UserData();
	
	public static ManagerData adminData = new ManagerData();
	
	public static final Scanner SC = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		new PrintTemplate().printTitle();
		
		new PageController().controllPage();
		
	}
}
