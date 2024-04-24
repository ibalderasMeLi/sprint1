package com.example.sprint1.repository;
import com.example.sprint1.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<User> findAll();
    Optional<User> getUserById(int id);
}
