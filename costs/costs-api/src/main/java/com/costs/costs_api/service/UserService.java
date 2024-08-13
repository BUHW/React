package com.costs.costs_api.service;

import com.costs.costs_api.domain.user.User;
import com.costs.costs_api.repositories.UserRepository;
import com.costs.costs_api.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        Sort.Order order = Sort.Order.asc("login");
        Sort sort = Sort.by(order);

        return userRepository.findAll(sort);
    }

    @Override
    // public User getById(UUID id) {
    public Optional<User> getById(UUID id) {
        // O Optional deveria estar sendo tratado dentro do Service, e não no Controller
        return userRepository.findById(id);
        // return userRepositoy.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public User update(UUID id, User user) {
        User userUpdate = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not updated"));

        userUpdate.setLogin(user.getLogin());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(userUpdate);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
