<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	int cPage = (int)request.getAttribute("cPage");
	List<String> list = (List<String>)request.getAttribute("list");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/black.css">
<div id="memberList-container">
	<h2><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a> | <span style="text-decoration:underline;">블랙리스트</span></h2>
	<!-- 회원조회 -->
	<div id="tableArea">
		<table id="tbl-member">
			<thead>
				<tr>
					<th>no</th>
					<th>이메일</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
					<%for(String s : list) { %>
				<tr>
					<td><%= (cPage-1)*10+list.indexOf(s)+1 %></td>
					<td><%= s %></td>
					<td><input type="button" data-email="<%=s %>" class=unBan value="해제" /></td>
				</tr>
					<%} %>
			</tbody>
		</table>
		<!-- 페이지바 -->
		<div id="pageBar">
			<%=request.getAttribute("pageBar") != null ? request.getAttribute("pageBar") : ""%>
		</div>
	</div>
</div>

<form 
	action="<%= request.getContextPath() %>/admin/blackList"
	name="admMemberUnBanFrm"
	method="POST">
	<input type="hidden" name="email"/>
</form>

<script>
$(".unBan").click(function(){
	var email = $(this).data("email");
	if(confirm(email+"의 이메일을 블랙리스트에서 해제 하시겠습니까?")){
		var $frm = $(document.admMemberUnBanFrm);
		$frm.find("[name=email]").val(email);
		$(document.admMemberUnBanFrm).submit();
	}
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>