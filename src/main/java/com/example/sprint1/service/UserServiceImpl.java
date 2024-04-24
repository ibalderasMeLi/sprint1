package com.example.sprint1.service;

import com.example.sprint1.dto.FollowdUserDto;
import com.example.sprint1.dto.FollowListDto;
import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public void addFollower(Integer userID, Integer userIdToFollow) {
    }

    @Override
    public Object getFollowerCount(Integer userId) {
        return null;
    }

    @Override
    public FollowListDto getFollowerList(Integer userId, String order) {
        return null;
    }

    /**
     * This method is used to get the list of users that the user follows and package it into a FollowedListDto object
     * @param userId - The id of the user
     * @return - A FollowedListDto object that contains the list of users that the user follows
     */
    @Override
    public FollowListDto getFollowedList(Integer userId) {
        // Get all users from repository
        List<User> allUsers = userRepository.findAll();
        // Select the user that matches with the id supplied
        Optional<User> userSpecified = allUsers.stream().filter(user -> Objects.equals(user.getId(), userId)).findFirst();
        if(userSpecified.isPresent()&&userSpecified.get().getFollowed()!=null){
            // Get the list of users that the user follows
            Set<Integer> followedList = userSpecified.get().getFollowed();
            // Return the list of users that the user follows mapped to a FollowUserDto object
            List<FollowdUserDto> followedUsersDtos = allUsers.stream().map(user -> {
                if(followedList.contains(user.getId())){
                    FollowdUserDto followdUserDto = new FollowdUserDto();
                    followdUserDto.setUser_id(user.getId());
                    followdUserDto.setUser_name(user.getUser_name());
                    return followdUserDto;
                }
                return null;
            }).toList();
            FollowListDto followedListDto = new FollowListDto();
            followedListDto.setFollowed(followedUsersDtos);
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
    public FollowListDto getFollowersOrdered(Integer userId, String order) {
        return null;
    }

    @Override
    public FollowListDto getFollowedOrdered(Integer userId, String order) {
        return null;
    }

}
