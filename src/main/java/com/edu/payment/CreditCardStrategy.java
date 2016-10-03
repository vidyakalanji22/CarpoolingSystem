package com.edu.payment;

import java.sql.Date;

public class CreditCardStrategy implements PaymentStrategy {

	private String name;
	private double cardNumber;
	private int cvv;
	private Date dateOfExpiry;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(double cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public void makePayment(float amount, String member) {
		System.out.println("amount $" +amount+ " payed using credit card. \nThank you for your payment");
	}
		
}
