package com.jess.spring5first.api;

import com.jess.spring5first.model.Base;
import com.jess.spring5first.repository.DataRepository;
import com.jess.spring5first.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("data")
    public Mono<Base> test() {

        return dataRepository.data(1);
    }

    @GetMapping("datas")
    public Flux<Base> test2() {

        return dataRepository.datas();
    }

    @GetMapping("concat")
    public Flux<Base> concatOperator() {

        log.info("start");

        Flux<Base> f1 = dataRepository.datas().doOnNext(c -> log.info("1 do on next : " + c));
        Flux<? extends Base> f2 = userRepository.users().doOnNext(c -> log.info("2 do on next : " + c));


        Flux<String> f3 = Flux.just(generateValue()).doOnNext( c -> log.info("do on next : " + c));

        log.info("data concat");

        Flux<Base> concatF = Flux.concat(f1, f2).log();

        log.info("end");

        return concatF;
    }


    @GetMapping("test4")
    public String test4() {

        dataRepository.datas()
                .subscribe( i -> System.out.println(i));

        Flux.just("", "", "")
                .subscribe( i -> System.out.println(i), //onNext
                        error -> System.err.println("Error " + error), //onError
                        () -> System.out.println("completed"),  //onCompleted
                        s -> s.request(10));    //request

        return "testes";
    }


    private String generateValue() {
        return "tfgdgdf";
    }
}
