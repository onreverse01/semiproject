package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지 제공
		request.getRequestDispatcher("/WEB-INF/views/member/memberUpdate.jsp")
			   .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.한글 깨짐 방지 인코딩처리 (필터처리)
		
		//2.사용자 입력값 처리
		//String javax.servlet.ServletRequest.getParameter(String arg0)
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String event1 = request.getParameter("event1");
		String event2 = request.getParameter("event2");
		String memberRole = request.getParameter("memberRole");//관리자인 경우에만 memberRole 수정함.
		
		if(event1 == null) {
			event1 = "F";
		}
		if(event2 == null) {
			event2 = "F";
		}
		
		Member member = new Member(memberId, password, name, email, phone, event1, event2, null, memberRole);
		System.out.println("member@servlet = " + member);
		
		//3.업무로직
		int result = memberService.updateMember(member);  

		//4. 사용자피드백 및 리다이렉트 처리
		HttpSession session = request.getSession();
		String msg = "";

		if(result>0){
			msg = "성공적으로 회원정보를 수정했습니다.";
			//세션의 정보도 갱신
			session.setAttribute("loginMember", memberService.selectOne(memberId));
		}
		else 
			msg = "회원정보수정에 실패했습니다.";	
		
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/member/mypage");
	}

}