<%@page import="member.service.DeleteMemberService"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%


	//사용자의 입력 데이터를 변수로 받는다.
	String userid = request.getParameter("userid");


	//응답결과를 구분하기 위한 변수
	DeleteMemberService deleteMemberService = DeleteMemberService.getInstance();
	int deleteCnt = deleteMemberService.delete(userid);

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		if (deleteCnt > 0) {
	%>
	<h1>삭제가 정상적으로 처리되었습니다.</h1>
	<a href="member_list.jsp">회원리스트</a>
	<%
		} else {
	%>
	<h1>해당 데이터르 찾지 못했습니다.</h1>
	<%
		}
	%>

</body>
</html>