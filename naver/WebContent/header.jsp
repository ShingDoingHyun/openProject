<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="https://fonts.googleapis.com/css?family=Archivo+Black"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/naver/css/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<jsp:useBean id="loginMemberInfo" class="com.bitcamp.op.member.model.MemberInfo" scope="session" />
<a href="/naver"><h1>SDH</h1></a>
<div style="background: #00BC3D">
	<ul class="box">
		<li id="loginBtn" class="left li-menu"><a
			href="/naver/member/member_login_form.jsp">로그인</a></li>
		<li id="" class="left li-menu noneLoginMenu"><a
			href="/naver/member/join_agree.jsp">회원가입</a></li>
		<li id="" class="left li-menu loginMenu"><a
			href="/naver/member/myPage.jsp">마이페이지</a></li>
		<li id="" class="left li-menu loginMenu"><a
			href="/naver/member/member_list.jsp">회원리스트</a></li>
		<li id="" class="left li-menu loginMenu"><a
			href="/naver/emp/emp_list.jsp">사원 리스트</a></li>
		<li id="" class="left li-menu loginMenu"><a
			href="/naver/emp/emp_insert_form.jsp">사원 등록</a></li>
		<li id="" class="left li-menu loginMenu"><a
			href="/naver/guestbook/guestbook_write.jsp">방명록쓰기</a></li>
					<li id="" class="left li-menu loginMenu"><a
			href="/naver/guestbook/list.jsp">방명록</a></li>
	</ul>
</div>



<script>
	$(function() {
		//var id = getCookie();
		var sId = '${loginMemberInfo.userid}';
		if (sId !== "") {
			$('#loginBtn').css('background', 'red').find('a').attr('href',
					'/naver/member/member_logout.jsp').text("로그아웃");
			$('.noneLoginMenu').css('display', 'none');
			$('.loginMenu').css('display', '');
		} else {
			$('.loginMenu').css('display', 'none');
			$('.noneLoginMenu').css('display', '');
		}
	});

	function logout() {

		var expire = new Date();
		expire.setDate(expire.getDate() - 1);
		var cookies = 'userid="";expires=' + expire.toGMTString() + ';';
		document.cookie = cookies;
		location.href = "/naver";
	}

	function getCookie() {
		var x, y;
		var val = document.cookie.split(';');

		for (var i = 0; i < val.length; i++) {
			x = val[i].substr(0, val[i].indexOf('='));
			y = val[i].substr(val[i].indexOf('=') + 1);
			x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
			if (x == 'userid') {
				return unescape(y); // unescape로 디코딩 후 값 리턴
			}
		}
	}
</script>
