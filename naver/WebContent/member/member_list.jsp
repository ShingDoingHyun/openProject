<%@page import="com.bitcamp.op.member.model.MemberInfoList"%>
<%@page import="com.bitcamp.op.member.model.MemberInfo"%>
<%@page import="member.service.GetMemberListService"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 테이블 직원 리스트 출력</title>
<style>
td {
	text-align: right;
	padding: 10px 20px;;
	border-bottom: 1px solid gray;
}
</style>
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
		GetMemberListService messageListService = GetMemberListService.getInstance();
		MemberInfoList viewData = messageListService.getMessageList(pageNumber);
	%>

	<div class="wrap">
		<h2>회원 테이블 직원 목록</h2>
		<table border="1" width="1000px">
			<tr>
				<td>ID</td>
				<td>비밀번호</td>
				<td>이름</td>
				<td>생일</td>
				<td>성별</td>
				<td>이메일</td>
				<td>전화번호</td>
				<td>관리</td>


			</tr>

			<%
			for (MemberInfo memberInfo : viewData.getMemberInfoList()) {
			%>

			<tr>
				<td><%=memberInfo.getUserid()%></td>
				<td><%=memberInfo.getPassword()%></td>
				<td><%=memberInfo.getName()%></td>
				<td><%=memberInfo.getBirthday()%></td>
				<td><%=memberInfo.isGender() == true ? "남" : "여"%></td>
				<td><%=memberInfo.getEmail()%></td>
				<td><%=memberInfo.getPhone()%></td>
				<td>
					<a href="member_edit_form.jsp?userid=<%=memberInfo.getUserid()%>" style="display: inline;">수정</a><a href="member_delete.jsp?userid=<%=memberInfo.getUserid()%>" style="display: inline;">삭제</a>
				</td>
			</tr>
			<%}%>

		</table>


		<%
				for (int i = 1; i <= viewData.getPageTotalCount(); i++) {
			%>
		<a href="member_list.jsp?page=<%=i%>">[<%=i%>]</a>
		<%
			}
		%>

	<h3><a href="member_list_xml.jsp">XML로 보기</a></h3>
	<h3><a href="member_list_json.jsp">JSON으로 보기</a></h3>

	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>