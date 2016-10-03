package com.edu.reports;

import java.util.ArrayList;
import java.util.List;

public abstract class MemberReports extends Reports {

	List<Reports> reportList = new ArrayList<Reports>();

	public void showReport() {
		super.showReport();
		for (int i = 0; i < reportList.size(); i++) {
			reportList.get(i).showReport();
		}
	}

	public void addReport(Reports r) {
		reportList.add(r);
	}

}
