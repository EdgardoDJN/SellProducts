package com.example.SellProducts.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String email;
    private String phone_number;
    private boolean blocked;
    private boolean email_verified;
    private boolean phone_verified;
    private String given_name;
    private String family_name;
    private String name;
    private String nickname;
    private String picture;
    private String user_id;
    private String connection;
    private String password;
    private boolean verify_email;
    private String username;
    private String last_login;
}
