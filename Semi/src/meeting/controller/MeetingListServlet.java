package meeting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import meeting.model.service.MeetingService;
import meeting.model.vo.Attachment;
import meeting.model.vo.Meeting;

@WebServlet("/meeting/meetingList")
public class MeetingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingService meetingService = new MeetingService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값처리
		String location = request.getParameter("location");
		String category = request.getParameter("category");
		String search = request.getParameter("search");
		final int numPerPage = 9;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
		}
		
		if(location==null){
			location="";
		}
		if(category==null) {
			category="";
		}
		if(search==null) {
			search="";
		}
		
		System.out.println("location@servlet = "+location);
		System.out.println("category@servlet = "+category);
		System.out.println("search@servlet = "+search);
		
		//2. 업무로직
		List<Meeting> list = null;
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		list = meetingService.selectMeetingList(start, end, location, category, search);
		
		for(Meeting m : list) {
			Attachment attach = meetingService.selectAttachOne(m.getMeetingNo());
			if(attach != null) {
				m.setAttach(attach);
			}else {
				attach=new Attachment();
				attach.setRenamedFilename("no_img.png");
				m.setAttach(attach);
			}
			System.out.println(m);
		}
		
		int totalContents = meetingService.selectMeetingTotalContent(location, category, search);
		
		String url = request.getRequestURI()+"?category="+category+"&location="+location+"&search="+search;
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		//3. 이동
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.setAttribute("location", location);
		request.setAttribute("category", category);
		request.setAttribute("search", search);
		request.getRequestDispatcher("/WEB-INF/views/meeting/meetingList.jsp").forward(request, response);
	}
}
