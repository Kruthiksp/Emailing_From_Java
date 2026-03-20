package com.kruthik.javaEmail.service;

import com.kruthik.javaEmail.dto.MailDTO;

public interface MailService {
	String sendBasicMail(MailDTO mailDTO);
}
