package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Board;
import common.MvcUtils;
import member.model.vo.Member;

/**
 * Servlet implementation class MyBoardListServlet
 */
@WebServlet("/member/myBoardList")
public class MyBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
		}
		
		// 2. 업무로직
		int start = (cPage-1) * numPerPage +1;
		int end = cPage * numPerPage;
		
		List<Board> list = null;
		
		HttpSession session = request.getSession();
		String writer = ((Member) session.getAttribute("loginMember")).getMemberId();
		System.out.println(writer);
		
		list = boardService.selectMyBoardList(writer, start, end);
		System.out.println("list "+ list);
		for(Board b : list) {
			b.setCommentCnt(boardService.selectCommentCnt(b.getBoardNo()));
		}
		
		int totalContents = boardService.selectMyBoardTotal(writer);
		System.out.println("토탈컨텐츠 : " + totalContents);
		String url = request.getRequestURI();
		System.out.println(url);
		
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/member/myBoardList.jsp").forward(request, response);
	}

}
