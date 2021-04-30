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
<html lang="ko" id="ho_html">
<head>
<meta charset="UTF-8" />

<title>미플</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header0.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
</head>
<body id="ho_body">

	
	<header id="ho_header">
	<div id="header_total_wrapper">
	
	<div id="icon1" class="icon"><img src="<%=request.getContextPath() %>/images/icon.png" id="icon1_image" class="icon_image"/></div>
	<div id="icon2" class="icon"><img src="<%=request.getContextPath() %>/images/icon.png" id="icon2_image" class="icon_image"/></div>
	<div id="icon3" class="icon"><img src="<%=request.getContextPath() %>/images/icon.png" id="icon3_image" class="icon_image"/></div>
	<div id="icon4" class="icon"><img src="<%=request.getContextPath() %>/images/icon.png" id="icon4_image" class="icon_image"/></div>
	<div id="icon5" class="icon"><img src="<%=request.getContextPath() %>/images/icon.png" id="icon5_image" class="icon_image"/></div>
	<div id="icon6" class="icon"><img src="<%=request.getContextPath() %>/images/icon.png" id="icon6_image" class="icon_image"/></div>
	
	<div id="background_image_wrapper_ho"><img src="<%=request.getContextPath() %>/images/backgroundimage.png" id="ho_header_background"/>
	<div id="airplane_icon"><img src="<%=request.getContextPath() %>/images/airplane.png" id="airplane_icon_image"/></div></div>
				<a href="<%=request.getContextPath()%>/" id="ho_header_logo_a"><img src="<%=request.getContextPath() %>/images/Logo.png" id="ho_Logo"/></a>
	<div class="ho_login">
			<%
				if (loginMember == null) {
			%>
			
			<input type="button" value="로그인" id="login_button">
			<input type="button" value="회원가입" id="signup_button" onclick="location.href='<%=request.getContextPath()%>/member/enroll';">
			<%
				} else {
			%>
		
			<%-- 로그인 성공시 --%>
			<table id="ho_login">
				<tr>
					<td><%=loginMember.getName()%>님, 안녕하세요.</td>
				</tr>
				<tr>
					<td><input type="button" value="mypage"
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
			<div id="ment_header2">너와 나의 연결 고리! 취미 생활 크루 찾기!</div>
			<div id="ment_header">
				No.1 취미모임 앱, 미플
			</div>
		<%-- 검색창 --%>
			<div class="ho_boxContainer">
				<table class="ho_elementsContainer">
					<tr>
						<td>
							<input id="ho_searchKeyword" type="text" placeholder="Search" class="ho_search" onkeyup="enterkey();" />
						</td>
						<td>
							<img class="ho_material-icons" src="<%=request.getContextPath() %>/images/baseline_search_black_24dp.png"  onclick="search();"/>
						</td>
					</tr>
				</table>
			</div>
	

			
			<div class="ho_loginEnd"></div>
		<div id="login_frame_wrapper" >
	</div>
		<!-- Loginframe 평소에 hide -->
		<div id="login_frame_div">
			<div>
			<div id="information_in_image1_wrapper">
			<h1 id="information_in_image1">지금 바로 원하는 지역의 모임 </h1>
			
			<h1 id="information_in_image2">미플 </h1>
			<h1 id="login_count1" class="background_ment">현재 <a id="memberCount" class="background_ment"></a>명의 회원이 미플과 함께하고 있어요.</h1>
			<h1 id="login_count2" class="background_ment"><a id="meetingCount" class="background_ment"></a>개의 모임에 지금 바로참여하세요  :)</h1>
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
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/meeting/meetingList';">
			<img src="<%=request.getContextPath() %>/images/all.png" id="li_all_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/meeting/meetingList">전체</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/meeting/meetingList?category=C1';">
			<img src="<%=request.getContextPath() %>/images/music.png" id="li_music_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/meeting/meetingList?category=C1">음악</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/meeting/meetingList?category=C2';">
			<img src="<%=request.getContextPath() %>/images/game.png" id="li_game_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/meeting/meetingList?category=C2">게임</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/meeting/meetingList?category=C3';">
			<img src="<%=request.getContextPath() %>/images/gym.png" id="li_gym_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/meeting/meetingList?category=C3">운동</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/meeting/meetingList?category=C4';">
			<img src="<%=request.getContextPath() %>/images/cook.png" id="li_cook_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/meeting/meetingList?category=C4">요리</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/meeting/meetingList?category=C5';">
			<img src="<%=request.getContextPath() %>/images/book.png" id="li_book_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/meeting/meetingList?category=C5">독서</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/meeting/meetingList?category=C6';">
			<img src="<%=request.getContextPath() %>/images/finantial.png" id="li_financial _image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/meeting/meetingList?category=C6">재테크</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/meeting/meetingList?category=C7';">
			<img src="<%=request.getContextPath() %>/images/car.png" id="li_car_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/meeting/meetingList?category=C7">자동차</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/board/boardList';">
			<img src="<%=request.getContextPath() %>/images/board.png" id="li_board_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/board/boardList">자유게시판</a></li>
			
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/board/adminBoardList';">
			<img src="<%=request.getContextPath() %>/images/notice.png" id="li_notice_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/board/adminBoardList">공지사항</a></li>
			
			<%if(loginMember!=null && loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE)) { %>
			<li class="header_title_li" onclick="location.href='<%=request.getContextPath()%>/admin/memberList';">
			<img src="<%=request.getContextPath() %>/images/manage.png" id="li_manage_image" class="li_image"/>
			<br />
			<a href="<%=request.getContextPath()%>/admin/memberList">관리</a></li>
			<%} %>
			<%-- <li><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a></li> --%>
		</ol>
		

		<script>
			<%if(msg!=null){%>
				alert("<%=msg%>");
			<%}%>
			<%if(loginMember == null){%>
			$(signup_button).click(function(){
				location.href="<%=request.getContextPath()%>/member/enroll";
			});
			$(login_button).click(function() {
				var top = screen.availHeight / 2 - 300;
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
			});
			
			//검색창 엔터
			function enterkey() {
		        if (window.event.keyCode == 13) {
		        	search();
		        }
			}
			function search(){
				var $keyword = $("#ho_searchKeyword").val();
				
				if(/^.{1,}$/.test($keyword)==false){
					$("#ho_searchKeyword").focus();
					alert("검색 키워드를 1글자 이상 입력해주세요");
					return;
				}
				
				location.href="<%=request.getContextPath()%>/meeting/meetingList?search="+$keyword;
			}
		</script>
		
		
	</header>
	</div>

	<section id="ho_section">