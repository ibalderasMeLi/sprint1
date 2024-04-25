package com.example.sprint1.repository;

import com.example.sprint1.model.Post;
import java.util.List;

public interface IPostRepository {
    // begin modify Leonardo
    Post save(Post post);
    List<Post> findAll();
    Post findById(Integer id);
}
