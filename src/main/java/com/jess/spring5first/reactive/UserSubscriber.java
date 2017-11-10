package com.jess.spring5first.reactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class UserSubscriber<T> implements Subscriber {

    private Subscription subscription;

    private static final Logger log = LoggerFactory.getLogger(UserSubscriber.class);

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Object item) {

        log.info("Got : " + item);

        //TODO : Something

        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
    }
}
