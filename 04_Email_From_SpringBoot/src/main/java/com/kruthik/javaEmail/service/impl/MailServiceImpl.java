package com.kruthik.javaEmail.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kruthik.javaEmail.dto.MailDTO;
import com.kruthik.javaEmail.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String senderEmail;

	@Override
	public String sendBasicMail(MailDTO mailDTO) {
		return sendEmail(mailDTO);
	}

	private String sendEmail(MailDTO mailDTO) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(senderEmail);
			message.setTo(mailDTO.to());
			message.setSubject(mailDTO.subject());
			message.setText(mailDTO.body());

			mailSender.send(message);

			return "E-mail sent Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to send Mail Because; " + e.getMessage();
		}
	}

}
