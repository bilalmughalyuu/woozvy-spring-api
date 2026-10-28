package com.bilal.woozvy_spring_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PropertyDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
}
