package com.example.sprint1.service;

import com.example.sprint1.model.User;

import java.util.List;

public interface IUserService {
    void addFollower(Integer userID, Integer userIdToFollow);

    Object getFollowerCount(Integer userId);

    Object getFollowerList(Integer userId);

    Object getFollowedList(Integer userId);

    Object setUnfollow(Integer userId, Integer userIdToUnfollow);

    Object getFollowedList(Integer userId, String order);

    Object getFollowerList(Integer userId, String order);

    Object getFollowersOrdered(Integer userId, String order);

    List<User> getUsers();
}
