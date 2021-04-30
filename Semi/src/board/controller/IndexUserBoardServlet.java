package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

@WebServlet("/board/indexUserBoard")
public class IndexUserBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Board> list = null;

		// 페이지당 표시할 목록을 가져와야함 
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		list = boardService.selectBoardList(1, 10);
		for(int i=0; i<list.size(); i++) {
			Board b = list.get(i);
			//수정한 부분
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = new GregorianCalendar(cal1.get(Calendar.YEAR),cal1.get(Calendar.MONTH),cal1.get(Calendar.DAY_OF_MONTH));
			long now = cal2.getTimeInMillis();
			long regDate = b.getRegDate().getTime();
			b.setCommentCnt(boardService.selectCommentCnt(b.getBoardNo()));
			if(i!=list.size()-1) {
				out.println("<h4>");
				if(now - regDate <= (1000*60*60*24*3)) {
					out.println("<span id='newBoard' style='font-size:10px; color:red;'>[new]</span>");
				}
				//
				out.println("<div class='index_board_width'><div><a href='"+request.getContextPath()+"/board/boardView?no="+b.getBoardNo()+"'>"+b.getTitle()+
						(b.getCommentCnt()!=0 ? " ("+b.getCommentCnt()+")" : "")+
						"</a></div></div>");
				out.println("<span class="+"index_board"+">"+b.getWriter()+" / "+b.getRegDate()+"</span></h4>");
				
				
			}else {
				out.println("<h4>");
				if(now - regDate <= (1000*60*60*24*3)) {
					out.println("<span id='newBoard' style='font-size:10px; color:red;'>[new]</span>");
				}
				out.println("<div class='index_board_width'><div><a href='"+request.getContextPath()+"/board/boardView?no="+b.getBoardNo()+"'>"+b.getTitle()+
						(b.getCommentCnt()!=0 ? " ("+b.getCommentCnt()+")" : "")+
						"</a></div></div>");
				out.print("<span class="+"index_board"+">"+b.getWriter()+" / "+b.getRegDate()+"</span></h4>");
			}
		}
	}
}
