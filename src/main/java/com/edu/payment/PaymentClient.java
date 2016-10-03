package com.edu.payment;

public class PaymentClient {
	
	public static void main(String[] args){
	PaymentContext payCotext = new PaymentContext();
	
	payCotext.makePayment("paypal", 25, "Passenger");
	}

}
