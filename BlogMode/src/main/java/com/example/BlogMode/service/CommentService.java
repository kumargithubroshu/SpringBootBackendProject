package com.example.BlogMode.service;

import com.example.BlogMode.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
