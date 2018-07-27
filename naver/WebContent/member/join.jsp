

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="member.service.joinMemberService"%>
<%@page import="com.bitcamp.op.member.model.MemberInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//사용자의 입력데이터의 한글처리
	request.setCharacterEncoding("utf-8");
%>




<%

	//이름이 겹치지 않게 따로 이름 설정
	String imgName = "";
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	MemberInfo minfo = new MemberInfo();
	if (isMultipart) {
		// 2. 메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory 설정
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 3. 업로드 요청을 처리하는 ServletFileUpload 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 4. 업로드 요청 파싱해서 FileItem 목록 구함
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = iter.next();

			// 5. FileItem이 폼 입력 항목인지 여부에 따라 알맞은 처리

			// 텍스트 입력인 경우 isFormField(-> type이 file타입 이외의 것인지 체크
			if (!item.isFormField()) {
				String fileName = item.getName();//업로드 파일 이름

				SimpleDateFormat name_date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_");
				String date = name_date.format(new java.util.Date());
				//저장하고자 하는 파일의 이름
				imgName = date + fileName;
				//웹서비스에서 사용되는 저장 경로
				String uploadURI = "/file/photo";
				//실제 물리적인 경로
				String dir = request.getSession().getServletContext().getRealPath(uploadURI);
				item.write(new File(dir, imgName));
				String photo=imgName;
				
				minfo.setPhoto(photo);


			}else{
				String name = item.getFieldName();//폼 데이터 이름
				String value = item.getString("utf-8");//밸류값 뒤는 인코딩
				
				switch(name){
					case  "userid" : 
						minfo.setUserid(value);
					break;
					case  "password" : 
						minfo.setPassword(value);
					break;
					case  "name" : 
						minfo.setName(value);
					break;
					case  "birthday" : 
						minfo.setBirthday(value);
					break;
					case  "gender" : 
						boolean g = value.equals("1")? true : false;
						minfo.setGender(g);
					break;
					case  "email" : 
						minfo.setEmail(value);
						break;
					case  "phone" : 
						minfo.setPhone(value);
					break;
					
				}

			}

		}
	}
		
	System.out.println(minfo);

	joinMemberService service = joinMemberService.getInstance();
	int insertCnt = service.join(minfo);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	성공여부 테스트 :
	<%=insertCnt%><br>
	<%
		if (insertCnt > 0) {
	%>

	<script>
		function login_fail() {
			alert("가입성공.");
			location.href = "/naver";
		}
	</script>

	<%
		} else {
	%>

	가입실패

	<%
		}
	%>

	<a href="/naver">메인으로</a>
</body>
</html>


