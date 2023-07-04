package com.example.BlogMode.service.Impl;

import com.example.BlogMode.exception.ResourceNotFoundException;
import com.example.BlogMode.model.User;
import com.example.BlogMode.payload.UserDto;
import com.example.BlogMode.repository.UserRepository;
import com.example.BlogMode.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User save = userRepository.save(user);
        return userToDto(save);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User update = userRepository.save(user);
        UserDto userDto1 = userToDto(update);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getUser() {
        List<User> all = userRepository.findAll();
        List<UserDto> collect = all.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepository.delete(user);
    }

    public User dtoToUser(UserDto userDto)
    {
        User user = modelMapper.map(userDto, User.class);
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user)
    {
        UserDto userDto = modelMapper.map(user, UserDto.class);
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
