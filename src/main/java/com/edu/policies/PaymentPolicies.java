package com.edu.policies;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class PaymentPolicies extends Policies {
	@Override
	public void definePolicy() {

		String policy = null;
		System.out.println("Enter Payment policy description");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		try {
			policy = input.readLine();
			if (policy.isEmpty()) {
				System.out.println("Please enter description");
				definePolicy();

			} else {
				setPolicyDescription(policy);
				Random rand = new Random();
				setPolicyId(rand.nextInt(100));
			}
		} catch (Exception e) {
			System.out.println("Error in recording policy");

		}
	}

	@Override
	public void modify() {

		String policy = null;
		System.out.println("Enter modified policy");
		BufferedReader inputRule = new BufferedReader(new InputStreamReader(System.in));
		try {
			policy = inputRule.readLine();
			if (policy != null) {
				setPolicyDescription(policy);
				System.out.println("policy has been modified");
			}
		}

		catch (Exception e) {
			System.out.println("Error in recording policy");
		}

	}
}
