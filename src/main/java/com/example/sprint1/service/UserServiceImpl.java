package com.example.sprint1.service;
import com.example.sprint1.dto.CountFollowersUserDto;

import com.example.sprint1.exception.BadRequestException;
import com.example.sprint1.dto.FollowerListDto;
import com.example.sprint1.dto.FollowerUsersDto;
import com.example.sprint1.dto.FollowListDto;
import com.example.sprint1.dto.FollowdUserDto;
import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.model.User;
import com.example.sprint1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

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
        if(userID.equals(userIdToFollow))
            throw new BadRequestException("Can't follow yourself");
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

    /**
     * Method to retrieve the follower count for a given user. REQ US0002
     * @param userId
     * @return
     */
    @Override
    public CountFollowersUserDto getFollowerCount(Integer userId) {
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


    /**
     * Get the list of followers for a user
     * @param userId The ID of the user
     * @param order The order in which to return the followers
     * @return The list of followers for the user
     */
    @Override
    public FollowListDto getFollowerList(Integer userId, String order) {

        // User validation
        Optional<User> optionalUser = userRepository.getUserById(userId);
        optionalUser.orElseThrow(() -> new NotFoundException("No se encontró el usuario con el ID proporcionado"));

        //Call to service
        List<User> followerList = userRepository.getFollowersById(userId);

        //Set default value of order if null
        if (order == null){
            order = "default";
        }

        //Sorting by alphanumerical name
        switch (order){
            case "name_asc":
                followerList = followerList.stream().sorted(Comparator.comparing(User::getUser_name)).toList();
                break;

            case "name_desc":
                followerList = followerList.stream().sorted(Comparator.comparing(User::getUser_name).reversed()).toList();
                break;

            case "default":
                break;

            default:
                throw new BadRequestException("query param must exist");

        }

        //DTOAssembly
        List<FollowerUsersDto> followerUsersDto = followerList.stream().map(user -> new FollowerUsersDto(user.getId(), user.getUser_name())).toList();
        FollowListDto followerListDto = new FollowListDto(optionalUser.get().getId(), optionalUser.get().getUser_name(), followerUsersDto);

        return followerListDto;
    }

    /**
     * Gets the followed list
     * @param userId
     * @param order alphanumerical order
     * @return FollowListDto
     */
    @Override
    public  FollowListDto getFollowedList(Integer userId, String order) {

        // User validation
        Optional<User> optionalUser = userRepository.getUserById(userId);
        optionalUser.orElseThrow(() -> new NotFoundException("No se encontró el usuario con el ID proporcionado"));

        //Call to service
        List<User> followedList = userRepository.getFollowedById(userId);

        //Set default value of order if null
        if (order == null){
            order = "default";
        }

        //Sorting by alphanumerical name
        switch (order){
            case "name_asc":
                followedList = followedList.stream().sorted(Comparator.comparing(User::getUser_name)).toList();
                break;

            case "name_desc":
                followedList = followedList.stream().sorted(Comparator.comparing(User::getUser_name).reversed()).toList();
                break;

            case "default":
                break;

            default:
                throw new BadRequestException("query param must exist");

        }

        //DTOAssembly
        List<FollowerUsersDto> followedUsersDto = followedList.stream().map(user -> new FollowerUsersDto(user.getId(), user.getUser_name())).toList();
        FollowListDto followedListDto = new FollowListDto(optionalUser.get().getId(), optionalUser.get().getUser_name(), followedUsersDto);

        return followedListDto;
    }

    /**
     * Unfollows userId
     * @param userId
     * @param userIdToUnfollow
     * @return
     */
    @Override
    public String setUnfollow(Integer userId, Integer userIdToUnfollow) {
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
        userRepository.updateUserFollowerDelete(user, userToUnfollow);
        // Return a success message after successfully unfollowing the user
        return "Unfollow successful";
    }
    // finished method US0007


    /**
     * Mapper user to followerUsersDto
     * @param user
     * @return
     */
    @Override
    public FollowerUsersDto convertToFollowUserDto(User user) {
        return new FollowerUsersDto(user.getId(), user.getUser_name());
    }

    /**
     * Returns all users
     * @return
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addPostToUser(Integer userId, Integer postId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException("User not found.");
        }
        if(checkPostIdUnique(user.getPosts().stream().toList(),postId)){
            user.getPosts().add(postId);
            userRepository.addPost(userId,postId);
        }else{
            throw new IllegalArgumentException("Post Id is already in the list");
        }
    }

    private Boolean checkPostIdUnique(List<Integer> postList,Integer id){
        for(Integer p:postList){
            if(p.equals(id)){
                return false;
            }
        }
        return true;
    }

}
