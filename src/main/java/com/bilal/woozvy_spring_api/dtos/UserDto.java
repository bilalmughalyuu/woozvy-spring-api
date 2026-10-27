package com.bilal.woozvy_spring_api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isDeleted;
    private String accessToken;
}