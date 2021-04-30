package claim.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import claim.model.service.ClaimService;
import claim.model.vo.ClaimBoard;

/**
 * Servlet implementation class claimErollServlet
 */
@WebServlet("/claim/claimEnroll")
public class ClaimErollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimService claimService = new ClaimService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/claim/claimEnroll.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 버튼 눌렀을때 처리 되는거
		// 1. claim 입력값 처리
		String choice = request.getParameter("choice");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		String location;

		System.out.println("choice@servelet = " + choice);
		System.out.println("title@servelet = " + title);
		System.out.println("content@servelet = " + content);
		System.out.println("writer@servelet = " + writer);

		ClaimBoard board = new ClaimBoard();
		board.setChoice(choice);
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);

		// 2. 업무로직 : DB에 insert

		int result = claimService.insertBoard(board);
		String msg = (result > 0) ? "문의 등록 성공!" : "문의 등록 실패!";

		location = request.getContextPath();
		location += (result > 0) ? "/claim/claimList?writer=" + writer : "/claim/Enroll";
		// 3. DML요청 : 리다이렉트 & 사용자피드백
		// /mvc/board/boardList
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		response.sendRedirect(location);
	}
}
