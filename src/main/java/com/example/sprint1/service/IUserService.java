package com.example.sprint1.service;

import com.example.sprint1.dto.FollowerListDto;
import com.example.sprint1.dto.FollowerUsersDto;
import com.example.sprint1.model.User;

public interface IUserService {
    Object addFollower(Integer userID, Integer userIdToFollow);

    Object getFollowerCount(Integer userId);

    FollowerListDto getFollowerList(Integer userId, String order);

    Object getFollowedList(Integer userId);

    Object setUnfollow(Integer userId, Integer userIdToUnfollow);

    Object getFollowersOrdered(Integer userId, String order);

    FollowerUsersDto convertToFollowUserDto(User user);
}
