package claim.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import claim.model.service.ClaimService;
import claim.model.vo.AnwBoard;
import claim.model.vo.ClaimBoard;
import common.MvcUtils;
import member.model.vo.Member;

/**
 * Servlet implementation class claimList
 */
@WebServlet("/claim/claimList")
public class ClaimListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimService claimService = new ClaimService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
				final int numPerPage = 5;
				int cPage = 1;
				HttpSession session = request.getSession();
				String writer = ((Member)session.getAttribute("loginMember")).getMemberId();
				System.out.println(writer+"확인용");
				try {
					cPage = Integer.parseInt(request.getParameter("cPage"));			
				} catch(NumberFormatException e) {
				}
				
				//2. 업무로직
				//a. contents영역 : start ~ end
				//cPage 1, numPerPage 5 -> 1 ~ 5
				//cPage 2, numPerPage 5 -> 6 ~ 10
				int start = (cPage - 1) * numPerPage + 1;
		 		int end = cPage * numPerPage;
				List<ClaimBoard> list = claimService.selectList(start, end);
				List<ClaimBoard> wlist = claimService.selectWriterList(writer, start, end);
				List<AnwBoard> alist = claimService.selectAnwList();
				List<AnwBoard> walist = claimService.selectWriterAnwList(writer);
				
				System.out.println("list@servlet = " + list);
				System.out.println("alist@servlet = " + alist);
				System.out.println("wlist@servlet = " + wlist);
				System.out.println("walist@servlet = " + walist);
				//b. pageBar영역 
				int totalContents = claimService.selectBoardCount();
				String url = request.getRequestURI();
				String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
				
				
				//3. 응답 html처리 jsp에 위임.
				
				request.setAttribute("list", list);
				request.setAttribute("alist", alist);
				request.setAttribute("wlist", wlist);
				request.setAttribute("walist", walist);
				request.setAttribute("pageBar", pageBar);
				
				request.getRequestDispatcher("/WEB-INF/views/claim/claimList.jsp")
				   .forward(request, response);
	}

}
