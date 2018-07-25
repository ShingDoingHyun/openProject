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
<title>EMP 테이블 직원 리스트 출력</title>
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
	<div class="wrap-1500">
		<h2>EMP 테이블 직원 목록</h2>
		<table border="1" width="1500px">
			<tr>
				<td>사원번호</td>
				<td>사원이름</td>
				<td>직급(업무)</td>
				<td>상사(이름)</td>
				<td>입사일</td>
				<td>급여</td>
				<td>커미션</td>
				<td>부서번호</td>
				<td>부서이름</td>
				<td>부서위치</td>
				<td>관리</td>
				<td>삭제</td>
			</tr>

			<%
				// 1. JDBC 드라이버 로딩
				//Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;

				try {
					//String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
					String jdbcDriver = "jdbc:apache:commons:dbcp:pooltest";
					String dbUser = "test";
					String dbPass = "1234";

					String query = "select e.empno, e.ename, e.job, ee.ename, e.hiredate, e.sal, nvl(e.comm, 0), e.deptno, d.dname, d.loc "
							+ " from emp e, emp ee, dept d " + " where e.mgr=ee.empno and e.deptno=d.deptno "
							+ " order by e.ename ";
					// 2. 데이터베이스 커넥션 생성
					//conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
					conn = DriverManager.getConnection(jdbcDriver);
					// 3. Statement 생성
					stmt = conn.createStatement();
					// 4. 쿼리 실행
					rs = stmt.executeQuery(query);
					// 5. 쿼리 실행 결과 출력
					while (rs.next()) {
			%>


			<tr>
				<td><%=rs.getInt(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(3)%></td>
				<td><%=rs.getString(4)%></td>
				<td><%=rs.getDate(5)%></td>
				<td><%=rs.getInt(6)%></td>
				<td><%=rs.getInt(7)%></td>
				<td><%=rs.getInt(8)%></td>
				<td><%=rs.getString(9)%></td>
				<td><%=rs.getString(10)%></td>
				<td><a href="emp_edit_form2.jsp?empno=<%=rs.getInt(1)%>">
						수정</a></td>
				<td><a href="emp_delete.jsp?empno=<%=rs.getInt(1)%>"> 삭제</a></td>
			</tr>
			<%
				}
				} catch (SQLException ex) {
					out.println(ex.getMessage());
					ex.printStackTrace();
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

		</table>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>