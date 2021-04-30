package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리 : memberId, memberRole, event1, event2
		String memberId = request.getParameter("memberId");
		String memberRole = request.getParameter("memberRole");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberRole(memberRole);
		System.out.println("memberId = "+ memberId +" memberRole = "+ memberRole );
		//2. 업무로직
		int result = memberService.updateMemberRole(member);
//		String msg = result > 0 ?
//				"사용자 권한 변경에 성공했습니다." :
//					"사용자 권한 변경에 실패했습니다.";
		String msg = "";
		if(result>0) 
			msg = "사용자 권한 변경에 성공했습니다.";
		else
			msg = "사용자 권한 변경에 실패했습니다.";
		
		
		//3. 리다이렉트 및 사용자 피드백
		request.getSession().setAttribute("msg", msg);
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

}