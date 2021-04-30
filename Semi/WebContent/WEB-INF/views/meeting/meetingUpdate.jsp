<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="meeting.model.vo.Meeting"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<% Meeting m = (Meeting)request.getAttribute("meeting"); %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet" />
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- include summernote css/js-->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css"
	rel="stylesheet" />
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/meetingView.css" />
			<form action="<%=request.getContextPath()%>/meeting/meetingUpdate" method="post" id="updateFrm" enctype="multipart/form-data">

		
	<div id="meetingViewWrapper">
		<div id="meetingViewLeft">
			<div id="imgWrapper">
				<img id="imgTag" src="<%=request.getContextPath() %>/upload/<%=m.getAttach().getRenamedFilename()%>"/>
			</div>
			<div id="imgSrcWrapper">
				<input type="file" name="upFile">
				<%if(m.getAttach().getRenamedFilename().equals("no_img.png")==false){ %>
				<div id="imgSrcInfo">
	 			<%= m.getAttach().getOriginalFilename()%>
				<input type="checkbox" name="delfile" id="delFile" value="<%= m.getAttach().getAttachNo()%>"/>
				<label for="delFile">삭제</label>
				</div>
				<% } %>
			</div>
			<div id="contentWrapper">
			</div>	
		</div>
		
		
		<div id="meetingViewRight">
			<div id="description">

				<input type="text" name="title" id="title" required placeholder="제목을 입력해주세요" value="<%=m.getTitle()%>"/>			
				<table>
					<tr>
						<th>카테고리</th>
						<td>
							<select name="category" id="category">
								<option value="C1" <%=m.getCategoryCode().equals("C1") ? "checked" : ""%>>음악</option>
								<option value="C2" <%=m.getCategoryCode().equals("C2") ? "checked" : ""%>>게임</option>
								<option value="C3" <%=m.getCategoryCode().equals("C3") ? "checked" : ""%>>운동</option>
								<option value="C4" <%=m.getCategoryCode().equals("C4") ? "checked" : ""%>>요리</option>
								<option value="C5" <%=m.getCategoryCode().equals("C5") ? "checked" : ""%>>독서</option>
								<option value="C6" <%=m.getCategoryCode().equals("C6") ? "checked" : ""%>>재테크</option>
								<option value="C7" <%=m.getCategoryCode().equals("C7") ? "checked" : ""%>>자동차</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>지역</th>
						<td>
							<select name="location" id="location">
								<option value="L1" <%=m.getLocationCode().equals("L1") ? "checked" : "" %>>서울</option>
								<option value="L2" <%=m.getLocationCode().equals("L2") ? "checked" : "" %>>경기</option>
								<option value="L3" <%=m.getLocationCode().equals("L3") ? "checked" : "" %>>인천</option>
								<option value="L4" <%=m.getLocationCode().equals("L4") ? "checked" : "" %>>대전·충청</option>
								<option value="L5" <%=m.getLocationCode().equals("L5") ? "checked" : "" %>>대구·경북</option>
								<option value="L6" <%=m.getLocationCode().equals("L6") ? "checked" : "" %>>부산·경남</option>
								<option value="L7" <%=m.getLocationCode().equals("L7") ? "checked" : "" %>>울산</option>
								<option value="L8" <%=m.getLocationCode().equals("L8") ? "checked" : "" %>>광주</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>장소</th>
						<td><input type="text" name="place" id="place" value="<%=m.getPlace()%>" required/></td>
					</tr>
					<tr>
						<th>일자</th>
						<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); %>
						<td><input type="datetime-local" name="time" id="time" value="<%=sdf.format(m.getTime()) %>" required/></td>
					</tr>
					<tr>
						<th>참가비용</th>
						<td>
							<div class="flex">
								<span class="won">￦</span>
								<input type="number" name="cost" id="cost" required min="0" step="1000" placeholder="참가비용" value="<%=m.getCost() %>"/>
								<span class="won2">원</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>최대인원</th>
						<td>
							<div class="flex">
								<input type="number" name="max" id="max" min="2" value="<%=m.getMaxPeople() %>" required/>
								<span class="person">명</span>
							</div>
						</td>
					</tr>
				</table>
				<input type="hidden" name="no" value="<%=m.getMeetingNo()%>" />
				<input type="hidden" name="writer" value="<%=loginMember.getMemberId() %>" />
				<input type="hidden" name="content" value="" id="realContent"/>
			</div>
		</div>
	</div>
			</form>
	<div id="updateDeleteWrapper">
		<input type="button" value="수정"  onclick="updatee();" />
		<input type="button" value="취소"  onclick="cancel();" />
	</div>
	
	<script>
	
	
	
	$(document).ready(function(){
		$("#contentWrapper").summernote({
	        width: 800,
	        height: 500,
	        focus: true,
	        disableResizeEditor: true,
	    }).summernote("code",'<%=m.getContent()%>');
		
		$("[name=upFile]").on('change',function(){
			var value = $("[name=upFile]").val();
			if(value.length != 0){
				var form = $('#updateFrm')[0];
			    var formData = new FormData(form);
					$.ajax({
					url: "<%= request.getContextPath() %>/meeting/tempFile",
					enctype: 'multipart/form-data',
		            processData: false,
		            contentType: false,
					data : formData,
					method: "POST",
					
					success: function(data){
						console.log(data);
						$("#imgTag").attr('src','<%=request.getContextPath() %>/upload/'+data+"?time="+new Date());
					},
					
					error: function(xhr, status, error){
						console.log("error call!");
						console.log(xhr, status, error);
					}
				});
			}else{
				$("#imgTag").attr('src','<%=request.getContextPath() %>/upload/<%=m.getAttach().getRenamedFilename()%>');
			}
		});
	})
	
	function updatee(){
		var content = $("#contentWrapper").summernote("code");
		$("#realContent").val(content);
		//유효성 검사 넣어야함
		
		
		if(confirm("게시물을 수정하시겠습니까?")){
			$("#updateFrm").submit();
		}
	}
	
	$("[name=upFile]").change(function(){
		console.log($(this).val());
		if($(this).val() != ""){
			//파일을 선택하면 체크상태로 변경하고 더이상 변경이 불가능 하게 해버림
			$("#delFile").prop("checked",true)
				.on('click',function(){
				return false;
			})
		}else{
			//파일을 해제하면 체크가능 상태가 되고 변경이 가능하다
			$("#delFile").prop("checked",false)
				.off('click');
		}
	});
	function cancel(){
		<% String referer = request.getHeader("Referer");%>
		location.href="<%=referer%>";
	}
	</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>