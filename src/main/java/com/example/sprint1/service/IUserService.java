package com.example.sprint1.service;

import com.example.sprint1.dto.FollowListDto;

public interface IUserService {
    void addFollower(Integer userID, Integer userIdToFollow);

    Object getFollowerCount(Integer userId);

    FollowListDto getFollowerList(Integer userId, String order);

    Object getFollowedList(Integer userId);

    Object setUnfollow(Integer userId, Integer userIdToUnfollow);


    public FollowListDto getFollowersOrdered(Integer userId, String order);

    Object getFollowedOrdered(Integer userId, String order);
}
