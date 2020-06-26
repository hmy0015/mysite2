package com.javaex.dao;

import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.UserVo;

public class DaoTest {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		
		//UserVo vo = new UserVo("hi06", "1234", "황민영", "female");
		//dao.insert(vo);
		
		List<UserVo> list = new ArrayList<UserVo>();
		list = dao.getUserList();
		
		for(UserVo uvo : list) {
			System.out.println("No : " + uvo.getNo() + ", id : " + uvo.getId() + ", password : " + uvo.getPassword()
								+ ", name : " + uvo.getName() + ", gender : " + uvo.getGender());
		}
	}
}
