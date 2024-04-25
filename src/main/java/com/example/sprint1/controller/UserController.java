package com.example.sprint1.controller;

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

    @PostMapping("/{userID}/follow/{userIdToFollow}")
    public ResponseEntity<?> postNewFollower(@PathVariable Integer userID, @PathVariable Integer userIdToFollow){
        return new ResponseEntity<>(userService.addFollower(userID,userIdToFollow), HttpStatus.CREATED);
    }

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
        else {
            //TODO modify method value
            return new ResponseEntity<>(userService.getFollowedOrdered(userId, order),HttpStatus.OK);
        }
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable Integer userId, @RequestParam(value = "order", required = false) String order){


        if (order == null){
            return new ResponseEntity<>(userService.getFollowedList(userId), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(userService.getFollowedOrdered(userId, order), HttpStatus.OK);
        }
    }

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
