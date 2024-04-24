package com.example.sprint1.service;

import com.example.sprint1.dto.PostDto;

import java.util.List;

public interface IPostService {
    Object addPost(PostDto postDto);

    List<PostDto> followedList(Integer userId, String order);

    Object postPromo(PostDto postDto);

    Object quantityPromo(Integer user_id);

    Object getPromo(Integer user_id);
}
