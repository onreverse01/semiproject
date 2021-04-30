package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberDeleteServlet
 */
@WebServlet("/admin/memberDelete")
public class AdminMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 입력값
		String memberId = request.getParameter("memberId");
		System.out.println("memberId = "+memberId);
		//2. 서비스 호출
		int result = memberService.deleteMemberAD(memberId);
		
		String msg = "";
		if(result>0) 
			msg = "사용자 강제탈퇴에 성공했습니다.";
		else
			msg = "사용자 강제탈퇴에 실패했습니다.";
		
		
		//3. 페이지 보내기
		request.getSession().setAttribute("msg", msg);
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

}