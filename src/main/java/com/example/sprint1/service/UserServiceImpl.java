package com.example.sprint1.service;

import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Object getFollowedList(Integer userId) {
        return null;
    }

    // US0007
    @Override
    public Object setUnfollow(Integer userId, Integer userIdToUnfollow) {
        // Checks if the IDs are the same, indicating the user is trying to unfollow themselves.
        if (userId.equals(userIdToUnfollow)) {
            throw new IllegalArgumentException("You cannot unfollow yourself.");
        }
        // Retrieve the user and the user to unfollow from the repository
        User user = userRepository.findUserById(userId);
        User userToUnfollow = userRepository.findUserById(userIdToUnfollow);
        if (user == null || userToUnfollow == null) {
            throw new IllegalArgumentException("User not found.");
        }
        // Verify if the current user actually follows the user to unfollow
        if (!user.getFollowed().contains(userIdToUnfollow)) {
            // If the current user is not following the other user, return an error
            throw new IllegalArgumentException("You are not following this user.");
        }
        // Proceed to update the follow state between users
        userRepository.updateUserFollower(user, userToUnfollow);
        // Return a success message after successfully unfollowing the user
        return "Unfollow successful";
    }
    // finished method US0007

    @Override
    public Object getFollowersOrdered(Integer userId, String order) {
        return null;
    }
}
