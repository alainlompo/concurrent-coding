package org.hypernovae.apis.code.snippets;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ThreadConfinement1Test {
	
	@Test
	public void getSquaredPairsTest() {
		List<Integer> datas = Arrays.asList(1,2,4,16,5,6,36);
		assertEquals(4, new ThreadConfinement1().getSquaredPairs(datas));
	}

}
