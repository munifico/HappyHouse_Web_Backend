<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dongsearch</title>
<style>
.container{
           /* position: relative; */
           /* z-index: 1; */
           max-width: 100%;
           margin: 0 auto;
           height: auto;
           min-height: 100%;
           /* padding-bottom: 70px; */
         }

</style>
</head>
<body>
<body class="container">

    <!-- 중앙 content start -->
    <main>
        <!-- 중앙 search content start -->
        
        <section class="d-flex col-8 justify-content-center">
            <select id="select-div" class="form-select border-3" aria-label="Default select example">
                <option selected>구분</option>
                <option value="1">동별</option>
                <option value="2">아파트별</option>
            </select>
            
			<form action="${root}/apt" method="post">   
			<input type="hidden" name="act" value="dongAptlist" >  
            <select id="select-dong" class="form-select border-3" name="dongName" aria-label="Default select example">
                <option selected value="0">동/읍/면</option>
                <option value="필운동">필운동</option>
                <option value="사직동">사직동</option>
                <option value="내수동">내수동</option>
                <option value="익선동">익선동</option>
                <option value="인의동">인의동</option>
                <option value="이화동">이화동</option>
                <option value="충신동">충신동</option>
                <option value="동숭동">동숭동</option>
                <option value="창신동">창신동</option>
                <option value="숭인동">숭인동</option>
                <option value="평동">평동</option>
                <option value="홍파동">홍파동</option>
                <option value="평창동">평창동</option>
                <option value="한남동">한남동</option>
                <option value="명륜1가">명륜1가</option>
            </select>
            <div class="input-group">
                <button id="search-button" type="submit" class="btn btn-primary rounded">실거래가 보기</button>
            </div>
        	</form>
        	<form action="${root}/apt" method="post">    
			<input type="hidden" name="act2" value="Storelist" > 	
            <select id="select-dong" class="form-select border-3" name="dongName" aria-label="Default select example">
                <option selected value="0">동/읍/면</option>
                <option value="필운동">필운동</option>
                <option value="사직동">사직동</option>
                <option value="내수동">내수동</option>
                <option value="익선동">익선동</option>
                <option value="인의동">인의동</option>
                <option value="이화동">이화동</option>
                <option value="충신동">충신동</option>
                <option value="동숭동">동숭동</option>
                <option value="창신동">창신동</option>
                <option value="숭인동">숭인동</option>
                <option value="평동">평동</option>
                <option value="홍파동">홍파동</option>
                <option value="평창동">평창동</option>
                <option value="한남동">한남동</option>
                <option value="명륜1가">명륜1가</option>
            </select>
            <div class="input-group">
                 <button id="search-button" type="submit" class="btn btn-primary rounded">주변상권 보기</button>
            </div>
        	</form>
        	<form action="${root}/apt" method="post">   
        	<div class="input-group">
			<input type="hidden" name="act" value="Aptlist" >
			<input id="search-input"  class="form-control border border-3 rounded text-end ms-4" name="aptName"  placeholder="입력"/>
			<button id="search-button" type="submit" class="btn btn-primary rounded">실거래가 보기</button>
			</div>
			</form>
			
        </section>
        <!-- 중앙 search content end -->
        <!-- 중앙 main content start -->
     <!--    <section class="m-2 d-flex justify-content-between">
            <div id="map" style="width:45%; height:600px"></div>
            <div id="loadedData" class="me-5" style="width:45%"></div>
        </section> -->
        <!-- 중앙 main content end -->
    </main>
    <!-- 중앙 content end -->
    <c:if test="${not empty Apts}">
    	<c:forEach items="${Apts}" var="apt">
    	<table class="table table-active text-left">
                <tbody>
                    <tr class="table-info">
                    	<th>아파트이름</th><th>동이름</th><th>실거래가</th>                       
                    </tr>
                    <tr>
                        <td>${apt.dongName}</td> <td>${apt.aptName}</td> <td>${apt.dealAmount}</td>
                    </tr>
                </tbody>
            </table>
    	</c:forEach>
    </c:if>
    <c:if test="${not empty Stores}">
    	<c:forEach items="${Stores}" var="store">
    	<table class="table table-active text-left">
                <tbody>
                    <tr class="table-info">
                    	<th>상권이름</th><th>종류</th><th>동이름</th>                       
                    </tr>
                    <tr>
                        <td>${store.sname}</td> <td>${store.type}</td> <td>${store.dongName}</td>
                    </tr>
                </tbody>
            </table>
    	</c:forEach>
    </c:if>
<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
        <p class="col-md-4 mb-0 text-muted">&copy; 2022 SSAFY 7th FrontEnd Proj</p>
    
        <a href="/" class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
        </a>
    
        <ul class="nav col-md-4 justify-content-end">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">About</a></li>
        </ul>
    </footer>
</body>
</html>