package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = { 
	"/board/boardCommentDelete", 
	"/board/boardCommentInsert", 
	"/board/boardDelete", 
	"/board/boardEnroll",
	"/board/boardUpdate",
	"/meeting/meetingApply",
	"/meeting/meetingCancel",
	"/meeting/meetingDelete",
	"/meeting/meetingEnroll",
	"/meeting/meetingUpdate",
	"/member/memberDelete",
	"/member/logout",
	"/member/memberUpdate",
	"/member/myBoardFinder",
	"/member/myBoardList",
	"/member/mypage",
	"/member/updatePassword",
	"/claim/claimEnroll",
    "/claim/claimList",
    "/claim/claimView",
    "/claim/answer",
    "/claim/claimMenu"
})
public class LoginFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpRes = (HttpServletResponse)response;
		
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인후 사용할 수 있습니다.");
			httpRes.sendRedirect(httpReq.getContextPath()+"/");
			return;
		}
		chain.doFilter(request, response);
	}

}
