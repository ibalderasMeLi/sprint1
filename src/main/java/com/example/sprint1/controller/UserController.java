package com.example.sprint1.controller;

import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    //US 0001

    /**
     * US 001
     * Endpoint that implements that follows a certain seller
     * @param userID
     * @param userIdToFollow
     * @return
     */
    @PutMapping("/{userID}/follow/{userIdToFollow}")
    public ResponseEntity<?> postNewFollower(@PathVariable Integer userID, @PathVariable Integer userIdToFollow){
        userService.addFollower(userID,userIdToFollow);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //US 0002

    /**
     * US 0002
     * Gets the quantity of users that follow a seller
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getFollowerCount(userId),HttpStatus.OK);
    }

    /*
     * This method is used to get the list of followers that the user have and package it into a FolloweRListDto object
     * @param userId - The id of the user
     * @return - A FollowerListDto object that contains the list of followers that the user have
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> getFollowerList(@PathVariable Integer userId, @RequestParam(required = false) String order){

        // Validation on whether the list will have any order
        if (order == null || order.isEmpty()){
            return new ResponseEntity<>(userService.getFollowerList(userId, order),HttpStatus.OK);
        }
        if (order.equals("name_asc") || order.equals("name_desc")){
            return new ResponseEntity<>(userService.getFollowersOrdered(userId, order),HttpStatus.OK);
        }
        else {
            throw new NotFoundException("Query params not matching any case");

        }
    }

    //US 0004 US, US 0008

    /**
     * US 0004, US 0008
     * List of all the sellers that follow some user
     * Sort alphabetically, ascending and descending
     * @param userId
     * @param order
     * @return
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable Integer userId, @RequestParam(value = "order", required = false) String order){
        if (order == null){
            return new ResponseEntity<>(userService.getFollowedList(userId), HttpStatus.OK);
        }
        if(order.equals("name_asc") || order.equals("name_desc")){
            return new ResponseEntity<>(userService.getFollowedOrdered(userId, order), HttpStatus.OK);
        }
        else {
            throw new NotFoundException("Query params not matching any case");
        }
    }

    //US 0007

    /**
     * US 0007
     * Unfollows a user (userId unfollows userIdToUnfollow)
     * @param userId
     * @param userIdToUnfollow
     * @return
     */
    @DeleteMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> setUnfollow(@PathVariable Integer userId,@PathVariable Integer userIdToUnfollow){
        return new ResponseEntity<>(userService.setUnfollow(userId,userIdToUnfollow),HttpStatus.NO_CONTENT);
    }
          
    //TODO quitar el ultimo método
    @GetMapping("{userId}/followers/list-ordered")
    public ResponseEntity<?> getFollowerListOrderByName(@PathVariable Integer userId,@RequestParam String order){
        return new ResponseEntity<>(userService.getFollowersOrdered(userId,order),HttpStatus.OK);
    }
}
