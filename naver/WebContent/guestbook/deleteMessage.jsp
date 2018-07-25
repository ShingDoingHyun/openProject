<%@ page import="guestBook.service.DeleteMessageService"%>
<%@ page import="guestBook.service.InvalidMessagePassowrdException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	//1. 사용자 요청 데이터 받기
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	boolean invalidPassowrd = false;

	//3. 요청 처리 객체 생성
	DeleteMessageService deleteService = DeleteMessageService.getInstance();

	//3. 삭제처리
	try {
		deleteService.deleteMessage(messageId);
	} catch (InvalidMessagePassowrdException ex) {
		invalidPassowrd = true;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방멸록 삭제</title>
</head>
<body>
	<%
		if (!invalidPassowrd) {
	%>
	메시지를 삭제하였습니다.
	<%
		} else {
	%>
	입력한 암호가 올바르지 않습니다. 암호를 확인해주세요.
	<%
		}
	%>
	<br />
	<a href="list.jsp">[목록 보기]</a>
</body>
</html>