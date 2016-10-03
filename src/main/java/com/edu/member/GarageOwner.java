package com.edu.member;

public class GarageOwner extends Member {

	String garageId;
	String garageName;
	String garageLocation;

	public String getGarageId() {
		return garageId;
	}

	public void setGarageId(String garageId) {
		this.garageId = garageId;
	}

	public String getGarageName() {
		return garageName;
	}

	public void setGarageName(String garageName) {
		this.garageName = garageName;
	}

	public String getGarageLocation() {
		return garageLocation;
	}

	public void setGarageLocation(String garageLocation) {
		this.garageLocation = garageLocation;
	}

	@Override
	public String toString() {

		String garageInfo = "[" + this.getGarageName() + "(" + this.getGarageId() + ")" + this.getGarageLocation()
				+ "]";

		return super.toString() + " " + garageInfo;
	}

	public void receiveNotification() {
		System.out.println("Garage Owner has been notified");
	}

	@Override
	public void displayMemberDetails() {
		System.out.println("Garage Owner");

	}

}
