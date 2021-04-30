package meeting.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import common.MvcFileRenamePolicy;
import meeting.model.service.MeetingService;
import meeting.model.vo.Attachment;
import meeting.model.vo.Meeting;

@WebServlet("/meeting/meetingUpdate")
public class MeetingUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingService meetingService = new MeetingService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		Meeting meeting = meetingService.selectOne(meetingNo);
		Attachment attach = meetingService.selectAttachOne(meetingNo);
		if(attach ==null) {
			attach = new Attachment();
			attach.setRenamedFilename("no_img.png");
		}
		meeting.setAttach(attach);
		
		int particiCnt = meetingService.selectParticiCnt(meetingNo);
		meeting.setCountParticipation(particiCnt);
		System.out.println(meeting);
		request.setAttribute("meeting", meeting);
		request.getRequestDispatcher("/WEB-INF/views/meeting/meetingUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = getServletContext().getRealPath("/upload");
		System.out.println("saveDirectory@servlet = "+saveDirectory);
		
		int maxPostSize = 10* 1024 * 1024;
		
		//인코딩
		String encoding = "utf-8";
		
		//파일명 변경정책 객체
//		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MvcFileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartRequest = new MultipartRequest(
													request, 
													saveDirectory,
													maxPostSize,
													encoding,
													policy);
		
		
		int no = Integer.parseInt(multipartRequest.getParameter("no"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		if(content.equals("") || content.length()==0) {
			content=" "; 
		}
		String category = multipartRequest.getParameter("category");
		String location = multipartRequest.getParameter("location");
		String place = multipartRequest.getParameter("place");
		String t = multipartRequest.getParameter("time");
		int cost = Integer.parseInt(multipartRequest.getParameter("cost"));
		int max = Integer.parseInt(multipartRequest.getParameter("max"));
		String writer = multipartRequest.getParameter("writer");
		
		//업로드한 파일명
		String originalFileName = multipartRequest.getOriginalFileName("upFile");
		String renamedFileName = multipartRequest.getFilesystemName("upFile");
		System.out.println("originalFileName@servlet = "+originalFileName);
		
		//삭제할 첨부파일 번호
		String attachNo = multipartRequest.getParameter("delfile");
		System.out.println("attachNo@servelt = "+attachNo);
		//2021-04-30T16:00
		int year = Integer.parseInt(t.substring(0, 4));
		int month = Integer.parseInt(t.substring(5,7));
		int dayOfMonth = Integer.parseInt(t.substring(8,10));
		int hourOfDay = Integer.parseInt(t.substring(11,13));
		int minute = Integer.parseInt(t.substring(14));
		
		Calendar cal = new GregorianCalendar(year, month-1, dayOfMonth, hourOfDay, minute);
		Date time = new Date(cal.getTimeInMillis());
		
		System.out.println("cal = "+cal);
		System.out.println("time = "+time);
		
		Meeting m =new Meeting(no, title, writer, content, null, place, time, max, cost, category, null, location, null, 0, null);
		if(originalFileName != null) {
			Attachment attach = new Attachment();
			attach.setMeetingNo(no);
			attach.setOriginalFilename(originalFileName);
			attach.setRenamedFilename(renamedFileName);
			m.setAttach(attach);
			System.out.println(m.getAttach());
		}
		System.out.println(m);
		System.out.println(m.getAttach());
		int result =0;
		if(attachNo != null) {
			result = meetingService.deleteAttachment(attachNo);
		}
		
		result = meetingService.updateMeeting(m);
		
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("msg", "모임 내용 수정이 완료되었습니다.");
		}else {
			session.setAttribute("msg", "모임 내용 수정을 실패했습니다.");
		}
		
		response.sendRedirect(request.getContextPath()+"/meeting/meetingView?no="+m.getMeetingNo());
	}

}
