<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/enroll.css" />

<!-- Section 1 -->
<form id="ho_enroll_from"
	action="<%=request.getContextPath()%>/member/enroll" method="POST">
	<div class="ho_enroll_box_div" id="ho_enroll_box_div">
		<h1>딱 이것만 작성하면 가입완료!</h1>
		<div class="ho_id_form">
		
		<div id="for_enroll_css_wrapper">
			<h3>아이디</h3>
			<input type="text" class="input" name="ho_id_input" id="ho_id_input" placeholder="사용하실 아이디를 입력해주세요." value="" required> 
			<!-- text박스의 id입력한거랑 idTest값이 같으면 중복검사 성공 -->
			<input type="hidden" name="idTest" value="0" />
			<input type="button" name="ho_id_check_button" id="ho_id_check_button" value="중복검사" required/> <br>
			<div class="testwrapper"></div>
		


			<div class="ho_password_form">
				<h3>비밀번호</h3>
				<input type="password" class="input" name="ho_password_input1"
					id="ho_password_input1" placeholder="비밀번호를 입력해주세요.(8자 이상)" required><br>
					<label for="" id="pwd_chk1"></label>
				 <input type="password" class="input" name="ho_password_input2"
					id="ho_password_input2" placeholder="비밀번호를 한번 더 입력해주세요." required> <br>
					<label for="" id="pwd_chk2"></label>
			</div>

			<div class="ho_name_form">
				<h3>이름</h3>
				<input type="text" class="input" name="ho_name_input" id="ho_name_input"
					placeholder="이름을 입력해 주세요." required> <br>
					<label for="" id="name_chk"></label>
			</div>

			<div class="email_form">
				<h3>이메일</h3>
				<input type="email" class="input" name="ho_eamil_input" id="ho_eamil_input"
					placeholder="이메일을 입력해주세요." required> 
					<input type="button" name="ho_email_check_button" id="ho_email_check_button" value="인증하기"/>
					<br />
					<input type="text" name="ho_eamil_confirm_input" id="ho_eamil_confirm_input" placeholder="인증번호를 입력해주세요." style="visibility: hidden;" >
					<input type="button" name="ho_eamil_confirm_button" id="ho_eamil_confirm_button" value="인증번호확인" style="visibility: hidden;"/>
					<input type="hidden" name="emailTest" value="0" />
					<br>
			</div>

			<div class="ho_phone_form">
				<h3>휴대폰번호</h3>
				<input type="number" class="input" name="ho_phone_input" id="ho_phone_input"
					placeholder="휴대폰 번호 '-'표 없이 입력해 주세요." required> <br>
					<label for="" id="phone_chk"></label>
			</div>

			<div class="ho_agremment_form">
			<h3>약관동의</h3>
			</div>
		</div>
			
		<div class="ho_agreement_div" id="ho_agreement_div">
			<fieldset class="ho_agreement_filedset">
				<input type="checkbox" name="ho_agreement_input1"
					id="ho_agreement_input1" onclick="chkAll()"/> <label for="ho_agreement_input1">모두
					동의합니다.</label> <br>
				<hr>
				<input type="checkbox" class ="checkbox_ho" name="ho_agreement_input2"
					id="ho_agreement_input2" onclick="chkOne()" required />
					
					 <label
					for="ho_agreement_input2">만 14세 이상입니다.</label> <label
					for="ho_agreement_input2" class="ho_necessary">(필수)</label> <br>
				<input type="checkbox" class ="checkbox_ho" name="ho_agreement_input3"
					id="ho_agreement_input3"  onclick="chkOne()" required /> <label
					for="ho_agreement_input3"><u>서비스 이용약관</u>에 동의합니다.</label> <label
					for="ho_agreement_input3" class="ho_necessary">(필수)</label> <br>
				<input type="checkbox" class ="checkbox_ho" name="ho_agreement_input4"
					id="ho_agreement_input4"  onclick="chkOne()" required /> <label
					for="ho_agreement_input4"><u>개인정보 수집•이용</u> 에 동의합니다.</label> <label
					for="ho_agreement_input4" class="ho_necessary">(필수)</label> <br>
				<input type="checkbox" class ="checkbox_ho" name="ho_agreement_input5"
					id="ho_agreement_input5"  onclick="chkOne()" value="T"> <label
					for="ho_agreement_input5">이벤트 할인 혜택 알림 수신에 동의합니다.</label> <label
					for="ho_agreement_input5" class="ho_choice">(선택)</label> <br>
				<input type="checkbox" class ="checkbox_ho" name="ho_agreement_input6"
					id="ho_agreement_input6"  onclick="chkOne()" value="T" /> <label
					for="ho_agreement_input5">장기 미접속 시 계정 활성 상태 유지합니다. </label> <label
					for="ho_agreement_input5" class="ho_choice">(선택)</label> <br>
			</fieldset>
		</div>
				<br> <input type="submit" id="enroll_sumit_button" value="버튼을 누르면 회원가입이 완료됩니다."id="ho_submit_button">
		</div>
	</div>
</form>

<script>
	//아이디중복검사 
	$(ho_id_check_button).click(function(){
		var id = $('#ho_id_input').val();
		
		//아이디유효성검사
		var $ho_id_input = $("#ho_id_input");
		if (/^[a-zA-Z0-9_]{4,}$/.test($ho_id_input.val()) == false) {
			alert("유효한 아이디를 입력해주세요.");
			return;
		}
		
		
		//DB를 통해 중복검사
		$.ajax({
			url: "<%= request.getContextPath() %>/member/checkid",
			data : {
				memberid : id
				},
			method: "POST",
			dataType: "text",
			success: function(data){
				$(".testwrapper").html(data);
				if(data=="사용가능한 아이디입니다."){
					$("[name=idTest]").val("1");
				}else{
					$('#ho_id_input').val("").focus();
				}
			},
			error: function(xhr, status, error){
				console.log("error call!");
				console.log(xhr, status, error);
			}
		});
	});
	
	//아이디 중복검사 이후 id값 변경시 다시 중복검사 하게끔
	$("#ho_id_input").change(function(){
		$("[name=idTest]").val("0");
		$(".testwrapper").html("");
	});
	
	
	
	//첫번째 비밀번호 유효성검사
	$("#ho_password_input1").change(function(){
		var p1 = $("#ho_password_input1");
		
		if(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(p1.val())==false){
			$("#pwd_chk1").html("최소 8 자, 최소 하나의 문자 및 하나의 숫자를 입력해주세요.<br>");
			$("#pwd_chk1").attr('style', 'visibility:visible;');
			
		}else if(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(p1.val())==true) {
			$("#pwd_chk1").html("");
			$("#pwd_chk1").attr('style', 'visibility:hidden;');
		}
	});
	
	//두번째 비밀번호 유효성검사
	$("#ho_password_input2").change(function(){
		var p1 = $("#ho_password_input1");
		var p2 = $("#ho_password_input2");
		
		if(p1.val() != p2.val()){
			$("#pwd_chk2").html("비밀번호가 일치하지 않습니다.");
			$("#pwd_chk2").attr('style', 'visibility:visible;');
		}else{
			$("#pwd_chk2").html("");
			$("#pwd_chk2").attr('style', 'visibility:hidden;');
		}
	});
	
	//이름 유효성검사
	$("#ho_name_input").change(function(){
	var $ho_name_input = $("#ho_name_input");
	
	if(/^[가-힣]{2,}$/.test($ho_name_input.val()) == false){
		$("#name_chk").html("이름은 한글 2글자 이상이어야 합니다.");
		$("#name_chk").attr('style', 'visibility:visible;');
		return false;
	}else{
		$("#name_chk").html("");
		$("#name_chk").attr('style', 'visibility:hidden;');
		
	}
	});
	
	
	
	//이메일 인증하기 구현 
	$(ho_email_check_button).click(function(){
		var eamilId = $('#ho_eamil_input').val();
		
		//미플에서 보낼 인증번호 담는곳
		var confirmCode;
		$.ajax({
			url: "<%= request.getContextPath() %>/member/send",
			data : {
				memberEamilId : eamilId
				},
			method: "POST",
			dataType: "text",
			
			//성공했을시
			success: function(data){
				if(data=="Ban"){
					alert("가입이 제한된 이메일 입니다.");
					return;
				}
				if(data=="overlap"){
					alert("이미 가입된 이메일 입니다.");
					return;
				}
				
			confirmCode = data;
			
			//인증번호 박스, 버튼나타나기			
			$("#ho_eamil_confirm_input, #ho_eamil_confirm_button").attr('style', 'visibility:visible;');
			
			
				//인증번호 확인눌렀을 시 구현
				$(ho_eamil_confirm_button).click(function(){
					var memberConfirmCode = $('#ho_eamil_confirm_input').val();
					
					
					if(confirmCode==memberConfirmCode){
						alert("인증에 성공하셨습니다");
						$("[name=emailTest]").val("1"); //인증에 성공하면 value를 1로바꿈
						$('#ho_eamil_input, #ho_eamil_confirm_input').attr('readonly', true); //인증성공하면 이메일 + 인증번호창 readonly
					}else{
						alert("인증에 실패하셨습니다");
					}
					
				});
			
			},
			error: function(xhr, status, error){
				console.log("error call!");
				console.log(xhr, status, error);
			}
		});
	});
	
	
	
	//휴대폰 유효성검사
	$("#ho_phone_input").change(function(){
		var $ho_phone_input = $("#ho_phone_input");
		
		if(/^010[0-9]{8}$/.test($ho_phone_input.val()) == false){
			$("#phone_chk").html("유효한 전화번호가 아닙니다.");
			$("#phone_chk").attr('style', 'visibility:visible;');
			return false;
		}else{
			$("#phone_chk").html("");
			$("#phone_chk").attr('style', 'visibility:hidden;');
			
		}
		});
	
	//모두동의하기 구현
	function chkAll() {
        if ($("#ho_agreement_input1").is(':checked')) {
            $("#ho_agreement_input2, #ho_agreement_input3, #ho_agreement_input4, #ho_agreement_input5, #ho_agreement_input6").prop("checked", true);
        } else {
            $("#ho_agreement_input2, #ho_agreement_input3, #ho_agreement_input4, #ho_agreement_input5, #ho_agreement_input6").prop("checked", false);
        }
    }
	
	
	//하나라도 체크헤제 시 모두동의하기 헤제취소
	function chkOne() {
		
		var checkall = document.querySelector("#ho_agreement_input1");
		var checkboxes = document.querySelectorAll(".checkbox_ho");
		var checkedCheckBox = document.querySelectorAll(".checkbox_ho:checked");
		
		checkall.checked = (checkboxes.length == checkedCheckBox.length);
		};
	
	
	
	//제출버튼 클릭시 모든 값 유무 확인
	$("#ho_enroll_from").submit(function(){
		
		//중복검사 실시 유무
		if($("[name=idTest]").val() != "1"){
			alert("중복검사를 해주세요");
			$("#ho_id_input").focus();
			return false;
		}
		
		//비밀번호 유효성 여부결과표시가 나와있으면 return
		if($("#pwd_chk1").css("visibility") != "hidden"){
			alert("비밀번호를 제대로 입력 해주세요");
			$("#ho_password_input1").focus();
			return false;
		}
		
		if($("#pwd_chk2").css("visibility") != "hidden"){
			alert("비밀번호를 제대로 입력 해주세요");
			$("#ho_password_input2").focus();
			return false;
		}
		
		//이름 유효성 여부표시가 나와있으면 return
		if($("#name_chk").css("visibility") != "hidden"){
			alert("이름을 제대로 입력 해주세요");
			$("#ho_name_input").focus();
			return false;
		}
		
		//이메일 인증 실시 유무
		if($("[name=emailTest]").val() != "1"){
			alert("이메일인증 해주세요");
			return false;
		}
		
		//이메일유효성 hidden이면 인증하기
		if($("#ho_eamil_confirm_button").css("visibility") == "hidden"){
			alert("이메일 인증을 해주세요");
			$("#ho_eamil_input").focus();
			return false;
		}
		
		//휴대폰유효성 여부표시가 나와있으면 return
		if($("#phone_chk").css("visibility") != "hidden"){
			alert("휴대폰번호를 제대로 입력 해주세요");
			$("#ho_phone_input").focus();
			return false;
		}
		
	});
	
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
