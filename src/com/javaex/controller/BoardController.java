package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		// 게시판
		if("board".equals(action)) {
			System.out.println("board");
			
			BoardDao boardDao = new BoardDao();
			List<BoardVo> boardList = boardDao.getBoardList();
			request.setAttribute("bList", boardList);

			/*
			UserDao userDao = new UserDao();
			UserVo authVo = userDao.getUser(id, password);
			
			HttpSession session = request.getSession();
			session.setAttribute("authUser", authVo);
			*/
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/list.jsp");
		}
				
		else if("delete".equals(action)) {
			System.out.println("delete");

			BoardDao boardDao = new BoardDao();
			int no = Integer.parseInt(request.getParameter("no"));
			boardDao.delPost(no);

			WebUtil.redirect(request, response, "/mysite2/board?action=board");
			
			System.out.println("delete 완료");
		}
		
		else if("read".equals(action)) {
			System.out.println("read");

			WebUtil.forword(request, response, "/WEB-INF/views/board/read.jsp");
		}
		
		else if("modifyForm".equals(action)) {
			System.out.println("modifyForm");

			WebUtil.forword(request, response, "/WEB-INF/views/board/modifyForm.jsp");
		}
		
		else if("writeForm".equals(action)) {
			System.out.println("writeForm");

			WebUtil.forword(request, response, "/WEB-INF/views/board/writeForm.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
