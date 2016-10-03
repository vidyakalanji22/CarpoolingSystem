package com.edu.reports;

public class AdminReports extends MemberReports {

	public AdminReports() {
		super();
		reportType = "Admin report";
	}

	public AdminReports(String reportDesc) {
		this();
		this.reportDesc = reportDesc;
	}

	public void showReport() {
		super.showReport();
	}

}
