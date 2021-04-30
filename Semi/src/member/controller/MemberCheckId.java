package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberCheckId
 */
@WebServlet("/member/checkid")
public class MemberCheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자입력값 처리
		String memberId = request.getParameter("memberid");
		System.out.println("memberid@servlet.doPost = " + memberId);
		
		
		//2.업무로직
		Member member = new MemberService().selectOne(memberId);
		
		
		//3.응답처리 : 출력스트림을 이용해서 응답메세지 직접 작성
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//중복 아이디가 있으면
		if(member != null) {
			out.print("["+ memberId +"]은 이미 존재하는 아이디입니다. 다시 입력해주세요");
		}
		else {
			out.print("사용가능한 아이디입니다.");
		};
	}

}
