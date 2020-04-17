package mail.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mail.domain.EmailVO;

@Service("emailService")
public class EmailService {

	@Autowired
	protected JavaMailSender mailSender; 

	public boolean sendMail(EmailVO email) throws Exception {

		try {

			MimeMessage msg = mailSender.createMimeMessage();

			msg.setSubject(email.getSubject());

			// 일반 텍스트만 전송하려는 경우
//			msg.setText(email.getContent());
			msg.setFrom(new InternetAddress("oksk327@naver.com"));
			/* msg.setFrom(new InternetAddress("발신자 이메일주소")); */
			// HTML 컨텐츠를 전송하려면.
//			"<span>인증번호 : </span><span>"+email.getContent()+"</span>"+
			msg.setContent(
					"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">" + 
					"<html>" + 
					"<head>" + 
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" + 
					"<title>인증 메일</title>" + 
					"</head>" + 
					"<body><div class=\"email-content\" style=\"font-size: 1.3em;\">" + 
					"<h1 style=\"font-size: 1.75em\">HRM &copy; Authentication code</h1><hr>" + 
					"<p>"+email.getContent()+"</p>" + 
					"</div></body>" + 
					"</html>", "text/html;charset=utf-8"
			);
			System.out.println(email.getReceiver());
			msg.setRecipient(RecipientType.TO, new InternetAddress(email.getReceiver()));//수신자 setting

			mailSender.send(msg);

			return true;

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return false;

	}

}