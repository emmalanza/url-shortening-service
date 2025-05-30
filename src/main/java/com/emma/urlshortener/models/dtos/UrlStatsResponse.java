package com.emma.urlshortener.models.dtos;

import java.time.Instant;

import lombok.Data;
@Data
public class UrlStatsResponse {

    private Long id;
    private String url;
    private String shortCode;
    private Instant createdAt;
    private Instant updatedAt;
    private Long accessCount;
    
}