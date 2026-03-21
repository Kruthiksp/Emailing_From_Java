package com.kruthik.javaEmail.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.kruthik.javaEmail.dto.MailDTO;

public interface MailService {
	String sendBasicMail(MailDTO mailDTO);

	String sendAdvancedMail(MailDTO mailDTO, Resource attachment);

	String sendwithAttachment(MailDTO mailDTO, MultipartFile attachment);
}
