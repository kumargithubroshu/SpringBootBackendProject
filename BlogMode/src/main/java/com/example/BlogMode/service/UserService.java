package com.example.BlogMode.service;

import com.example.BlogMode.payload.UserDto;

import java.util.List;

public interface UserService {
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto, Integer userId);
    public UserDto getUserById(Integer userId);
    public List<UserDto> getUser();
    public void deleteUser(Integer userId);
}
