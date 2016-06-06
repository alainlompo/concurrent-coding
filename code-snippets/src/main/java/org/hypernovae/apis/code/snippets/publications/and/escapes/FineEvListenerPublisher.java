package org.hypernovae.apis.code.snippets.publications.and.escapes;

/**
 * The use of a factory method and a private reference to the event listener
 * allows us to register the listener without causing a [[this]] reference to escape
 * in an improperly construction of the object. See the notes below.
 * @author LOMPO
 *
 */
public class FineEvListenerPublisher {
	private final EventListener eventListener;
	
	private FineEvListenerPublisher() {
		eventListener = new EventListener() {

			public void onEvent(Event event) {
				// TODO do something here with the event object				
			}			
		};
	}
	
	public static FineEvListenerPublisher getNewInstance(EventSource eventSource) {
		
		// There is no [[this]] escape here for the this
		//  reference will not be registered before the constructor ends its course
		FineEvListenerPublisher properlyConstructed = new FineEvListenerPublisher();
		
		// Now the listener can be registered without causing a this reference escape
		eventSource.registerEventListener(properlyConstructed.eventListener);
		return properlyConstructed;		
	}
}
