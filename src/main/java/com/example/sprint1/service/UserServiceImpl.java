package com.example.sprint1.service;

import com.example.sprint1.dto.CountFollowersUserDto;
import com.example.sprint1.dto.ProductDto;
import com.example.sprint1.dto.ResponseDto;
import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public Object addFollower(Integer userID, Integer userIdToFollow) {
        return null;
    }

    // Method to retrieve the follower count for a given user. REQ US0002
    @Override
    public Object getFollowerCount(Integer userId) {
        // Retrieve all users from the repository.
        List<User> userList = userRepository.findAll();

        // Initialize variables to store follower count and username.
        Integer followerCount = 0;
        String name = "";

        // Validate in all users if the user is followed
        for (User u : userList) {
            // Check if the current user matches the given userId.
            if(u.getId().equals(userId)){
                // If matched, store the username.
                name = u.getUser_name();
            }
            // Check if the current user's followers list contains the given userId.
            if (u.getFollowers().contains(userId)) {
                // If yes, increment the follower count.
                followerCount++;
            }
        }

        // If no user is found with the given userId, throw a NotFoundException.
        if(name==""){
            throw new NotFoundException("No se encontró al vendedor");
        }

        // Return a CountFollowersUserDto object containing userId, username, and follower count.
        return new CountFollowersUserDto(userId,name,followerCount);
    }


    @Override
    public Object getFollowerList(Integer userId, String order) {
        return null;
    }

    @Override
    public Object getFollowedList(Integer userId, String order) {
        return null;
    }

    @Override
    public Object setUnfollow(Integer userId, Integer userIdToUnfollow) {
        return null;
    }

}
