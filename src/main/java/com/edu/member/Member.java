package com.edu.member;

import com.edu.notification.NotificationObserver;

public abstract class Member implements NotificationObserver {

	int id;
	String name;
	String contactNo;
	String email;

	private boolean regularMember;
	private boolean premiumMember;

	public boolean isRegularMember() {
		return regularMember;
	}

	public void setRegularMember(boolean regularMember) {
		this.regularMember = regularMember;
	}

	public boolean isPremiumMember() {
		return premiumMember;
	}

	public void setPremiumMember(boolean premiumMember) {
		this.premiumMember = premiumMember;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		String string = this.getName() + "(email: " + this.getEmail() + ", Contact No: " + this.getContactNo() + ") ";
		return string;
	}

	public abstract void displayMemberDetails();

}
