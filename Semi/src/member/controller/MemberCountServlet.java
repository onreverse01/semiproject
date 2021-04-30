package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dao.MemberDao;
import member.model.service.MemberService;

//맴버와 생성 모임 수를 카운트하는 서블릿

@WebServlet("/member/count")
public class MemberCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();


	//맴버수 카운트
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memberCount = memberService.memberCount();
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(memberCount);
		
		
		
	}

	
	//모임갯수카운트
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int meetingCount = memberService.meetingCount();
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(meetingCount);
	}

}
