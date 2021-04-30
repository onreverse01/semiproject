package claim.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import claim.model.service.ClaimService;
import claim.model.vo.AnwBoard;
import claim.model.vo.ClaimBoard;

@WebServlet("/claim/answer")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimService claimService = new ClaimService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no;
		if(!(request.getParameter("no")==null))
			no = Integer.parseInt(request.getParameter("no"));
		else
			no = Integer.parseInt(request.getParameter("claimNo"));
		
		ClaimBoard oneClaim = claimService.selectOneClaim(no);
		System.out.println("list@servlet = " + oneClaim);
		//1. claim 입력값 처리
		String anwcontent = request.getParameter("anwcontent");
		String location;
		
		System.out.println("content@servelet = " + anwcontent);
		
		AnwBoard board = new AnwBoard();
		board.setContent(anwcontent);
		
		//2. 업무로직 : DB에 insert
		if(!(anwcontent==null)) {
			int result = claimService.insertAnwBoard(no, board);
			String msg = (result > 0) ? 
					"문의 등록 성공!" :
						"문의 등록 실패!";
			
			location = request.getContextPath();
			location += (result > 0) ?
							"/claim/claimList": 
								"/claim/answer?no="+no;
			//3. DML요청 : 리다이렉트 & 사용자피드백
			// /mvc/board/boardList
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(location);
		}

		//4. http처리
		else {
			
			request.setAttribute("oneClaim", oneClaim);
			
			if (request.getHeader("REFERER") == null) {
				request.getRequestDispatcher("/WEB-INF/views/claim/claimMenu.jsp")
				   .forward(request, response);
			}
			else {
				request.getRequestDispatcher("/WEB-INF/views/claim/answer.jsp")
			       .forward(request, response);
			}
		}
		
		
	}

}
