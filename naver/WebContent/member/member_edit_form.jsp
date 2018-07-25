<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원수정</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="wrap">
		<div style="width: 600px; margin: auto; text-align: center;">
			<h2>사원정보수정</h2>
			<hr>

			<form action="emp_edit.jsp" style="margin: 0 auto; width: 300px">
				<table border="1">
					<input type="hidden" name="empno" value="" />
					<tr>
						<td>아이디</td>
						<td>${param.userid }</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="text" name="password" value="" /></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="name" value="" /></td>
					</tr>
					<tr>
						<td>생일</td>
						<td><input type="text" name="sal" value="" /></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email" value="" /></td>
					</tr>
					<tr>
						<td>전환번호</td>
						<td><input type="number" name="phone" value="" /></td>
					</tr>
					<tr>
						<td>사진</td>
						<td><input type="file" name="photo" value="" /></td>
					</tr>

					<tr>
						<td><input type="submit" value="변경" /></td>
						<td><input type="reset" value="취소" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>