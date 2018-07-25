<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	$(function() {
		$('#mem').append('<table>');
		$.ajax({
			url : 'member_list_json.jsp',
			success : function(data) {
				$.each(data, function(key, value) {
					alert(value.userid);
					$('#mem').append(
							'<tr><td>' + value.userid + '</td><td>'
									+ value.password + '</td><td>'
									+ value.birthday + '</td><td>'
									+ value.gender + '</td><td>' + value.email
									+ '</td><td>' + value.phone + '</td><td>'
									+ value.photo + '</td></tr>');
				});
			}
		});
		$('#mem').append('</table>');
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="mem"></div>
</body>
</html>
