package com.example.BlogMode.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
//    private Set<CommentDto> comment=new HashSet<>();
//    private List<CommentDto> comment=new ArrayList<>();
}
