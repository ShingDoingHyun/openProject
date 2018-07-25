<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>가입동의</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!--    아이콘, 폰트 불러오기-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Archivo+Black"
	rel="stylesheet">
<style>
body {
	margin: 0;
	background: #F1F3F6;
}

#wrap {
	width: 450px;
	margin: 10px auto;
}

h1 {
	font-size: 60px;
	text-align: center;
	color: #00BC3D;
	font-family: 'Archivo Black', sans-serif;
}

h4 {
	margin-top: 15px;
	margin-bottom: 10px;
}

.font-logo-small {
	font-family: 'Archivo Black', sans-serif;
	color: #00BC3D;
}

p {
	font-size: 12px;
	color: #656565;
	text-align: center;
}

input[type=radio] {
	display: none;
}

input[type=radio]+label {
	display: block;
}

/*        여러칸을 감싸주는 div*/
.box {
	overflow: hidden;
}

/*        모든 input, select의 마진 설정*/
input, select, i {
	margin-left: 10px;
	margin-top: 10px;
}

/*        id입력칸 설정*/
#mId {
	float: left;
	width: 300px;
}

#mIdLabel {
	float: right;
	line-height: 50px;
	width: 130px;
	color: grey;
}

/*       입력칸을 감싸주는 div*/
.text-box {
	width: 450px;
	height: 50px;
	background: white;
	border: 1px solid #D9D9D9;
}

/*        입력칸*/
.text-box input, .text-box select {
	width: 430px;
	height: 30px;
	border: 0;
	display: block;
}

.pw input {
	width: 300px;
	float: left;
}

.pw div {
	margin-right: 10px;
	text-align: right;
	width: 100px;
	float: right;
}

.pw div span {
	line-height: 45px;
	font-size: 12px;
	margin-right: -5px;
}

.pw div i, .pw div span {
	float: right;
}

.material-icons {
	color: #9E9E9E;
}

/*        각각의 생일칸 div*/
.birthday-box>div {
	width: 140px;
	height: 50px;
	background: white;
	border: 1px solid #D9D9D9;
	display: inline-block;
}

/*        생일칸 입력*/
.birthday-box input, .birthday-box select {
	width: 115px;
	height: 30px;
	border: 0;
}

/*        가운데 생일칸에 마진을 준다*/
.birthday-box div:nth-child(2) {
	margin: 0px 6px;
}

/*        성별칸 설정*/
.gender-box>div {
	width: 223px;
	height: 50px;
	background: white;
	border: 1px solid #D9D9D9;
	display: inline-block;
	float: left;
	text-align: center;
	line-height: 50px;
	color: grey;
}

/*            폰관련 설정*/
.phone-box {
	margin: 10px 0;
}

.phone-box div:first-child {
	width: 297px;
	height: 50px;
	background: white;
	border: 1px solid #D9D9D9;
	float: left;
}

.phone-box div:first-child>input {
	width: 280px;
	height: 30px;
	border: 0;
}

.phone-box div:last-child {
	width: 140px;
	height: 50px;
	text-align: center;
	line-height: 50px;
	background: #00BC3D;
	color: white;
	float: right;
}

.disabled, .disabled input {
	background: #F0F2F5;
	line-height: 50px;
	color: #8D8D8D;
}

/*섬밋 버튼설정*/
#submit {
	margin-top: 30px;
	width: 450px;
	height: 60px;
	text-align: center;
	line-height: 60px;
	background: #00BC3D;
	color: white;
}

.red-alert {
	color: red;
	font-size: 12px;
	text-align: left;
}
</style>


</head>

<body>
	<div id="wrap">
		<h1>NAVER</h1>
		<form id="join_form" action="join.jsp" method="post"
			enctype="multipart/form-data">
			<div class="content-wrap">
				<h4>아이디</h4>
				<div class="text-box box">
					<input type="text" id="mId" name="userid"> <label for="mId"
						id="mIdLabel">@naver.com</label>
				</div>
				<p class="red-alert" id="idP"></p>
			</div>
			<div class="content-wrap">
				<h4>비밀번호</h4>
				<div class="text-box box pw">
					<input type="password" id="pwd1" name="password">
					<div>
						<i class="material-icons">&#xe899;</i> <span></span>
					</div>
				</div>
				<p class="red-alert" id="pw1P"></p>
			</div>
			<div class="content-wrap">
				<h4>비밀번호 재확인</h4>
				<div class="text-box box pw">
					<input type="password" id="pwd2">
					<div>
						<i class="material-icons">&#xe899;</i> <span></span>
					</div>
				</div>
				<p class="red-alert" id="pw2P"></p>
			</div>
			<br>
			<div class="content-wrap">
				<h4>이름</h4>
				<div class="text-box">
					<input type="text" id="name" name="name">
				</div>
				<p class="red-alert"></p>
			</div>
			<div class="content-wrap">
				<h4>생년월일</h4>
				<div class="birthday-box">
					<input type="hidden" id="birthday" name="birthday">
					<div>
						<input type="text" id="year" placeholder="년(4자)" name="year">
					</div>
					<div>
						<select id="month" name="month"><option value="">월</option></select>
					</div>
					<div>
						<input type="text" id="day" name="day" placeholder="일">
					</div>
				</div>
				<p class="red-alert" id="birthP"></p>
			</div>
			<div class="content-wrap">
				<h4>성별</h4>
				<div class="gender-box box ">
					<div>
						<input type="radio" id="man" name="gender" value="1"> <label
							for="man">남자</label>
					</div>
					<div>
						<input type="radio" id="woman" name="gender" value="0"> <label
							for="woman">여자</label>
					</div>
				</div>
				<p class="red-alert"></p>
			</div>
			<div class="content-wrap">
				<h4>본인 확인 이메일</h4>
				<div class="text-box">
					<input type="text" id="email" name="email">
				</div>
				<p class="red-alert"></p>
			</div>
			<div class="content-wrap">
				<h4>휴대전화</h4>
				<div class="text-box">
					<select id=country>

					</select>
				</div>
				<div class="phone-box box">
					<div>
						<input type="text" id="phone" name="phone">
					</div>
					<div>인증번호 받기</div>
				</div>
				<div class="text-box disabled">
					<input type="text" value="인증번호 입력하세요" disabled>
				</div>
				<p class="red-alert" id="emailP"></p>


			</div>
			<input type="file" name="photo" class="photo">

			<div id="submit">가입하기</div>
		</form>
		<p>이용약관 | 개인정보처리방침 | 책임의 한계와 법적고지 | 회원정보 고객센터</p>
		<p>
			<span class="font-logo-small">NAVER</span> Copyright NAVER Corp. All
			Rights Reserved.
		</p>
	</div>
</body>

</html>


<script>
	var birth = 0;
	$(function() {
		/*아이디 비밀번호 정규식체크*/
		var idCheck = /^[A-Za-z0-9_-]{5,20}$/;
		var passCheck = /^.*(?=^.{6,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		var date = new Date();

		/*100년 지난 사람은 정말인지 물어봄*/
		var year = date.getFullYear() - 100;

		/*달설정*/
		for (var mon = 1; mon <= 12; mon++)
			$('#month')
					.append("<option value=" + mon + ">" + mon + "</option>");

		/*국가설정*/
		var country = [ '대한민국 +82', '덴마크 +45' ];
		$.each(country, function(index) {
			$('#country').append(
					"<option value=" + country[index] + ">" + country[index]
							+ "</option>");
		});

		//        포커스가 잡히면 테두리에 효과를준다.
		$('input:not([type=file]), select ').focusin(function() {
			$(this).parent().css('border', '1px solid #00BB40');
		});
		//        포커스를 잃으면 테두리에 효과를 제거한다.
		$('input:not([type=file]), select ')
				.focusout(
						function() {
							$(this).parent().css('border', '1px solid #D9D9D9');
							/*입력값이 없을때 경고*/
							var id = $(this).attr('id');
							/*날짜,  비밀번호는 아래쪽 스위치문에서 공백체크, 이메일은 공백체크 안함*/
							if ($(this).val() === '' && id !== 'year'
									&& id !== 'month' && id !== 'day'
									&& id !== 'email' && id !== 'pwd1'
									&& id !== 'pwd2') {
								if (id !== 'phone')
									$(this).parent().parent()
											.find('.red-alert').text(
													"필수 정보입니다.").css('color',
													'red');
								else
									$(this).parent().parent().parent().find(
											'.red-alert').text("필수 정보입니다.");
							} else {
								/*해당 div 조건에따라 유효성 체크*/
								switch (id) {

								case 'mId':
									if (!idCheck.test($(this).val()))
										$('#idP')
												.text(
														"5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.")
												.css('color', 'red');
									else
										$('#idP').text("멋진아이디네요!").css('color',
												'#00BB40');
									break;
								case 'pwd1':
									if (!passCheck.test($(this).val())) {
										if ($(this).val() === "")
											$('#pw1P').text("필수 정보입니다.").css(
													'color', 'red');
										else {
											$('#pw1P')
													.text(
															"6~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
											$(this).parent().find('div').find(
													'i').css('color', 'red');
											$(this).parent().find('div').find(
													'span').text("사용불가").css(
													'color', 'red');
										}
									} else {
										$('#pw1P').text("");
										$(this).parent().find('div').find('i')
												.css('color', '#00BB40');
										$(this).parent().find('div').find(
												'span').text("안전").css('color',
												'#00BB40');
									}
									break;
								case 'pwd2':
									if ($(this).val() === "")
										$('#pw2P').text("필수 정보입니다.").css(
												'color', 'red');
									else {
										if ($('#pwd1').val() !== $(this).val()) {
											$('#pw2P').text("비밀번호가 일치하지 않습니다.");
											$(this).parent().find('div').find(
													'i').css('color', 'red');
											$(this).val('');
										} else {
											$('#pw2P').text("");
											$(this).parent().find('div').find(
													'i')
													.css('color', '#00BB40');
										}
									}
									break;
								case 'year':
								case 'month':
								case 'day':
									birth = 0;
									if ($('#year').val() === "")
										$('#birthP').text(
												"태어난 년도 4자리를 정확하게 입력하세요.");
									else if ($('#day').val() === "")
										$('#birthP').text(
												"태어난 일(날짜) 2자리를 정확하게 입력하세요.");
									else if ($('#month').val() === "")
										$('#birthP').text("생년월일을 다시 확인해주세요.");
									else {
										if ($('#year').val() < year)
											$('#birthP').text("정말이세요?");
										else {
											birth = 1;
											$('#birthP').text("");
										}
									}
									break;
								case 'phone':
									/*2경우 경고 공백으로*/
									$(this).parent().parent().parent().find(
											'.red-alert').text("");
								case 'name':
									/*2경우 경고 공백으로*/
									$(this).parent().parent()
											.find('.red-alert').text("");

								}
							}

						});

		/*라디오버튼 checked되면 껍데기 초록색으로*/
		$('input[type=radio]').change(function() {
			$('input[type=radio]').each(function() {
				if ($(this).prop('checked'))
					$(this).parent().css('border', '1px solid #00BB40');
				else
					$(this).parent().css('border', '1px solid #D9D9D9');
			});

		});

		$('#submit').click(
				function() {

					$('#birthday').val(
							$('#year').val() + '-' + $('#month').val() + '-'
									+ $('#day').val());

					if ($("#mId").val() === '') {
						alert("아이디를 입력해주세요.");
						return false;
					} else if ($("#pwd2").val() === '') {
						alert("비밀번호를를 입력해주세요.");
						return false;
					} else if ($("#name").val() === '') {
						alert("이름을 입력해주세요.");
						return false;
					} else if (birth !== 1) {
						alert("생년월일을 입력해주세요.");
						return false;
					} else if ($("#gender").val() === '') {
						alert("성별을 입력해주세요.");
						return false;
					} else if ($("#email").val() === '') {
						alert("email을 입력해주세요.");
						return false;
					} else if ($("#phone").val() === '') {
						alert("전화번호를 입력해주세요.");
						return false;
					}

					$('#join_form').submit();
				})
	});
</script>
