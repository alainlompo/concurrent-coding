package org.hypernovae.apis.using.volatilevars;

public class HeatManager implements Runnable {
	
	private final int MAX_TEMP = 45;
	private int initialTemp = 15;
	volatile boolean notTooHot = true;
	volatile int currentTemp = initialTemp;
	
	public void run() {
		while (notTooHot) {
			currentTemp += 1;
			System.out.println("New temps via heater n: " + Thread.currentThread().getName() + " = " + currentTemp);
			try {
				Thread.sleep(3000);
			} catch (Exception ex) {
				
			}
			if (currentTemp >= MAX_TEMP) {
				notTooHot = false;
			}
		}
		
		System.out.println("Terminating heating process from thread " + Thread.currentThread().getName());	
	}
	
	
}
