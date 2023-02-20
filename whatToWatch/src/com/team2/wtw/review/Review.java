package com.team2.wtw.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.team2.wtw.contents.ContentsHome;

public class Review {

	public void writeReviwe(String contentsNo) {

		System.out.print("리뷰 작성 : ");
		String reviewContents = ContentsHome.SC.nextLine();
		System.out.print("평점 : ");
		double rating = ContentsHome.SC.nextDouble();

		try {
			// DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "C##TEAM2TEST";
			String pw = "T2T";
			Connection conn = DriverManager.getConnection(url, id, pw);

			// 최신순
			String movieList_sql = "";
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // method | writeReviwe

}
