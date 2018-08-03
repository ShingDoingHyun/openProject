package com.bitcamp.op.sendmail.service;

import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SimpleMailSenderService {

	@Autowired
	JavaMailSender mailSender;

	public void simpleMailSend() {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("sdhyun486@gmail.com");
		message.setTo("sdhyun486@naver.com");
		message.setSubject("안녕하세요. 회원가입 추카합니다.");
		message.setText("축축~!");

		mailSender.send(message);

		System.out.println("발송 완료");

	}

	public void htmlMailSend(String userid, String email) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			message.setSubject("[안내] 회원가입 안내", "utf-8");
			String html = "<h1>안녕하세요 "+userid+"회원님</h1>회원가입 축하<br> 재방문을 할경우 아래 클릭<br><br><a href=\"http://localhost/op/\">사이트가기</a>";

			message.setText(html, "utf-8", "html");

			message.setFrom(new InternetAddress("sdhyun486@gmail.com"));

			message.addRecipients(RecipientType.TO, email);

			mailSender.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fileMailSend() {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			// 제목 설정
			messageHelper.setSubject("[공지] 안녕하세요 회원님.");

			// 내용 구성 ( HTML )
			String html = "<h1>안녕하세요 회원님</h1> 회원 가입을 진심으로 감사드립니다.<br>" + " 재 방문을 원하실 경우 아래 링크를 클릭해 주세요. <br><br>"
					+ "<a href=\"http://localhost/mvc0803\">사이트 가기</a>";

			// 내용 설정
			messageHelper.setText(html, true);

			// 보내는 사람의 메일 설정
			messageHelper.setFrom("sdhyun486@gmail.com", "신동현");

			// 받는 사람의 메일 설정
			messageHelper.setTo(new InternetAddress("sdhyun486@naver.com", "sdh", "utf-8"));

			// 파일 첨부 설정
			DataSource source = new FileDataSource("C:\\Users\\HB04-20\\Desktop\\설명.txt");
			messageHelper.addAttachment(MimeUtility.encodeText("설명.txt", "UTF-8", "B"), source);
			mailSender.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
