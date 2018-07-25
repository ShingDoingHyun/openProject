<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//1. 사용자 데이터 받기
	//사용자의 입력데이터의 한글처리
	request.setCharacterEncoding("utf-8");

	//사용자의 입력 데이터를 변수로 받는다.
	int empno = Integer.parseInt(request.getParameter("empno"));
	String ename = request.getParameter("ename");
	int sal = Integer.parseInt(request.getParameter("sal"));

	//응답결과를 구분하기 위한 변수
	int updateCnt = 0;
	//2. db연결
	//jsbc드라이버로딩
	//Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = null;
	//Statement stmt = null;
	PreparedStatement pstmt = null;

	//String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
	String jdbcDriver = "jdbc:apache:commons:dbcp:pooltest";
	//+ "useUnicode=true&characterEncoding=utf8";
	String dbUser = "test";
	String dbPass = "1234";
//	String sql_update = "update emp " + " set ename='" + ename + "', sal=" + sal + " where empno=" + empno;
	
	String sql_update = "update emp set ename=?, sal=? where empno=?";
	
	try {
		conn = DriverManager.getConnection(jdbcDriver);
		
	
		//3. Statement 생성
		//stmt = conn.createStatement();
		pstmt = conn.prepareStatement(sql_update);
		pstmt.setString(1, ename);
		pstmt.setInt(2, sal);
		pstmt.setInt(3, empno);
	
		//4. sql 실행
		//updateCnt = stmt.executeUpdate(sql_update);
		updateCnt = pstmt.executeUpdate();
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
		if (updateCnt > 0) {
	%>
	<h1>정보 변경이 정상적으로 처리되었습니다.</h1>
	<%
		} else {
	%>
	<h1>해당 데이터를 찾지 못했습니다.</h1>
	<%
		}
	%>








</body>
</html>