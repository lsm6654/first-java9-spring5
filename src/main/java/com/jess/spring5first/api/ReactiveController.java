package com.jess.spring5first.api;

import com.jess.spring5first.model.User;
import com.jess.spring5first.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive")
public class ReactiveController {

    private static final Logger log = LoggerFactory.getLogger(ReactiveController.class);

    @Autowired
    private UserRepository repository;


    @GetMapping("subscribe")
    public Flux<User> subscribe() {

        log.info("start");

        Flux<User> users = repository.users();

        Flux<User> userFlux = users
                .flatMap(user -> getUser(user.getName()))
                .flatMap(user -> doSomethingBatchProcess(user)).log();

        log.info("end");

        return userFlux;
    }

    private Mono<User> getUser(String userName) {

        WebClient client = WebClient.create("http://localhost:8080");

        Mono<User> result = client.get()
                .uri("/users/" +userName )
                .exchange()
                .flatMap(response -> response.bodyToMono(User.class));

        return result;
    }

    private Mono<User> doSomethingBatchProcess(User user) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Mono.just(user);
    }

}
