package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService = new BoardService();

	//boardList.jsp에서 글등록 버튼 눌렀을때 boardEnroll.jsp로 연결
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer=request.getParameter("writer");
		request.setAttribute("writer", writer);
		request.getRequestDispatcher("/WEB-INF/views/board/boardEnroll.jsp").forward(request, response);
	}

	//boardEnroll.jsp에서 글 게시버튼 눌렀을때 등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 값처리
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		System.out.println("title@servlet : "+title);
		System.out.println("content@servlet : "+content);
		System.out.println("writer@servlet : "+writer);
		
		//2. 업무로직
		Board b = new Board();
		b.setTitle(title);
		b.setWriter(writer);
		b.setContent(content);
		System.out.println(b);
		
		int result = 0;
		result=boardService.insertBoard(b);
		if(result > 0) {
			System.out.println("게시글 등록 성공");
		}else {
			System.out.println("게시글 등록 실패");
		}
		
		//3. 이동
		response.sendRedirect(request.getContextPath()+"/board/boardList");
		
		
	}

}
