<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/header0.jsp"%>
	<!-- 지역별 -->
	<div class="titleWrapper">
		<h4>지역　　</h4> <div onclick="left1()">&lt</div><div onclick="right1()">&gt</div>
	</div>
	<div id="locationList" class="boxWrapper">
			<% 	
				List<String> list = new ArrayList<>();
				list.add("L1");
				list.add("/images/location/L1.png");
				list.add("서울");
				list.add("L2");
				list.add("/images/location/L2.png");
				list.add("경기");
				list.add("L3");
				list.add("/images/location/L3.png");
				list.add("인천");
 				list.add("L4");
				list.add("/images/location/L4.png");
				list.add("대전·충청");
				list.add("L5");
				list.add("/images/location/L5.png");
				list.add("대구·경북");
				list.add("L6");
				list.add("/images/location/L6.png");
				list.add("부산·경남");
				list.add("L7");
				list.add("/images/location/L7.png");
				list.add("울산");
				list.add("L8");
				list.add("/images/location/L8.png");
				list.add("광주");
				
				for(int i=0; i<list.size(); ){%>
				<div class="boxContents">
					<a href="<%=request.getContextPath()+"/meeting/meetingList?location="+list.get(i++)%>">
						<img src="<%=request.getContextPath() %><%=list.get(i++)%>" width=220px height=150px/>
						<span><%=list.get(i++)%></span>
					</a>
				</div>
				<% } %>
	</div>
	
	
	<!-- 카테고리별 -->
	<div class="titleWrapper">
		<h4>카테고리</h4> <div onclick="left2()">&lt</div><div onclick="right2()">&gt</div>
	</div>
	<div id="categoryList" class="boxWrapper">
		<% 	
				list = new ArrayList<>();
				list.add("C1");
				list.add("/images/category/C1.png");
				list.add("음악");
				list.add("C2");
				list.add("/images/category/C2.png");
				list.add("게임");
				list.add("C3");
				list.add("/images/category/C3.png");
				list.add("운동");
				list.add("C4");
				list.add("/images/category/C4.png");
				list.add("요리");
				list.add("C5");
				list.add("/images/category/C5.png");
				list.add("독서");
				list.add("C6");
				list.add("/images/category/C6.png");
				list.add("재테크");
				list.add("C7");
				list.add("/images/category/C7.png");
				list.add("자동차");
				
				for(int i=0; i<list.size(); ){%>
				<div class="boxContents">
					<a href="<%=request.getContextPath()+"/meeting/meetingList?category="+list.get(i++)%>">
						<img src="<%=request.getContextPath() %><%=list.get(i++)%>" width=220px height=150px/>
						<span><%=list.get(i++)%></span>
					</a>
				</div>
				<% } %>
	</div>
	
	<!-- 최근생성된 모음 10개 할거임 -->
	<div class="titleWrapper">
		<h4>최근생성</h4> <div onclick="left3()">&lt</div><div onclick="right3()">&gt</div>
	</div>
	<div id="recent" class="boxWrapper">
	</div>
	
	<!-- 게시판 영역 -->
	<div class="boardWrapper">
		<div class="board" id="board_index">
			<h3>자유게시판</h3>
			<div id="userBoard">
			</div>
		</div>	
		
		<div class="board" id="notice_index">
			<h3>공지사항</h3>
			<div id="adminBoard">
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
			//최근 생성 모임 불러오기
			$.ajax({
				url:"<%= request.getContextPath()%>/meeting/indexRecentMeeting",
				success:function(data){
					$("#recent").html(data);
				},
				error:function(xhr, status, error){
					console.log(xhr, status, error);
				}
			});
			
			
			//자유게시판 불러오기
			$.ajax({
				url: "<%= request.getContextPath() %>/board/indexUserBoard",
				success: function(data){
					$("#userBoard").html(data);
				},
				error: function(xhr, status, error){
					console.log("error call!");
					console.log(xhr, status, error);
				}
			});
			
			//공지사항 불러오기
			$.ajax({
				url: "<%= request.getContextPath() %>/board/indexAdminBoard",
				success: function(data){
					$("#adminBoard").html(data);
				},
				error: function(xhr, status, error){
					console.log("error call!");
					console.log(xhr, status, error);
				}
			});
		});
	</script>
	
	<script>
		//#locationList 좌우 움직이는 스크립트
		var move1 = 1;
		function right1(){
			if(move1 >= 1 && move1 <= 3 ){
				$("#locationList").attr("style","transform:translateX("+(-240)*(move1++)+"px);")
			}
		}
		function left1(){
			if(move1 >= 2 && move1 <= 4 ){
				$("#locationList").attr("style","transform:translateX("+(-240)*(--move1-1)+"px);")
			}
		}
		
		//#locationList 좌우 움직이는 스크립트
		var move2 = 1;
		function right2(){
			if(move2 >= 1 && move2 <= 2 ){
				$("#categoryList").attr("style","transform:translateX("+(-240)*(move2++)+"px);")
			}
		}
		function left2(){
			if(move2 >= 2 && move2 <= 3 ){
				$("#categoryList").attr("style","transform:translateX("+(-240)*(--move2-1)+"px);")
			}
		}
		
		//#recent 좌우 움직이는 스크립트(AJax동적생성)
	</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>