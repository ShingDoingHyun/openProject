<%@page import="guestBook.service.WriteMessageService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//1. 사용자 입력 데이터 받기
	//2. 요구사항을 처리할 서비스 객체 생성 (받아오는것)
	//3. 처리요청 (service.write 실행)
	//4. 결과 받아서 사용자에게 보여줄 응답 view를 작성

	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="message" class="guestBook.model.Message" />
<jsp:setProperty name="message" property="*" />
<%
	WriteMessageService service = WriteMessageService.getInstance();
	int resultCnt = service.write(message);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuestBook</title>
</head>
<body>
	<%
		if (resultCnt > 0) {
	%>
	<h1>
		방명록 메세지가 작성되었습니다.<br> 
		<a href="list.jsp">[리스트 보기]</a>
	</h1>
	<%
		} else {
	%>
	<h1>
		메세지가 정상적으로 작성되지 않았습니다!<br> 
		<a href="list.jsp">[리스트 보기]</a>
	</h1>

	<%
		}
	%>


</body>
</html>