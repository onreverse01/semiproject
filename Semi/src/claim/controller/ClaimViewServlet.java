package claim.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claim.model.service.ClaimService;
import claim.model.vo.AnwBoard;
import claim.model.vo.ClaimBoard;

@WebServlet("/claim/claimView")
public class ClaimViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimService claimService = new ClaimService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));

		ClaimBoard oneClaim = claimService.selectOneClaim(no);
		AnwBoard oneClaimAnw = claimService.selectOneClaimAnw(no);
		System.out.println("list@servlet = " + oneClaim);
		System.out.println("list@servlet = " + oneClaimAnw);
		
		
		request.setAttribute("oneClaim", oneClaim);
		request.setAttribute("oneClaimAnw", oneClaimAnw);
		
//		request.getRequestDispatcher("/WEB-INF/views/claim/claimView.jsp")
//			   .forward(request, response);

		request.getRequestDispatcher("/WEB-INF/views/claim/claimView.jsp")
		   .forward(request, response);
	}

}
