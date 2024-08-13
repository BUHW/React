package com.costs.costs_api.infra.security;

import com.costs.costs_api.domain.user.User;
import com.costs.costs_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Usar injecao com construtor
    @Autowired
    private UserRepository repository;

    // public CustomUserDetailsService(UserRepository repository) {
    //     this.repository = repository;
    // }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = this.repository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Login not found"));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }

}
