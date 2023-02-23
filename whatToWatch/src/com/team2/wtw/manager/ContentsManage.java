package com.team2.wtw.manager;

import java.sql.*;
import java.util.Scanner;

public class ContentsManage {

    static JdbcTemplate jt = new JdbcTemplate();
    Scanner sc = new Scanner(System.in);

    public void contentsManage() {
        try (Connection conn = jt.getConnection()) {
            System.out.println("\n--------컨텐츠 관리--------");
            System.out.print("컨텐츠 수행 작업 입력(수정,추가,삭제) : ");
            String action = sc.nextLine();
            switch (action) {
                case "수정":
                    try (PreparedStatement stmt = conn.prepareStatement("UPDATE CONTENTS SET CONTENTS_TITLE = ?, SYNOPSIS = ? WHERE CONTENTS_NO = ?")) {
                        System.out.print("수정할 컨텐츠 번호 입력 : ");
                        int contentsNo = sc.nextInt();
                        sc.nextLine();
                        System.out.print("작품 이름 작성 : ");
                        String contentsTitle = sc.nextLine();
                        System.out.print("줄거리 작성 : ");
                        String synopsis = sc.nextLine();
                        stmt.setString(1, contentsTitle);
                        stmt.setString(2, synopsis);
                        stmt.setInt(3, contentsNo);
                        int rows = stmt.executeUpdate();
                        if (rows > 0) {
                            System.out.println("컨텐츠 수정이 완료되었습니다.");
                        } else {
                            System.out.println("컨텐츠 수정에 실패했습니다.");
                        }
                    }
                    break;
                case "추가":
                    try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO CONTENTS (CONTENTS_NO, DIRECTOR_NO, CATEGORY_NO, CONTENTS_TITLE, SYNOPSIS, RELEASE_DATE) VALUES (CONTENTS_NO_SEQ.NEXTVAL, ?,?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'))")) {
                        System.out.print("카테고리 번호 입력 : ");
                        int categoryNo = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("감독 번호 입력 : ");
                        int directorNo = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("작품이름 입력 : ");
                        String contentsTitle = sc.nextLine();
                        System.out.print("줄거리 입력 : ");
                        String synopsis = sc.nextLine();
                        System.out.print("방영날짜 입력 (yyyy-mm-dd): ");
                        String releaseDate = sc.nextLine();
                        stmt.setInt(1, directorNo);
                        stmt.setInt(2, categoryNo);
                        stmt.setString(3, contentsTitle);
                        stmt.setString(4, synopsis);
                        stmt.setString(5, releaseDate);
                        int rows = stmt.executeUpdate();
                        if (rows > 0) {
                            System.out.println("컨텐츠 추가가 완료되었습니다.");
                        } else {
                            System.out.println("컨텐츠 추가에 실패했습니다.");
                        }
                    }
                    break;
                case "삭제":
                    try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM CONTENTS WHERE CONTENTS_NO = ?")) {
                        System.out.print("삭제할 컨텐츠 번호 입력 : ");
                        int contentsNo = sc.nextInt();
                        stmt.setInt(1, contentsNo);
                        int rows = stmt.executeUpdate();
                        if (rows > 0) {
                            System.out.println("컨텐츠 삭제가 완료되었습니다.");
                        } else {
                            System.out.println("컨텐츠 삭제에 실패했습니다.");
                        }
                    }
                    break;
                default:
                    System.out.println("잘못된 작업입니다.");
                    break;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
