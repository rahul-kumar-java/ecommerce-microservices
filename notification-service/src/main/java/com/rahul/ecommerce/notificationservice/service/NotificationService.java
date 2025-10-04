package com.rahul.ecommerce.notificationservice.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void sendEmail(Long userId, String message) {
		System.out.println("Email sent to User ID  " + userId + " :  " + message);
	}
	
	public void sendSMS(Long userId, String message) {
		System.out.println("SMS sent to User ID  " + userId + " : " + message);
	}
}
