package com.bobcode.store.services;

import com.bobcode.store.entities.User;
import com.bobcode.store.repositories.AddressRepository;
import com.bobcode.store.repositories.ProfileRepository;
import com.bobcode.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;

    @Transactional
    public void showEntityState(){
        var user = User.builder()
                .name("John Doe")
                .email("johndoe@testmail.com")
                .password("12345")
                .build();

        if(entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");

        userRepository.save(user);

        if(entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");
    }

    @Transactional
    public void showRelatedEntities(){
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress() {
        addressRepository.findById(1L).orElseThrow();
    }
}
