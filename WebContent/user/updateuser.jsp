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
        	var isId = false;
        	// 아이디 중복검사
        	$("#userid").keyup(function () {
        		var ckid = $("#userid").val();
        		if(ckid.length < 4 || ckid.length > 16) {
        			$("#idresult").text("아이디는 4자이상 16자이하입니다.").removeClass('text-primary').removeClass('text-danger').addClass('text-dark');
        			isId = false;
        		} else {
	                $.ajax({
	                	url: '${root}/user',
	                	data: {'act': 'idcheck', 'ckid': ckid},
	                  	type: 'GET',
	                  	dataType: 'text',
	                  	success: function (response) {
	                    	var cnt = parseInt(response);
	                    	if(cnt == 0) {
	                    		$("#idresult").text(ckid + "는 사용가능합니다.").removeClass('text-dark').removeClass('text-danger').addClass('text-primary');
	                    		isId = true;
	                    	} else {
	                    		$("#idresult").text(ckid + "는 사용할 수 없습니다.").removeClass('text-dark').removeClass('text-primary').addClass('text-danger');
	                    		isId = false;
	                    	}
	                  	}
					});
        		}
			});
        	
        	// 회원가입
            $("#registerBtn").click(function () {
                if (!$("#username").val()) {
                    alert("이름 입력!!!");
                    return;
                } else if (!$("#userpwd").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if ($("#userpwd").val() != $("#pwdcheck").val()) {
                    alert("비밀번호 확인!!!");
                    return;
                } else {
                    $("#memberform").attr("action", "${root}/user").submit();
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
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="orange">회원정보 수정</mark></h2>
            <form id="memberform" class="text-left mb-3" method="post" action="">
                <input type="hidden" name="act" id="act" value="updateUser">
                <div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="username" name="username" value="${userInfo.name}">
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" readonly="readonly" name="userid" id="userid" value="${userInfo.id}">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="password" class="form-control" id="userpwd" name="userpwd" value="${userInfo.password}">
                </div>
                <div class="form-group">
                    <label for="pwdcheck">비밀번호재입력</label>
                    <input type="password" class="form-control" id="pwdcheck" name="pwdcheck" value="${userInfo.password}">
                </div>
                <div class="form-group">
                    <label for="useraddress">주소</label>
                    <input type="text" class="form-control" id="useraddress" name="useraddress" value="${userInfo.address}">
                </div>
                <div class="form-group">
                    <label for="useremail">이메일</label><br>
                    <input type="text" class="form-control" id="useremail" name="useremail" value="${userInfo.email}">
                </div>
                <div class="form-group text-center">
                    <button type="button" id="registerBtn" class="btn btn-outline-primary">수정하기</button>
                    <button type="reset" class="btn btn-outline-danger">초기화</button>
                </div>
            </form>
        </div>
    </div>

<%@ include file="/template/footer.jsp" %>