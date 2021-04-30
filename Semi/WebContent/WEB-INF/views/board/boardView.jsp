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
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	boolean editable = loginMember != null && (loginMember.getMemberId().equals(b.getWriter()) || loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE));
%>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/board.css" />
	
	<h2>자유게시판</h2>
	<br />
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
			<input type="button" value="수정" id="boardViewBtnUpdate" onclick="location.href='<%=request.getContextPath()%>/board/boardUpdate?boardNo=<%=b.getBoardNo()%>&cPage=<%=cPage%>'"/>
			<input type="button" value="삭제" id="boardViewBtnDelete" onclick="deleteBoard();"/>
		</div>
		<% } %>
		<form action="<%= request.getContextPath()%>/board/boardDelete" id = "boardDelFrm" method="post">
			<input type="hidden" name="no" value="<%= b.getBoardNo()%>"/>
		</form>
	</div>
	<!-- 게시글 댓글부분 -->
	<div id="boardViewRe">
		<hr />
		<!-- 새로운 댓글 작성부분 -->
		<div class="comment-editor"> 
            <form action="<%=request.getContextPath()%>/board/boardCommentInsert" method="post" name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%= b.getBoardNo() %>" />
                <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
				<textarea name="content" cols="60" rows="3"></textarea>
                <button type="submit" id="btn-insert">등록</button>
            </form>
        </div>
        <hr />
		<% if(!commentList.isEmpty() && commentList!=null){ %>
		<table id="tbl-comment">
			<% for(BoardComment bc : commentList){ 
				boolean removable = loginMember != null && (loginMember.getMemberId().equals(bc.getWriter()) || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()));
			   		if(bc.getCommentLevel() ==1){
			%>  	<tr class="level1">
						<td>
							<div class="comment-level1">
								<sub class="comment-writer"><%= bc.getWriter()!=null ? bc.getWriter() : "탈퇴회원" %></sub>
								<div class="comment-content"><%= bc.getContent() %></div>
								<sub class="comment-date"><%= bc.getRegDate() %></sub>
							</div>
						</td>
						<td>
							<button class="btn-reply" value="<%= bc.getCommentNo() %>">답글</button>
							<% if(removable){ %>
							<button class="btn-delete" value="<%= bc.getCommentNo() %>">삭제</button>
							<%} %>
						</td>
					</tr>
			   		<%}else { %>
			   			<tr class="level2">
						<td>
							<div class="comment-level2">
								<sub class="comment-writer"><%= bc.getWriter()!=null ? bc.getWriter() : "탈퇴회원" %></sub>
								<div class="comment-content"><%= bc.getContent() %></div>
								<sub class="comment-date"><%= bc.getRegDate() %></sub>
							</div>
						</td>
						<% if(removable){ %>
						<td>
							<button class="btn-delete" value="<%= bc.getCommentNo() %>">삭제</button>
						</td>
						<% } %>
					</tr>
			<% 		}
			  } %>
		</table>
		<% } %>
	</div>
	<form action="<%= request.getContextPath() %>/board/boardCommentDelete" name="boardCommentDelFrm" method="post">
		<input type="hidden" name="no" />
		<input type="hidden" name="boardNo" value="<%= b.getBoardNo() %>"/>
	</form>
	
	<div id="boardViewNav">
		<% if(prev.getBoardNo()!=0){ %>
		<div>
			<a href="<%= request.getContextPath()%>/board/boardView?no=<%=prev.getBoardNo()%>&cPage=<%=cPage%>">[이전 게시글] <strong><%=prev.getTitle() %></strong></a>
		</div>
		<% } %>
		<% if(next.getBoardNo()!=0){ %>
		<div>
			<a href="<%= request.getContextPath()%>/board/boardView?no=<%=next.getBoardNo()%>&cPage=<%=cPage%>">[다음 게시글] <strong><%=next.getTitle() %></strong>	x</a>
		</div>
		<% } %>
	</div>
	<img id="boardViewImg" src="<%=request.getContextPath() %>/images/List_icon.png" onclick="location.href='<%=request.getContextPath() %>/board/boardList?cPage=<%=cPage %>'" />
	
	<script>
	//게시물 삭제
	<% if(editable){%>
		function deleteBoard(){
			if(confirm("정말 게시글을 삭제 하시겠습니까?")){
				$("#boardDelFrm").submit();
			}
		};
	<% } %>
	
	//댓글달기 textArea접근시 로그인 여부 판단
	$("[name=content]").focus(function(){
		//로그인 여부 검사
		<% if(loginMember == null){%>
			loginAlert();
		<% } %>
	});
	
	function loginAlert(){
		alert("로그인후 이용 하실수 있습니다.");
		$("#login_button").click();
		$("#password_input").focus();
	}
	
	//답글버튼 클릭시 폼 동적생성
	$(".btn-reply").click(function(){
		<% if(loginMember == null){ %>
		loginAlert();
		return;
		<% } %>
		
		//대댓글 작성폼 동적 생성
		var html = "<tr>"; 
		html += "<td colspan='2' style='display:none; text-align:left;'>";
		html += '<form action="<%=request.getContextPath()%>/board/boardCommentInsert" method="post" name="boardCommentFrm">';
		html += '<input type="hidden" name="boardNo" value="<%= b.getBoardNo() %>" />';
		html += '<input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />';
		html += '<input type="hidden" name="commentLevel" value="2" />';
		html += '<input type="hidden" name="commentRef" value="' + $(this).val() + '" />';    
		html += '<textarea class="btn-insert-reply-textarea" name="content" cols="60" rows="2"></textarea>';
		html += '<button type="submit" class="btn-insert-reply" style="background-color: #fd9000;; color:white;">등록</button>';
		html += '</form>';
		html += "</td>";
		html += "</tr>";
		
		var $trOfBtn = $(this).parent().parent();
		$(html)
			.insertAfter($trOfBtn)
			.children("td")
			.slideDown(800);
		$(this).off("click");
	});
	
	//댓글삭제 클릭시 삭제요청
	$(".btn-delete").click(function(){
		if(confirm("해당 댓글을 삭제 하시겠습니까?")){
			var $frm = $(document.boardCommentDelFrm);
			var boardCommentNo = $(this).val();
			$frm.find("[name=no]").val(boardCommentNo);
			$frm.submit();
		}
	});
	
	$(document.boardCommentFrm).submit(function(){
		//로그인 여부 검사
		<% if(loginMember == null){%>
			loginAlert();
			return false;
		<% } %>
		
		//유효성 검사
		var $content = $("[name=content]");
		if(/^(.|\n){1,}$/.test($content.val())==false){
			alert("댓글 내용을 작성하세요.");
			$content.focus();
			return false;
		}
	});
	
	</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>