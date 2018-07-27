<%@page import="member.model.MemberInfo"%>
<%@page import="member.model.MemberInfoList"%>
<%@page import="member.service.GetMemberListService"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
	GetMemberListService messageListService = GetMemberListService.getInstance();
	MemberInfoList viewData = messageListService.getMessageList();
%>
[<%
for (MemberInfo memberInfo : viewData.getMemberInfoList()) {
%>
{
"userid" : "<%=memberInfo.getUserid()%>",
"password" : "<%=memberInfo.getPassword()%>",
"name": "<%=memberInfo.getName()%>",
"birthday": "<%=memberInfo.getBirthday()%>",
"gender": "<%=memberInfo.isGender()%>",
"email": "<%=memberInfo.getEmail()%>",
"phone": "<%=memberInfo.getPhone()%>",
"photo": "<%=memberInfo.getPhoto()%>"
}
<%
}
%>
]
