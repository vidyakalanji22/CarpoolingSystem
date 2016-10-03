package com.edu.member;

public class Passenger extends Member {

	String creditCardNo;
	int cvv;
	String expirationDate;
	private String plan;
	private int membershipFee;
	private String paypalEmail;
	private String paypalPassword;
	private String paymentType;

	public Passenger(int id, String name, String contactNum, String email, String creditCardNum, int cvv,
			String expDate, String plan, String paymentType) {
		this.id = id;
		this.name = name;
		this.contactNo = contactNum;
		this.email = email;
		this.creditCardNo = creditCardNum;
		this.cvv = cvv;
		this.expirationDate = expDate;
		this.plan = plan;
		this.paymentType = paymentType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaypalEmail() {
		return paypalEmail;
	}

	public void setPaypalEmail(String paypalEmail) {
		this.paypalEmail = paypalEmail;
	}

	public String getPaypalPassword() {
		return paypalPassword;
	}

	public void setPaypalPassword(String paypalPassword) {
		this.paypalPassword = paypalPassword;
	}

	public int getMembershipFee() {
		return membershipFee;
	}

	public void setMembershipFee(int membershipFee) {
		this.membershipFee = membershipFee;
	}

	public Passenger() {

	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public void receiveNotification() {
		System.out.println("Passenger has been notified via email");
		System.out.println("Passenger has been notified via SMS");

	}

	@Override
	public void displayMemberDetails() {
		System.out.println("Passenger");

	}

}
