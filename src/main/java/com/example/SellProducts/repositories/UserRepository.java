package com.example.SellProducts.repositories;

import com.example.SellProducts.entities.User;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface UserRepository {
    public List<User> allUsers() throws UnirestException;
    public User findUserByEmail(String email);
    public User createUser(User user);
}
