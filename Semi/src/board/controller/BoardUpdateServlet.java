package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardComment;

@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 값처리
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int cPage = Integer.parseInt(request.getParameter("cPage"));
		
		System.out.println(boardNo);
		
		//2. 업무로직
		Board board = boardService.selectBoardOne(boardNo);
		request.setAttribute("board", board);
		request.setAttribute("cPage", cPage);
		
		//3. 이동
		request.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 값처리
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = (String)request.getParameter("title");
		String content = (String)request.getParameter("content");
		int cPage = Integer.parseInt(request.getParameter("cPage"));
		
		//2. 업무로직
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setBoardNo(boardNo);
		
		int result = 0;
		result = boardService.updateBoard(board);
		
		if(result > 0) {
			System.out.println("수정이 완료");
		}else {
			System.out.println("수정 실패");
		}
		
		board = boardService.selectBoardOne(boardNo);
		board.setCommentCnt(boardService.selectCommentCnt(boardNo));
		request.setAttribute("board", board);
		
		List<Integer> list = boardService.selectBoardNumList();
		List<BoardComment> commentList = boardService.selectCommentList(board.getBoardNo());
		int prev=0;
		int next=0;
		

		if(list.size()!=0) {
			for(int x : list) {
				if(x==boardNo) {
					int index = list.indexOf(x);
					
					if(index==0 && list.size()!=1) {
						next=list.get(index+1);
						break;
					}
					if(index==list.size()-1) {
						prev=list.get(index-1);
						break;
					}
					prev=list.get(index-1);
					next=list.get(index+1);
					break;
				}
			}
		}
		Board prevBoard = new Board();
		Board nextBoard = new Board();
		if(prev!=0) {
			prevBoard = boardService.selectBoardOne(prev);
		}else {
			prevBoard.setBoardNo(prev);
		}
		
		if(next!=0) {
			nextBoard = boardService.selectBoardOne(next);
		}else {
			nextBoard.setBoardNo(next);
		}
		request.setAttribute("prev", prevBoard);
		request.setAttribute("next", nextBoard);
		request.setAttribute("cPage", cPage);
		request.setAttribute("commentList", commentList);
		
		//3. 이동
		request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp").forward(request, response);
	}

}
