package com.edu.member;

public class PremiumMember extends MembershipType {

	public PremiumMember(Member member) {
		super(member);
		member.setPremiumMember(true);
	}

	public void displayMemberDetails() {
		super.displayMemberDetails();
		System.out.println("Premium member");
	}

	public void getDiscount(float amount) {
		System.out.println("Premium member gets 10% discount");
	}

	public float redeemDiscount(float amount) {
		System.out.println("Redeeming 10% discount for Premium member");
		return amount - 5;

	}

	public void receiveNotification() {
		System.out.println("Premium member has been notified");

	}
}
