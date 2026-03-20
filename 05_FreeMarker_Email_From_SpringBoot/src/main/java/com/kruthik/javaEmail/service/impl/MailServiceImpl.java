package com.kruthik.javaEmail.service.impl;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kruthik.javaEmail.dto.MailDTO;
import com.kruthik.javaEmail.service.MailService;

import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;
	private final Configuration templateConfig;
	@Value("${spring.mail.username}")
	private String senderEmail;

	@Override
	public String sendBasicMail(MailDTO mailDTO) {
		return sendEmail(mailDTO);
	}

	@Override
	public String sendAdvancedMail(MailDTO mailDTO, Resource attachment) {
		return sendEmailWithAttachment(mailDTO, attachment);
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

	private String sendEmailWithAttachment(MailDTO mailDTO, Resource attachment) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom(senderEmail);
			messageHelper.setTo(mailDTO.to());
			messageHelper.setSubject(mailDTO.subject());
			messageHelper.setText(formatEmailBody(mailDTO.body()), true);
//			(formatEmailBody, mailDTO.body());
			if (Objects.nonNull(attachment))
				messageHelper.addAttachment(attachment.getFilename(), attachment);

			mailSender.send(message);

			return "E-mail sent Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to send Mail Because; " + e.getMessage();
		}
	}

	private String formatEmailBody(String name) {
		Map<String, String> dataModel = new HashMap<>();
		dataModel.put("Name", name);

		Writer writer = new StringWriter();
		try {
			templateConfig.getTemplate("Hey.ftlh").process(dataModel, writer);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return name;
		}
	}

}
