package com.edu.parking;

public class Garage {

	private int garageId;
	private String GarageName;
	private String garageLoc;
	private String parkingDate;
	private float fromTime;
	private float toTIme;
	private float parkingCost;

	public Garage(int garageId, String garageName, String garageLoc, String parkingDate, float fromTime, float toTime,
			float parkingCost) {
		this.garageId = garageId;
		this.GarageName = garageName;
		this.garageLoc = garageLoc;
		this.parkingDate = parkingDate;
		this.fromTime = fromTime;
		this.toTIme = toTime;
		this.parkingCost = parkingCost;
	}

	public int getGarageId() {
		return garageId;
	}

	public void setGarageId(int garageId) {
		this.garageId = garageId;
	}

	public String getGarageName() {
		return GarageName;
	}

	public void setGarageName(String garageName) {
		GarageName = garageName;
	}

	public String getGarageLoc() {
		return garageLoc;
	}

	public void setGarageLoc(String garageLoc) {
		this.garageLoc = garageLoc;
	}

	public String getParkingDate() {
		return parkingDate;
	}

	public void setParkingDate(String parkingDate) {
		this.parkingDate = parkingDate;
	}

	public float getFromTime() {
		return fromTime;
	}

	public void setFromTime(float fromTime) {
		this.fromTime = fromTime;
	}

	public float getToTIme() {
		return toTIme;
	}

	public void setToTIme(float toTIme) {
		this.toTIme = toTIme;
	}

	public float getParkingCost() {
		return parkingCost;
	}

	public void setParkingCost(float parkingCost) {
		this.parkingCost = parkingCost;
	}

	@Override
	public String toString() {
		return "Garage [garageId=" + garageId + ", GarageName=" + GarageName + ", garageLoc=" + garageLoc
				+ ", parkingDate=" + parkingDate + ", fromTime=" + fromTime + ", toTIme=" + toTIme + ", parkingCost="
				+ parkingCost + ", availability=";
	}

}
