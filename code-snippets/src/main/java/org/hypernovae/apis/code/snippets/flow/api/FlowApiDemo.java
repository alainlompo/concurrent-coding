package org.hypernovae.apis.code.snippets.flow.api;

public class FlowApiDemo {
	public static void main(String[] args) throws InterruptedException {
		DummyPublisher publisher = new DummyPublisher();
		DummySubscriber dSA = new DummySubscriber("Subscrber-A");
		DummySubscriber dsB = new DummySubscriber("Subscriber-B");
		publisher.subscribe(dSA);
		publisher.subscribe(dsB);
		publisher.waitUntilEnded();
	}
}
