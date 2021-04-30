<%@page import="member.model.service.MemberService"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="member.model.vo.Member"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 헤더 --%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%-- css --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage.css" />

	<aside class="aside2">
	<h2 class="Mp">My page</h2>
	<div class="info">
	<%-- 좌측 이동페이지 바 --%>
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
	<h3 class="me">개인정보</h3>
<form id="memberUpdateFrm" method="post">
	<table class="my">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="memberId" id="memberId" class="up"
				value="<%=loginMember.getMemberId()%>" readonly /></td>
		</tr>

		<tr>
			<th>이름</th>
			<td><input type="text" name="name" id="name" class="up"
				value="<%=loginMember.getName()%>" required /><br /></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="email" placeholder="abc@xyz.com" name="email"
				id="email" class="up" value="<%=loginMember.getEmail()%>" /><br />
			</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><input type="tel" placeholder="(-없이)01012345678"
				name="phone" id="phone" class="up" maxlength="11"
				value="<%=loginMember.getPhone()%>" required /><br /></td>
		</tr>
		<tr>
			<td colspan="2"><label for="event1">이벤트 할인
					혜택 알림 수신에 동의합니다.(선택)</label> <input type="checkbox" name="event1"
				id="event1" class="up" value="T"
				<%=loginMember.getEvent1().equals("T") ? "checked" : ""%> /></td>
		</tr>
		<tr>
			<td colspan="2"><label for="event2">장기 미접속 시
					계정 활성 상태 유지합니다.(선택)</label> <input type="checkbox" name="event2"
				id="event2" class="up" value="T"
				<%=loginMember.getEvent2().equals("T") ? "checked" : ""%> /></td>
		</tr>
	</table>
	<input type="button" class="btn2" onclick="updateMember();" value="정보수정" /> <input
		type="button" class="btn3" onclick="updatePassword();" value="비밀번호변경" /> <input
		type="button" class="btn4" onclick="deleteMember();" value="탈퇴" /> <br />
</form>

<%-- 탈퇴 --%>
<form name="memberDelFrm"
	action="<%=request.getContextPath()%>/member/memberDelete"
	method="POST">
	<input type="hidden" name="memberId"
		value="<%=loginMember.getMemberId()%>" />
</form>

	</div>
<script>
/**
 * 비밀번호 업데이트
 */
function updatePassword(){
	location.href = "<%=request.getContextPath()%>/member/updatePassword";
}
/**
 * 유효성 검사
 */
$("#memberUpdateFrm").submit(function(){	
	//memberName
	var $name = $("#name");
	if(/^[가-힣]{2,}$/.test($name.val()) == false){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		$name.select();
		return false;
	}
	
	//phone
	var $phone = $("#phone");
	//-제거하기
	$phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
	
	if(/^010[0-9]{8}$/.test($phone.val()) == false){
		alert("유효한 전화번호가 아닙니다.");
		$phone.select();
		return false;
	}
	
	return true;
});
/**
 * 정보수정
 */
 function updateMember(){
		$("#memberUpdateFrm")
			.attr("action","<%=request.getContextPath() %>/member/memberUpdate")
			.submit();
	}
	/**
	 * 회원탈퇴
	 */
	function deleteMember() {
		if (confirm("정말로 탈퇴하시겠습니까?")) {
			$(document.memberDelFrm).submit();
		}
	}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>