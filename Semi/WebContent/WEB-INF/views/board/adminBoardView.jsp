<%@page import="board.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<% 
	Board b = (Board)request.getAttribute("board");
	Board prev = (Board)request.getAttribute("prev");
	Board next = (Board)request.getAttribute("next");
	int cPage = (int)request.getAttribute("cPage");
	boolean editable = loginMember != null && (loginMember.getMemberId().equals(b.getWriter()) || loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE));
%>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/board.css" />
	
	<h2>공지사항</h2>
	<hr />
	<!-- 게시글 번호, 제목 -->
	<div id="boardViewDesc">
		<div><h4><%=b.getBoardNo() %>. <%=b.getTitle() %></h4></div>
		<hr />
		<div><span id="boardViewWriter"><%=b.getWriter() %></span> <br> <span id="boardViewRegDate"><%=b.getRegDate() %></span></div>
	</div>
	<!-- 게시글 내용 -->
	<div id="boardViewContent">
		<%=b.getContent() %>
		<br>
		<% if(editable){ %>
		<div>
			<input type="button" value="수정" id="boardViewBtnUpdate" onclick="location.href='<%=request.getContextPath()%>/board/adminBoardUpdate?boardNo=<%=b.getBoardNo()%>&cPage=<%=cPage%>'"/>
			<input type="button" value="삭제" id="boardViewBtnDelete" onclick="deleteBoard();"/>
		</div>
		<% } %>
		<form action="<%= request.getContextPath()%>/board/adminBoardDelete" id = "boardDelFrm" method="post">
			<input type="hidden" name="no" value="<%= b.getBoardNo()%>"/>
		</form>
	</div>
	
	<div id="boardViewNav">
		<% if(prev.getBoardNo()!=0){ %>
		<div>
			<a href="<%= request.getContextPath()%>/board/adminBoardView?no=<%=prev.getBoardNo()%>&cPage=<%=cPage%>">[이전 게시글] <strong><%=prev.getTitle() %></strong></a>
		</div>
		<% } %>
		<% if(next.getBoardNo()!=0){ %>
		<div>
			<a href="<%= request.getContextPath()%>/board/adminBoardView?no=<%=next.getBoardNo()%>&cPage=<%=cPage%>">[다음 게시글] <strong><%=next.getTitle() %></strong>	x</a>
		</div>
		<% } %>
	</div>
	<img id="boardViewImg" src="<%=request.getContextPath() %>/images/List_icon.png" onclick="location.href='<%=request.getContextPath() %>/board/adminBoardList?cPage=<%=cPage %>'" />
	
	<script>
	//게시물 삭제
	<% if(editable){%>
		function deleteBoard(){
			if(confirm("정말 게시글을 삭제 하시겠습니까?")){
				$("#boardDelFrm").submit();
			}
		};
	<% } %>
	</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>