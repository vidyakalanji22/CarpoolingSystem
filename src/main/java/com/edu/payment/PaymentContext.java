package com.edu.payment;

import java.util.ArrayList;
import java.util.List;

import com.edu.notification.NotificationObservable;
import com.edu.notification.NotificationObserver;

public class PaymentContext implements NotificationObservable {

	private List<NotificationObserver> users = new ArrayList<NotificationObserver>();

	private PaymentStrategy ps;

	public void makePayment(String type, float amount, String member) {
		ps = setPaymentStrategy(type);
		ps.makePayment(amount, member);
	}

	public PaymentStrategy setPaymentStrategy(String n) {
		if (n.equalsIgnoreCase("debitcard")) {
			ps = new DebitcardStrategy();
		} else if (n.equalsIgnoreCase("creditcard")) {
			ps = new CreditCardStrategy();
		} else if (n.equalsIgnoreCase("paypal")) {
			ps = new PaypalStrategy();
		}
		return ps;
	}

	public void addObserver(NotificationObserver o) {
		users.add(o);

	}

	public void removeObserver(NotificationObserver o) {
		users.remove(o);

	}

	public void notifyObserver(String name) {
		if (!name.equals(null))
			System.out.println("Payment Notification for " + name);

		for (NotificationObserver user : users) {
			user.receiveNotification();
		}

	}
}
