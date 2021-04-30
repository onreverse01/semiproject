package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * loginservlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자 입력값 받기
		String memberId = request.getParameter("id_input");
		String memberPassword = MvcUtils.getSha512(request.getParameter("password_input"));
		String saveId = request.getParameter("saveid");
		
		//2. meberId로 회원객체 조회
		Member member = memberService.selectOne(memberId);
		System.out.println("member@servlet = " + member);
		
		//2-1. 로그인맴버 설정
		HttpSession session = request.getSession(true);
		if(member != null && memberPassword.equals(member.getPassword())) {
			session.setAttribute("loginMember", member);
			Cookie c = new Cookie("saveId", memberId);
			c.setPath(request.getContextPath()+"/"); //path 쿠키를 전송할 url
			
			if(saveId != null) {
				c.setMaxAge(60 * 60 * 24 * 7); //7일짜리 영속쿠키로 지정 
			}
			else {
				//saveId 체크해제시
				c.setMaxAge(0); //0으로 지정해서 즉시 삭제, 음수로 지정하면 session종료시 제거 
			}
			response.addCookie(c);
		}
		else {
			//로그인 실패
			session.setAttribute("msg", "로그인에 실패했습니다.");
		}
		
		//이전페이지로 리다이렉트
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
		
	}

}