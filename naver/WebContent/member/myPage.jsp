<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Date"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="https://fonts.googleapis.com/css?family=Archivo+Black"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<title>가입동의</title>
</head>
<body>


	<%@include file="../header.jsp"%>
	<div class="memberInfo">
		<img src="../file/photo/${loginMemberInfo.photo }">
		<br> 아이디 : ${loginMemberInfo.userid} 
		<br> 이름 : ${loginMemberInfo.name } <br>
		EMAIL :${loginMemberInfo.email }<br>
	</div>
	<%@include file="../footer.jsp"%>

</body>
</html>


<script>
	$(function() {
		/*포커스가 잡히면 테두리에 효과를준다.*/
		$('input').focusin(function() {
			$(this).parent().css('border', '1px solid #00BB40');
		});
		/*포커스를 잃으면 테두리에 효과를 제거한다.*/
		$('input').focusout(function() {
			$(this).parent().css('border', '1px solid #D9D9D9');
		});
	});
</script>
