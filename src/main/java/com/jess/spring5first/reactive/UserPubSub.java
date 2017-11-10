package com.jess.spring5first.reactive;

import com.jess.spring5first.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserPubSub {

    private static final Logger log = LoggerFactory.getLogger(UserPubSub.class);


    public static void main(String[] args) throws InterruptedException {

        log.info("start");

        //publisher의 스레드풀 설정
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        //Data stream
        List<User> collect = Stream.iterate(1, n -> n+1)
                .map(i -> new User("test" + i, i)).limit(50)
                .collect(Collectors.toList());


        SubmissionPublisher<User> publisher = new SubmissionPublisher(executor, 100);

        //구독 시작
        publisher.subscribe(new UserSubscriber<>());
        //publisher에 Data submit
        collect.forEach(publisher::submit);

        //프로세스가 먼저 죽으면 안되니 잠깐 기다림
        ForkJoinPool.commonPool().awaitTermination(30, TimeUnit.SECONDS);
        publisher.close();

        log.info("end");
    }
}
