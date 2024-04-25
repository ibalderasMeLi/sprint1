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

    //US 0003, US 0008

    /**
     * US 0003, US 0008
     * Gets a list of all the users that follow a seller
     * Sort alphabetically, ascending and descending
     * @param userId
     * @param order
     * @return
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable Integer userId, @RequestParam(value = "order", required = false) String order){
        if (order == null){
            return new ResponseEntity<>(userService.getFollowedList(userId, order), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(userService.getFollowedOrdered(userId, order), HttpStatus.OK);
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
    public ResponseEntity<?> getFollowedList(@PathVariable Integer userId,@RequestParam String order){
        return new ResponseEntity<>(userService.getFollowedList(userId,order),HttpStatus.OK);
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
}
