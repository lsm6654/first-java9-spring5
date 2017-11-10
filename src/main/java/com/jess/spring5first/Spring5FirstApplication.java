package com.jess.spring5first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Spring5FirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5FirstApplication.class, args);
	}

}
