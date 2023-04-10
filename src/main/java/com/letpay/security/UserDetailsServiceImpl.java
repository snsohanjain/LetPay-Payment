package com.letpay.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Map<String, User> users = new HashMap<>();

    public UserDetailsServiceImpl() {
        // Add sample users to the in-memory database
        users.put("sohan", new User("sohan","12345", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
        users.put("john", new User("john", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
        users.put("jane", new User("jane", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}
