package com.edu.member;

public class RegularMember extends MembershipType {

	public RegularMember(Member member) {
		super(member);
		member.setRegularMember(true);
	}

	public void displayMemberDetails() {
		super.displayMemberDetails();
	}

	public void discounts() {
		System.out.println("No discount for regular member");
	}

	public void receiveNotification() {
		System.out.println("Regular  member has been notified");

	}

}
