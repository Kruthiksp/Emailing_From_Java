package com.kruthik.javaEmail.service;

import org.springframework.core.io.Resource;

import com.kruthik.javaEmail.dto.MailDTO;

public interface MailService {
	String sendBasicMail(MailDTO mailDTO);

	String sendAdvancedMail(MailDTO mailDTO, Resource attachment);
}
