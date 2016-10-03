package com.edu.vehicle;

import java.util.ArrayList;

import com.edu.member.Passenger;

public class Vehicle {

	ArrayList<Passenger> passengerList = new ArrayList<Passenger>();

	protected int capacity;

	protected int vehicleId;
	protected String vehicleColor;
	protected String vehicleType;
	protected String modelNo;
	protected String licensePlate;
	protected String driverName;

	protected int distance;
	protected int currentLongitude;
	protected int currentLatitude;

	protected String status;
	protected String vehicleSize;

	public String getVehicleSize() {
		return vehicleSize;
	}

	public void setVehicleSize(String vehicleSize) {
		this.vehicleSize = vehicleSize;
	}

	public ArrayList<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(ArrayList<Passenger> passengerList) {
		this.passengerList = passengerList;
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

	public Vehicle() {
		this.status = "hasFreeSpace";
	}

	public Vehicle(int vehicleId, String vehicleColor, String vehicleType, String modelNo, String licensePlate,
			String driverName, String status, String vehicleSize) {
		this.vehicleId = vehicleId;
		this.vehicleColor = vehicleColor;
		this.vehicleType = vehicleType;
		this.modelNo = modelNo;
		this.licensePlate = licensePlate;
		this.driverName = driverName;
		this.status = status;
		this.vehicleSize = vehicleSize;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getVacancy() {

		int vacancy = this.getCapacity() - passengerList.size();
		return vacancy;
	}

	public void addPassenger(Passenger passenger) {
		if (getVacancy() > 0) {
			passengerList.add(passenger);
			System.out.println("Passenger added");

		} else if (getVacancy() == 0) {
			this.setStatus("occupied");
		} else {
			System.out.println("Vehicle is full. can not add passengers ");
		}
	}

	public void removePassenger(Passenger passenger) {
		if (passengerList.size() > 0) {
			passengerList.remove(passenger);
			System.out.println("Passenger has been removed");
		} else {
			System.out.println("There are no passengers");
		}
	}

}
