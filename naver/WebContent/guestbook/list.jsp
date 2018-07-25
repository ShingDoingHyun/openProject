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
	<%
		request.setCharacterEncoding("utf-8");
		String pageNumberStr = request.getParameter("page");
		int pageNumber = 1;
		if (pageNumberStr != null) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		GetMessageListService messageListService = GetMessageListService.getInstance();
		MessageListView viewData = messageListService.getMessageList(pageNumber);
	%>
	<div class="wrap">

		<%
			if (viewData.isEmpty()) {
		%>
		등록된 메시지가 없습니다.
		<%
			} else { /* 메시지 있는 경우 처리 시작 */
		%>
		<table border="1" style="width:100%;">
			<tr>
				<td>메시지 번호</td>
				<td>손님 이름</td>
				<td>메시지</td>
				<td>삭제</td>
			</tr>
			<%
				for (Message message : viewData.getMessageList()) {
			%>
			<tr>
				<td><%=message.getId()%></td>
				<td><%=message.getGuestName()%></td>
				<td><%=message.getMessage()%></td>
				<td>
					<%
						if (message.getGuestName().equals(loginMemberInfo.getUserid())) {
					%> <a href="deleteMessage.jsp?messageId=<%=message.getId()%>">[삭제하기]</a>
					<%
						}
					%>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			for (int i = 1; i <= viewData.getPageTotalCount(); i++) {
		%>
		<a href="list.jsp?page=<%=i%>">[<%=i%>]
		</a>
		<%
			}
		%>
		<%
			} /* 메시지 있는 경우 처리 끝 */
		%>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>