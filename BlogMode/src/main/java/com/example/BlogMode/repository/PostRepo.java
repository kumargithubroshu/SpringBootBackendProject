package com.example.BlogMode.repository;

import com.example.BlogMode.model.Category;
import com.example.BlogMode.model.Post;
import com.example.BlogMode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser( User user);

    List<Post> findByCategory(Category category);

//    @Query("select p from Post p where p.title like : key")        @Param("key")
    List<Post> findByTitle(String title);
}
