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


        /**
         * US 0005
         * Create a new post
         * @param postDto
         * @return
         */
        @PostMapping("/products/post")
        public ResponseEntity<?> addPost (@RequestBody PostDto postDto){
            return new ResponseEntity<>(postService.addPost(postDto), HttpStatus.CREATED);
        }


        /**
         * US 0006 US 0009
         * Obtain a list of the publications made by the sellers that a user follows in the last two weeks
         * Sort by ascending and descending date
         * @param userId
         * @param order
         * @return
         */
        @GetMapping("/followed/{userId}/list")
        public ResponseEntity<?> followedList (@PathVariable Integer
        userId, @RequestParam(value = "order", required = false) String order){
            return new ResponseEntity<>(postService.followedList(userId, order), HttpStatus.OK);
        }

        /**
         * US 0010
         * Carry out the publication of a new promotional product
         * @param postDto
         * @return
         */
        @PostMapping("/promo-post")
        public ResponseEntity<?> postPromo (@RequestBody PostDto postDto){
            return new ResponseEntity<>(postService.postPromo(postDto), HttpStatus.CREATED);
        }


        /**
         * US 0011
         * Obtain the number of products on promotion from a certain seller
         * @param user_id
         * @return
         */
        @GetMapping("/promo-post/count")
        public ResponseEntity<?> quantityPromo (@RequestParam Integer user_id){
            return new ResponseEntity<>(postService.quantityPromo(user_id), HttpStatus.OK);
        }


        /**
         * US 0012
         * Obtain a list of all the products on promotion from a certain seller
         * @param user_id
         * @return
         */
        @GetMapping("/promo-post/list")
        public ResponseEntity<?> getPromo (@RequestParam Integer user_id){
            return new ResponseEntity<>(postService.getPromo(user_id), HttpStatus.OK);
        }
    }