package com.example.sprint1.service;

import com.example.sprint1.exception.BadRequestException;
import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;

    /**
    * Adds a follower to the set of follower and a followed to the set of followed
    * @param userID Id of the user who will follow
     * @param userIdToFollow Id of the user to follow
     */
    @Override
    public void addFollower(Integer userID, Integer userIdToFollow) {
        User userAux = userRepository.findUserById(userID);
        User userToFollow = userRepository.findUserById(userIdToFollow);
        if(userAux == null)
            throw new BadRequestException("User not found");
        if(userToFollow == null)
            throw new BadRequestException("User to follow not found");
        if(userAux.getFollowed().contains(userToFollow.getId()) || userToFollow.getFollowers().contains(userAux.getId()))
            throw new BadRequestException("User already followed");
        userRepository.updateUserFollower(userAux, userToFollow);
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
    public Object getFollowedList(Integer userId, String order) {
        return null;
    }


    @Override
    public Object setUnfollow(Integer userId, Integer userIdToUnfollow) {
        return null;
    }


    @Override
    public Object getFollowerList(Integer userId, String order) {
        return null;
    }

    @Override
    public Object getFollowersOrdered(Integer userId, String order) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Object getFollowedOrdered(Integer userId, String order) {
        return null;
    }
}
