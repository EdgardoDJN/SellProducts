package com.example.SellProducts.service;

import com.example.SellProducts.dto.user.UserDto;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers() throws UnirestException;
}
