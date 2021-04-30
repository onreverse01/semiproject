<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Member> list = (List<Member>) request.getAttribute("list");
	Member member = new Member();
	String type = request.getParameter("searchType");
	String kw = request.getParameter("searchKeyword");
	int cPage = (int)request.getAttribute("cPage");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
<style>
div#search-memberId {
	display: <%= type == null || "memberId".equals(type) ? "inline-block" : "none" %>;
}
div#search-memberName{
	display: <%= "memberName".equals(type) ? "inline-block" : "none" %>;
}
</style>
<script>
$(document).ready(function(){
    $("#searchType").change(function(){
        var result = $("#searchType option:selected").val();
        if(result == 'memberId'){
            $("#search-memberId").css("display","inline-block");
            $("#search-memberName").css("display","none");
        }
        else if(result == 'memberName'){
            $("#search-memberId").css("display","none");
            $("#search-memberName").css("display","inline-block");
        }
    });
    
    $(".member-role").change(function(){
    	var memberId = $(this).data("memberId");
    	var memberRole = $(this).val();
    	
    	var memberRoleStr = 
    		memberRole == "<%= MemberService.ADMIN_ROLE %>" ?
    				"관리자" : "일반";
    	
    	if(confirm("[" + memberId + "] 회원의 권한을 [" + memberRoleStr + "]로 변경하시겠습니까?")){
    		var $frm = $(document.memberRoleUpdateFrm);
    		$frm.find("[name=memberId]").val(memberId);
    		$frm.find("[name=memberRole]").val(memberRole);
    		$frm.submit();
    	}
    	else {
    		//기본 선택된 option태그로 다시 변경
    		$(this).children("[selected]").prop("selected", true);
    	}
    });
    
    $(".event1").change(function(){
    	var memberId = $(this).data("memberId");
    	var event1 = $(this).val();
    	
    	var event1Str = 
    		event1 == "<%= MemberService.EVENT_TRUE %>" ?
    				"동의" : "동의안함";
    	
    	if(confirm("[" + memberId + "] 회원의 권한을 [" + event1Str + "]로 변경하시겠습니까?")){
    		var $frm = $(document.memberEventUpdateFrm);
    		$frm.find("[name=memberId]").val(memberId);
    		$frm.find("[name=event1]").val(event1);
    		$frm.submit();
    	}
    	else {
    		//기본 선택된 option태그로 다시 변경
    		$(this).children("[selected]").prop("selected", true);
    	}
    });
    
    $(".event2").change(function(){
    	var memberId = $(this).data("memberId");
    	var event2 = $(this).val();
    	
    	var event2Str = 
    		event2 == "<%= MemberService.EVENT_TRUE %>" ?
    				"동의" : "동의안함";
    	
    	if(confirm("[" + memberId + "] 회원의 권한을 [" + event2Str + "]로 변경하시겠습니까?")){
    		var $frm = $(document.memberEventUpdateFrm2);
    		$frm.find("[name=memberId]").val(memberId);
    		$frm.find("[name=event2]").val(event2);
    		$frm.submit();
    	}
    	else {
    		//기본 선택된 option태그로 다시 변경
    		$(this).children("[selected]").prop("selected", true);
    	}
    });
    
});
</script>
<div id="memberList-container">
    <h2><span style="text-decoration:underline;">회원관리</span> | <a href="<%=request.getContextPath()%>/admin/blackList">블랙리스트</a></h2>

    <!-- 검색 -->
    <div id="search-container">
            검색타입 : 
            <select id="searchType">
                <option value="memberId" <%= "memberId".equals(type) ? "selected" : "" %>>아이디</option>
                <option value="memberName" <%= "memberName".equals(type) ? "selected" : "" %>>회원명</option>
            </select>
            <div id="search-memberId">
                <form action="<%=request.getContextPath() %>/admin/memberFinder" >
                    <input type="hidden" name="searchType" value="memberId">
                    <input type="text" name="searchKeyword" placeholder="검색할 아이디 입력" value="<%= "memberId".equals(type) ? kw : "" %>">
                    <button type="submit">검색</button>
                </form>
            </div>
            <div id="search-memberName">
                <form action="<%=request.getContextPath() %>/admin/memberFinder">
                    <input type="hidden" name="searchType" value="memberName">
                    <input type="text" name="searchKeyword" placeholder="검색할 이름 입력" value="<%= "memberName".equals(type) ? kw : "" %>">
                    <button type="submit">검색</button>
                </form>
            </div>
        </div>

        <!-- 회원조회 -->
        <div id="tableArea">
            <table id = "tbl-member">
                <thead>
                    <tr>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>회원권한</th>
                        <th>이메일</th>
                        <th>전화번호</th>
                        <th>event1</th>
                        <th>event2</th>
                        <th>가입일</th>
                        <th id="hidden-table"></th>
                    </tr>
                </thead>
                <tbody>
                <% if(list == null || list.isEmpty()){ %>
                    <tr>
                        <td colspan="9" style="text-align:center;">조회된 회원이 없습니다.</td>
                    </tr>
                <% 
                	}
                else {
                	for(Member m : list){
                %>
                	<tr>
                        <td><%= m.getMemberId() %></td>
                        <td><%= m.getName() %></td>
                        <td>
                        	<select class="member-role" data-member-id="<%= m.getMemberId() %>">
                        		<option
                        			value="<%=MemberService.ADMIN_ROLE %>"
                        			<%=MemberService.ADMIN_ROLE.equals(m.getMemberRole()) ? "selected" : "" %>>관리자</option>
                        		<option 
                        			value="<%=MemberService.MEMBER_ROLE %>"
                        			<%=MemberService.MEMBER_ROLE.equals(m.getMemberRole()) ? "selected" : "" %>>일반</option>
                        	</select>
                        </td>
                        <td><%= m.getEmail() %></td>
                        <td><%= m.getPhone() %></td>
                        <td>
                        	<select class="event1" data-member-id="<%= m.getMemberId() %>">
								<option 
									value="<%= MemberService.EVENT_TRUE %>"
									<%= MemberService.EVENT_TRUE.equals(m.getEvent1()) ? "selected" : ""  %>>동의</option>
								<option 
									value="<%= MemberService.EVENT_FALSE %>"
									<%= MemberService.EVENT_FALSE.equals(m.getEvent1()) ? "selected" : ""  %>>동의안함</option>
							</select>
                        </td>
                        <td>
                        	<select class="event2" data-member-id="<%= m.getMemberId() %>">
								<option 
									value="<%= MemberService.EVENT_TRUE %>"
									<%= MemberService.EVENT_TRUE.equals(m.getEvent2()) ? "selected" : ""  %>>동의</option>
								<option 
									value="<%= MemberService.EVENT_FALSE %>"
									<%= MemberService.EVENT_FALSE.equals(m.getEvent2()) ? "selected" : ""  %>>동의안함</option>
							</select>
                        </td>
                        <td><%= m.getEnrollDate() %></td>
                        <td><input type="button" data-member-id="<%= m.getEmail() %>" class="delMember" value="강제추방" /></td>
                    </tr>
 
                <%		
                	}
                }
                %>
                </tbody>
            </table>
 

            <!-- 페이지바 -->
            <div id="pageBar">
            	<%= request.getAttribute("pageBar") != null ? request.getAttribute("pageBar") : ""%>
            </div>
            
     </div>
</div>
<form 
	action="<%= request.getContextPath() %>/admin/memberDelete"
	name="admMemberDelFrm"
	method="POST">
	<input type="hidden" name="memberId"/>
</form>
<script>
$(".delMember").click(function(){
	var memberId = $(this).data("memberId");
	if(confirm(memberId+"를 정말 추방시키겠습니까?")){
		var $frm = $(document.admMemberDelFrm);
		$frm.find("[name=memberId]").val(memberId);
		$(document.admMemberDelFrm).submit();
	}
});
</script>
<form
	action="<%=request.getContextPath() %>/admin/memberRoleUpdate"
	name="memberRoleUpdateFrm" method="POST">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="memberRole" />
</form>

<form
	action="<%=request.getContextPath() %>/admin/memberEventUpdate"
	name="memberEventUpdateFrm" method="POST">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="event1" />
</form>

<form
	action="<%=request.getContextPath() %>/admin/memberEventUpdate2"
	name="memberEventUpdateFrm2" method="POST">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="event2" />
</form>





<%@ include file="/WEB-INF/views/common/footer.jsp" %>