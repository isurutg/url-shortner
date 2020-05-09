package com.app.urlshortener.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "shortenUrlMap")
@Getter
@Setter
public class ShortenURLMap {

    @Id
    private Long id;
    @NotNull
    @NotEmpty
    private String longUrl;
    @NotNull
    @NotEmpty
    @Indexed(unique = true)
    private String shortUrl;
    private Date createdAt = new Date();
    private Date expireAt;
}
