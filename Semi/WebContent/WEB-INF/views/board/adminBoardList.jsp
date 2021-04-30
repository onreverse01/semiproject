<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/header.jsp"%>
<% 
	List<Board> list = (List<Board>)request.getAttribute("list");  
	String type = request.getParameter("searchType");
	String kw = request.getParameter("searchKeyword");
	int cPage = (int)request.getAttribute("cPage");
%>

<!-- css링크 -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/board.css" />
<style>
div#search-boardTitle{
	display: <%= type == null || "boardTitle".equals(type) ? "inline-block" : "none" %>;
}
div#search-boardWriter {
	display: <%= "boardWriter".equals(type) ? "inline-block" : "none" %>;
}
</style>
	<h2>공지사항</h2>
<div id="boardListWrapper">
	<%if(loginMember!=null && loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE)){ %>
	<input type="button" value="글쓰기" id="btn-add"
		onclick="location.href='<%= request.getContextPath() %>/board/adminBoardEnroll?writer=<%= loginMember.getMemberId() %>'" />
	<% } %>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%if(list == null || list.isEmpty()){%>
		<tr>
			<td colspan="6" style="text-align: center; height: 441px; font-size: 25px;">조회된 게시글이 없습니다.</td>
		</tr>
		<%}else{ 
			for(Board b:list){
			%>
		<tr>
			<td><%= b.getBoardNo()%></td>
			<td>
				<% 
					//new 구현
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = new GregorianCalendar(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH));
					long now = cal2.getTimeInMillis();
					long regDate = b.getRegDate().getTime();
					if(now - regDate <= (1000*60*60*24*3)) {%>
					<span id="newBoard">[new]</span>
				<%} %>
				<a
				href="<%= request.getContextPath()%>/board/adminBoardView?no=<%=b.getBoardNo()%>&cPage=<%=cPage %>">
					<%= b.getTitle() %>
			</a></td>
			<td><%= b.getWriter() %></td>
			<td><%= b.getRegDate() %></td>
			<td><%=b.getReadCnt() %></td>
		</tr>
		<%}} %>
	</table>
	<br />
	<!-- 검색 -->
	<div id="search-container">
		검색타입 : 
			<select id="searchType">
				<option value="boardTitle" <%= "boardTitle".equals(type) ? "selected" : "" %>>제목</option>
				<option value="boardWriter" <%= "boardWriter".equals(type) ? "selected" : "" %>>작성자</option>
			</select>
			<div id="search-boardTitle">
				<form action="<%=request.getContextPath() %>/board/adminBoardFinder">
					<input type="hidden" name="searchType" value="boardTitle">
					<input type="text" class="searchKeyword" name="searchKeyword" placeholder="검색할 제목 입력" value="<%= "boardTitle".equals(type) ? kw : "" %>">
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-boardWriter">
				<form action="<%=request.getContextPath() %>/board/adminBoardFinder">
					<input type="hidden" name="searchType" value="boardWriter">
					<input type="text" class="searchKeyword" name="searchKeyword" placeholder="검색할 작성자 입력" value="<%= "boardWriter".equals(type) ? kw : "" %>">
					<button type="submit">검색</button>
				</form>
			</div>
	</div>
</div>
<div id='pageBar'>
	<%= request.getAttribute("pageBar") != null ? request.getAttribute("pageBar") : ""%>
</div>

<script>
$(document).ready(function(){
	$("#searchType").change(function(){
		var result = $("#searchType option:selected").val();
		if(result == 'boardWriter'){
			$("#search-boardWriter").css("display", "inline-block");
			$("#search-boardTitle").css("display", "none");
		}
		else if(result == 'boardTitle'){
			$("#search-boardWriter").css("display", "none");
			$("#search-boardTitle").css("display", "inline-block");
		}
	});
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>