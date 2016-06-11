package org.hypernovae.apis.code.snippets;

/**
 * A simple illustration of the use of ThreadLocal to enforce thread confinement
 * ThreadLocal allows us to associate a per-thread value with a value holding object
 * Each thread gets its own copy of the value and can access it using classic accessors like get and set
 * A get obtains the most recent value that was set through the current thread.
 * Assuming that the BDContext object is a global application level context that is initialized
 * once at the starting of the application, its access is not thread safe without additionnal safety management
 * effort. Now by using the ThreadLocal to handle the BDContext, each thread will access its own BCContext.
 * 
 * @author LOMPO
 *
 */
public class ThreadConfinement3 {
	private static ThreadLocal<BDContext> contextHolder
	= new ThreadLocal<BDContext>() {
		public BDContext initialValue() {
			return initialize();
		}
	};
	
	private static BDContext initialize() {
		BDContext context =null;
		// TODO: initialize the context in a safe fashion here...
		return context;
	}
	
	public static BDContext getContext() {
		return contextHolder.get();
	}
}
