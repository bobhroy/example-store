package com.bobcode.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //Always use noun (ex. stripe) instead of verbs (ex. getStripePaymentService)
    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }
    
    @Bean
    public OrderService orderService() {
        return new OrderService(stripe());
    }
}
