package com.edu.parking;

import java.io.File;
import java.io.FileWriter;

public abstract class Parking {
	protected ParkingType parkingType;

	protected int garageId;
	protected String location;
	protected String date;
	protected String fromTime;
	protected String toTime;

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public ParkingType getParkingType() {
		return parkingType;
	}

	public void setParkingType(ParkingType parkingType) {
		this.parkingType = parkingType;
	}

	public int getGarageId() {
		return garageId;
	}

	public void setGarageId(int garageId) {
		this.garageId = garageId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Parking(ParkingType pt) {
		this.parkingType = pt;
	}

	public Parking(int garageId, String location, String date, String fromTime, String toTime) {

	}

	public abstract void parkVehicle();

	public void showParkingDetails(String location, String garageName, String date, float fromTime, float toTime,
			float cost) throws Exception {
		if(location.equals(null)){
			System.out.println("No garage available for this location");
		}else{
		System.out.println("");
		System.out.println("=====================Parking Details=====================");
		System.out.println("Location: " + location);
		if (!garageName.equals("")) {
			System.out.println("Garage Name: " + garageName);
		}
		System.out.println("Date: " + date);
		System.out.println("From: " + fromTime);
		System.out.println("To: " + toTime);
		System.out.println("Cost: $" + cost);
		addParkingToFile(location, garageName, date, fromTime, toTime, cost);
	}
	}

	public void addParkingToFile(String location, String garageName, String date, float fromTime, float toTime,
			float cost) throws Exception {
		File file = new File("C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/park.txt");
		FileWriter writer = new FileWriter(file, true);
		writer.append(location + ":" + garageName + ":" + date + ":" + fromTime + ":" + toTime + ":" + cost + "\n");
		writer.close();
	}

}
