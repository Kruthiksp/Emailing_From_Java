package com.kruthik.javaEmail;

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

			MailServiceImpl mailServiceImpl = new MailServiceImpl();
			String response = mailServiceImpl.sendBasicMail(to, subject, body);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
