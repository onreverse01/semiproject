package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoggerFilter
 */
@WebFilter({ "/LoggerFilter", "/*" })
public class LoggerFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. servlet 호출전
		System.out.println("\n===========================================");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String uri = httpReq.getRequestURI();
		System.out.println(uri);
		chain.doFilter(request, response);
		System.out.println("===========================================");
	}
}
