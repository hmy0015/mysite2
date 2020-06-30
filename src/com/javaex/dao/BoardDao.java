package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestVo;

public class BoardDao {
	// import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// JDBC 드라이버 (Oracle) 로딩, Connection 얻어오기
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 리스트
	public List<BoardVo> getBoardList() {
		// 리스트 준비
		List<BoardVo> bList = new ArrayList<BoardVo>();
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select  b.no, ";
			query += "         b.title, ";
			query += "         b.content, ";
			query += "         u.name, ";
			query += "         b.hit, ";
			query += "         b.reg_date, ";
			query += "         b.user_no";
			query += " from board b, users u ";
			query += " where b.user_no = u.no ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			rs = pstmt.executeQuery();
		
			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");

				BoardVo vo = new BoardVo(no, title, content, name, hit, reg_date, user_no);
				bList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return bList;
	}

	// 삭제
	public void delPost(int no) {
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " delete from board ";
			query += " where user_no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no); // ?(물음표) 중 1번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	// 등록

	// 수정
	
}
