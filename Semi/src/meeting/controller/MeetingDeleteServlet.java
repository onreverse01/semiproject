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
 * Servlet implementation class MeetingDeleteServlet
 */
@WebServlet("/meeting/meetingDelete")
public class MeetingDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingService meetingService = new MeetingService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));


		// 참가자 테이블에 이용자 참여안함으로 변경
		int result = 0;
		result = meetingService.deleteMeeting(meetingNo);
		HttpSession session = request.getSession();
		if (result > 0) {
			session.setAttribute("msg", "모임 삭제 완료");
		} else {
			session.setAttribute("msg", "모임 삭제 실패");
		}

		response.sendRedirect(request.getContextPath()+"/meeting/meetingList");
	}

}
