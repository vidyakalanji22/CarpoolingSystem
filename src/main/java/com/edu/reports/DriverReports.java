package com.edu.reports;

import java.io.BufferedReader;
import java.io.FileReader;

public class DriverReports extends MemberReports {

	public DriverReports() {
		super();
		reportType = "Driver report";
	}

	public DriverReports(String reportDesc) {
		this();
		this.reportDesc = reportDesc;
	}

	public void showReport() {
		super.showReport();

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

}
