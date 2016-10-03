package com.edu.client;

import java.io.IOException;
import java.util.List;

import com.edu.request.*;

public class Menu {

	public Menu() {
		while (true)
			displayMenu();
	}

	private static void displayMenu() {
		System.out.println("****************************************************************");
		System.out.println("************** Welcome to A1 carpooling System *****************");
		System.out.println("****************************************************************");

		System.out.println("1. Administrator");
		System.out.println("2. Passenger");
		System.out.println("3. Driver");
		System.out.println("4. Garage Owner");
		System.out.println("0. Exit");

		System.out.println("Please Enter you Option: ");

		try {
			String input = Client.getReader().readLine();
			if (input.isEmpty() || input.length() >= 2) {
				System.out.println("Wrong Input. Please enter again.");
				displayMenu();
			} else {
				int inputOption = Integer.parseInt(input);
				switch (inputOption) {
				case 1:
					displayAdminMenu();
					break;
				case 2:
					displayPassengerMenu();
					break;
				case 3:
					displayDriverMenu();
					break;
				case 4:
					displayGarageOwnerMenu();
					break;
				case 0:
					System.out.println("You are exiting the system. Have a nice day!!! ");
					System.exit(0);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void displayGarageOwnerMenu() {
		GarageOwnerMenu gOwnerMenu = new GarageOwnerMenu();
		System.out.println("********** Displaying Garage Owner Menu **************");
		System.out.println("1. Register");
		System.out.println("2. Login ");
		System.out.println("0. Go to Main Menu");
		System.out.println("Please Enter you Option: ");
		String input;
		try {
			input = Client.getReader().readLine();
			if (input.isEmpty() || input.length() >= 2) {
				System.out.println("Wrong Input. Please enter again.");
				displayMenu();
			} else {
				int option = Integer.parseInt(input);
				switch (option) {
				case 1:
					gOwnerMenu.register();
					break;
				case 2:
					gOwnerMenu.login();
					break;
				case 0:
					break;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void displayDriverMenu() {

		DriverMenu driverMenu = new DriverMenu();

		System.out.println("********** Displaying Driver Menu **************");
		System.out.println("1. Register ");
		System.out.println("2. Login ");
		System.out.println("3. View rides ");
		System.out.println("0. Go to Main Menu");
		System.out.println("Please Enter you Option: ");
		String input;
		try {
			input = Client.getReader().readLine();
			if (input.isEmpty() || input.length() >= 2) {
				System.out.println("Wrong Input. Please enter again.");
				displayMenu();
			} else {
				int option = Integer.parseInt(input);
				switch (option) {
				case 1:
					driverMenu.register();
					break;
				case 2:
					driverMenu.login();
					break;
				case 3:
					driverMenu.viewRides();
					break;
				case 0:
					break;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void displayPassengerMenu() {

		PassengerMenu passengerMenu = new PassengerMenu();
		List<Request> requestList = null;

		System.out.println("********** Displaying Passenger Menu **************");
		System.out.println("1. Register ");
		System.out.println("2. Login ");
		System.out.println("3. Request a Ride ");
		System.out.println("4. Track ");
		System.out.println("0. Go to Main Menu");
		System.out.println("Please Enter you Option: ");
		String input;
		try {
			input = Client.getReader().readLine();
			if (input.isEmpty() || input.length() >= 2) {
				System.out.println("Wrong Input. Please enter again.");
				displayMenu();
			} else {
				int option = Integer.parseInt(input);
				switch (option) {
				case 1:
					passengerMenu.register();
					break;
				case 2:
					passengerMenu.login();
					break;
				case 3:
					requestList = passengerMenu.passengerRequests();
					passengerMenu.requestRide(requestList);
					break;
				case 4:
					PassengerMenu.trackRide();
					break;
				case 0:
					break;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void displayAdminMenu() {
		AdminMenu adminMenu = new AdminMenu();
		System.out.println("*** Displaying Admin Menu ******");
		System.out.println("1. Register a Vehicle");
		System.out.println("2. List of Vehicles");
		System.out.println("3. List of Passengers");
		System.out.println("4. List of Drivers");
		System.out.println("5. Update Policies");
		System.out.println("6. View Reports");
		System.out.println("0. Go to Main Menu");
		System.out.println("Please Enter you Option: ");
		String input;
		try {
			input = Client.getReader().readLine();
			if (input.isEmpty() || input.length() >= 2) {
				System.out.println("Wrong Input. Please enter again.");
				displayMenu();
			} else {
				int option = Integer.parseInt(input);
				switch (option) {
				case 1:
					adminMenu.registerVehicle();
					break;
				case 2:
					adminMenu.getVehicleList();
					break;
				case 3:
					adminMenu.getPassengerList();
					break;
				case 4:
					adminMenu.getDriverList();
					break;
				case 5:
					adminMenu.updatePolicies();
					break;
				case 6:
					adminMenu.viewReports();
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
