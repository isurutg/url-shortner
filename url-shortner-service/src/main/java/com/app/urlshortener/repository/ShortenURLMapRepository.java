package com.app.urlshortener.repository;

import com.app.urlshortener.model.ShortenURLMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenURLMapRepository extends MongoRepository<ShortenURLMap, String> {
    ShortenURLMap findByShortUrl(String shortUrl);

    ShortenURLMap findFirstByOrderByIdDesc();

    void deleteById(Long id);
}
