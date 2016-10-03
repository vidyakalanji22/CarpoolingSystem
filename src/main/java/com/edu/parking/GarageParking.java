package com.edu.parking;

public class GarageParking extends Parking {

	public GarageParking(ParkingType pt) {
		super(pt);
	}

	@Override
	public void parkVehicle() {
		parkingType.parkVehicleInGarage();
	}

	public void showParkingDetails(String location, String garageName, String date, float fromTime, float toTime,
			float cost) throws Exception {
		super.showParkingDetails(location, garageName, date, fromTime, toTime, cost);
	}
}
