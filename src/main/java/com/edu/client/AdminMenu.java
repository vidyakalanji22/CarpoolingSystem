package com.edu.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.edu.policies.DispatchPolicies;
import com.edu.policies.ParkingPolicies;
import com.edu.policies.PaymentPolicies;
import com.edu.reports.AdminReports;
import com.edu.reports.DriverReports;
import com.edu.reports.ParkReports;
import com.edu.reports.PassengerReports;
import com.edu.reports.Reports;
import com.edu.reports.RideReport;
import com.edu.vehicle.*;

public class AdminMenu {
	private BufferedReader bufferedReader;
	
	static{
		
	}

	public AdminMenu() {
		bufferedReader = Client.getReader();
	}

	public Vehicle registerVehicle() {
		Vehicle vehicle = new Vehicle();

		try {
			System.out.println();
			System.out.println("===================================================================");
			System.out.println("Vehicle registration form");
			System.out.println("===================================================================");
			System.out.println("Please enter details: ");

			System.out.println("Vehicle Color: ");
			vehicle.setVehicleColor(bufferedReader.readLine());

			System.out.println("Vehicle Type: [Sedan/XUV]");
			vehicle.setVehicleType(bufferedReader.readLine());

			System.out.println("Model Number: ");
			vehicle.setModelNo(bufferedReader.readLine());

			System.out.println("License Plate Number: ");
			vehicle.setLicensePlate(bufferedReader.readLine());

			System.out.println("Driver Name: ");
			vehicle.setDriverName(bufferedReader.readLine());

			System.out.println("Do you want to Submit registration? [y/n]: ");
			if (bufferedReader.readLine().equalsIgnoreCase("y")) {
				this.addVehicleToFile(vehicle.getVehicleColor(), vehicle.getVehicleType(), vehicle.getModelNo(),
						vehicle.getLicensePlate(), vehicle.getDriverName());

				System.out.println("You are successfully registered.");
			} else {
				System.out.println("Successfully Cancelled.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void getVehicleList() {
		try {
			String line = null;
			//String fileName = "config/vehicle";
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/vehicle.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPassengerList() {
		try {
			String line = null;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/passenger.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getDriverList() {
		try {
			String line = null;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/driver.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updatePolicies() {

		DispatchPolicies dp = new DispatchPolicies();
		ParkingPolicies pp = new ParkingPolicies();
		PaymentPolicies payPolicy = new PaymentPolicies();

		System.out.println("*** Displaying Policy Menu ******");
		System.out.println("1. Dispatch Policy");
		System.out.println("2. Parking Policy");
		System.out.println("3. Payment Policy");
		System.out.println("0. Go to Main Menu");
		System.out.println("Please Enter you Option: ");
		String input;
		try {
			input = Client.getReader().readLine();
			if (input.isEmpty() || input.length() >= 2) {
				System.out.println("Wrong Input. Please enter again.");
			} else {
				int option = Integer.parseInt(input);
				switch (option) {
				case 1:
					dp.makePolicy();
					break;
				case 2:
					pp.makePolicy();
					break;
				case 3:
					payPolicy.makePolicy();
					break;
				case 0:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addVehicleToFile(String vehicleColor, String vehicleType, String modelNum, String licenseNum,
			String driverName) throws Exception {
		File file = new File("C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/vehicle.txt");
		FileWriter writer = new FileWriter(file, true);
		writer.append(vehicleColor + ":" + vehicleType + ":" + modelNum + ":" + licenseNum + ":" + driverName + "\n");
		writer.close();
	}

	public void viewReports() {

		System.out.println("* Displaying Report Menu ***");
		System.out.println("1. Passenger Report");
		System.out.println("2. Driver Report");
		System.out.println("3. Ride Report");
		System.out.println("4. Parking Report");
		System.out.println("5. View all Reports");
		System.out.println("0. Go to Main Menu");
		System.out.println("Please Enter you Option: ");
		String input;
		try {
			input = Client.getReader().readLine();
			if (input.isEmpty() || input.length() >= 2) {
				System.out.println("Wrong Input. Please enter again.");
			} else {
				int option = Integer.parseInt(input);
				RideReport rideRep = new RideReport("Ride report Description");
				ParkReports parkRep = new ParkReports("Park report description");

				PassengerReports passeRep = new PassengerReports("Passenger Report description");
				DriverReports driverRep = new DriverReports("Driver report description");

				AdminReports adminRep = new AdminReports();
				Reports reports = adminRep;

				switch (option) {
				case 1:
					passeRep.showReport();
					break;
				case 2:
					driverRep.showReport();
					break;
				case 3:
					rideRep.showReport();
					break;
				case 4:
					parkRep.showReport();
					break;
				case 5:
					adminRep.addReport(rideRep);
					adminRep.addReport(parkRep);
					adminRep.addReport(passeRep);
					adminRep.addReport(driverRep);
					reports.showReport();
					break;
				case 0:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
