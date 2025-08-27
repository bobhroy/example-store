package com.bobcode.store;

import com.bobcode.store.entities.User;
import com.bobcode.store.repositories.UserRepository;
import com.bobcode.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var service = context.getBean(UserService.class);
        service.fetchAddress();
	}
}
