package com.jess.spring5first.reactive;

import java.util.concurrent.Flow;

public class SimpleSubscriber<T> implements Flow.Subscriber {

    private Flow.Subscription subscription;

    private static System.Logger LOGGER1 = System.getLogger("logger");

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Object item) {
        LOGGER1.log(System.Logger.Level.INFO, "got next data : " + item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER1.log(System.Logger.Level.ERROR, "error : " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        LOGGER1.log(System.Logger.Level.INFO, "completed" );
    }
}
