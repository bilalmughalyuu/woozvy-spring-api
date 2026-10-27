package com.bilal.woozvy_spring_api.controllers;

import com.bilal.woozvy_spring_api.dtos.UserDto;
import com.bilal.woozvy_spring_api.entities.User;
import com.bilal.woozvy_spring_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already exists"));
        }

        User saveUser = new User();
        saveUser.setFirstName(userDto.getFirstName());
        saveUser.setLastName(userDto.getLastName());
        saveUser.setEmail(userDto.getEmail());
        saveUser.setPassword(userDto.getPassword());
        userRepository.save(saveUser);
        return ResponseEntity.ok(saveUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody UserDto signinUserDto) {
        String email = signinUserDto.getEmail();
        String password = signinUserDto.getPassword();


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "User already exists with this email"
                ));

        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid email or password"
            );
        }

        return ResponseEntity.ok(user);
    }
}
