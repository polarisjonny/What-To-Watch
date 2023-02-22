package com.team2.wtw.search;

import java.sql.Connection;

import com.team2.wtw.main.Main;
import com.team2.wtw.template.JdbcConncetionTemplate;

public class Search {

	public void processSearch() {

		String searchCategory = searchCategory();

	} // method | processSEarch

	public String searchCategory() {

		String category = "";
		System.out.println("검색 카테고리 : 1. 제목,  2. 출연자  3. 감독  4. 장르");
		System.out.print("입력 : ");
		category = Main.SC.nextLine();

		switch (category) {
		case "1":
			return "CONTENTS_TITLE";
		case "2":
			return "DIRECTOR";
		case "3":
			return "STARRING";
		case "4":
			return "GENRE";
		}

		return null;
	}

	public void titleSearch(String searchCategory) {

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String search_sql = "";

	}

	public void directorSearch(String searchCategory) {

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String search_sql = "";

	}

	public void starringSearch(String searchCategory) {

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String search_sql = "";

	}

	public void genreSearch(String searchCategory) {

		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();

		String search_sql = "";

	}

} // class | Search

/*
 * SELECT RANK, CONTENTS_NO, CONTENTS_TITLE, SUBSTR(SYNOPSIS, 1, 10) SYNOPSIS,
 * TO_CHAR(RELEASE_DATE, 'YY/MM/DD') RELEASE_DATE FROM ( SELECT RANK() OVER
 * (ORDER BY RELEASE_DATE DESC) RANK, CONTENTS_NO, CONTENTS_TITLE, SYNOPSIS,
 * RELEASE_DATE FROM CONTENTS T1 JOIN ? T2 ON T1.CONTENTS_NO = T2.CONTENTS_NO
 * WHERE ?_NAME = ? ) WHERE RANK > ? AND RANK <= ?
 */