package com.edu.member;

import java.util.ArrayList;

public class Driver extends Member {

	String licenceNo;
	String registrationDate;
	ArrayList<String> shiftDays;
	String shiftStartTime;
	String shiftEndTime;
	private int distance;
	private int currentLongitude;
	private int currentLatitude;
	private String status;

	public Driver() {

	}

	public Driver(int id, String name, String contactNum, String email, String licenseNum, int curLat, int curLong,
			String status) {
		this.id = id;
		this.name = name;
		this.contactNo = contactNum;
		this.email = email;
		this.licenceNo = licenseNum;
		this.currentLatitude = curLat;
		this.currentLongitude = curLong;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public ArrayList<String> getShiftDays() {
		return shiftDays;
	}

	public void setShiftDays(ArrayList<String> shiftDays) {
		this.shiftDays = shiftDays;
	}

	public String getShiftStartTime() {
		return shiftStartTime;
	}

	public void setShiftStartTime(String shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}

	public String getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getCurrentLongitude() {
		return currentLongitude;
	}

	public void setCurrentLongitude(int currentLongitude) {
		this.currentLongitude = currentLongitude;
	}

	public int getCurrentLatitude() {
		return currentLatitude;
	}

	public void setCurrentLatitude(int currentLatitude) {
		this.currentLatitude = currentLatitude;
	}

	public void receiveNotification() {
		System.out.println("Driver has been notified via email");
		System.out.println("Driver has been notified via SMS");

	}

	@Override
	public void displayMemberDetails() {
		System.out.println("Driver");

	}
}
