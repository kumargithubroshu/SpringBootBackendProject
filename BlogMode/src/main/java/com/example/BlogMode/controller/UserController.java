package com.example.BlogMode.controller;

import com.example.BlogMode.payload.ApiResponse;
import com.example.BlogMode.payload.UserDto;
import com.example.BlogMode.service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createData(@Valid @RequestBody UserDto userDto)
    {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateData(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId)
    {
        UserDto userDto1 = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1 , HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getData()
    {
        List<UserDto> user = userService.getUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getDataById(@PathVariable Integer userId)
    {
        UserDto userById = userService.getUserById(userId);
        return ResponseEntity.ok(userById);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteData(@PathVariable Integer userId)
    {
        userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully with id : " +userId,true),HttpStatus.OK);
    }
}
