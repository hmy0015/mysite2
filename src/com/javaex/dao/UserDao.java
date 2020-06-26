package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.UserVo;

public class UserDao {
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
	private void close() {
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
	
	public List<UserVo> getUserList() {
		List<UserVo> list = new ArrayList<UserVo>();
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select id, password, no, name, gender ";
			query += " from users ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			rs = pstmt.executeQuery(); // 쿼리문 실행
			
			while(rs.next()) {
				String uid = rs.getString("id");
				String pw = rs.getString("password");
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				
				UserVo vo = new UserVo(no, uid, pw, name, gender);
				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return list;
	}
	
	// 회원 등록
	public int insert(UserVo vo) {
		int count = 0;
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " INSERT INTO users VALUES(seq_user_no.nextval, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, vo.getId()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, vo.getPassword()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, vo.getName()); // ?(물음표) 중 3번째, 순서중요
			pstmt.setString(4, vo.getGender()); // ?(물음표) 중 4번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	// 로그인 한 사용자 정보 가져오기
	public UserVo getUser(String id, String password) {
		UserVo vo = null;
		
		getConnection();
		
		try {			
			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select id, password, no, name, gender ";
			query += " from users ";
			query += " where id = ? ";
			query += " and password = ? ";
			
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, id); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, password); // ?(물음표) 중 2번째, 순서중요
			
			rs = pstmt.executeQuery(); // 쿼리문 실행
			
			// 4. 결과 처리
			while (rs.next()) {
				String uid = rs.getString("id");
				String pw = rs.getString("password");
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				
				vo = new UserVo();
				vo.setId(uid);
				vo.setPassword(pw);
				vo.setNo(no);
				vo.setName(name);
				vo.setGender(gender);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		return vo;
	}
	
	// 로그인 한 사용자 정보 가져오기02
	public UserVo getUser(int no) {
		UserVo vo = null;
		
		getConnection();
		
		try {			
			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select id, password, no, name, gender ";
			query += " from users ";
			query += " where no = ? ";
			
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no); // ?(물음표) 중 1번째, 순서중요
			
			rs = pstmt.executeQuery(); // 쿼리문 실행
			
			// 4. 결과 처리
			while (rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				int uNo = rs.getInt("no");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				
				vo = new UserVo();
				vo.setId(id);
				vo.setPassword(password);
				vo.setNo(uNo);
				vo.setName(name);
				vo.setGender(gender);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		return vo;
	}
	
	// 수정
	public int modify(UserVo vo) {
		int count = 0;
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update users ";
			query += " set password = ? , ";
			query += "     name = ? , ";
			query += "     gender = ? ";
			query += " where id = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, vo.getPassword()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, vo.getName()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, vo.getGender()); // ?(물음표) 중 3번째, 순서중요
			pstmt.setString(4, vo.getId()); // ?(물음표) 중 4번째, 순서중요

			count = pstmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
}
