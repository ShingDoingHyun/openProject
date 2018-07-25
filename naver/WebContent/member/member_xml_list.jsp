<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<title>Insert title here</title>
</head>
<body>
<div id="mem">
</div>
</body>
</html>
<script>

	$(function() {
		$('#mem').append('<table>');
			$.ajax({
			    url : 'member_list_xml.jsp',
			    success : function(data) {
			        $(data).find('member').each(function() {
			            var userid = $(this).find('userid').text();
			            var password = $(this).find('password').text();
			            var birthday = $(this).find('name').text();
			            var gender = $(this).find('gender').text();
			            var email = $(this).find('email').text();
			            var phone = $(this).find('phone').text();
			            var photo = $(this).find('photo').text();
			         
			       
			            $('#mem').append(
			            		'<tr><td>'+userid+'</td><td>'+password+'</td><td>'+birthday+'</td><td>'+gender+'</td><td>'+email+'</td><td>'+phone+'</td><td>'+photo+'</td></tr>');
			         
			        });
			    }
			}); 
		 	$('#mem').append('</table>');
});
</script>