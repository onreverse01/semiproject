package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.한글 깨짐 방지 인코딩(필터처리)
		
		//2.사용자 입력값 처리
		//String javax.servlet.ServletRequest.getParameter(String arg0)
		String memberId = request.getParameter("memberId");
		
		//3.서비스로직호출
		int result = memberService.deleteMember(memberId);
		
		//4. 받은 결과에 따라 뷰페이지 내보내기
		HttpSession session = request.getSession(false);
		if(result>0) {
			//session.setAttribute("msg", "성공적으로 회원정보를 삭제했습니다.");
			//location으로 logout페이지 지정
			
			if(session != null) {
				Cookie c = new Cookie("saveId", memberId);
				c.setPath(request.getContextPath()); // setpath = 쿠키를 전송할 url
				c.setMaxAge(0);// 0으로 해서 즉시삭제
				response.addCookie(c);
				session.invalidate();
			}
			response.sendRedirect(request.getContextPath()+"/");
		}
		else {
			session.setAttribute("msg", "회원정보삭제에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/member/mypage");			
		}
	}

}
