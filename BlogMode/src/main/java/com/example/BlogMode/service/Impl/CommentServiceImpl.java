package com.example.BlogMode.service.Impl;

import com.example.BlogMode.exception.ResourceNotFoundException;
import com.example.BlogMode.model.Comment;
import com.example.BlogMode.model.Post;
import com.example.BlogMode.payload.CommentDto;
import com.example.BlogMode.repository.CommentRepo;
import com.example.BlogMode.repository.PostRepo;
import com.example.BlogMode.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        Comment com = modelMapper.map(commentDto, Comment.class);

        com.setPost(post);

        Comment save = commentRepo.save(com);
        post.setContent(com.getContent());
        postRepo.save(post);

        return modelMapper.map(save,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
        commentRepo.delete(comment);

    }
}
