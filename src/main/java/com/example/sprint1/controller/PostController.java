package com.example.sprint1.controller;

import com.example.sprint1.dto.PostDto;
import com.example.sprint1.service.IPostService;
import com.example.sprint1.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PostController {

    @Autowired
    IPostService postService;

    // US 0005
    /**
     * This method is used to create a new post.
     * @param postDto - The DTO containing the necessary information for creating a new post.
     * @return ResponseEntity<?> - This method returns a ResponseEntity object. If the post is successfully created,
     * it returns the post's details with an HTTP 200 OK status. If an error occurs,
     * it returns an error message with an HTTP 400 Bad Request status.
     */
    // method to create a new post
    @PostMapping("/post")
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto){
        // try block to handle exceptions
        try {
            // Call service to handle logic of adding a new post
            Object response = postService.addPost(postDto);
            // Return a ResponseEntity with status 200 Ok
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // Return a ResponseEntity with status 400 Bad Request
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // finished method US0005

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
