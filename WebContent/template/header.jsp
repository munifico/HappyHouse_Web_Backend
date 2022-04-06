<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SSAFY</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
          aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="${root}/user?act=main"><img src="${root}/img/house.png" style="height: 35px; width:35px" class="me-2">HAPPY HOUSE</a>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="${root}/article?act=list&pg=1&key=&word=">공지사항</a>
            </li>
            <c:if test="${empty userInfo}">
            <li class="nav-item">
              <a class="nav-link" href="${root}/user?act=mvlogin">로그인</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${root}/user?act=mvregister">회원가입</a>
            </li>
            </c:if>
            <c:if test="${not empty userInfo}">
            <li class="nav-item">
              <a class="nav-link" href="${root}/user?act=logout">로그아웃</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${root}/user?act=showinfo">회원정보</a>
            </li>
            </c:if>
          </ul>
        </div>
      </div>
    </nav>
  </header>

</body>
</html>