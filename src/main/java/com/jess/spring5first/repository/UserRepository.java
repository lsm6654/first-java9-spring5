package com.jess.spring5first.repository;

import com.jess.spring5first.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserRepository {

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    public Flux<User> users() {

        List<User> users = Stream.iterate(1, n -> n + 1)
                .map(i -> new User("test" + i, i))
                .limit(20)
                .collect(Collectors.toList());

        return Flux.fromIterable(users);
    }
}
