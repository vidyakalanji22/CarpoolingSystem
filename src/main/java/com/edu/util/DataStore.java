package com.edu.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.member.Driver;
import com.edu.member.Passenger;
import com.edu.parking.Garage;
import com.edu.ride.Ride;
import com.edu.vehicle.Sedan;
import com.edu.vehicle.Vehicle;
import com.edu.vehicle.XUV;

public class DataStore {

	public static List<Driver> driverList = new ArrayList<Driver>();
	public static List<Vehicle> vehicleList = new ArrayList<Vehicle>();
	public static List<Passenger> passengerList = new ArrayList<Passenger>();
	public static List<Garage> garageList = new ArrayList<Garage>();
	public static Map<String, ArrayList<Ride>> frequestRideList = new HashMap<String, ArrayList<Ride>>();
	public static List<Ride> rideList = new ArrayList<Ride>();

	private static ArrayList<String> weekdays = new ArrayList<String>(
			Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday"));
	private static ArrayList<String> weekends = new ArrayList<String>(Arrays.asList("Friday", "Saturday", "Sunday"));
	private static ArrayList<String> alterdays = new ArrayList<String>(Arrays.asList("Monday", "Wednesday", "Friday"));

	public static void main(String[] args) {

		intializeDrivers();
		initializeVehicles();
		initializePassengers();
		initializeFrequestRideList();
		intializeGarageList();

	}

	private static void initializeFrequestRideList() {

		String source1 = "SanJose", destin1 = "SanFrancisco";
		String source2 = "PaloAlto", destin2 = "Milbrae";
		String source3 = "Sunnyvale", destin3 = "SanMateo";
		ArrayList<Ride> rideList1 = new ArrayList<Ride>();
		ArrayList<Ride> rideList2 = new ArrayList<Ride>();
		ArrayList<Ride> rideList3 = new ArrayList<Ride>();

		Ride ride1;
		Vehicle veh;
		for (String day : weekdays) {
			veh = new Sedan(2200, "Red", "Sedan", "Accord", "CA98232", "Jane", "hasFreeSpace", "regular");
			ride1 = new Ride();
			ride1.setVehicle(veh);
			ride1.setPickUpLoc(source1);
			ride1.setDestination(destin1);
			ride1.setRideDate(day);
			ride1.setRideTime(10);
			rideList1.add(ride1);
		}

		Ride ride2;
		for (String day : weekends) {
			veh = new Sedan(2200, "Green", "Sedan", "Accord", "CA98232", "Jack", "hasFreeSpace", "compact");
			ride2 = new Ride();
			ride2.setVehicle(veh);
			ride2.setPickUpLoc(source2);
			ride2.setDestination(destin2);
			ride2.setRideDate(day);
			ride2.setRideTime(9);
			rideList2.add(ride2);
		}

		Ride ride3;
		for (String day : alterdays) {
			veh = new XUV(2200, "Black", "BMW X5", "Accord", "CA98232", "Lorence", "hasFreeSpace", "regular");
			ride3 = new Ride();
			ride3.setVehicle(veh);
			ride3.setPickUpLoc(source1);
			ride3.setDestination(destin1);
			ride3.setRideDate(day);
			ride3.setRideTime(12);
			rideList3.add(ride3);
		}

		frequestRideList.put(source1.concat("_").concat(destin1), rideList1);
		frequestRideList.put(source2.concat("_").concat(destin2), rideList2);
		frequestRideList.put(source3.concat("_").concat(destin3), rideList3);

	}

	private static void initializeVehicles() {
		for (Driver driver : driverList) {
			String driverName = driver.getName();
			vehicleList.add(new Sedan(22, "Black", "Sedan", "Accord", "CA9876", driverName, "hasFreeSpace", "regular"));
			vehicleList.add(new XUV(23, "Maroon", "XUV", "Honda", "CA6576", driverName, "hasFreeSpace", "regular"));
			vehicleList.add(new Sedan(25, "Blue", "Sedan", "Accord", "CA7776", driverName, "hasFreeSpace", "compact"));
			vehicleList
					.add(new Sedan(220, "Black", "Sedan", "Accord0", "CA9876", driverName, "hasFreeSpace", "regular"));
			vehicleList.add(new XUV(230, "Maroon", "XUV", "Honda0", "CA6576", driverName, "hasFreeSpace", "regular"));
			vehicleList
					.add(new Sedan(250, "Blue", "Sedan", "Accord0", "CA7776", driverName, "hasFreeSpace", "compact"));
		}
	}

	private static void initializePassengers() {
		try {
			String line = null;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/passenger.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] passenger = line.split(":");
				int cvv = Integer.parseInt(passenger[4]);
				Passenger p = new Passenger(14, passenger[0], passenger[1], passenger[2], passenger[3], cvv,
						passenger[5], passenger[6], passenger[7]);
				passengerList.add(p);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void intializeGarageList() {
		garageList.add(new Garage(1, "South main Garage", "SanFrancisco", "08/14/2016", 9.0f, 11.0f, 15.0f));
		garageList.add(new Garage(2, "North Garage", "SanMateo", "08/16/2016", 8.0f, 5.0f, 40.0f));
		garageList.add(new Garage(3, "Park easy Garage", "PaloAlto", "08/18/2016", 9.0f, 12.0f, 20.0f));
		garageList.add(new Garage(4, "East west Garage", "Milbrae", "08/19/2016", 7.0f, 11.0f, 50.0f));
	}

	private static void intializeDrivers() {
		try {
			String line = null;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/driver.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] driver = line.split(":");
				Driver d = new Driver(1, driver[0], driver[1], driver[2], driver[3], 5, 6, "available");
				driverList.add(d);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Driver> getDriverListByStatus(String status) {
		List<Driver> dList = new ArrayList<Driver>();
		for (Driver driver : driverList) {
			String driverStatus = driver.getStatus();
			if (driverStatus.equalsIgnoreCase(status)) {
				dList.add(driver);
			}
		}
		return dList;
	}

	public static List<Vehicle> getVehicleListByStatus(String status) {
		List<Vehicle> vList = new ArrayList<Vehicle>();
		for (Vehicle vehicle : vehicleList) {
			String vehicleStatus = vehicle.getStatus();
			if (vehicleStatus.equalsIgnoreCase(status)) {
				vList.add(vehicle);
			}
		}
		return vList;
	}

	public static Vehicle getVehicleByDriver(String driverName) {
		Vehicle vehicle = null;
		for (Vehicle veh : vehicleList) {
			String dName = veh.getDriverName();
			if (dName.equalsIgnoreCase(driverName)) {
				vehicle = veh;
				break;
			}
		}
		return vehicle;
	}

	public static Driver getDriverByName(String driverName) {
		Driver driver = null;
		for (Driver d : driverList) {
			String dName = d.getName();
			if (dName.equalsIgnoreCase(driverName)) {
				driver = d;
				break;
			}
		}
		return driver;
	}

	public static Passenger getPassengerByName(String name) {
		Passenger passenger = null;
		for (Passenger p : passengerList) {
			String pName = p.getName();
			if (pName.equalsIgnoreCase(name)) {
				passenger = p;
			}
		}

		return passenger;
	}

	public static List<Ride> getFrequestRidesBySourceAndDestin(String source, String destin) {
		String lookupKey = source.trim().concat("_").concat(destin.trim());
		ArrayList<Ride> freqRideList = new ArrayList<Ride>();

		if (frequestRideList.containsKey(lookupKey)) {
			freqRideList = frequestRideList.get(lookupKey);
		}
		return freqRideList;
	}

	public static Garage getGarageListByDestination(String destination) {
		Garage garage = null;
		for (Garage g : garageList) {
			String gDestination = g.getGarageLoc();
			if (gDestination.equalsIgnoreCase(destination)) {
				garage = g;
				break;
			}
		}
		return garage;
	}
}
