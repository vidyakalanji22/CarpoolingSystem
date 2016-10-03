package com.edu.payment;

public class DebitcardStrategy implements PaymentStrategy {

	public void makePayment(float amount, String member) {
		System.out.println("amount $" +amount+ " payed using debit card. \nThank you for your payment");
	}
		
}
