<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

   <form action="<%= request.getContextPath() %>/claim/claimEnroll" id="claimBorder" method="post">
    <h1>1:1 문의</h1>
    <input type="hidden" name="writer" value="<%= loginMember.getMemberId() %>">
    <div class="choice">
      <label for="문의 채널">문의 채널 : </label>
      <select name="choice" id="claimChoice">
        <option value="choice">문의사항선택</option>
        <option value="모임관련">모임관련문의</option>
        <option value="회원관련">회원관련문의</option>
        <option value="기타">기타</option>
      </select>
    </div>
    
    <br>
    <div class="title">
      <label for="문의 제목">문의 제목 : </label>
      <input type="text" name="title" id="claimTitle">
    </div>
    
    <br>
    
    <div class="content">
      <label for="문의 내용" class="contentLabel">문의 내용 : </label>
      <textarea name="content" id="claimContent" cols="50" rows="20"></textarea>
    </div>
    
    <div class="submit">
      <input type="submit" value="문의하기">
      <input type="button" value="취소" onclick="cancel()">
    </div>
  </form>
</div>

<script>
	$("#claimBorder").submit(function(){
		var $choice = $("[name=choice]").val();
		var $title = $("[name=title]").val();	
		var $content = $("[name=content]").val();	
		console.log($choice);
		if($choice=="choice"){
			alert("문의사항을 선택해주세요!");
			$("[name=choice]").focus();
			return false;
		}
		else if(/^.{1,}$/.test($title)==false){
			alert("제목을 입력해주세요!");
			$("[name=title]").focus();
			return false;
		}
		else if($content.length==0 || $content=="<p><br></p>"){
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