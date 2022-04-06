<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>

    <style>
        mark.orange {
            background: linear-gradient(to top, orange 20%, transparent 30%);
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {
        	
        	// 회원탈퇴
            $("#deleteBtn").click(function () {
                if (confirm("정말 탈퇴하시겠습니까?")) {
                   $("#deleteform").attr("action", "${root}/user").submit();
                }
            });

            // $('#zipcode').focusin(function () {
            //     $('#zipModal').modal();
            // });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="orange">회원정보</mark></h2>
            <form id="memberform" class="text-left mb-3" method="post" action="${root}/user">
                <input type="hidden" name="act" id="act" value="mvupdate">
                <div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" readonly="readonly" name="username" id="username" value="${userInfo.name}">
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" readonly="readonly" name="userid" id="userid" value="${userInfo.id}">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="text" class="form-control" readonly="readonly" name="userpwd" id="userpwd" value="${userInfo.password}">
                </div>
                <div class="form-group">
                    <label for="useraddress">주소</label>
                    <input type="text" class="form-control" readonly="readonly" name="useraddress" id="useraddress" value="${userInfo.address}">
                </div>
                <div class="form-group">
                    <label for="usereamil">이메일</label>
                    <input type="text" class="form-control" readonly="readonly" name="usereamil" id="usereamil" value="${userInfo.email}">
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-outline-primary">정보수정</button>
                </div>
            </form>
            
            <div class="form-group">
            <form id="deleteform" method="post" action="">
            	<input type="hidden" name="act" id="act" value="deleteUser">
            	<input type="hidden" name="userid" id="userid" value="${userInfo.id}">
            	<button type="button" id="deleteBtn" class="btn btn-outline-danger">탈퇴하기</button>
            </form>
            </div>
        </div>
    </div>
<%@ include file="/template/footer.jsp" %>