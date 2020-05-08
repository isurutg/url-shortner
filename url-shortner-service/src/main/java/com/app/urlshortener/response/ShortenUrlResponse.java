package com.app.urlshortener.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortenUrlResponse {
    private String longUrl;
    private String shortUrl;
    private Date expireDate;
}