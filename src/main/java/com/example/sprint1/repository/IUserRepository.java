package com.example.sprint1.repository;


import java.util.List;
import com.example.sprint1.model.User;
import java.util.Optional;

public interface IUserRepository {
    List<User> findAll();
    User findUserById(Integer id);
    void updateUserFollower(User user, User userToFollow);
    Optional<User> getUserById(int id);
}
