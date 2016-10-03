package com.edu.policies;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class Policies {

	private int policyId;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyDescription() {
		return policyDescription;
	}

	public void setPolicyDescription(String policyDescription) {
		this.policyDescription = policyDescription;
	}

	private String policyDescription;

	// template method
	public void makePolicy() {
		definePolicy();

		if (modifyPolicy()) {
			modify();
		}

		getApproval();
		publishPolicy();
	}

	private void publishPolicy() {
		System.out.println("The following policy has been published");
		System.out.println("Policy Id: " + getPolicyId());
		System.out.println("Policy description: " + getPolicyDescription());
	}

	private void getApproval() {
		System.out.println("This policy has been approved");

	}

	public abstract void modify();

	private boolean modifyPolicy() {
		String input = null;
		System.out.println("Do you want to modify this policy?[y/n]");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = reader.readLine();
			if (input.toLowerCase().startsWith("y")) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error in receiving input");

		}
		return false;
	}

	public abstract void definePolicy();

}
