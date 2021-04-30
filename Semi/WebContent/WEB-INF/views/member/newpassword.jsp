<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newpassword.css" />

    <form action="<%=request.getContextPath()%>/member/newpassword"
		method="POST">
		
		
		<div class="new_password_div" id="new_password_div">
		<div id="new_password_wrapper">
			<div id="newpassword_title">새로운 비밀번호</div>
            <br> 
            <input type="password" class="input_newpassword" name="new_password_input1" id="new_password_input1"placeholder="새로운 비밀번호를 입력해주세요"> 
            <br /><label for="" id="pwd_chk1" required></label>
			<input type="password" class="input_newpassword" name="new_password_input2" id="new_password_input2"
				placeholder="새로운 비밀번호를 다시한번 입력해주세요" required> 
			<br /><label for="" id="pwd_chk2"></label>
                <br>
				<input type="hidden" name="hidden_id" id="hidden_id" value="<%= request.getParameter("id") %>"/>
				<input type="hidden" name="hidden_email" id="hidden_email"  value="<%= request.getParameter("email") %>"/>
				<input type="submit"name="new_password_button" id="new_password_button" value="비밀번호 변경하기">
				</div>
		</div>
	</form>
	
	
	<script>
	
	/* 비밀번호유효성검가 */
	$("#new_password_input1").change(function(){
		var p1 = $("#new_password_input1");
		
		if(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(p1.val())==false){
			$("#pwd_chk1").html("최소 8 자, 최소 하나의 문자 및 하나의 숫자를 입력해주세요.<br>");
			$("#pwd_chk1").attr('style', 'visibility:visible;');
			
		}else if(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(p1.val())==true) {
			$("#pwd_chk1").html("");
			$("#pwd_chk1").attr('style', 'visibility:hidden;');
		}
	});
	
	$("#new_password_input2").change(function(){
		var p1 = $("#new_password_input1");
		var p2 = $("#new_password_input2");
		
		if(p1.val() != p2.val()){
			$("#pwd_chk2").html("비밀번호가 일치하지 않습니다.");
			$("#pwd_chk2").attr('style', 'visibility:visible;');
		}else{
			$("#pwd_chk2").html("");
			$("#pwd_chk2").attr('style', 'visibility:hidden;');
		}
	});
	
	
	
	/* submit햇을때 검사*/
	$("#new_password_button").submit(function(){
		
		//비밀번호 유효성 여부결과표시가 나와있으면 return
		if($("#pwd_chk1").css("visibility") != "hidden"){
			alert("비밀번호를 제대로 입력 해주세요");
			$("#new_password_input1").focus();
			return false;
		}
		
		if($("#pwd_chk2").css("visibility") != "hidden"){
			alert("비밀번호를 제대로 입력 해주세요");
			$("#new_password_input2").focus();
			return false;
		}
	});
	
	</script>
	
	<style>
	
	
	#new_password_div{
	padding: 78px;
	}
	
	#new_password_wrapper{
	border: 1px solid #d6bcd6;
    width: 447px;
    height: 200px;
    margin: auto;
    padding: 35px;
	
	
	}
	
	#newpassword_title{
	font-weight: 800;
    font-size: 21px;
	}
	
	.input_newpassword{
	width: 252px;
    height: 27px;
    margin: 3px;
    border: 1px solid black;
    border-color: #e6e6e6;
}

#new_password_button{
box-shadow: 0px 0px 10px -5px #899599;
    background-color: #ededed;
    border-radius: 5px;
    border: 1px solid #d6bcd6;
    display: inline-block;
    cursor: pointer;
    color: #4c6369;
    font-size: 15px;
    padding: 10px 67px;
    transform: translateY(1px);
    margin-top: 9px;
}


#new_password_button:hover{
	background-color:#bab1ba;
}
#new_password_button:active{
	position:relative;
	top:1px;
}

#pwd_chk1, #pwd_chk2{
	font-size:11px;
}
	
	</style>
	
	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>