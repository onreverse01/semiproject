package member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class MyBoardListFinderServlet
 */
@WebServlet(name = "MyBoardFinderServlet", urlPatterns = { "/member/myBoardFinder" })
public class MyBoardListFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자입력
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch (NumberFormatException e) {
		}
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param = " + param);
		
		//업무로직
		int end = cPage * numPerPage;
		int start = (cPage-1)*numPerPage+1;
		
		HttpSession session = request.getSession();
		String writer = ((Member)session.getAttribute("loginMember")).getMemberId();
		System.out.println(writer);
		
		List<Board> list = boardService.searchMyBoardList(writer, param, start, end);
		System.out.println(list);
		
		int totalContents = boardService.searchMyBoardListCount(writer, param);
		System.out.println(totalContents);
		
		//pageBar 영역 작업
		String url = request.getRequestURI() + "?searchType=" + searchType + "&searchKeyword=" + searchKeyword ;
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		//jsp 위임
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/member/myBoardList.jsp").forward(request, response);;
		
	}

}
