package org.hypernovae.apis.code.snippets.memory.visibility;

public class MemoryVisibilityIssues {
	private static boolean isReady = false;
	private static Integer intObjValue;
	
	private static class ReadingThread extends Thread {
		public void run() {
			while(!isReady) {
				// Gives a hint to the thread scheduler
				// That the current thread is willing to yield its current
				// use of the processor. If it is not ignored by the scheduler
				// It will result in a positive impact on performances (it's a heuristic)
				Thread.yield();
			}
			
			System.out.println(intObjValue);
		}
		
		/**
		 * This practice is to be avoided.
		 * It might seem obvious that this program will print 120, and it will do so often, but there
		 * is a possibility that it never ends or it prints null (non initialized intObjValue)
		 * As a matter of fact insufficient synchronization can produce stale datas. the ReadingThread instance
		 * might see an out - of - date isReady value.
		 * Stale datas can be the source of important issues such as:
		 * ==> infinite loops
		 * ==> inaccurate computations
		 * ==> unexpected exceptions
		 * ==> corrupted data structures
		 * When the access to a given data is synchronized there is a guaranty that two threads accessing
		 * successfully to the data using the same will see the up - to - date value of the data
		 * @param args
		 */
		public static void main(String[] args) {
			new ReadingThread().start();
			intObjValue = 120;
			isReady = true;
		}
		
		
	}

}
