package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class AdminBoardDeleteServlet
 */
@WebServlet("/board/adminBoardDelete")
public class AdminBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.값처리
		int boardNo = Integer.parseInt(request.getParameter("no"));
		System.out.println("BoardNo@servlet = "+boardNo);
		
		//2. 업무로직
		int result = 0;
		result = boardService.deleteAdminBoard(boardNo);
		if(result > 0) {
			System.out.println("게시글이 삭제처리 되었습니다.");
		}else {
			System.out.println("게시글 삭제처리에 실패했습니다.");
		}
		
		//3. 이동
		response.sendRedirect(request.getContextPath()+"/board/adminBoardList");
	}

}
