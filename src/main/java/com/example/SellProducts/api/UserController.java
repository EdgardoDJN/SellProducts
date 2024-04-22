package com.example.SellProducts.api;

import com.example.SellProducts.dto.user.UserDto;
import com.example.SellProducts.service.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() throws UnirestException {
        return ResponseEntity.ok().body(userService.getAllUsers()).getBody();
    }
}
