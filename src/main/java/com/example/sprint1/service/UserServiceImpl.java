package com.example.sprint1.service;

import com.example.sprint1.dto.FollowerListDto;
import com.example.sprint1.dto.FollowerUsersDto;
import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public FollowerListDto getFollowerList(Integer userId, String order) {
        Optional<User> optionalUser = userRepository.getUserById(userId);
        User principalUser = optionalUser.orElseThrow(
                () -> new NotFoundException("No se encontró el usuario con el ID proporcionado"));

        List<FollowerUsersDto> followersList = new ArrayList();

        Set<Integer> followers = principalUser.getFollowers();

        for (Integer miniId : followers) {
            optionalUser = userRepository.getUserById(miniId);
            optionalUser.ifPresent(user -> followersList.add(convertToFollowUserDto(user)));
        }
        return new FollowerListDto(principalUser.getId(), principalUser.getUser_name(), followersList);
    }

    @Override
    public Object getFollowedList(Integer userId) {
        return null;
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
    public FollowerUsersDto convertToFollowUserDto(User user) {
        return new FollowerUsersDto(user.getId(), user.getUser_name());
    }
}
