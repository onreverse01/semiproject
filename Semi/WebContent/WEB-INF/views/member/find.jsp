<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/find.css" />

<div id="find_all_div">
	<form action="<%=request.getContextPath()%>/member/findid" method="POST">
			<div>
				<h2>아이디/비밀번호 찾기</h2>
			</div>
	
			<div class="find_id_div" id="find_id_div">
				<div class="find_div_div">아이디찾기</div>
				<br> <input type="text" name="name_input" id="name_input"
					placeholder="이름"> <br> <input type="text"
					name="email_input" id="email_input" placeholder="이메일"> <br>
				<br> 
				<div class="information_small_div"><small id="information_small"> 본인인증 받으신 정보를 입력해
					주세요. SMS로 아이디(이메일 주소)를 보내드립니다. </small> </div>
					<br> <br /><input type="submit"
					name="find_id_button" id="find_id_button" value="찾기">
				</div>
			
	</form>
	<br />
	
	<br />
	
	
	<form action="<%=request.getContextPath()%>/member/findpassword"
		method="POST">
		<div class="find_password_div" id="find_password_div">
			<div class="find_div_div">비밀번호 찾기</div>
			<br> <input type="text" name="id_input" id="id_input"
				placeholder="ID"> <br> <input type="text"
				name="email_input" id="email_input" placeholder="이메일"> <br>
			<br> 
			<div class="information_small_div"><small id="information_small"> 가입시 등록하신 이메일 주소를 입력해
				주세요. 비밀번호 재설정 링크를 보내드립니다. </small> </div><br> <br /><input type="submit"
				name="find_password_button" id="find_password_button" value="찾기">
		</div>
	</form>
	<br />
	<br />
<div id="goto_button_div">
	<input type="button" class="goto_button" name="goto_loginpage_button"
		id="goto_loginpage_button" value="메인페이지" onclick="location.href='<%=request.getContextPath()%>';"> 
		<input type="button" value="무료회원가입" class="goto_button" name="goto_enrollpage_button"  id="goto_enrollpage_button" onclick="location.href='<%=request.getContextPath()%>/member/enroll';">
</div>
</div>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>