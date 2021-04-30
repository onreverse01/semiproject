package meeting.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meeting.model.service.MeetingService;
import meeting.model.vo.Attachment;
import meeting.model.vo.Meeting;

/**
 * Servlet implementation class test2
 */
@WebServlet("/meeting/meetingView")
public class MeetingViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingService meetingService = new  MeetingService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int meetingNo = Integer.parseInt(request.getParameter("no"));
		System.out.println(meetingNo);
		
		//미팅 하나 불러오고(지역, 카테고리 조인) - attach붙여주고 - 카운트 따로
		Meeting meeting = meetingService.selectOne(meetingNo);
		if(meeting == null) {
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
			return;
		}
		
		Attachment attach = meetingService.selectAttachOne(meetingNo);
		if(attach ==null) {
			attach = new Attachment();
			attach.setRenamedFilename("no_img.png");
		}
		meeting.setAttach(attach);
		
		int particiCnt = meetingService.selectParticiCnt(meetingNo);
		meeting.setCountParticipation(particiCnt);
		
		System.out.println(meeting);
		Calendar cal = Calendar.getInstance();
		cal.setTime(meeting.getTime());
		System.out.println("약속시간 :" + cal);
		
		List<String> list = meetingService.selectParticiList(meetingNo);
		System.out.println(list);
		
		request.setAttribute("meeting", meeting);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/meeting/View.jsp").forward(request, response);
	}

}
