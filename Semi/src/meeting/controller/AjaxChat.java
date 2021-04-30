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
import meeting.model.vo.Chat;

/**
 * Servlet implementation class AjaxChat
 */
@WebServlet("/meeting/chat")
public class AjaxChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingService meetingService = new MeetingService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String login = request.getParameter("login");
		List<Chat> list = null;

		// 페이지당 표시할 목록을 가져와야함 
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		list = meetingService.selectChat(no);
		
		for(int i=0; i<list.size(); i++) {
			if(i!=list.size()-1) {
				if(list.get(i).getWriter().equals(login)) {
					out.println("<div class=\"me\">");
					out.println("<p class=\"chatwriter\">"+list.get(i).getWriter()+"</p>");
					out.println("<div class=\"chatMe\">");
					out.println("<span>"+list.get(i).getContent()+"</span></div><div style=\"clear: both\"></div></div></div>");
				}else {
					out.println("<div class=\"notme\">");
					out.println("<p class=\"chatwriter\">"+list.get(i).getWriter()+"</p>");
					out.println("<div class=\"chatNotMe\">");
					out.println("<span>"+list.get(i).getContent()+"</span></div><div style=\"clear: both\"></div></div></div>");
				}
			}else {
				if(list.get(i).getWriter().equals(login)) {
					out.println("<div class=\"me\">");
					out.println("<p class=\"chatwriter\">"+list.get(i).getWriter()+"</p>");
					out.println("<div class=\"chatMe\">");
					out.print("<span>"+list.get(i).getContent()+"</span></div><div style=\"clear: both\"></div></div></div>");
				}else {
					out.println("<div class=\"notme\">");
					out.println("<p class=\"chatwriter\">"+list.get(i).getWriter()+"</p>");
					out.println("<div class=\"chatNotMe\">");
					out.print("<span>"+list.get(i).getContent()+"</span></div><div style=\"clear: both\"></div></div></div>");
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String contents = request.getParameter("contents");
		String writer = request.getParameter("writer");
		
		Chat c = new Chat(writer, contents, no);
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		int result=meetingService.insertChat(c);
		if(result>0) {
			out.print("T");
		}else {
			out.print("F");
		}
	}
	
	

}
