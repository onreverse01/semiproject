package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.값처리
		
		int boardCommentNo = Integer.parseInt(request.getParameter("no"));
		System.out.println(boardCommentNo);
		
		//2.업무로직
		int result=0;
		result = boardService.deleteBoardComment(boardCommentNo);
		
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("msg", "댓글 삭제 성공");
		}else {
			session.setAttribute("msg", "댓글 삭제 실패");
		}
		
		//이전페이지로 리다이렉트
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

}
