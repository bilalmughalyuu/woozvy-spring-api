package com.bilal.woozvy_spring_api.repositories;

import com.bilal.woozvy_spring_api.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
