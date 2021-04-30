package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentInsert
 */
@WebServlet("/board/boardCommentInsert")
public class BoardCommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		boardNo, writer, commentLevel, commentRef, content
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String writer = request.getParameter("writer");
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		String content = request.getParameter("content");
		if(content==null) {
			content=" ";
		}
		
		BoardComment bc = new BoardComment(0, commentLevel, writer, content, boardNo, commentRef, null);
		
		int result = 0;
		
		result = boardService.insertComment(bc);
		
		HttpSession session = request.getSession();
		if(result > 0) {
			session.setAttribute("msg", "댓글 작성 완료");
		}else {
			session.setAttribute("msg", "댓글 작성 실패");
		}
		
		//이전페이지로 리다이렉트
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
		
	}

}
