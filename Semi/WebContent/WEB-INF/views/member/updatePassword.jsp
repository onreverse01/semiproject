<%@page import="member.model.service.MemberService"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 헤더 --%>
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
	<div class="mypageWrapper2">
	
	<%-- 수정!!! --%>
	<h3 class="uppass">비밀번호 변경</h3>
	<form name="updatePwdFrm"
		action="<%=request.getContextPath()%>/member/updatePassword"
		method="post">
		<table class="updatePasswordTable">
			<tr>
				<th>현재 비밀번호</th>
				<td>
				<input type="hidden" name="id" value="<%=loginMember.getMemberId()%>"/>
				<input type="password" name="password" id="password" class="up"
					required></td>
			</tr>
			<tr>
				<th>변경할 비밀번호</th>
				<td><input type="password" name="newPassword" id="newPassword" class="up"
					required></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" id="passwordCheck" class="up" required><br>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="변경" class="btn" /></td>
			</tr>
		</table>
	</form>
	</div>
<script>
//폼제출 유효성검사
$("[name=updatePwdFrm]").submit(function() {
    var $oldPassword = $("#password");
    var $newPassword = $("#newPassword");
    passwordValidate();
    
    if ($oldPassword.val() == $newPassword.val()) {
        alert("기존비밀번호와 신규비밀번호는 같을 수 없습니다.");
        $oldPassword.select();
        return false;
    }
    
    var p1 = $("#newPassword");
    
    if(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(p1.val())==false){
        alert("최소 8 자, 최소 하나의 문자 및 하나의 숫자를 입력해주세요.");
        $newPassword.select();
        return false;
    }
    
    return true;
});
//신규비밀번호 일치 검사
$("#passwordCheck").blur(passwordValidate);
function passwordValidate() {
    var $newPassword = $("#newPassword");
    var $newPasswordCheck = $("#passwordCheck");
    if ($newPassword.val() != $newPasswordCheck.val()) {
        alert("입력한 비밀번호가 일치하지 않습니다.");
        $newPassword.select();
        return false;
    }
    return true;
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>