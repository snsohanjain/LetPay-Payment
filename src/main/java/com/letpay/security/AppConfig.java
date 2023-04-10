package com.letpay.security;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.letpay.axies.MongoConfig.mongoAccess;

@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

//    @Bean
//    public MongoClient mongoClient() {
//        return new MongoClient(mongoAccess);
//    }
//
//    @Bean
//    public MongoDatabase mongoDatabase() {
//        return mongoClient().getDatabase("useusers");
//    }
}
