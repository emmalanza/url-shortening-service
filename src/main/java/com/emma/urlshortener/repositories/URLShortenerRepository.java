package com.emma.urlshortener.repositories;

import com.emma.urlshortener.models.URLShortenerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLShortenerRepository extends JpaRepository<URLShortenerModel, Long> {

    Optional<URLShortenerModel> findByShortCode(String shortCode);
    void deleteByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
    
}
