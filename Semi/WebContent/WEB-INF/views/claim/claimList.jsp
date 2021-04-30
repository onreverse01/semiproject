<%@page import="claim.model.vo.AnwBoard"%>
<%@page import="claim.model.vo.ClaimBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<ClaimBoard> list = (List<ClaimBoard>) request.getAttribute("list");
	List<AnwBoard> alist = (List<AnwBoard>) request.getAttribute("alist");
	List<ClaimBoard> wlist = (List<ClaimBoard>) request.getAttribute("wlist");
	List<AnwBoard> walist = (List<AnwBoard>) request.getAttribute("walist");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/claim.css">
<div class="claimfrm">
    <div class="menubar">
        <ul>FAQ
            <hr>
            <li><a href="#">모임관련</a></li>
            <li><a href="#">회원관련</a></li>
        </ul>
        <ul>문의하기
            <hr>
            <li><a href="<%= request.getContextPath() %>/claim/claimEnroll">1:1 문의</a></li>
            <li><a href="<%= request.getContextPath() %>/claim/claimList">문의내역</a></li>
        </ul>
    </div>
   	<div class="claimList">
       <table class="cList">
           <tr class="cListh">
               <th>No</th>
               <th>문의채널</th>
               <th width="300" >문의제목</th>
               <th>문의일</th>
               <th>답변일</th>
               <th>처리상태</th>
           </tr>
           <% 
	   if(list != null && !list.isEmpty()) { 
		 if(loginMember.getMemberRole().equals("A")) {
		  	for(int i=0; i < list.size(); i++){
		%>
		<tr class="cListb">
			<td><%= list.get(i).getNo() %></td>
			<td><%= list.get(i).getChoice() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/claim/claimView?no=<%= list.get(i).getNo() %>"><%= list.get(i).getTitle() %></a>
			</td>
			<td><%= list.get(i).getRegDate() %></td>
			<% if(!(alist.isEmpty())&&(list.get(i).getNo() == alist.get(i).getNo())) {%>
				<td><%= alist.get(i).getRegDate() == null ? "대기중" : alist.get(i).getRegDate() %></td>
			<% } %>
			<td><%= list.get(i).getState() %></td>
			<% if(!(alist.isEmpty())&&!(alist.get(i).getContent()==null)) {%>
					
			<% } else {%>
				<td><input type="button" value="답변하기" onclick="location.href='<%= request.getContextPath() %>/claim/answer?no=<%= list.get(i).getNo() %>'"></td>
			<% } %>
		</tr>
		<% if(!(alist.isEmpty())&&!(alist.get(i).getContent()==null)&&list.get(i).getNo() == alist.get(i).getNo()) {%>
			<tr class="cLista">
	            <td></td>
	            <td>↪</td>
	            <td colspan=4><a href="<%= request.getContextPath() %>/claim/claimView?no=<%= list.get(i).getNo() %>">Re: <%= list.get(i).getTitle() %></a></td>
	        </tr>
		<% } %>
	<%   
		  }
		 } else if(loginMember.getMemberRole().equals("U")){ 
			 for(int i=0; i < wlist.size(); i++){
	%>
		<tr class="cListb">
			<td><%= wlist.get(i).getRnum() %></td>
			<td><%= wlist.get(i).getChoice() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/claim/claimView?no=<%= wlist.get(i).getNo() %>"><%= wlist.get(i).getTitle() %></a>
			</td>
			<td><%= wlist.get(i).getRegDate() %></td>
			<% if(!(walist.isEmpty())&&(wlist.get(i).getNo() == walist.get(i).getNo())) {%>
				<td><%= walist.get(i).getRegDate() == null ? "대기중" : walist.get(i).getRegDate() %></td>
			<% } %>
			<td><%= wlist.get(i).getState() %></td>
			<% if(!(walist.isEmpty())&&!(walist.get(i).getContent()==null)&&wlist.get(i).getNo() == walist.get(i).getNo()) {%>
			<tr class="cLista">
	            <td></td>
	            <td>↪</td>
	            <td colspan=4><a href="<%= request.getContextPath() %>/claim/claimView?no=<%= wlist.get(i).getNo() %>">Re: <%= wlist.get(i).getTitle() %></a></td>
	        </tr>
		<% } %>
	<% 
		 	}
		  }
		} else { 
	%>
		<tr>
			<td colspan="6" style="text-align:center;">조회된 게시글이 없습니다.</td>
		</tr>
	<% } %>
       </table>
    </div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>