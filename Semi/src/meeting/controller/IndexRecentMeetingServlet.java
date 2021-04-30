package meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meeting.model.service.MeetingService;
import meeting.model.vo.Meeting;

@WebServlet("/meeting/indexRecentMeeting")
public class IndexRecentMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingService meetingService = new MeetingService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Meeting> list = null;
		//인덱스 페이지에는 최근 생성된 10개를 보여줄거임
		list = meetingService.selectIndexRecentlist();
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(list.size()==0) {
			out.print("");
			return;
		}

		for(Meeting m : list) {
			m.setAttach(meetingService.selectAttachOne(m.getMeetingNo()));
			
			out.println("<div class='boxContents'>");
			out.println("<a href='"+request.getContextPath()+"/meeting/meetingView?no="+m.getMeetingNo()+"'>");
			if(m.getAttach()!=null) {
				out.println("<img src=\""+request.getContextPath()+"/upload/"+m.getAttach().getRenamedFilename()+"\" width=220px height=150px/>");
			}else {
				out.println("<img src=\""+request.getContextPath()+"/upload/no_img.png\" width=220px height=150px/>");
			}
			out.println("<span>"+m.getTitle()+"</span></a></div>");
		}
		out.println("<script>");
		out.println("var move3 =1");
		out.println("function right3(){");
		out.println("if(move3 >= 1 && move3 <= "+(list.size()-5)+"){");
		out.println("$(\"#recent\").attr(\"style\",\"transform:translateX(\"+(-240)*(move3++)+\"px);\")}}");
		out.println("function left3(){");
		out.println("if(move3 >= 2 && move3 <= "+(list.size()-4)+"){");
		out.println("$(\"#recent\").attr(\"style\",\"transform:translateX(\"+(-240)*(--move3-1)+\"px);\")");
		out.println("}}</script>");
	}
}
