<%@ page import="guestBook.model.Message"%>
<%@ page import="guestBook.model.MessageListView"%>
<%@ page import="guestBook.service.GetMessageListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuestBook</title>
</head>
<body>
<%@include file="../header.jsp"%>
	<form action="writeMessage.jsp" method="post">
		 아이디: ${loginMemberInfo.userid}<br />
		 <input type="hidden"  name="guestName" value="${loginMemberInfo.userid}"/>
		 메시지:<textarea name="message" cols="30" rows="3"></textarea><br />
		<input type="submit" value="메시지 남기기" />
	</form>

<%@include file="../footer.jsp"%>
</body>
</html>