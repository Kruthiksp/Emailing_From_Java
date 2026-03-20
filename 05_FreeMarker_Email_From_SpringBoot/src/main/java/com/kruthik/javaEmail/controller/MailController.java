package com.kruthik.javaEmail.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruthik.javaEmail.dto.MailDTO;
import com.kruthik.javaEmail.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;

	@PostMapping("/basicMail")
	public ResponseEntity<String> sendBasicMail(@RequestBody MailDTO mailDTO) {
		String response = mailService.sendBasicMail(mailDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/advancedMail")
	public ResponseEntity<String> sendAdvancedMail(@RequestBody MailDTO mailDTO) {
		Resource attachment = new ClassPathResource("images/SampleImage.png");
		String response = mailService.sendAdvancedMail(mailDTO, attachment);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
