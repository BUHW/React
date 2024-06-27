package com.costs.costs_api.service.interfaces;

import com.costs.costs_api.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServiceInterface {

    List<User> getAll();
    Optional<User> getById(UUID id);
    User update(UUID id, User user);
    void delete(UUID id);

}
