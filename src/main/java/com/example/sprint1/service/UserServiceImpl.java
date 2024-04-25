package com.example.sprint1.service;

import com.example.sprint1.dto.FollowerListDto;
import com.example.sprint1.dto.FollowerUsersDto;
import com.example.sprint1.dto.FollowListDto;
import com.example.sprint1.dto.FollowdUserDto;
import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * Get the list of followers for a user
     * @param userId The ID of the user
     * @param order The order in which to return the followers
     * @return The list of followers for the user
     */
    @Override
    public FollowerListDto getFollowerList(Integer userId, String order) {
        // Get the user by ID and check if the user exists
        Optional<User> optionalUser = userRepository.getUserById(userId);
        User principalUser = optionalUser.orElseThrow(
                () -> new NotFoundException("No se encontró el usuario con el ID proporcionado"));

        List<FollowerUsersDto> followersList = new ArrayList();

        Set<Integer> followers = principalUser.getFollowers();

        // Iterate over the followers and add them to the list in DTO format
        for (Integer miniId : followers) {
            optionalUser = userRepository.getUserById(miniId);
            optionalUser.ifPresent(user -> followersList.add(convertToFollowUserDto(user)));
        }
        return new FollowerListDto(principalUser.getId(), principalUser.getUser_name(), followersList);
    }

    public FollowListDto getFollowerList(Integer userId) {
        List<User> allUsers = userRepository.findAll();
        Optional<User> userSpecified = allUsers.stream().filter(user -> user.getId() == userId).findFirst();
        if (userSpecified.isPresent() && userSpecified.get().getFollowers()!=null){
            Set<Integer> followerList = userSpecified.get().getFollowers();
            List<FollowdUserDto> followerUsersDto =  allUsers.stream()
                    .filter(user ->  followerList.contains(user.getId()))
                    .map(user -> new FollowdUserDto(user.getId(), user.getUser_name())).toList();
            FollowListDto followerListDto = new FollowListDto();
            followerListDto.setFollowed(followerUsersDto);
            followerListDto.setUser_id(userId);
            followerListDto.setUser_name(userSpecified.get().getUser_name());
            return followerListDto;
        }
        else {
            throw new NotFoundException("User not Found");
        }

    }

    @Override
    public  FollowListDto getFollowedList(Integer userId) {
        // Get all users from repository
        List<User> allUsers = userRepository.findAll();
        // Select the user that matches with the id supplied
        Optional<User> userSpecified = allUsers.stream().filter(user -> user.getId() == userId).findFirst();
        if(userSpecified.isPresent()&&userSpecified.get().getFollowed()!=null){
            // Get the list of users that the user follows
            Set<Integer> followedList = userSpecified.get().getFollowed();
            // Return the list of users that the user follows pa
            List<FollowdUserDto> followdUsersDtos = allUsers.stream()
                    .filter(user -> followedList.contains(user.getId()))
                    .map(user -> new FollowdUserDto(user.getId(), user.getUser_name())).toList();
            FollowListDto followedListDto = new FollowListDto();
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
    public FollowListDto getFollowersOrdered(Integer userId, String order) {
        //Decides order of sorting
        Comparator<String> comparador;
        if(order.equals("name_asc")){
            comparador = Comparator.naturalOrder();
        }
        else {
            comparador = Comparator.reverseOrder();
        }

        //Call to getFollowedList (already exception checked)
        FollowListDto followerListDto = getFollowerList(userId);
        List<FollowdUserDto> followerList = followerListDto.getFollowed().stream()
                .sorted(Comparator.comparing(FollowdUserDto::getUser_name, comparador))
                .toList();



        //Set ordered list
        followerListDto.setFollowed(followerList);

        return followerListDto;
    }
    /**
     * Call to getFollowerList to sort by name the followed users.
     * @param userId
     * @param order
     * @return followerListDto
     */
    public FollowListDto getFollowedOrdered(Integer userId, String order) {
        //Decides order of sorting
        Comparator<String> comparador;
        if(order.equals("name_asc")){
            comparador = Comparator.naturalOrder();
        }
        else {
            comparador = Comparator.reverseOrder();
        }
        //Call to getFollowedList (already exception checked)
        FollowListDto followedListDto = getFollowerList(userId);
        List<FollowdUserDto> followerList = followedListDto.getFollowed().stream()
                .sorted(Comparator.comparing(FollowdUserDto::getUser_name, comparador)).toList();

        //Set and return followers
        followedListDto.setFollowed(followerList);

        return followedListDto;
    }

    @Override
    public FollowerUsersDto convertToFollowUserDto(User user) {
        return new FollowerUsersDto(user.getId(), user.getUser_name());
    }
}
