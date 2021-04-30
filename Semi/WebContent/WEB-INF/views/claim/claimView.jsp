<%@page import="claim.model.vo.AnwBoard"%>
<%@page import="claim.model.vo.ClaimBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<% 
	ClaimBoard cb = (ClaimBoard)request.getAttribute("oneClaim");
	AnwBoard ab = (AnwBoard)request.getAttribute("oneClaimAnw");
%>
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
   
   	    <div class="claimView">
        <h1>문의내역보기</h1>
        <ul>
            <li>문의제목 : <%= cb.getTitle() %></li>
            <li>문의채널 : <%= cb.getChoice() %></li>
            <li>문의내용</li>
            <p><%= cb.getContent() %></p>
            
        </ul>
        
        <% if(!(ab.getContent()==null)) {%>
        	<h1>답변보기</h1>
        	<ul>
            <li>답변내용</li>
            <p><%= ab.getContent() %></p>
        </ul>
        <% } %>
    </div>
    
    
    
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>