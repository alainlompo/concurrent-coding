package org.hypernovae.apis.code.snippets.intrinsic.reentrancy;

public class Descendant extends Parent {
	/**
	 * This code will not deadlock because of intrinsic reentrancy
	 * locks are acquired on thread basis rather than on request basis
	 * So since the thread has already acquired the lock a request to the same
	 * lock when invoking the super.doSomeBusiness will succeed and not block
	 */
	public synchronized void doSomeBusiness() {
		System.out.println("The descendant doing business and invoking the parent doSomeBusiness method...");
		super.doSomeBusiness();
	}

}
