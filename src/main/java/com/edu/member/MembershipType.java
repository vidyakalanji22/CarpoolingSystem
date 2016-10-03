package com.edu.member;

public abstract class MembershipType extends Member {

	protected Member member;

	public MembershipType(Member member) {
		this.member = member;
	}

	public void displayMemberDetails() {
		member.displayMemberDetails();
	}

}
