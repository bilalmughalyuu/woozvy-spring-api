package com.bilal.woozvy_spring_api.controllers;

import com.bilal.woozvy_spring_api.dtos.PropertyDto;
import com.bilal.woozvy_spring_api.entities.Property;
import com.bilal.woozvy_spring_api.entities.User;
import com.bilal.woozvy_spring_api.repositories.PropertyRepository;
import com.bilal.woozvy_spring_api.repositories.UserRepository;
import com.bilal.woozvy_spring_api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/property")
public class PropertyController {

    private PropertyRepository propertyRepository;
    private UserService userService;

    @PostMapping("/new")
    public PropertyDto addProperty(
            @RequestBody PropertyDto propertyDto,
            @AuthenticationPrincipal String userId) {

        User user = userService.getUserById(Long.valueOf(userId));

        if (user == null || Boolean.TRUE.equals(user.getIsDeleted())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "User not found"
            );
        }

        Property newProperty = new Property();
        newProperty.setTitle(propertyDto.getTitle());
        newProperty.setDescription(propertyDto.getDescription());
        newProperty.setCategory(propertyDto.getCategory());
        newProperty.setPrice(propertyDto.getPrice());
        newProperty.setUser(user);

        Property property = propertyRepository.save(newProperty);

        return new PropertyDto(
                property.getId(),
                property.getTitle(),
                property.getDescription(),
                property.getCategory(),
                property.getPrice()
        );

    }

    @GetMapping("/{id}")
    public Object getProperty(@PathVariable Long id) {
        Property property = propertyRepository.findById(id).orElse(null);

        if(property == null) {
            return Map.of("error", "Property not found");
        }

        return new PropertyDto(
                property.getId(),
                property.getTitle(),
                property.getDescription(),
                property.getCategory(),
                property.getPrice()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProperty(
            @RequestBody PropertyDto propertyDto,
            @AuthenticationPrincipal String userId) {

        Property property = propertyRepository
                .findById(propertyDto.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Property not found"
                ));

        if (!property.getUser().getId().equals(Long.valueOf(userId))) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You cannot update this property"
            );
        }

        if (propertyDto.getTitle() != null) {
            property.setTitle(propertyDto.getTitle());
        }

        if (propertyDto.getDescription() != null) {
            property.setDescription(propertyDto.getDescription());
        }

        if (propertyDto.getCategory() != null) {
            property.setCategory(propertyDto.getCategory());
        }

        if (propertyDto.getPrice() != null) {
            property.setPrice(propertyDto.getPrice());
        }

        Property updatedProperty = propertyRepository.save(property);

        return ResponseEntity.ok(updatedProperty);
    }


//    public ResponseEntity<?> updateProperty(
//            @RequestBody PropertyDto propertyDto,
//            @AuthenticationPrincipal String userId) {
//        User user = userService.getUserById(Long.valueOf(userId));
//        if (user == null) {
//
//        }
//
//        var property = propertyRepository.findById(propertyDto.getId()).orElse(null);
//
//        if (property == null) {
//
//        }
//
//
//
//    }
}
