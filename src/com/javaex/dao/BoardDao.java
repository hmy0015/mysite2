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
import com.javaex.vo.UserVo;

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
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");

				BoardVo vo = new BoardVo(no, title, "", name, hit, reg_date, user_no);
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
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no); // ?(물음표) 중 1번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	// 등록
	public void insert(String title, String content, int uNo) {
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " INSERT INTO board VALUES(seq_board_no.nextval, ?, ?, 0, sysdate, ?) ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, title); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, content); // ?(물음표) 중 2번째, 순서중요
			pstmt.setInt(3, uNo); // ?(물음표) 중 3번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	// 수정
	public void modify(int no, String title, String content) {
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update board ";
			query += " set title = ? , ";
			query += "     content = ? ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, title); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, content); // ?(물음표) 중 2번째, 순서중요
			pstmt.setInt(3, no); // ?(물음표) 중 3번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
	}

	// 검색

	// 조회수 카운트
	public void cnt(int pNo) {
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update board ";
			query += " set hit = hit + 1 ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, pNo); // ?(물음표) 중 1번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
	}

	// 게시글 불러오기
	public BoardVo getPost(int pNo) {
		// 리스트 준비
		BoardVo vo = null;
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
			query += " and b.no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, pNo); // ?(물음표) 중 1번째, 순서중요

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

				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setName(name);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setUser_no(user_no);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return vo;
	}
}
