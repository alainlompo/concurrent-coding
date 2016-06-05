package org.hypernovae.apis.code.snippets.publications.and.escapes;

/**
 * An inner class instance contains a hidden reference to the enclosing
 * instance. So in this case, it means that when the new EventListener instance
 * is published, the UnsafeEvListenerPublisher instance is also implicitely published.
 * It means that [[this]] instance of UnsafeEvListenerPublisher is implictely published
 * Which is an issue because an object may not be in a completely constructed state before
 * its constructor returns. It is only after the constructor returns that [[this]] instance of
 * UnsafeEvListenerPublisher gets in a consistent and predictable state.
 * @author LOMPO
 *
 */
public class UnsafeEvListenerPublisher {
	public UnsafeEvListenerPublisher(EventSource eventSource) {
		eventSource.registerEventListener(
				new EventListener() {
					public void onEvent(Event event) {
						// TODO: do something with the event here
					}
				}
				);
	}

}
