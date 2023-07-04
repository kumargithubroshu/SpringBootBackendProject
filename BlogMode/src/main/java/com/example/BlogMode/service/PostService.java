package com.example.BlogMode.service;

import com.example.BlogMode.model.Category;
import com.example.BlogMode.model.Post;
import com.example.BlogMode.payload.PostDto;
import com.example.BlogMode.payload.PostResponse;

import java.util.*;

public interface PostService {

    // create post
    PostDto createPost(PostDto postDto , Integer userId, Integer categoryId);

    // update post
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete post
    void deletePost(Integer postId);

    // get all post
    PostResponse getPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir );

    // get post by id
    PostDto getPostById(Integer postId);

    // get post by category
    List<PostDto> getByCategory(Integer categoryId);

    // get post by user
    List<PostDto> getByUser(Integer userId);

    // search post
    List<PostDto> searchPost(String keyword);


}
