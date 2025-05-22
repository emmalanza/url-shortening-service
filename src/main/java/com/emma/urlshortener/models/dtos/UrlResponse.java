package com.emma.urlshortener.models.dtos;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class UrlResponse {

    private Long id;
    private String url;
    private String shortCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Europe/Madrid")
    private Instant createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Europe/Madrid")
    private Instant updatedAt;

}
