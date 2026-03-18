package com.kruthik.javaEmail.service;

import java.io.File;

public interface MailService {

	String sendBasicMail(String to, String subject, String body);

	String sendMailWithAttachment(String to, String subject, String body, File attachment);
}
