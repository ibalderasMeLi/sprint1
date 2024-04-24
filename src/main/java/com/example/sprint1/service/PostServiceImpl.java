package com.example.sprint1.service;

import com.example.sprint1.dto.PostDto;
import com.example.sprint1.dto.PostForListDto;
import com.example.sprint1.exception.NotFoundException;
import com.example.sprint1.model.Post;
import com.example.sprint1.repository.IPostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    IPostRepository postRepository;

    @Override
    public Object addPost(PostDto postDto) {
        return null;
    }

    public List<PostForListDto> followedList(Integer userId, String order) {
        List<Post> sortedList = postRepository.getResentPost(userId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(sortedList.isEmpty()){
            throw new NotFoundException("No se encontró ninguna publicación de las personas seguidas");
        }
        if(order== null||order == ""||order.isEmpty()||order.isBlank()||order.equals("date_desc")){

            List<Post> orderList = sortedList.stream()
                    .sorted(Comparator.comparing(post -> LocalDate.parse(((Post) post).getDate())).reversed())
                    .toList();
            //---
            ObjectMapper mapper = new ObjectMapper();
            return  orderList.stream().map(post -> mapper.convertValue(post, PostForListDto.class)).collect(Collectors.toList());
        }
        else if (order.equals("date_asc")) {
            List<Post> orderList = sortedList.stream()
                    .sorted(Comparator.comparing(post -> LocalDate.parse(post.getDate(), formatter))).toList();
            ObjectMapper mapper = new ObjectMapper();
            return orderList.stream().map(post -> mapper.convertValue(post, PostForListDto.class)).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public Object postPromo(PostDto postDto) {
        return null;
    }

    @Override
    public Object quantityPromo(Integer user_id) {
        return null;
    }

    @Override
    public Object getPromo(Integer user_id) {
        return null;
    }
}
