package com.bilal.woozvy_spring_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String accessToken;
}
