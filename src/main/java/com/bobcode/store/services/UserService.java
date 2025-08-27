package com.bobcode.store.services;

import com.bobcode.store.entities.Address;
import com.bobcode.store.entities.Category;
import com.bobcode.store.entities.User;
import com.bobcode.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

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

    public void persistRelated(){
        var user = User.builder()
                .name("John Doe")
                .email("johndoe@testmail.com")
                .password("12345")
                .build();
        var address = Address.builder()
                .street("123 Main St")
                .city("Main St")
                .state("state")
                .zip("12345")
                .build();

        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated(){
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts(){
        productRepository.deleteById(1L);
    }

    @Transactional
    public void updateProductPrices(){
        productRepository.updatePriceByCategory(BigDecimal.valueOf(5), (byte)1);
    }

    public void fetchProducts(){
        var products = productRepository.findByCategory(new Category((byte) 1));
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUser(){
        var users = userRepository.findAllWithAddress();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }
}
