package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;
import com.javaex.vo.UserVo;

@WebServlet("/guest")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// 방명록 폼
		if ("addList".equals(action)) {
			System.out.println("addList");

			GuestDao dao = new GuestDao();
			List<GuestVo> gList = dao.getBookList();
			
			request.setAttribute("guestList", gList);
			
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/addList.jsp");
		}
		// 등록 시
		else if ("add".equals(action)) {
			System.out.println("add");

			request.setCharacterEncoding("UTF-8");

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestVo vo = new GuestVo(name, password, content);
			//System.out.println(vo.toString());
			
			GuestDao dao = new GuestDao();
			dao.bookInsert(vo);

			WebUtil.redirect(request, response, "/mysite2/guest?action=addList");
		}
		// 삭제 폼
		else if ("deleteForm".equals(action)) {
			System.out.println("deleteForm");
			
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");			
		}
		
		// 삭제
		else if ("delete".equals(action)) {
			System.out.println("delete");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			//System.out.println(no + password);

			GuestDao dao = new GuestDao();
			dao.bookDelete(no, password);

			WebUtil.redirect(request, response, "/mysite2/guest?action=addList");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
