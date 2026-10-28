package com.bilal.woozvy_spring_api.services;


import com.bilal.woozvy_spring_api.entities.User;
import com.bilal.woozvy_spring_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
