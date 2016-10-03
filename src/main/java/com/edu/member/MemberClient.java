package com.edu.member;

public class MemberClient {
	
	public static void main(String[] args){

	Passenger pass = new Passenger();
	
	pass.displayMemberDetails();
	
	PremiumMember premMember = new PremiumMember(pass);
	premMember.displayMemberDetails();
	premMember.getDiscount(100);
	
	}
	

}
