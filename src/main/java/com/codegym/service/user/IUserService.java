package com.codegym.service.user;


import com.codegym.model.User;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    Optional<User> findByUsername(String username);
    Iterable<User> findAllByFullNameContaining(String name);
}
