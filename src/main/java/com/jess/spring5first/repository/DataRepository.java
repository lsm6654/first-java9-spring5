package com.jess.spring5first.repository;

import com.jess.spring5first.model.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Component
public class DataRepository {

    private static final Logger log = LoggerFactory.getLogger(DataRepository.class);

    public Flux<Base> datas() {

        //log.info("called datas()");

        List<Base> list = Arrays.asList(new Base("1"), new Base("2"), new Base("3"), new Base("4"));
        Flux<Base> f = Flux.fromIterable(list);

        return f;
    }


    public Mono<Base> data(int index) {

        return Mono.just(new Base("first data"));
    }
}
