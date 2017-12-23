package org.hypernovae.apis.code.snippets.flow.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.lang.Thread.currentThread;

import java.util.concurrent.CompletableFuture;

public class DummySubscription implements Subscription {
	private final ExecutorService executorService;
	private Subscriber<? super Integer> subscriber;
	private final AtomicInteger value;
	private final SubscriptionCancelService subscriptionCancelService;
	private AtomicBoolean isCanceled;
	final CompletableFuture<Void> ended;

	public DummySubscription(Subscriber<? super Integer> subscriber, ExecutorService executorService,
			SubscriptionCancelService subscriptionCancelService, final CompletableFuture<Void> ended) {
		this.subscriber = subscriber;
		this.executorService = executorService;
		value = new AtomicInteger();
		isCanceled = new AtomicBoolean(false);
		this.subscriptionCancelService = subscriptionCancelService;
		this.ended = ended;
	}

	@Override
	public void cancel() {
		isCanceled.set(true);
		if (subscriptionCancelService.cancelAndAssertEmpty(this)) {
			shutdown();
		}
	}

	private void shutdown() {
		log("Shutting down executor service...");
		executorService.shutdown();
		newSingleThreadExecutor().submit(() -> {
			log("shutting down is finished.");
			ended.complete(null);
		});
	}

	@Override
	public void request(long n) {
		if (isCanceled.get()) {
			return;
		}
		if (n < 0) {
			executorService.execute(() -> subscriber.onError(new IllegalArgumentException()));
		} else {
			publishItems(n);
		}
	}

	private void publishItems(long n) {
		for (int i = 0; i < n; i++) {
			executorService.execute(() -> {
				int v = value.incrementAndGet();
				log("publish item: " + v);
				subscriber.onNext(v);

			});
		}
	}

	private void log(String message) {
		System.out.println(message + "[" + currentThread().getName() + "]");
	}
}
