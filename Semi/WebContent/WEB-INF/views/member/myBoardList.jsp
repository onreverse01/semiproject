<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%-- css --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage.css" />
<%-- 멤버 임의값 --%>
	<aside class="aside2"> <!-- //enroll-container -->
	<h2 class="Mp">My page</h2>
	<%-- 좌측 이동페이지 바 --%>
	<div class="info">
		<br />
		<a class="a" href="<%=request.getContextPath()%>/member/mypage">내 정보</a> 
		<br />
		<br />
		<a class="a" href="<%=request.getContextPath()%>/member/memberUpdate">개인정보 수정</a> 
		<br />
		<br /> 
		<a class="a" href="<%=request.getContextPath()%>/member/myBoardList">내가 쓴글</a> 
		<br />
		<%-- 
		<a class="a" href="<%=request.getContextPath()%>/member/meeting">신청한	모임</a>
		--%>
	</div>
	</aside>
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
</style>
<div class="mypageWrapper3">
	<h3 class="me2">내가 쓴 글</h3>
<div id="boardListWrapper">
	<%if(loginMember != null){ %>
	<input type="button" value="글쓰기" id="btn-add" style="margin-right:0;"
		onclick="location.href='<%= request.getContextPath() %>/board/boardEnroll?writer=<%= loginMember.getMemberId() %>'" />
	<% } %>
	<table id="tbl-board" style="width:800px; margin-left:0px;">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%if(list == null || list.isEmpty()){%>
		<tr>
			<td colspan="6" style="text-align: center; width:1200px">조회된 게시글이 없습니다.</td>
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
				href="<%= request.getContextPath()%>/board/boardView?no=<%=b.getBoardNo()%>&cPage=<%=cPage %>">
					<%= b.getTitle() %> <%= b.getCommentCnt() != 0 ? " ("+b.getCommentCnt()+")":"" %>
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
			</select>
			<div id="search-boardTitle">
				<form action="<%=request.getContextPath() %>/member/myBoardFinder">
					<input type="hidden" name="searchType" value="boardTitle">
					<input type="text" name="searchKeyword" placeholder="검색할 제목 입력" value="<%= "boardTitle".equals(type) ? kw : "" %>" />
					<button type="submit">검색</button>
				</form>
			</div>
		</div>
	<div id='pageBar'>
		<%= request.getAttribute("pageBar") != null ? request.getAttribute("pageBar") : ""%>
	</div>
	</div>
</div>
<script>

$(document).ready(function(){
	$("#searchType").change(function(){
		var result = $("#searchType option:selected").val();
		if(result == 'boardTitle'){
			$("#search-boardTitle").css("display", "inline-block");
		}
	});
});

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>