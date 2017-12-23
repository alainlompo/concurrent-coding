package org.hypernovae.apis.code.snippets.flow.api;

import java.util.List;

public class SubscriptionCancelService {
	private final List<DummySubscription> subscriptions;
	
	public SubscriptionCancelService(List<DummySubscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	synchronized boolean cancelAndAssertEmpty(DummySubscription target) {
		subscriptions.remove(target);
		return (subscriptions.size() == 0);
	}
}
