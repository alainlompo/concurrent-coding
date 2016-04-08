package org.hypernovae.apis.code.snippets;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ThreadConfinement1 {
	
	/**
	 * With Stack confinement an object can only be reached through local variables.
	 * Thus the local variable being confined to the executing thread (this is intrinsically the case),
	 * they only exist on the executing thread's stack and therefore will not be accessible by another thread
	 * This approach is simple to maintain compared to ad - hoc thread confinement
	 * When we are dealing with primitive types it then becomes impossible to violate the thread
	 * confinement because the java language semantics prevents it.(Primitive local variables are always
	 * thread confined).
	 * @param datas
	 * @return
	 */
	public int getSquaredPairs(List<Integer> datas) {
		SortedSet<Integer> confinedDatas;
		int numOfPairs = 0;
		Integer candidateData = null;
		
		// Confining the datas to the method
		confinedDatas = new TreeSet<Integer>();
		confinedDatas.addAll(datas);
		
		// TODO: note that at this point there is only one reference to the confinedDatas
		//  Therefore it is thread confined. This can be violated if we pass a reference
		// of confinedDatas to any other method.
		for (Integer i: confinedDatas) {
			candidateData = i;
			Integer target = new Integer(candidateData.intValue() * candidateData.intValue());
			if (confinedDatas.contains(target)) {
				numOfPairs++;
			}
		}
		return numOfPairs;
	}
}
