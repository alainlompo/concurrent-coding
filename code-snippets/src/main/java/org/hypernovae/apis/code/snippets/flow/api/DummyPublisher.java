package org.hypernovae.apis.code.snippets.flow.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

public class DummyPublisher implements Publisher<Integer> {
	final ExecutorService executorService = Executors.newFixedThreadPool(6);
	private List<DummySubscription> subscriptions = Collections.synchronizedList(new ArrayList<DummySubscription>());
	final SubscriptionCancelService subscriptionCancelService = new SubscriptionCancelService(subscriptions);
	private final CompletableFuture<Void> ended = new CompletableFuture<>();

	public void subscribe(Subscriber<? super Integer> subscriber) {
		DummySubscription subscription = new DummySubscription(subscriber, executorService, subscriptionCancelService, ended);
		subscriptions.add(subscription);
		subscriber.onSubscribe(subscription);
	}
	
	public void waitUntilEnded() throws InterruptedException {
		try {
			ended.get();
		} catch (ExecutionException e) {
			System.out.println(e);
		}
	}
	
	
}
