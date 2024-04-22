package com.example.SellProducts.dto.user;

import lombok.Builder;

@Builder
public class UserDto {
    public String nickname;
    public String email;
    public String last_login;
}
