package com.app.urlshortener.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortenUrlRequest {
    @NotNull
    @NotEmpty
    private String longUrl;
}