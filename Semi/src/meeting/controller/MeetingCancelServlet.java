package meeting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import meeting.model.service.MeetingService;

/**
 * Servlet implementation class MeetingCancelServlet
 */
@WebServlet("/meeting/meetingCancel")
public class MeetingCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingService meetingService = new MeetingService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));


		// 참가자 테이블에 이용자 참여안함으로 변경
		int result = 0;
		result = meetingService.updateUnParticipation(memberId, meetingNo);
		HttpSession session = request.getSession();
		if (result > 0) {
			session.setAttribute("msg", "모임 취소 완료");
		} else {
			session.setAttribute("msg", "모임 취소 실패");
		}

		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

}
