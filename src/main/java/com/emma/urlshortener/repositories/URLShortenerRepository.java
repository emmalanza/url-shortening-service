package com.emma.urlshortener.repositories;

import com.emma.urlshortener.models.UrlShortenerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlShortenerRepository extends JpaRepository<UrlShortenerModel, Long> {

    Optional<UrlShortenerModel> findByShortCode(String shortCode);
    void deleteByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
    
}
