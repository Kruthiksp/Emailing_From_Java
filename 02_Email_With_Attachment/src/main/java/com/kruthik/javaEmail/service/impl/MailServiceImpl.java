package com.kruthik.javaEmail.service.impl;

import java.io.File;
import java.io.IOException;

import com.kruthik.javaEmail.config.MailConfig;
import com.kruthik.javaEmail.service.MailService;

import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class MailServiceImpl implements MailService {

	private static final Session SESSION = MailConfig.getSession();
	private static final String SENDER_EMAIL = MailConfig.get("mail.username");
	private static final String SUCCESS = "Email sent Successfully";

	@Override
	public String sendBasicMail(String to, String subject, String body) {
		return sendEmail(to, subject, body);
	}

	@Override
	public String sendMailWithAttachment(String to, String subject, String body, File attachment) {
		return sendEmailWithAttachment(to, subject, body, attachment);
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

	private String sendEmailWithAttachment(String to, String subject, String body, File attachment) {
		try {
			Message message = new MimeMessage(SESSION);
			message.setFrom(new InternetAddress(SENDER_EMAIL));
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);

			Multipart multipart = new MimeMultipart();

			BodyPart textPart = new MimeBodyPart();
			textPart.setText(body);
			multipart.addBodyPart(textPart);

			MimeBodyPart attachmentPart = new MimeBodyPart();
			try {
				attachmentPart.attachFile(attachment);
			} catch (IOException e) {
				e.printStackTrace();
			}
			multipart.addBodyPart(attachmentPart);

			message.setContent(multipart);

			Transport.send(message);
			return SUCCESS;
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Failed to send Email";
		}
	}

}
