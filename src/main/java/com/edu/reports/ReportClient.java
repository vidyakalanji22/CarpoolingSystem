package com.edu.reports;


public class ReportClient {

	public static void main(String[] args) {
		
		DriverReports driverReports = new DriverReports("Driver description");
		
		PassengerReports passengerReports = new PassengerReports("Passenger description");
		
		AdminReports adminReports = new AdminReports();
		Reports reports = adminReports;
		
		adminReports.addReport(driverReports);
		adminReports.addReport(passengerReports);
		
		reports.showReport();
	}

}
