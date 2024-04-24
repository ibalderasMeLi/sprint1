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
        userService.addFollower(userID,userIdToFollow);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //US 0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getFollowerCount(userId),HttpStatus.OK);
    }

    //US 0003, US 0008
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> getFollowerList(@PathVariable Integer userId,@RequestParam String order){
        return new ResponseEntity<>(userService.getFollowerList(userId,order),HttpStatus.OK);
    }

    //US 0004 US, US 0008
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable Integer userId,@RequestParam String order){
        return new ResponseEntity<>(userService.getFollowedList(userId,order),HttpStatus.OK);
    }

    //US 0007
    @DeleteMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> setUnfollow(@PathVariable Integer userId,@PathVariable Integer userIdToUnfollow){
        return new ResponseEntity<>(userService.setUnfollow(userId,userIdToUnfollow),HttpStatus.NO_CONTENT);
    }
}
