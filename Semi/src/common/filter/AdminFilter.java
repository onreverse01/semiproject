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

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	/**
	 * 관리자가 아닌 부정요청에 대한 처리
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpRes = (HttpServletResponse)response;
		HttpSession session = httpReq.getSession();
		
		Member loginMember = ((Member)session.getAttribute("loginMember"));
		
		if(loginMember == null || !MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())){
			session.setAttribute("msg", "관리자만 사용가능합니다.");
			httpRes.sendRedirect(httpReq.getContextPath()+"/");
			return;
		}
		chain.doFilter(request, response);
	}

}
