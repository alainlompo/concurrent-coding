package org.hypernovae.apis.code.snippets;

/**
 * Illustrating Stack confinement: a particular case of Thread confinement
 * @author LOMPO
 * Local variables exist only on the executing Thread stack and that stack is not accessible to
 * other threads, thus they are intrinsically confined to the current Thread. 
 * The primitive type numberOfThreeFactors for example is stack confined and in the particular
 * case of primitively typed local variables, the Java language semantics ensure that they are always stack
 * confined and there is no way to violate the rule.
 *
 */
public class ThreadConfinement2 {
	public void showThreeFactorsFromZeroTo(int max) {
		int numberOfThreeFactors = 0;
		for (int i = 0; i <= max; i++) {
			if (i % 3 == 0) {
				numberOfThreeFactors++;
			}
		}
		System.out.println("The number of 3 factors between zero and "+ max + " is " + numberOfThreeFactors);		
	}
}
