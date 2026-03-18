package com.kruthik.javaEmail;

import java.io.File;
import java.util.Scanner;

import com.kruthik.javaEmail.service.impl.MailServiceImpl;

public class App {
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter Recipent Email: ");
			String to = sc.nextLine();

			System.out.println("Enter Email Subject: ");
			String subject = sc.nextLine();

			System.out.println("Enter Email Body: ");
			String body = sc.nextLine();

			System.out.println("Do you want to attach the Attachment:  Y/N");
			String withAttachment = sc.next();
			boolean flag = withAttachment.toLowerCase().charAt(0) == 'y';

			MailServiceImpl mailServiceImpl = new MailServiceImpl();
			if (flag) {
				File attachment = new File("Screenshot 2025-05-10 123745.png");
				String response = mailServiceImpl.sendMailWithAttachment(to, subject, body, attachment);
				System.out.println(response);
			} else {
				String response = mailServiceImpl.sendBasicMail(to, subject, body);
				System.out.println(response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
