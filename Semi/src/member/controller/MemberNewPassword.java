package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberNewPassword
 */
@WebServlet("/member/newpassword")
public class MemberNewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get방식으로받은 randomString이랑 DB의 randomString이랑 배교해서 같으면 newpassword.jsp로 이동
		
		String id = request.getParameter("id");
		String randomString = request.getParameter("randomString");
		
		
		String certification = null;
		certification = memberService.selectCertification(id);
		System.out.println("randomString@servlet = "+randomString);
		System.out.println("certification@servlet = "+certification);
		
		if(!randomString.equals(certification)) {
			HttpSession session = request.getSession();
			session.setAttribute("msg", "유효하지 않은 링크입니다.메인화면으로 돌아갑니다.");
			response.sendRedirect(request.getContextPath());
		}else {
			RequestDispatcher reqDispatcher = 
			request.getRequestDispatcher("/WEB-INF/views/member/newpassword.jsp");
			reqDispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//고객정보 전달용
		String memberId = request.getParameter("hidden_id");
		String email = request.getParameter("hidden_email");
		
		//새로운비밀번호
		String newpassword = MvcUtils.getSha512(request.getParameter("new_password_input1"));
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPassword(newpassword);
		
		//2.업무로직 
		// id와 email값 대조해서 비밀번호 변경해주기
		
		int result = memberService.updatePassword(member);
		
		//4. 사용자피드백 및 리다이렉트 처리
		HttpSession session = request.getSession();
		String msg = "";

		if(result>0){
			msg = "성공적으로 비밀번호를 변경했습니다.";
			//세션의 정보도 갱신
			session.setAttribute("loginMember", memberService.selectOne(memberId));
			//인증코드 삭제
			result=memberService.deleteCertification(memberId);
		}
		else 
			msg = "비밀번호 변경에 실패했습니다.";	
		
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/");
		
	}

}
