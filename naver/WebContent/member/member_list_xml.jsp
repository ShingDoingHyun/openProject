<%@page import="member.model.MemberInfo"%>
<%@page import="member.model.MemberInfoList"%>
<%@page import="member.service.GetMemberListService"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	GetMemberListService messageListService = GetMemberListService.getInstance();
	MemberInfoList viewData = messageListService.getMessageList();
%>
<?xml version="1.0" encoding="UTF-8"?>
<members>
<%
for (MemberInfo memberInfo : viewData.getMemberInfoList()) {
%>
	<member>
		<userid><%=memberInfo.getUserid()%></userid>
		<password><%=memberInfo.getPassword()%></password>
		<name><%=memberInfo.getName()%></name>
		<birthday><%=memberInfo.getBirthday()%></birthday>
		<gender><%=memberInfo.isGender()%></gender>
		<email><%=memberInfo.getEmail()%></email>
		<phone><%=memberInfo.getPhone()%></phone>
		<photo><%=memberInfo.getPhoto()%></photo>
	</member>
	<%
}
%>
</members>
