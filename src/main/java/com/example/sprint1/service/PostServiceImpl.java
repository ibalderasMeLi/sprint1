package com.example.sprint1.service;

import com.example.sprint1.dto.PostDto;
import com.example.sprint1.dto.PostForListDto;
import com.example.sprint1.exception.BadRequestException;
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

    // Method to obtain the list of followed posts of a user, sorted by date. REQ. US0006, US0009
    public List<PostForListDto> followedList(Integer userId, String order) {
        // Retrieve the list of recent posts (Last two weeks) from the repository for the given user.
        List<Post> sortedList = postRepository.getResentPost(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Validations:
        // If the obtained list is empty (No existing user or No pub)
        if (sortedList.isEmpty()) {
            throw new NotFoundException("No se encontró ninguna publicación de las personas seguidas");
        }
        // Check the value of the 'order' parameter.
        List<Post> orderList;
        ObjectMapper mapper = new ObjectMapper();

        if (order == null || order.trim().isEmpty() || order.equals("date_desc")) {
            // If 'order' is null, empty, or "date_desc", sort the list by date in descending order.
             orderList = sortedList.stream()
                    .sorted(Comparator.comparing(post -> LocalDate.parse(((Post) post).getDate())).reversed())
                    .toList();
        } else if (order.equals("date_asc")) {
             orderList = sortedList.stream()
                    .sorted(Comparator.comparing(post -> LocalDate.parse(post.getDate(), formatter))).toList();
        } else {
            // If 'order' is neither "date_desc" nor "date_asc", it's an invalid value.
            throw new BadRequestException("Invalid sorting order: " + order);
        }
        return orderList.stream().map(post -> mapper.convertValue(post, PostForListDto.class)).collect(Collectors.toList());
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
