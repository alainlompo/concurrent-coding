package org.hypernovae.apis.using.volatilevars;

public class HeatingDemo {
	public static void main(String[] args) {
		Thread heater1 = new Thread(new HeatManager());
		HeatManager hMngr = new HeatManager();
		Thread heater2 = new Thread(hMngr);
		Thread heater3 = new Thread(hMngr);
		Thread heater4 = new Thread(hMngr);
		System.out.println("Starting the heating process....");
		heater1.start();
		heater2.start();
		heater3.start();
		heater4.start();
	}
}
