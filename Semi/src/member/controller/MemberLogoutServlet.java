package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/logout")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션무효화 : 세션에 저장된 속성값을 모두 폐기
		//만약 세션이 존재하지 않으면, 새로 만들지 않고, null을 리턴.
		HttpSession session = request.getSession(false);
		
		if(session != null)
			session.invalidate();
		
		//이전페이지로 리다이렉트
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}
}