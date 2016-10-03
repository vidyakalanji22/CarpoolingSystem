package com.edu.payment;

public class PaypalStrategy implements PaymentStrategy {

	private String emailId;
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void makePayment(float amount, String member) {
		System.out.println("amount $" +amount+ " payed using paypal. \nThank you for your payment");
	}
	
}
