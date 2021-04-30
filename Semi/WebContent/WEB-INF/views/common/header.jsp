<%@page import="member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member"%>


<%
	String msg = (String) session.getAttribute("msg");
	if (msg != null)
	session.removeAttribute("msg");
	Member loginMember = (Member) session.getAttribute("loginMember");
	//사용자 쿠키처리
	String saveId = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie c : cookies) {
			String name = c.getName();
			String value = c.getValue();
			System.out.println(name + " : " + value);
			if ("saveId".equals(name))
		saveId = value;
		}
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>미플</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<header>
	<div id="background_header_div">
	<div id="header_icon"><img src="/semi/images/icon3.png" id="icon4_image" class="icon_image"></div>
		<%-- 검색창 --%>
			<div class="boxContainer">
				<table class="elementsContainer">
					<tr>
						<td>
							<input id="searchKeyword" type="text" placeholder="키워드를 입력해주세요" class="search" onkeyup="enterkey();" />
						</td>
						<td>
							<img class="material-icons" src="<%=request.getContextPath() %>/images/baseline_search_black_24dp.png"  onclick="search();"/>
						</td>
					</tr>
				</table>
			</div>
	
			<a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath() %>/images/Logo.png" id="Logo"/></a>
			<div class="login">
			<%
				if (loginMember == null) {
			%>
			
			<input type="button" value="로그인" id="login_button">
			<input type="button" value="회원가입" id="signup_button" onclick="location.href='<%=request.getContextPath()%>/member/enroll';">
			<%
				} else {
			%>
		
			<%-- 로그인 성공시 --%>
			<table id="login">
				<tr>
					<td class="header_td"><%=loginMember.getName()%>님, 안녕하세요.</td>
				</tr>
				<tr>
					<td class="header_td"><input type="button" value="mypage"
						onclick="location.href='<%=request.getContextPath()%>/member/mypage';" class="loginbtn" />
						<input type="button" value="logout"
						onclick="location.href='<%=request.getContextPath()%>/member/logout';" class="loginbtn"/>
					</td>
				</tr>
			</table>

			<%
				}
			%>
			</div>
			<div class="loginEnd"></div>
		<div id="login_frame_wrapper" >
	</div>
		<!-- Loginframe 평소에 hide -->
		<div id="login_frame_div">
			<div>
			<div id="information_in_image1_wrapper">
			<h1 id="information_in_image1">지금 바로 원하는 지역의 모임 </h1>
			
			<h1 id="information_in_image2">미플 </h1>
			<h1 id="login_count1" class="background_ment">현재 <a id="memberCount"></a>명의 회원이 미플과 함께하고 있어요.</h1>
			<h1 id="login_count2" class="background_ment"><a id="meetingCount"></a>개의 모임에 지금 바로참여하세요  :)</h1>
			</div>
			<img src="<%=request.getContextPath()%>/images/meet.jpg" id="login_image">
			</div>
			<!-- 로그인 포지션-->
			<form id="login_form"
				action="<%=request.getContextPath()%>/member/login" method="POST">
					<input type="button" id="login_closeBtn" value="X" style="float:right;"/>
					<h1 style="clear:both;">로그인</h1>
					<br> 
					<input type="text" name="id_input" id="id_input" placeholder="아이디를 입력해주세요."<%=saveId != null ? "value='" + saveId + "'" : ""%>> <br>
					<input type="password" name="password_input" id="password_input" placeholder="비밀번호를 입력해주세요."> <br> 
					<input type="submit" name="login_submit" id="login_submit" value="로그인">
					<br>
					<div id="checkbox_findbox_wrapper">
						<div id="checkbox_wrapper">
							<input type="checkbox" name="saveid" id="saveid" <%=saveId != null ? "checked" : ""%> /> 
							<label for="saveid" id="saveidlabel">로그인 유지하기</label>
						</div>

						<a href="<%=request.getContextPath()%>/member/find"><span id="find_span">아이디+비밀번호 찾기</span></a>
					</div>
					<br> <br>
					<div class="enroll_div" id="enroll_div"  onclick="location.href='<%=request.getContextPath()%>/member/enroll';" style="clear:both";>
						미플 회원가입하기
					</div>
			</form>
		</div>	
			
		<ol>
			<li><a href="<%=request.getContextPath()%>/meeting/meetingList" id="header_li1">전체</a></li>
			<li><a href="<%=request.getContextPath()%>/meeting/meetingList?category=C1" id="header_li2">음악</a></li>
			<li><a href="<%=request.getContextPath()%>/meeting/meetingList?category=C2" id="header_li3">게임</a></li>
			<li><a href="<%=request.getContextPath()%>/meeting/meetingList?category=C3" id="header_li4">운동</a></li>
			<li><a href="<%=request.getContextPath()%>/meeting/meetingList?category=C4" id="header_li5">요리</a></li>
			<li><a href="<%=request.getContextPath()%>/meeting/meetingList?category=C5" id="header_li6">독서</a></li>
			<li><a href="<%=request.getContextPath()%>/meeting/meetingList?category=C6" id="header_li7">재테크</a></li>
			<li><a href="<%=request.getContextPath()%>/meeting/meetingList?category=C7" id="header_li8">자동차</a></li>
			<li><a href="<%=request.getContextPath()%>/board/boardList" id="header_li9">자유게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/board/adminBoardList" id="header_li10">공지사항</a></li>
			
			<%if(loginMember!=null && loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE)) { %>
			<li><a href="<%=request.getContextPath()%>/admin/memberList" id="header_li11">관리</a></li>
			<%} %>
		</ol>
		</div>

		<script>
			<%if(msg!=null){%>
				alert("<%=msg%>");
			<%}%>
			<%if(loginMember==null){%>
				$(signup_button).click(function(){
					location.href="<%=request.getContextPath()%>/member/enroll";
				});
			$(login_button).click(function() {
				var top = screen.availHeight / 2 - 250;
				$("#login_frame_div").attr('style', 'display:flex; top:'+top+'px;');
				$("#login_frame_wrapper").attr('style', 'display:flex; top:'+top+'px;');
				
				
				
				if(id_input.value.length==0){
					$('#id_input').focus();
				}else{
					$('#password_input').focus();
				}
			});
			$(login_closeBtn).click(function(){
				$("#login_frame_div").attr('style', 'display:none;');
				$("#login_frame_wrapper").attr('style', 'display:none;');
			});
			
			
			$(login_frame_wrapper).click(function(){
				$("#login_frame_div").attr('style', 'display:none;');
				$("#login_frame_wrapper").attr('style', 'display:none;');
			});
			<%}%>
			
			
			
			$(document).ready(function(){
				//총인원과 총모임 ajax
				<%if(loginMember==null){%>
				$.ajax({
					url:"<%= request.getContextPath()%>/member/count",
					success:function(data){
						
						/* 총인원수 저장 */
						var memberCount=data;
						$("#memberCount").html(memberCount);
						
					},
					error:function(xhr, status, error){
						console.log(xhr, status, error);
					}
				});
				
				$.ajax({
					url:"<%= request.getContextPath()%>/member/count",
					method: "POST",
					success:function(data){
						var meetingCount=data;
						$("#meetingCount").html(meetingCount);
						
					},
					error:function(xhr, status, error){
						console.log(xhr, status, error);
					}
				});
				<%}%>
			});
			
			//검색창 엔터
			function enterkey() {
		        if (window.event.keyCode == 13) {
		        	search();
		        }
			}
			function search(){
				var $keyword = $("#searchKeyword").val();
				
				if(/^.{1,}$/.test($keyword)==false){
					$("#searchKeyword").focus();
					alert("검색 키워드를 1글자 이상 입력해주세요");
					return;
				}
				
				location.href="<%=request.getContextPath()%>/meeting/meetingList?search="+$keyword;
			}
		</script>
		
		
	</header>

	<section>