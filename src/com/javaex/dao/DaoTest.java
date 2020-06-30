package com.javaex.dao;

import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class DaoTest {
	public static void main(String[] args) {
		/*
		UserDao dao = new UserDao();
		
		//UserVo vo = new UserVo("hi06", "1234", "황민영", "female");
		//dao.insert(vo);
		
		List<UserVo> list = new ArrayList<UserVo>();
		list = dao.getUserList();
		
		for(UserVo uvo : list) {
			System.out.println("No : " + uvo.getNo() + ", id : " + uvo.getId() + ", password : " + uvo.getPassword()
								+ ", name : " + uvo.getName() + ", gender : " + uvo.getGender());
		}
		*/

		BoardDao dao = new BoardDao();
		
		int no = 1;
		dao.delPost(no);

		List<BoardVo> list =  new ArrayList<BoardVo>();
		list = dao.getBoardList();
	
		for(BoardVo vo : list) {
			System.out.println("No : " + vo.getNo() + ", title : " + vo.getTitle() + ", content : " + vo.getContent() + ", name : " + vo.getName()
								+ ", hit : " + vo.getHit() + ", reg_date : " + vo.getReg_date() + ", user_no : " + vo.getUser_no());
		}
		
	}
}
