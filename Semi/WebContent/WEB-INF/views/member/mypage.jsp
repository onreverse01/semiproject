<%@page import="meeting.model.vo.Meeting"%>
<%@page import="java.util.List"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 헤더 --%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
List<Meeting> list = (List<Meeting>) request.getAttribute("list");
%>
<%-- css --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage.css" />
<%-- 멤버 임의값 --%>
<aside>
	<h2 class="Mp">My page</h2>
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
<div class="mypageWrapper">
	<form id="memberUpdateFrm" method="post">
		<%-- 좌측 이동페이지 바 --%>
		
		<%-- 정보보기 --%>
		<h3 class="me">기본정보</h3>
		<table class="my">
			<tr>
				<th>아이디</th>
				<td><%=loginMember.getMemberId()%></td>
			</tr>
	
			<tr>
				<th>이름</th>
				<td><%=loginMember.getName()%></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=loginMember.getEmail()%></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><%=loginMember.getPhone()%></td>
			</tr>
			<tr>
				<td colspan="2"><label for="event1">이벤트 할인
						혜택 알림 수신에 동의합니다.(선택)</label> <input type="checkbox" name="event1"
					id="event1" value="T" disabled
					<%=loginMember.getEvent1().equals("T") ? "checked" : ""%> /></td>
			</tr>
			<tr>
				<td colspan="2"><label for="event2">장기 미접속 시
						계정 활성 상태 유지합니다.(선택)</label> <input type="checkbox" name="event2"
					id="event2" value="F" disabled
					<%=loginMember.getEvent2().equals("T") ? "checked" : ""%> /></td>
			</tr>
		</table>
		
		<%-- 모임 간편보기 --%>
		<h3 class="mtm">최근모임</h3>
		<%-- 
		<a class="a2" href="<%=request.getContextPath()%>/member/meeting">더보기</a>
		--%>
		<%-- 수정!!! --%>
		<table class="meet">
		<%-- --%>
			<tr>
		<%System.out.println("참여 모임:"+list);%>
			<% if(list != null && list.size()!=0) { 
				System.out.println("if문 실행");
				for(Meeting m : list) {
			%>
				<td class="box">
				<div class="imagebox">
					<a href='<%=request.getContextPath() %>/meeting/meetingView?no=<%=m.getMeetingNo()%>'><img class="myImg" src="<%= request.getContextPath() %>/upload/<%= m.getAttach().getRenamedFilename() %>" /></a>
				</div>
				<div class="textbox">
					<a class="meetATag" href="<%=request.getContextPath()%>/meeting/meetingView?no=<%=m.getMeetingNo()%>"><%= m.getTitle() %></a>
				</div>
				</td>
			<%	}} else { 
				System.out.println("else문 실행");
			%>
				<td colspan="4" style="text-align:center;">신청한 모임이 없습니다.</td>
			<% } %>
			</tr>
		</table>
	</form>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>