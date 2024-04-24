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

    @Override
    public Object getFollowerCount(Integer userId) {
        List<User> userList = userRepository.findAll();
        Integer followerCount = 0;
        String name = "";
        for (User u : userList) {
            if(u.getId().equals(userId)){
                name = u.getUser_name();
            }
            if (u.getFollowers().contains(userId)) {
                followerCount++;
            }
        }
        if(name==""){
            throw new NotFoundException("No se encontró al vendedor");
        }
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
