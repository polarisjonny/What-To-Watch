package com.team2.wtw.manager;

import java.sql.*;

public class ContentsManage {

    static JdbcTemplate jt = new JdbcTemplate();

    public void contentsManage() {
        try (Connection conn = jt.getConnection()) {
        	System.out.println("\n--------컨텐츠 관리--------");
            System.out.print("컨텐츠 수행 작업 입력(수정,추가,삭제) : ");
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs;
                switch (System.console().readLine().toLowerCase()) {
                    case "수정":
                        System.out.print("수정할 컨텐츠 번호 입력 : ");
                        int contentsNo = Integer.parseInt(System.console().readLine());
                        System.out.print("작품 이름 작성 : ");
                        String contentsTitle = System.console().readLine();
                        System.out.print("줄거리 작성 ");
                        String synopsis = System.console().readLine();
                        stmt.executeUpdate("UPDATE CONTENTS SET CONTENTS_TITLE = '" + contentsTitle +
                            "', SYNOPSIS = '" + synopsis + "' WHERE CONTENTS_NO = " + contentsNo);
                        System.out.println("컨텐츠 수정이 완료되었습니다.");
                        break;
                    case "추가":
                        System.out.print("추가할 컨텐츠 번호 입력 : ");
                        contentsNo = Integer.parseInt(System.console().readLine());
                        System.out.print("감독번호 입력 : ");
                        int directorNo = Integer.parseInt(System.console().readLine());
                        System.out.print("카테고리 번호 입력 : ");
                        int categoryNo = Integer.parseInt(System.console().readLine());
                        System.out.print("작품이름 입력 : ");
                        contentsTitle = System.console().readLine();
                        System.out.print("줄거리 입력 : ");
                        synopsis = System.console().readLine();
                        System.out.print("방영날짜 입력 (yyyy-mm-dd): ");
                        String releaseDate = System.console().readLine();
                        stmt.executeUpdate("INSERT INTO CONTENTS (CONTENTS_NO, DIRECTOR_NO, CATEGORY_NO, CONTENTS_TITLE, SYNOPSIS, RELEASE_DATE) " +
                            "VALUES (" + contentsNo + ", " + directorNo + ", " + categoryNo + ", '" + contentsTitle + "', '" + synopsis + "', " +
                            "TO_TIMESTAMP('" + releaseDate + "', 'yyyy-mm-dd'))");
                        System.out.println("컨텐츠 추가가 완료되었습니다.");
                        break;
                    case "삭제":
                        System.out.print("삭제할 컨텐츠 번호 입력 : ");
                        contentsNo = Integer.parseInt(System.console().readLine());
                        stmt.executeUpdate("DELETE FROM CONTENTS WHERE CONTENTS_NO = " + contentsNo);
                        System.out.println("컨텐츠 삭제가 완료되었습니다.");
                        break;
                    default:
                        System.out.println("잘못된 작업입니다.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
