package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberEventUpdateServlet
 */
@WebServlet("/admin/memberEventUpdate")
public class AdminMemberEventUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String event1 = request.getParameter("event1");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setEvent1(event1);
		System.out.println("memberId "+ memberId +" event1 " + event1 );
		
		int result = memberService.updateMemberEvent(member);
		
		String msg = "";
		if(result>0)
			msg="사용자 이벤트 변경에 성공했습니다.";
		else
			msg="사용자 이벤트 변경에 실패했습니다.";
		
		request.getSession().setAttribute("msg", msg);
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

}