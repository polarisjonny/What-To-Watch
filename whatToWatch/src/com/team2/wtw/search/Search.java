package com.team2.wtw.search;

import java.sql.Connection;

import com.team2.wtw.template.JdbcConncetionTemplate;

public class Search {

	
	public void processSearch() {
		
		
		
	} // method | processSEarch
	
	
	public void Search(String searchCategory) {
		
		Connection conn = new JdbcConncetionTemplate().getJdbcConnection();
		
		String search_sql = "";
		
	}
	
} // class | Search
