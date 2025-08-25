package com.bobcode.store;

import com.bobcode.store.entities.Address;
import com.bobcode.store.entities.Profile;
import com.bobcode.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var user = User.builder()
                .name("John")
                .password("password")
                .email("john@testemail.com")
                .build();

        var profile = Profile.builder()
                .bio("bio")
                .build();
        user.setProfile(profile);
        profile.setUser(user);
        System.out.println(user);
	}

}
