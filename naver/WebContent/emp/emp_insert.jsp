<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="emp.model.EmpDTO"%>
<%@page import="java.sql.Date" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
    <jsp:useBean id="empDTO" class="emp.model.EmpDTO" />
	<jsp:setProperty name="empDTO" property="*" />
    
    <%
	//1. 사용자 데이터 받기
	//사용자의 입력데이터의 한글처리
	request.setCharacterEncoding("utf-8");

	//사용자의 입력 데이터를 변수로 받는다.


	//응답결과를 구분하기 위한 변수
	int insertCnt = 0;
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
	String sql_update = "insert into emp values (?,?,?,?,?,?,?,?)";

	try {
		//conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		conn = DriverManager.getConnection(jdbcDriver);
		//3. Statement 생성
		//stmt = conn.createStatement();
		pstmt = conn.prepareStatement(sql_update);
		pstmt.setInt(1, empDTO.getEmpno());
		pstmt.setString(2, empDTO.getEname());
		pstmt.setString(3, empDTO.getJob());
		pstmt.setInt(4, empDTO.getMgr());
		pstmt.setDate(5,Date.valueOf( empDTO.getHiredate()) );
		pstmt.setInt(6, empDTO.getSal());
		pstmt.setInt(7, empDTO.getComm());
		pstmt.setInt(8, empDTO.getDeptno());

		insertCnt = pstmt.executeUpdate();
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
		if (insertCnt > 0) {
	%>
	<h1>정보 입력이 정상적으로 처리되었습니다.</h1>
	<%
		} else {
	%>
	<h1>해당 데이터를 입력하지 못했습니다.</h1>
	<%
		}
	%>

<a href="/JDBC">emp테이블 직원 리스트 출력</a>

</body>
</html>