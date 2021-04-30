package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * enroll servlet
 */
@WebServlet("/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/enroll.jsp")
		   .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("ho_id_input");
		String memberPassword = MvcUtils.getSha512(request.getParameter("ho_password_input1"));
		String memberName = request.getParameter("ho_name_input");
		String memberEmail = request.getParameter("ho_eamil_input");
		String memberPhone = request.getParameter("ho_phone_input");
		String memberEvent1 = request.getParameter("ho_agreement_input5");
		String memberEvent2 = request.getParameter("ho_agreement_input6");
		if(memberEvent1==null ) {
			memberEvent1="F";
		}
		if(memberEvent2==null ) {
			memberEvent2="F";
		}
			
		
		Member member = new Member(memberId, memberPassword, memberName, memberEmail, memberPhone, memberEvent1, memberEvent2, null, MemberService.MEMBER_ROLE);
		System.out.println("입력한 회원정보 : "+member);
		
		//업무로직
		int result = memberService.insertMember(member);
		
		String msg = "";
		if(result>0)
			msg = "성공적으로 회원가입되었습니다.";
		else 
			msg = "회원등록에 실패했습니다.";	
		
		request.getSession().setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath()+"/");
	}

}