<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/footer.css" />
</section>
<hr style="margin-top:40px" />
<footer>
        <div class="meeple-footer container">
        <div class="infofooter">
            <h1>MeetPeople 제작 정보</h1>
            <hr>
            <p>MeetPeople</p>
            <p>소속 : KH정보교육원 | 서울특별시 강남구 역삼동 823-25</p>
            <p>팀장 : 김윤수</p>
            <p>팀원 : 천호현 강종성 박요한 이승우 최한성</p>
        </div>
        <div class="etc">
            <h1>관련 사이트</h1>
            <hr>
            <p>김윤수 : <a href="https://github.com/kimdia200" target="_blank">https://github.com/kimdia200 <i class="fas fa-external-link-alt"></i></a></p>
            <p>강종성 : <a href="https://github.com/bellcastle88" target="_blank">https://github.com/bellcastle88 <i class="fas fa-external-link-alt"></i></a></p>
            <p>박요한 : <a href="https://github.com/dygksqkr12" target="_blank">https://github.com/dygksqkr12 <i class="fas fa-external-link-alt"></i></a></p>
            <p>이승우 : <a href="https://github.com/onreverse01" target="_blank">https://github.com/onreverse01 <i class="fas fa-external-link-alt"></i></a></p>
            <p>천호현 : <a href="https://github.com/hohyuncheon" target="_blank">https://github.com/hohyuncheon <i class="fas fa-external-link-alt"></i></a></p>
            <p>최한성 : <a href="https://github.com/HSdover" target="_blank">https://github.com/HSdover <i class="fas fa-external-link-alt"></i></a></p>
        </div>
        <div class="contact">
            <h1>고객센터</h1>
            <hr>
            <a href="<%= request.getContextPath() %>/claim/claimEnroll">문의하기 <i class="fas fa-external-link-alt"></i></a>
            <p>Tel : 010-6353-4583 (평일 09:00 ~ 18:00)</p>
            <p>Mail : meetpeople_kh@gmail.com</p>
        </div>
    </div>
    
    <script 
     src="https://kit.fontawesome.com/39a2f80180.js"
     crossorigin="anonymous"
     ></script>
</footer>
        <hr style="margin-top:40px; width:100%;">
        <h2 id="last">Copyright 2021. &lt; KH-SEMI-PROJECT &gt; all rights reserved.</h2>
  </body>
</html>