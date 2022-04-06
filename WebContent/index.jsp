<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
<%-- <c:if test="${empty memberInfo}"> 
	<a href="${root}/member?action=login">로그인</a><br/>
    <a href="${root}/member?action=regi">회원가입</a><br/>
</c:if> 
<c:if test="${not empty memberInfo}">
       반갑습니다 ${memberInfo.username }님
	<br/>
	<a href="${root}/member?action=logout">로그아웃</a><br/>
	<a href="${root}/member?action=guestbook">게시글보기</a><br/>
</c:if>  --%>

<body class="container">
    <!-- 상단 Header content start -->
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
        <ul class="nav nav-pills">
            <form action="${root}/apt" method="post">
            <input type="hidden" name="act" value="goApt">
            <li class="nav-item"><button id="_aptlist" type="submit" class="btn btn-primary">실거래가 정보</button></li> 
            </form>         
        </ul>
    </header>
    <!-- 상단 Header content end -->
	<img src="img/house2.png"  class="me-2">
        
<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
        <p class="col-md-4 mb-0 text-muted">&copy; 2022 SSAFY 7th BackEnd Proj</p>
    
        <a href="/" class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
        </a>
    
        <ul class="nav col-md-4 justify-content-end">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">About</a></li>
        </ul>
    </footer>
</body>

</body>
</html>