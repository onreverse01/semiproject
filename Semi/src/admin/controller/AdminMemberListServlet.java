package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;


@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자입력값
		//현재페이지를 cPage로 처리
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
		
		//업무로직 
		//전체회원 - 회원가입일 내림차순으로 조회
		
		int start = (cPage -1)* numPerPage + 1;
		int end = cPage * numPerPage;
		
		List<Member> list = memberService.selectList(start, end);
		System.out.println("list@servlet"+ list);
		
		int totalContents = memberService.selectMemberCount();
		System.out.println("totalContents@servlet = " + totalContents);
		
		//페이지바 작업
		String url = request.getRequestURI();
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		//jsp에 html응답메세지 작성 위임
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		System.out.println("pageBar"+ pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
			   .forward(request, response);
	}
}
