package com.edu.vehicle;

import java.util.ArrayList;

import com.edu.member.Passenger;

public class Sedan extends Vehicle {

	ArrayList<Passenger> passengerList = new ArrayList<Passenger>();

	public Sedan() {
		this.setCapacity(4);
	}

	public Sedan(int vehicleId, String vehicleColor, String vehicleType, String modelNo, String licensePlate,
			String driverName, String status, String vehicleSize) {
		this();
		this.vehicleId = vehicleId;
		this.vehicleColor = vehicleColor;
		this.vehicleType = vehicleType;
		this.modelNo = modelNo;
		this.licensePlate = licensePlate;
		this.driverName = driverName;
		this.status = status;
		this.vehicleSize = vehicleSize;
	}
}
