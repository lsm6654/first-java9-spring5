package com.jess.spring5first.handler;

import com.jess.spring5first.model.Base;
import com.jess.spring5first.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Service
public class DataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DataHandler.class);

    @Autowired
    private DataRepository repository;


    public Mono<ServerResponse> datas(ServerRequest request) {

        logger.info("start");


        //mono는 subscribe되지 않는다면 실행되지 않는다
        //Mono m = Mono.fromSupplier(() -> test()).doOnNext(c -> logger.info(c)).doOnNext( c -> sleep()).doOnNext(c -> test()).log();

        Mono<ServerResponse> mono = ServerResponse.ok().body(repository.datas(), Base.class).log();


        //스프링에서 subscribe를 하는 시점에 실행이 되므로 순서가 다름.
        logger.info("end");
        //subscribe 하는 시점에 데이터가 흐름
        //mono나 flux는 다수의 subscriber를 가질 수 있음
        //cold (어떤 subscriber가 호출을 하든 같은 결과) & hot (구독 하는 시점마다 다른 데이터를 불러들임) 타입이 있다

        //block도 내부적으로 subscribe 를 실행함. block은 그 밑의 코드는 실행하지 않고 블락하는 것!!
        //코드에서는 가능한한 block 을 안쓰고 mono를 유지하는게 낫다.


        //Mono를 이용해서 필요한 형태의 연산을 조합하는 것이 편함
        //실제 동작은 subscribe 할 때 일어나고.. 에러 처리나 분기 처리가 편함


        return mono;
    }

    public Mono<ServerResponse> data(ServerRequest request) {

        int dataId = Integer.valueOf(request.pathVariable("id"));

        return ServerResponse.ok().body(repository.data(dataId), Base.class);
    }

    public String test() {

        logger.info("called test()");
        return "test";
    }

    public String sleep() {
        try {
            logger.info("thread sleep");
            Thread.sleep(1000);
            logger.info("thread sleep end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "test2";
    }
}
