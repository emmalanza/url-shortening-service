package com.emma.urlshortener.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.emma.urlshortener.repositories.UrlShortenerRepository;

@Component
public class ShortCodeGenerator {

    private final UrlShortenerRepository repository;

    public ShortCodeGenerator(UrlShortenerRepository repository) {
        this.repository = repository;
    }

    public String generate() {
        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 6);
        } while (repository.existsByShortCode(code));
        return code;
    }
}
