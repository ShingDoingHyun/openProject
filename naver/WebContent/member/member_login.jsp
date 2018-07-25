<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Date"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//사용자의 입력데이터의 한글처리
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="memberInfo" class="member.model.MemberInfo" />
<jsp:setProperty name="memberInfo" property="*" />

<%
	//1. 사용자 데이터 받기

	//응답결과를 구분하기 위한 변수
	int loginCheck = 0;
	String pw = "";
	//2. db연결
	//jsbc드라이버로딩
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String jdbcDriver = "jdbc:apache:commons:dbcp:pooltest";

	String dbUser = "test";
	String dbPass = "1234";
	String sql_update = "select userid, password, name, email, photo from member where userid like ?";

	try {
		conn = DriverManager.getConnection(jdbcDriver);

		pstmt = conn.prepareStatement(sql_update);
		pstmt.setString(1, memberInfo.getUserid());

		rs = pstmt.executeQuery();

		if (rs.next()) {
			if (rs.getString("password").equals(memberInfo.getPassword())) {
				loginCheck = 1;
			}
		}
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if (loginCheck > 0) {
			//1. 구키객체 생성
			
			
			%>
	<jsp:useBean id="loginMemberInfo" class="member.model.MemberInfo"
		scope="session" />
	<jsp:setProperty name="loginMemberInfo"
		property="userid"
		value="${memberInfo.userid }" />
		<jsp:setProperty name="loginMemberInfo"
		property="password"
		value="<%=rs.getString(2) %>" />
		<jsp:setProperty name="loginMemberInfo"
		property="name"
		value="<%=rs.getString(3) %>" />
		<jsp:setProperty name="loginMemberInfo"
		property="email"
		value="<%=rs.getString(4) %>" />
		<jsp:setProperty name="loginMemberInfo"
		property="photo"
		value="<%=rs.getString(5) %>" />
	<%
		//Cookie c = new Cookie("userid", memberInfo.getUserid());

				//2. response 객체에 쿠키를 담는다.
				//response.addCookie(c);

				if (memberInfo.getSave_id() != null) {
					Cookie s = new Cookie("save_id", memberInfo.getUserid());

					//2. response 객체에 쿠키를 담는다.
					response.addCookie(s);
				} else {
					Cookie s = new Cookie("save_id", memberInfo.getUserid());
					s.setMaxAge(0);
					//2. response 객체에 쿠키를 담는다.
					response.addCookie(s);
				}
				response.sendRedirect("/naver");
			} else {
	%>
	<script>
			alert("아이디 혹은 비밀번호가 다릅니다.");
			location.href = "member_login_form.jsp?userid=${memberInfo.userid}";
	</script>
	<%
		}

		} catch (Exception e) {
			out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			// 6. 사용한 Statement 종료
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			// 7. 커넥션 종료
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}

		}
	%>

</body>
</html>
