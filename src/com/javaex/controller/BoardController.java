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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// 게시판
		if ("board".equals(action)) {
			System.out.println("board");

			BoardDao boardDao = new BoardDao();
			List<BoardVo> boardList = boardDao.getBoardList();
			request.setAttribute("bList", boardList);

			WebUtil.forword(request, response, "/WEB-INF/views/board/list.jsp");
		}

		// 삭제
		else if ("delete".equals(action)) {
			System.out.println("delete");

			BoardDao boardDao = new BoardDao();
			int no = Integer.parseInt(request.getParameter("no"));
			boardDao.delPost(no);

			WebUtil.redirect(request, response, "/mysite2/board?action=board");

			System.out.println("delete 완료");
		}

		// 게시글 보기
		else if ("read".equals(action)) {
			System.out.println("read");

			int no = Integer.parseInt(request.getParameter("no"));

			BoardDao boardDao = new BoardDao();
			BoardVo vo = boardDao.getPost(no);

			request.setAttribute("vo", vo);

			WebUtil.forword(request, response, "/WEB-INF/views/board/read.jsp");
		}

		// 수정 폼
		else if ("modifyForm".equals(action)) {
			System.out.println("modifyForm");

			int no = Integer.parseInt(request.getParameter("no"));

			BoardDao boardDao = new BoardDao();
			BoardVo vo = boardDao.getPost(no);

			request.setAttribute("vo", vo);

			WebUtil.forword(request, response, "/WEB-INF/views/board/modifyForm.jsp");
		}

		// 수정
		else if ("modify".equals(action)) {
			System.out.println("modify");

			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardDao boardDao = new BoardDao();
			boardDao.modify(no, title, content);

			WebUtil.redirect(request, response, "/mysite2/board?action=board");
		}

		// 등록폼
		else if ("writeForm".equals(action)) {
			System.out.println("writeForm");
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/writeForm.jsp");
		}

		// 등록
		else if ("write".equals(action)) {
			System.out.println("write");

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int uNo = Integer.parseInt(request.getParameter("uNo"));

			BoardDao boardDao = new BoardDao();
			boardDao.insert(title, content, uNo);
			
			WebUtil.redirect(request, response, "/mysite2/board?action=board");
		}

		// 조회수 카운트
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
