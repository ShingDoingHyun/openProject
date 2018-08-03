package com.bitcamp.op.sendmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailSenderController {

	@Autowired
	SimpleMailSenderService simpleMailSenderService;

	@RequestMapping("/mail/simpleMail")
	public String simpleSendMail1() {
		simpleMailSenderService.simpleMailSend();
		return "sendmail";
	}

	@RequestMapping("/mail/htmlMail")
	public String HtmlSendMail() {
		simpleMailSenderService.htmlMailSend(null, null);
		return "sendmail";
	}

	@RequestMapping("/mail/fileMail")
	public String fileSendMail() {
		// 메일 발송
		simpleMailSenderService.fileMailSend();
		return "sendmail";
	}

}
