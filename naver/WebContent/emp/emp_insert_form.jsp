<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//1. JDBC 드라이버 로딩
	//Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	//String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
	String jdbcDriver = "jdbc:apache:commons:dbcp:pooltest";
	//+ "useUnicode=true&characterEncoding=utf8";
	String dbUser = "test";
	String dbPass = "1234";
	String query = "";
	try {
		// 2. 데이터베이스 커넥션 생성
		//conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		conn = DriverManager.getConnection(jdbcDriver);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 입력</title>
</head>
<body>
<%@include file="../header.jsp"%>
	<form action="emp_insert.jsp">
		<table border="1">
			<tr>
				<td>사원번호</td>
				<td><input type="text" name="empno" /></td>
			</tr>
			<tr>
				<td>사원이름</td>
				<td><input type="text" name="ename" /></td>
			</tr>
			<tr>
				<td>직급</td>
				<td><input type="text" name="job" /></td>
			</tr>
			<tr>
				<td>매니저</td>
				<td><select name="mgr" id="">
						<%
							query = "select empno, ename from emp where job='MANAGER' or job='PRESIDENT' order by ename";
								pstmt = conn.prepareStatement(query);

								//4. 쿼리실행
								rs = pstmt.executeQuery(query);
								while (rs.next()) {
						%>
						<option value="<%=rs.getInt(1)%>">(<%=rs.getInt(1)%>)
							<%=rs.getString(2)%>
						</option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>입사일</td>
				<td><input type="date" name="hiredate" /></td>
			</tr>
			<tr>
				<td>급여</td>
				<td><input type="number" name="sal" /></td>
			</tr>
			<tr>
				<td>커미션</td>
				<td><input type="number" name="comm" /></td>
			</tr>
			<tr>
				<td>부서번호</td>
				<td><select name="deptno" id="">
						<%
							query = "select deptno, dname from dept order by deptno";

								pstmt = conn.prepareStatement(query);

								//4. 쿼리실행
								rs = pstmt.executeQuery(query);
								while (rs.next()) {
						%>
						<option value="<%=rs.getInt(1)%>">(<%=rs.getInt(1)%>)
							<%=rs.getString(2)%>
						</option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="전송" /></td>
			</tr>
		</table>
	</form>
	<%
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
	<%@include file="../footer.jsp"%>
</body>
</html>