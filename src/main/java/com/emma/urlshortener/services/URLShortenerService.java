package com.emma.urlshortener.services;

import java.time.Instant;


import org.springframework.stereotype.Service;

import com.emma.urlshortener.exceptions.ResourceNotFoundException;
import com.emma.urlshortener.mappers.UrlMapper;
import com.emma.urlshortener.models.UrlShortenerModel;
import com.emma.urlshortener.models.dtos.UrlRequest;
import com.emma.urlshortener.models.dtos.UrlResponse;
import com.emma.urlshortener.models.dtos.UrlStatsResponse;
import com.emma.urlshortener.repositories.UrlShortenerRepository;
import com.emma.urlshortener.utils.ShortCodeGenerator;

@Service
public class UrlShortenerService {

    private final UrlShortenerRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;
    private final UrlMapper urlMapper;

    public UrlShortenerService(UrlShortenerRepository urlRepository, ShortCodeGenerator shortCodeGenerator, UrlMapper urlMapper) {
        this.urlRepository = urlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
        this.urlMapper = urlMapper;
    }

    public UrlResponse create(UrlRequest request) {
        UrlShortenerModel url = new UrlShortenerModel();
        url.setUrl(request.getUrl());
        url.setShortCode(shortCodeGenerator.generate());
        url.setAccessCount(0L);
        url.setCreatedAt(Instant.now());
        url.setUpdatedAt(Instant.now());

        urlRepository.save(url);
        return urlMapper.toResponse(url);
    }

    public UrlResponse getUrlByShortCode(String shortCode) {
        UrlShortenerModel url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
        return urlMapper.toResponse(url);
    }

    public UrlResponse updateUrl(String shortCode, UrlRequest request) {
        UrlShortenerModel url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
        url.setUrl(request.getUrl());
        url.setUpdatedAt(Instant.now());
        urlRepository.save(url);
        return urlMapper.toResponse(url);
    }

    public void deleteUrl(String shortCode) {
        UrlShortenerModel url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
        urlRepository.delete(url);
    }

    public UrlStatsResponse getUrlStats(String shortCode) {
        UrlShortenerModel url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
        return urlMapper.toStatsResponse(url);
    }

}
