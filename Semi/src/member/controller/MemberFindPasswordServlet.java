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
 * Servlet implementation class FindPasswordServlet
 */
@WebServlet("/member/findpassword")
public class MemberFindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SendFindEmail sendEmail = new SendFindEmail();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 값받아오기
		String memberId = request.getParameter("id_input");
		String memberEmail = request.getParameter("email_input");
		
		//2. 업무로직
		Member member = new MemberService().findPassword(memberId, memberEmail);
		
		//3. 이메일이 존재하면 이메일로 메일보내기.
		String msg = "";
		if (member != null) {
			//링크보내주기
			member = sendEmail.sendEmailPassword(member);
			msg = "이메일로 비밀번호 변경링크 메일이 발송되었습니다.";
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+"/");


		} else {
			msg = "비밀번호 찾기가 실패하였습니다.";
			request.getSession().setAttribute("msg", msg);
			// 뒤로가기
			String url = request.getHeader("Referer");
			response.sendRedirect(url);
		};
		
		
	}

}