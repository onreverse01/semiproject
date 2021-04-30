package meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.TempFileRenamePolicy;

/**
 * Servlet implementation class TempFileServlet
 */
@WebServlet("/meeting/tempFile")
public class TempFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = getServletContext().getRealPath("/upload");
		
		int maxPostSize = 10* 1024 * 1024;
		
		String encoding = "utf-8";
		
		TempFileRenamePolicy policy = new TempFileRenamePolicy();
		MultipartRequest multipartRequest = new MultipartRequest(
													request, 
													saveDirectory,
													maxPostSize,
													encoding,
													policy);
		String renamedFileName = multipartRequest.getFilesystemName("upFile");
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(renamedFileName);
	}

}
