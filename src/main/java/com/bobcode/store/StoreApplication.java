package com.bobcode.store;

import com.bobcode.store.entities.User;
import com.bobcode.store.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var repository = context.getBean(UserRepository.class);

//        var user = User.builder()
//                .name("Bob")
//                .email("bob@gmail.com")
//                .password("12345")
//                .build();
//
//        repository.save(user);

//        repository.findAll().forEach(u -> System.out.println(u.getEmail()));
        repository.deleteById(1L);
	}
}
