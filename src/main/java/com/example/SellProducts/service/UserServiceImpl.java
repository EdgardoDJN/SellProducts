package com.example.SellProducts.service;

import com.example.SellProducts.dto.user.UserDto;
import com.example.SellProducts.repositories.UserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() throws UnirestException {
        List<UserDto> users = (List<UserDto>) userRepository.allUsers()
                .stream().map(x -> UserDto.builder()
                        .last_login(x.getLast_login())
                        .email(x.getEmail())
                        .nickname(x.getNickname())
                        .build());
        return users;
    }
}
