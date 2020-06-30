package com.javaex.dao;

import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class DaoTest {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		BoardDao boardDao = new BoardDao();
		List<BoardVo> list = new ArrayList<BoardVo>();

		/*
		 * //UserVo vo = new UserVo("hi06", "1234", "황민영", "female"); //dao.insert(vo);
		 * 
		 * List<UserVo> list = new ArrayList<UserVo>(); list = dao.getUserList();
		 * 
		 * for(UserVo uvo : list) { System.out.println("No : " + uvo.getNo() + ", id : "
		 * + uvo.getId() + ", password : " + uvo.getPassword() + ", name : " +
		 * uvo.getName() + ", gender : " + uvo.getGender()); }
		 * 
		 * // 게시글 삭제
		 * 
		 * int no = 1; boardDao.delPost(no);
		 * 
		 * // 게시글 리스트 가져오기 List<BoardVo> list = new ArrayList<BoardVo>(); list =
		 * boardDao.getBoardList();
		 * 
		 * for(BoardVo vo : list) { System.out.println("No : " + vo.getNo() +
		 * ", title : " + vo.getTitle() + ", name : " + vo.getName() + ", hit : " +
		 * vo.getHit() + ", reg_date : " + vo.getReg_date() + ", user_no : " +
		 * vo.getUser_no()); }
		 * 
		 * // 게시글 정보 가져오기 BoardDao boardDao = new BoardDao();
		 * 
		 * BoardVo vo = boardDao.getPost(2);
		 * 
		 * System.out.println(vo.getNo());
		 * 

		// 게시글 업데이트
		boardDao.modify(1, "수정 - 제목 01", "수정 - 내용 01");


		// 게시글 등록
		boardDao.insert("등록 - 제목", "등록 - 내용", 1);
		
		 */
		
		// 게시글 리스트 가져오기
		list = boardDao.getBoardList(null);

		for (BoardVo vo : list) {
			System.out.println("No : " + vo.getNo() + ", title : " + vo.getTitle() + ", name : " + vo.getName()
					+ ", hit : " + vo.getHit() + ", reg_date : " + vo.getReg_date() + ", user_no : " + vo.getUser_no());
		}
		
		System.out.println("============================================================================================");
		
		// 검색
		list = boardDao.getBoardList("제목01");

		for (BoardVo vo : list) {
			System.out.println("No : " + vo.getNo() + ", title : " + vo.getTitle() + ", name : " + vo.getName()
					+ ", hit : " + vo.getHit() + ", reg_date : " + vo.getReg_date() + ", user_no : " + vo.getUser_no());
		}
	}
}
