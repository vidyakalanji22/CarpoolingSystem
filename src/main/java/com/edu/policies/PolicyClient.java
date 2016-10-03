package com.edu.policies;

public class PolicyClient {

	public static void main(String[] args) {
      DispatchPolicies disPoli = new DispatchPolicies();
      PaymentPolicies paymentPolicies = new PaymentPolicies();
      ParkingPolicies parkingPolicies = new ParkingPolicies();
      
      disPoli.makePolicy();
      paymentPolicies.makePolicy();
      parkingPolicies.makePolicy();
      
	}

}
