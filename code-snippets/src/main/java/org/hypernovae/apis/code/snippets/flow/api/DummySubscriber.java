package org.hypernovae.apis.code.snippets.flow.api;

import java.util.Random;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import static java.lang.Thread.currentThread;

public class DummySubscriber implements Subscriber<Integer> {

	private static final String LOG_MESSAGE_FORMAT = "Subscriber %s ==> (%s) %s%n";
	private static final int NEED = 4;
	private static final Random RANDOMIZER = new Random();

	private String name;
	private Subscription subscription;

	private int count;

	public DummySubscriber(String name) {
		this.name = name;
	}

	@Override
	public void onComplete() {
		log("Complete...");
	}

	@Override
	public void onError(Throwable t) {
		log("Error (from the subscriber) :: %s", t);
	}

	@Override
	public void onNext(Integer item) {
		if (item != null) {
			log(item.toString());

			synchronized (this) {
				count--;

				if (count == 0) {
					if (RANDOMIZER.nextBoolean()) {
						count = NEED;
						requestItems(count);
					} else {
						count = 0;
						log("Cancelling subscription...");
						subscription.cancel();
					}
				}
			}
		} else {
			log("Null item...");
		}

	}

	@Override
	public void onSubscribe(Subscription subscription) {
		log("Subscribed...");
		this.subscription = subscription;
		count = NEED;

		requestItems(NEED);

	}

	private void requestItems(int n) {
		log("Requesting %d new items...", n);
		subscription.request(n);
	}

	private void log(String message, Object... args) {
		String formatedMessage = String.format(LOG_MESSAGE_FORMAT, this.name, currentThread().getName(), message);
		System.out.printf(formatedMessage, args);
	}

}
