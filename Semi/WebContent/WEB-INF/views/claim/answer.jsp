<%@page import="claim.model.vo.ClaimBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<% 
	ClaimBoard cb = (ClaimBoard)request.getAttribute("oneClaim");
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

   <form action="<%= request.getContextPath() %>/claim/answer" id="claimBorder" method="GET">
   <div class="claimView">
        <h1>문의내역보기</h1>
        <ul>
        	<input type="hidden" name="claimNo" value="<%= cb.getNo() %>">
            <li>문의제목 : <%= cb.getTitle() %></li>
            <li>문의채널 : <%= cb.getChoice() %></li>
            <li>문의내용</li>
            <p><%= cb.getContent() %></p>
            
        </ul>
    </div>
    <h1>답변하기</h1>
    <div class="content">
      <label for="답변 내용" class="contentLabel">답변 내용 : </label>
      <textarea name="anwcontent" id="claimContent" cols="50" rows="20"></textarea>
    </div>
    
    <div class="submit">
      <input type="submit" value="답변하기">
      <input type="button" value="취소" onclick="cancel()">
    </div>
  </form>
</div>

<script>
$("#claimBorder").submit(function(){
	var $content = $("[name=anwcontent]").val();	
		if($content.length==0 || $content=="<p><br></p>"){
			alert("내용을 입력해주세요!");
			$("[name=content]").focus();
			return false;
		}
});
	
	function cancel(){
  	  if(confirm("정말 게시글 작성을 중단 하시겠습니까?")){
  		  location.href='<%=request.getContextPath()%>/claim/claimMenu';
		}
  	 }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>