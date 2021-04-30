package admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import common.MvcUtils;

/**
 * Servlet implementation class AdminBoardFinderSerlvet
 */
@WebServlet("/board/adminBoardFinder")
public class AdminBoardFinderSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
		}
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param@servlet = " + param);
		
		//업무로직
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		
		List<Board> list = null;
		
		list = boardService.searchAdminBoardList(param, start, end);
		System.out.println("list = " + list);
		
		int totalContents = boardService.searchAdminBoardListCount(param);
		System.out.println("totalContents = " + totalContents);
		
		//pageBar영역 작업
		String url = request.getRequestURI() + "?searchType=" + searchType + "&searchKeyword" + searchKeyword;
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		//jsp에 위임하기
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/adminBoardList.jsp").forward(request, response);
		
	}

}
