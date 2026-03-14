package com.kruthik.javaEmail.service.impl;

import com.kruthik.javaEmail.config.MailConfig;
import com.kruthik.javaEmail.service.MailService;

import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailServiceImpl implements MailService {

	private static final Session SESSION = MailConfig.getSession();
	private static final String SENDER_EMAIL = MailConfig.get("mail.username");
	private static final String SUCCESS = "Email sent Successfully";

	@Override
	public String sendBasicMail(String to, String subject, String body) {
		return sendEmail(to, subject, body);
	}

	private String sendEmail(String to, String subject, String body) {
		try {
			Message message = new MimeMessage(SESSION);
			message.setFrom(new InternetAddress(SENDER_EMAIL));
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			return SUCCESS;
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Failed to send Email";
		}
	}

}
