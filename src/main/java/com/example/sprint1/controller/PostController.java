package com.example.sprint1.controller;

import com.example.sprint1.dto.PostDto;
import com.example.sprint1.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PostController {

    @Autowired
    IPostService postService;

    //US 0005
    @PostMapping("/products/post")
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.addPost(postDto) , HttpStatus.CREATED);
    }

    //US 0006 US 0009
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<?> followedList(@PathVariable Integer userId,@RequestParam(required = false) String order){
        return new ResponseEntity<>(postService.followedList(userId,order), HttpStatus.OK);
    }

    //US 0010:
    @PostMapping("/promo-post")
    public ResponseEntity<?> postPromo(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.postPromo(postDto), HttpStatus.CREATED);
    }

    //US 0011
    @GetMapping("/promo-post/count")
    public ResponseEntity<?> quantityPromo(@RequestParam Integer user_id){
        return new ResponseEntity<>(postService.quantityPromo(user_id), HttpStatus.OK);
    }

    //US 0012
    @GetMapping("/promo-post/list")
    public ResponseEntity<?> getPromo(@RequestParam Integer user_id){
        return new ResponseEntity<>(postService.getPromo(user_id), HttpStatus.OK);
    }
}
