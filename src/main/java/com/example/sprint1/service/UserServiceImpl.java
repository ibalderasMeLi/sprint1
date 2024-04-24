package com.example.sprint1.service;

import com.example.sprint1.dto.FollowdUsersDto;
import com.example.sprint1.dto.FollowedListDto;
import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public Object addFollower(Integer userID, Integer userIdToFollow) {
        return null;
    }

    @Override
    public Object getFollowerCount(Integer userId) {
        return null;
    }

    @Override
    public Object getFollowerList(Integer userId) {
        return null;
    }

    /**
     * This method is used to get the list of users that the user follows and package it into a FollowedListDto object
     * @param userId - The id of the user
     * @return - A FollowedListDto object that contains the list of users that the user follows
     */
    @Override
    public FollowedListDto getFollowedList(Integer userId) {
        // Get all users from repository
        List<User> allUsers = userRepository.findAll();
        // Select the user that matches with the id supplied
        Optional<User> userSpecified = allUsers.stream().filter(user -> user.getId() == userId).findFirst();
        if(userSpecified.isPresent()&&userSpecified.get().getFollowed()!=null){
            // Get the list of users that the user follows
            Set<Integer> followedList = userSpecified.get().getFollowed();
            // Return the list of users that the user follows pa
            List<FollowdUsersDto> followdUsersDtos = allUsers.stream().
                    filter(user -> followedList.contains(user.getId())).
                    map(user -> new FollowdUsersDto(user.getId(), user.getUser_name())).toList();
            FollowedListDto followedListDto = new FollowedListDto();
            followedListDto.setFollowed(followdUsersDtos);
            followedListDto.setUser_id(userId);
            followedListDto.setUser_name(userSpecified.get().getUser_name());
            return followedListDto;
        }
        else{
            throw new NotFoundException("User not Found");
        }
    }

    @Override
    public Object setUnfollow(Integer userId, Integer userIdToUnfollow) {
        return null;
    }

    @Override
    public Object getFollowersOrdered(Integer userId, String order) {
        return null;
    }

    @Override
    public Object getFollowedOrdered(Integer userId, String order) {
        return null;
    }
}
