package com.emma.urlshortener.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UrlRequest {

    @NotBlank(message = "URL is mandatory")
    @Size(max = 2048, message = "URL too long")
    private String url;
    
}
