package com.emma.urlshortener.services;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emma.urlshortener.exception.ResourceNotFoundException;
import com.emma.urlshortener.models.URLShortenerModel;
import com.emma.urlshortener.models.dtos.UrlRequest;
import com.emma.urlshortener.models.dtos.UrlResponse;
import com.emma.urlshortener.models.dtos.UrlStatsResponse;
import com.emma.urlshortener.repositories.URLShortenerRepository;

@Service
public class URLShortenerService {

    private final URLShortenerRepository urlRepository;

    public URLShortenerService(URLShortenerRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse create(UrlRequest request) {
        URLShortenerModel url = new URLShortenerModel();
        url.setUrl(request.getUrl());
        url.setShortCode(generateShortCode());
        url.setAccessCount(0L);
        url.setCreatedAt(Instant.now());
        url.setUpdatedAt(Instant.now());

        urlRepository.save(url);
        return toResponse(url);
    }

    public UrlResponse getUrlByShortCode(String shortCode) {
        URLShortenerModel url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
        return toResponse(url);
    }

    public UrlResponse updateUrl(String shortCode, UrlRequest request) {
        URLShortenerModel url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
        url.setUrl(request.getUrl());
        url.setUpdatedAt(Instant.now());
        urlRepository.save(url);
        return toResponse(url);
    }

    public void deleteUrl(String shortCode) {
        URLShortenerModel url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
        urlRepository.delete(url);
    }

    public UrlStatsResponse getUrlStats(String shortCode) {
        URLShortenerModel url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
        return toStatsResponse(url);
    }

    private String generateShortCode() {
        String shortCode;
        do {
            shortCode = UUID.randomUUID().toString().substring(0, 6);
        } while (urlRepository.existsByShortCode(shortCode));
        return shortCode;
    }

    private UrlResponse toResponse(URLShortenerModel shortUrl) {
        UrlResponse res = new UrlResponse();
        res.setId(shortUrl.getId());
        res.setUrl(shortUrl.getUrl());
        res.setShortCode(shortUrl.getShortCode());
        res.setCreatedAt(shortUrl.getCreatedAt());
        res.setUpdatedAt(shortUrl.getUpdatedAt());
        return res;
    }

    private UrlStatsResponse toStatsResponse(URLShortenerModel shortUrl) {
        UrlStatsResponse res = new UrlStatsResponse();
        res.setId(shortUrl.getId());
        res.setUrl(shortUrl.getUrl());
        res.setShortCode(shortUrl.getShortCode());
        res.setCreatedAt(shortUrl.getCreatedAt());
        res.setUpdatedAt(shortUrl.getUpdatedAt());
        res.setAccessCount(shortUrl.getAccessCount());
        return res;
    }
}
