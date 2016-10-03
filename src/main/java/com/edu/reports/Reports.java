package com.edu.reports;

public abstract class Reports {

	public String reportType = "";
	public String reportDesc = "";

	public void showReport() {
		System.out.println(reportType + ": " + reportDesc);
	}
}
