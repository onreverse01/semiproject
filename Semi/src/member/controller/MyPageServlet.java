package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import meeting.model.vo.Attachment;
import meeting.model.vo.Meeting;
import member.model.service.MemberService;
import member.model.vo.Member;


@WebServlet("/member/mypage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Meeting> list = null;
		//인덱스 페이지에는 최근 생성된 4개를 보여줄거임
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		list = memberService.selectMylist(member.getMemberId());
		System.out.println("mypage@servlet = " + list);
		
		for(Meeting m : list) {
			Attachment attach=memberService.selectMyAttach(m.getMeetingNo());
			if(attach!=null) {
				m.setAttach(attach);
			}else {
				attach=new Attachment();
				attach.setRenamedFilename("no_img.png");
				m.setAttach(attach);
			}
		}
		
		for(Meeting m : list) {
			System.out.println(m);
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp")
		   .forward(request, response);
	}

}