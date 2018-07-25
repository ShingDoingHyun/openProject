<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//1. JDBC 드라이버 로딩
	//Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	//String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
	String jdbcDriver = "jdbc:apache:commons:dbcp:pooltest";
	//+ "useUnicode=true&characterEncoding=utf8";
	String dbUser = "test";
	String dbPass = "1234";
	//String query = "select empno, ename from emp order by ename";
	int chk = 0;
	int empno = Integer.parseInt(request.getParameter("empno"));
	

	String sql = "select empno, ename, sal from emp where empno=" + empno;
	// 2. 데이터베이스 커넥션 생성

	try {
		//conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		conn = DriverManager.getConnection(jdbcDriver);
		// 3. Statement 생성
		stmt = conn.createStatement();

		//4. 쿼리실행
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			
		
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 수정</title>
</head>
<body>
	<h1>사원정보수정</h1>
	<hr>

	<form action="emp_edit.jsp">
		<table border="1">
		<input type="hidden" name="empno" value="<%=rs.getString(1) %>" />
			<tr>
				<td>사원번호</td>
				<td>(<%=rs.getString(1) %>) <%=rs.getString(2) %></td>
			</tr>
			<tr>
				<td>사원이름</td>
				<td><input type="text" name="ename" value="<%=rs.getString(2) %>"/></td>
			</tr>
			<tr>
				<td>급여</td>
				<td><input type="number" name="sal" value="<%=rs.getString(3) %>"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="변경" /></td>
				<td><input type="reset" value="취소" /></td>
			</tr>
		</table>
	</form>
	<%
		}
		} catch (Exception e) {
			out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			// 6. 사용한 Statement 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (stmt != null)
				try {
					stmt.close();
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