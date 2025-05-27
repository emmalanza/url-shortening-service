package com.emma.urlshortener.mappers;

import org.springframework.stereotype.Component;

import com.emma.urlshortener.models.UrlShortenerModel;
import com.emma.urlshortener.models.dtos.UrlResponse;
import com.emma.urlshortener.models.dtos.UrlStatsResponse;

@Component
public class UrlMapper {

    public UrlResponse toResponse(UrlShortenerModel model) {
        UrlResponse res = new UrlResponse();
        res.setId(model.getId());
        res.setUrl(model.getUrl());
        res.setShortCode(model.getShortCode());
        res.setCreatedAt(model.getCreatedAt());
        res.setUpdatedAt(model.getUpdatedAt());
        return res;
    }

    public UrlStatsResponse toStatsResponse(UrlShortenerModel model) {
        UrlStatsResponse res = new UrlStatsResponse();
        res.setId(model.getId());
        res.setUrl(model.getUrl());
        res.setShortCode(model.getShortCode());
        res.setCreatedAt(model.getCreatedAt());
        res.setUpdatedAt(model.getUpdatedAt());
        res.setAccessCount(model.getAccessCount());
        return res;
    }
}