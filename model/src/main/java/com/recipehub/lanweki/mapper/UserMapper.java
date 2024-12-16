package com.recipehub.lanweki.mapper;

import com.recipehub.lanweki.entity.User;
import com.recipehub.lanweki.request.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User registerRequestToEntity(RegisterRequest request) {
        var user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return user;
    }

}
