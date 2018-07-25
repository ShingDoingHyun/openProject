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
	int empno = Integer.parseInt(request.getParameter("empno"));


	//응답결과를 구분하기 위한 변수

	int deleteCnt = 0;
	//2. db연결
	//jsbc드라이버로딩
	//Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = null;
	PreparedStatement pstmt = null;

	String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
	String dbUser = "test";
	String dbPass = "1234";
	String sql_update = "delete from emp where empno=?";

	try {
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		//3. Statement 생성
		pstmt = conn.prepareStatement(sql_update);
		pstmt.setInt(1, empno);
		deleteCnt = pstmt.executeUpdate();
		//4. sql 실행
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
		// 7. 커넥션 종료
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ex) {
			}

	}
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
	<a href="emp_list.jsp">사원리스트</a>
	<%
		} else {
	%>
	<h1>해당 데이터르 찾지 못했습니다.</h1>
	<%
		}
	%>

</body>
</html>