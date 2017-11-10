package com.jess.spring5first.config;

import com.jess.spring5first.model.User;
import com.jess.spring5first.router.DataRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
@EnableWebFlux
public class CommonConfig {


    @Autowired
    private DataRouter dataRouter;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return dataRouter.doRoute();
    }

    @Bean
    public List<User> users() {

        List<User> defaultUsers = Arrays.asList(new User("test1", 1), new User("test2", 1),
                new User("test3", 1), new User("test4", 1));

        return Collections.synchronizedList(defaultUsers);
    }


}
