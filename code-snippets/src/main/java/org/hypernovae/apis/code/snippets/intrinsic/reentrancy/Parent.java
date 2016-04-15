package org.hypernovae.apis.code.snippets.intrinsic.reentrancy;

public class Parent {
	/**
	 * A thread that is able to execute this must have obtained its lock
	 */
	public synchronized void doSomeBusiness() {
		
	}
}
