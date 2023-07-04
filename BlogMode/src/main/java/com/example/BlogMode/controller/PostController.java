package com.example.BlogMode.controller;

import com.example.BlogMode.config.AppConstant;
import com.example.BlogMode.payload.ApiResponse;
import com.example.BlogMode.payload.PostDto;
import com.example.BlogMode.payload.PostResponse;
import com.example.BlogMode.service.FileService;
import com.example.BlogMode.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId)
    {
        PostDto postCreated = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postCreated, HttpStatus.CREATED);
    }

    // get by user Id
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId)
    {
        List<PostDto> byUser = postService.getByUser(userId);
        return new ResponseEntity<>(byUser,HttpStatus.OK);
    }

    // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> byCategory = postService.getByCategory(categoryId);
        return new ResponseEntity<>(byCategory, HttpStatus.OK);
    }

    // get all post
    @GetMapping("/get")
    public ResponseEntity<PostResponse> getpost(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber ,
                                                @RequestParam (value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize ,
                                                @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy ,
                                                @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir)
    {
        PostResponse post = postService.getPost(pageNumber, pageSize,sortBy, sortDir);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/get/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
        PostDto postById = postService.getPostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    // delete post by id
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
    {
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted successfully with id : " +postId, true), HttpStatus.OK);
    }

    //update post by id
    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId)
    {
        PostDto postDto1 = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    // search post by keyword
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keywords") String keywords)
    {
        List<PostDto> postDtos = postService.searchPost(keywords);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    // upload image
    @PostMapping("/upload/image/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image")MultipartFile image,
                                                     @PathVariable Integer postId) throws IOException {
        PostDto postDto = postService.getPostById(postId);
        String fileName = fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    // method to serve files
    @GetMapping(value = "/download/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
