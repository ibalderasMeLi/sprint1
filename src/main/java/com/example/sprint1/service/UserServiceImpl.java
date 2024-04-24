package com.example.sprint1.service;

import com.example.sprint1.dto.FollowdUsersDto;
import com.example.sprint1.dto.FollowedListDto;
import com.example.sprint1.dto.FollowerListDto;
import com.example.sprint1.dto.FollowerUserDto;
import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    public FollowerListDto getFollowerList(Integer userId) {
        List<User> allUsers = userRepository.findAll();
        Optional<User> userSpecified = allUsers.stream().filter(user -> user.getId() == userId).findFirst();
        if (userSpecified.isPresent() && userSpecified.get().getFollowers()!=null){
            Set<Integer> followerList = userSpecified.get().getFollowers();
            List<FollowerUserDto> followerUsersDto =  allUsers.stream()
                    .filter(user ->  followerList.contains(user.getId()))
                    .map(user -> new FollowerUserDto(user.getId(), user.getUser_name())).toList();
            FollowerListDto followerListDto = new FollowerListDto();
            followerListDto.setFollowers(followerUsersDto);
            followerListDto.setUser_id(userId);
            followerListDto.setUser_name(userSpecified.get().getUser_name());
            return followerListDto;
        }
        else {
            throw new NotFoundException("User not Found");
        }

    }

    @Override
    public  FollowedListDto getFollowedList(Integer userId) {
        // Get all users from repository
        List<User> allUsers = userRepository.findAll();
        // Select the user that matches with the id supplied
        Optional<User> userSpecified = allUsers.stream().filter(user -> user.getId() == userId).findFirst();
        if(userSpecified.isPresent()&&userSpecified.get().getFollowed()!=null){
            // Get the list of users that the user follows
            Set<Integer> followedList = userSpecified.get().getFollowed();
            // Return the list of users that the user follows pa
            List<FollowdUsersDto> followdUsersDtos = allUsers.stream()
                    .filter(user -> followedList.contains(user.getId()))
                    .map(user -> new FollowdUsersDto(user.getId(), user.getUser_name())).toList();
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

    /**
     * Call to getFollowerList to sort by name the following users.
     * @param userId
     * @param order
     * @return followerListDto
     */
    @Override
    public FollowerListDto getFollowersOrdered(Integer userId, String order) {

        Comparator comparador;
        if(order.equals("name_asc")){
            comparador = Comparator.naturalOrder();
        }
        else {
            comparador = Comparator.reverseOrder();
        }
        FollowerListDto followerListDto = getFollowerList(userId);
        List<FollowerUserDto> followerList = followerListDto.getFollowers().stream().sorted(Comparator.comparing(FollowerUserDto::getUser_name, comparador)).toList();
        followerListDto.setFollowers(followerList);

        return followerListDto;
    }

    /**
     * Call to getFollowedList to sort by name the users followed users.
     * @param userId
     * @param order
     * @return followedListDto
     */
    @Override
    public FollowedListDto getFollowedOrdered(Integer userId, String order) {

        Comparator comparador;
        if(order.equals("name_asc")){
            comparador = Comparator.naturalOrder();
        }
        else {
            comparador = Comparator.reverseOrder();
        }

        FollowedListDto followedListDto = getFollowedList(userId);
        List<FollowdUsersDto> followedList = followedListDto.getFollowed().stream().sorted(Comparator.comparing(FollowdUsersDto::getUser_name, comparador)).toList();
        followedListDto.setFollowed(followedList);

        return followedListDto;
    }
}
