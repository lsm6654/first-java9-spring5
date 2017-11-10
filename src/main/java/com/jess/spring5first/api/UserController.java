package com.jess.spring5first.api;

import com.jess.spring5first.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Random;

@RestController
@RequestMapping("users")
public class UserController {


    @GetMapping("{id}")
    public Mono<User> userInfo(@PathVariable String id) {

        Integer age = new Random().nextInt(40);

        return Mono.just(new User(id, age));
    }

}
