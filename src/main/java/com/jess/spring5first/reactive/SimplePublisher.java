package com.jess.spring5first.reactive;

import java.util.concurrent.Flow;

public class SimplePublisher implements Flow.Publisher {

    private SimpleSubscription subscription;

    @Override
    public void subscribe(Flow.Subscriber subscriber) {

        SimpleSubscription subscription = new SimpleSubscription(subscriber);
        subscriber.onSubscribe(subscription);

    }


    public class SimpleSubscription implements Flow.Subscription {

        private Flow.Subscriber subscriber;

        public SimpleSubscription(Flow.Subscriber subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            subscriber.onNext(1);
        }

        @Override
        public void cancel() {

        }
    }
}
