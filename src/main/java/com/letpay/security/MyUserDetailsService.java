package com.letpay.security;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static com.mongodb.client.model.Filters.eq;

import java.util.Collections;


//@Service
//public class MyUserDetailsService implements UserDetailsService {
//    private final MongoDatabase mongoDatabase;
//    public MyUserDetailsService(MongoDatabase mongoDatabase) {
//        this.mongoDatabase = mongoDatabase;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MongoCollection<Document> users = mongoDatabase.getCollection("users");
//        Document userDocument = users.find(eq("username", username)).first();
//        if (userDocument == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        User user = new User(userDocument.getString("username"), userDocument.getString("password"),
//                Collections.emptyList());
//        return user;
//    }
//}
