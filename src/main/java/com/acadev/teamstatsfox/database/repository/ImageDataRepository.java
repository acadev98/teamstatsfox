package com.acadev.teamstatsfox.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acadev.teamstatsfox.database.entity.Images;

public interface ImageDataRepository extends JpaRepository<Images, Long> {
    Optional<Images> findByName(String name);
}
