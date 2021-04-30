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

@WebServlet("/meeting/meetingEnroll")
public class MeetingEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingService meetingService = new MeetingService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/meeting/meetingEnroll.jsp").forward(request, response);
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
		
		int year = Integer.parseInt(t.substring(0, 4));
		int month = Integer.parseInt(t.substring(5,7));
		int dayOfMonth = Integer.parseInt(t.substring(8,10));
		int hourOfDay = Integer.parseInt(t.substring(11,13));
		int minute = Integer.parseInt(t.substring(14));
		
		Calendar cal = new GregorianCalendar(year, month-1, dayOfMonth, hourOfDay, minute);
		Date time = new Date(cal.getTimeInMillis());
		Meeting m =new Meeting(0, title, writer, content, null, place, time, max, cost, category, null, location, null, 0, null);
		if(originalFileName != null) {
			Attachment attach = new Attachment();
			attach.setOriginalFilename(originalFileName);
			attach.setRenamedFilename(renamedFileName);
			m.setAttach(attach);
			System.out.println(m.getAttach());
		}
		System.out.println(m);
		System.out.println(m.getAttach());
		int result =0;
		
		result = meetingService.insertMeeting(m);
		
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("msg", "모임 등록이 완료되었습니다.");
		}else {
			session.setAttribute("msg", "모임 등록을 실패했습니다.");
		}
		
		response.sendRedirect(request.getContextPath()+"/meeting/meetingList");
	}

}
