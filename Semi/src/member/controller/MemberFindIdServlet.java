package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.SendFindEmail;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class FindIdServlet
 */
@WebServlet("/member/findid")
public class MemberFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SendFindEmail sendEmail = new SendFindEmail();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 값받아오기
		String memberName = request.getParameter("name_input");
		String memberEmail = request.getParameter("email_input");

		// 2. 업무로직
		Member member = new MemberService().findId(memberName, memberEmail);

		// 3. 이메일이 존재하면 이메일로 메일보내기.
		String msg = "";
		if (member != null) {
			member = sendEmail.sendEmailId(member);
			msg = "아이디가 이메일로 발송되었습니다.";
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+"/");

		} else {
			msg = "아이디가 찾기가 실패하였습니다.";
			request.getSession().setAttribute("msg", msg);
			// 뒤로가기
			String url = request.getHeader("Referer");
			response.sendRedirect(url);
		};
	}
}