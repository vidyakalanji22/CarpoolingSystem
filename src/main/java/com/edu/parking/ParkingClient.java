package com.edu.parking;

public class ParkingClient {
	
	public static void main(String[] args) throws Exception{
	RegularParking regParking = new RegularParking();
	
	ParkingType compParking = new CompactParking();
	
	GarageParking garageParking = new GarageParking(regParking);
	
	RoadSideParking roadParking = new RoadSideParking(compParking);
	
	garageParking.parkVehicle();
	garageParking.showParkingDetails("SFO", "South Garage", "12/12/2016", 10, 11, 15);
	
	roadParking.parkVehicle();
	roadParking.showParkingDetails("SanMateo", "East west Garage", "12/12/2016", 10, 11, 15);
	
	}
	

}
