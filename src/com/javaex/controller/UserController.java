package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// 회원가입 폼
		if ("joinForm".equals(action)) {
			System.out.println("joinForm");

			WebUtil.forword(request, response, "/WEB-INF/views/user/joinForm.jsp");
		}

		// 회원가입
		else if ("join".equals(action)) {
			System.out.println("join");

			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo(id, password, name, gender);
			// System.out.println(vo.toString());

			UserDao dao = new UserDao();
			dao.insert(vo);

			WebUtil.forword(request, response, "/WEB-INF/views/user/joinOk.jsp");
		}

		// 로그인 폼
		else if ("loginForm".equals(action)) {
			System.out.println("loginForm");

			WebUtil.forword(request, response, "/WEB-INF/views/user/loginForm.jsp");
		}

		// 로그인
		else if ("login".equals(action)) {
			System.out.println("login");

			String id = request.getParameter("id");
			String password = request.getParameter("password");

			// System.out.println("id : " + id + ", pw : " + password);

			UserDao dao = new UserDao();
			UserVo authVo = dao.getUser(id, password);

			// System.out.println(vo.toString());

			if (authVo == null) { // 로그인 실패
				System.out.println("로그인 실패");
				
				WebUtil.redirect(request, response, "/mysite2/user?action=loginForm&result=fail");
			}
			else { // 로그인 성공
				// 세션 영역에 값 추가하기
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authVo);

				WebUtil.redirect(request, response, "/mysite2/main");
			}
		}
		
		// 로그아웃
		else if ("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser"); // 지정된 이름에 해당하는 객체를 세션에서 제거
			session.invalidate(); // 해당 세션을 없애고 세션에 속해있는 값들을 삭제

			WebUtil.redirect(request, response, "/mysite2/main");
		}
		// 수정 폼
		else if ("modifyForm".equals(action)) {
			System.out.println("modifyForm");
			
			WebUtil.forword(request, response, "/WEB-INF/views/user/modifyForm.jsp");
		}
		
		// 수정
		else if("modify".equals(action)) {
			System.out.println("modify");

			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo vo = new UserVo(id, password, name, gender);
			//System.out.println(vo.toString());
			
			UserDao dao = new UserDao();
			dao.modify(vo);

			UserVo authVo = dao.getUser(id, password);
			
			HttpSession session = request.getSession();
			session.setAttribute("authUser", authVo);

			WebUtil.redirect(request, response, "/mysite2/main");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
