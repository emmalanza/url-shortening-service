package com.emma.urlshortener.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.urlshortener.models.dtos.UrlRequest;
import com.emma.urlshortener.models.dtos.UrlResponse;
import com.emma.urlshortener.services.URLShortenerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shorten")
public class URLShortenerController {

    private final URLShortenerService urlShortenerService;

    public URLShortenerController(URLShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> create(@Valid @RequestBody UrlRequest request) {
        UrlResponse response = urlShortenerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getOriginalUrl(@PathVariable String shortCode) {
        UrlResponse urlResponse = urlShortenerService.getUrlByShortCode(shortCode);
        return ResponseEntity.ok(urlResponse);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> updateUrl(@PathVariable String shortCode, @Valid @RequestBody UrlRequest request) {
        UrlResponse urlResponse = urlShortenerService.updateUrl(shortCode, request);
        return ResponseEntity.ok(urlResponse);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlShortenerService.deleteUrl(shortCode);
        return ResponseEntity.noContent().build();
    }
}
