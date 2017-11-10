package com.jess.spring5first.router;

import com.jess.spring5first.handler.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class DataRouter {

    @Autowired
    private DataHandler handler;

    public RouterFunction<ServerResponse> doRoute() {
        return
                route(GET("/datas"), handler::datas)
                .andRoute(GET("/data/{id}"), handler::data);
    }
}
