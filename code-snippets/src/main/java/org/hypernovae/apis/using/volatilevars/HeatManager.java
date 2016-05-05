package org.hypernovae.apis.using.volatilevars;

/**
 * Through the use of volatile variable, a less strong form of synchronization, you
 * can ensure that ensure that any update to your variable is propagated to other threads
 * in a predictable way. As soon as a variable is marked as volatile, operations done on it
 * will not be reordered with other memory operations. Also volatile variables are not hidden from 
 * other processor (via a registry cache or other types of caches). As a consequence each time
 * you read a volatile variable you receive the latest update (the last write value from whatever thread
 * wrote to it the last)
 * It's very IMPORTANT to notice that the impact of volatile variable extends beyond the value of the
 * volatile variable itself. When thread t1 writes to a volatile variable and then Thread t2  read that
 * variable, all variables that were visible to t1 prior to writing to the volatile variable become visible
 * to t2 after reading the volatile variable as though reading the variable was equivalent to an action
 * of entering a Synchronized block and writing to it was equivalent to an action of exiting from a
 * Synchronized block
 * 
 * However using volatile variable for visibility is not recommended: it may fragilize the code and
 * make it harder to understand than code that uses locking. 
 * @author LOMPO
 *
 */
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
