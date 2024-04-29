package com.example.SellProducts.repositories;

import com.example.SellProducts.entities.User;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Value("${okta.oauth2.bearer-token}")
    private String bearer;

    @Override
    public List<User> allUsers() throws UnirestException {
        var users = Unirest.get("https://dev-ygcz3lc8uue35omt.us.auth0.com/api/v2/users")
                .header("authorization", "Bearer " + bearer)
                .asJson();

        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
