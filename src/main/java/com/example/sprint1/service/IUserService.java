package com.example.sprint1.service;
public interface IUserService {
    Object addFollower(Integer userID, Integer userIdToFollow);

    Object getFollowerCount(Integer userId);

    Object getFollowerList(Integer userId, String order);

    Object getFollowedList(Integer userId, String order);

    Object setUnfollow(Integer userId, Integer userIdToUnfollow);

}
