package com.example.sprint1.controller;

import com.example.sprint1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    //US 0001
    @PostMapping("/{userID}/follow/{userIdToFollow}")
    public ResponseEntity<?> postNewFollower(@PathVariable Integer userID, @PathVariable Integer userIdToFollow){
        return new ResponseEntity<>(userService.addFollower(userID,userIdToFollow), HttpStatus.CREATED);
    }

    //US 0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getFollowerCount(userId),HttpStatus.OK);
    }

    //US 0003, US 0008
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> getFollowerList(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getFollowerList(userId),HttpStatus.OK);
    }

    //US 0004, US 0005
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getFollowedList(userId),HttpStatus.OK);
    }

    //US 0007
    /**
     * Endpoint to allow a user to unfollow another user using the DELETE method.
     */
    @DeleteMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> setUnfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        try {
            // Call the service to process the unfollow action
            userService.setUnfollow(userId, userIdToUnfollow);
            // If there's a logical error return an error message with HTTP 400 Bad Request
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // For other unexpected errors, return a generic error message with HTTP 400 Bad Request
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // finished method US0007
}
