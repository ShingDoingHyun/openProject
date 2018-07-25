<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="https://fonts.googleapis.com/css?family=Archivo+Black"
    rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/naver/css/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<title>가입동의</title>
</head>
<body>
    <%
    request.setCharacterEncoding("utf-8");
	
        //1. 쿠키 정보 가져오기
        Cookie[] cookies = request.getCookies();
        String userid = request.getParameter("userid")==null ? "" : request.getParameter("userid");
        if (cookies != null && cookies.length > 0 && userid=="") {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("save_id")) {
                	userid = cookies[i].getValue();
                    break;
                }
            }
        }/*  else {
            response.sendRedirect("index.jsp");
        } */
    %>
    <div id="wrap">
        <div id="left_wrap">
            <a href="/naver"><h1>NAVER</h1></a>
            <form action="member_login.jsp" id="login_form">
                <div class="text-box">
                    <input type="text" id="userid" name="userid" placeholder="아이디"
                        value="<%=userid%>">
                </div>
                <p id="id" class="red-alert"></p>
                <div class="text-box">
                    <input type="password" id="password" name="password" placeholder="비밀번호">
                </div>
                <p id="pw" class="red-alert"></p>
                <div class="box">
                    <div class="left">
                        <input type="checkbox" id="save_id" name="save_id"> <label
                            for="save_id" class="font1" ><span class="font1">아이디
                                저장</span></label>
                    </div>
                    <div class="right">
                        <span><a href="join_agree.jsp" class="font2">회원가입</a></span>
                    </div>
                </div>

                <input type="button" id="login_btn" value="로그인">

            </form>
            <%@include file="../footer.jsp"%>
        </div>
        <div id="right_wrap">
            <select>
                <option>한국어</option>
            </select>
        </div>
    </div>
</body>
</html>


<script>
    $(function() {
        
        /*포커스가 잡히면 테두리에 효과를준다.*/
        $('input[type=text]').focusin(function() {
            $(this).parent().css('border', '1px solid #00BB40');
        });
        /*포커스를 잃으면 테두리에 효과를 제거한다.*/
        $('input[type=text]').focusout(function() {
            $(this).parent().css('border', '1px solid #D9D9D9');
        });

        $("#login_btn").click(function() {
            if ($("#userid").val() === '') {
                $("#id").text("아이디를 입력해주세요.");
                return false;
            }else{
                $("#id").text("");
            }
            if ($("#password").val() === '') {
                $("#pw").text("비밀번호를를 입력해주세요.");
                return false;
            }else{
                $("#pw").text("");
            }
            return $('#login_form').submit();
        });

    });
</script>