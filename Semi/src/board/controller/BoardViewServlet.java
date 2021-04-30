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

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService = new BoardService();

	//boardList에서 게시글을 클릭했을때 넘어와서 boardView.jsp로 넘어가는 역할
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 값처리
		int boardNo = Integer.parseInt(request.getParameter("no"));
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage=1;
		}
		
		//2. 업무로직
		
		int result=boardService.updateReadCnt(boardNo);
		
		Board board = boardService.selectBoardOne(boardNo);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
