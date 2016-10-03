package com.edu.parking;

public class RoadSideParking extends Parking {

	public RoadSideParking(ParkingType pt) {
		super(pt);
	}

	@Override
	public void parkVehicle() {
		parkingType.parkVehicleRoadSide();
	}

	public void showParkingDetails(String location, String garageName, String date, float fromTime, float toTime,
			float cost) throws Exception {
		super.showParkingDetails(location, garageName, date, fromTime, toTime, cost);
	}
}
