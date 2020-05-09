package com.app.urlshortener.repository;

import java.util.Optional;

import com.app.urlshortener.model.ShortenURLMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenURLMapRepository extends MongoRepository<ShortenURLMap, String> {
    Optional<ShortenURLMap> findByShortUrl(String shortUrl);

    Optional<ShortenURLMap> findFirstByOrderByIdDesc();

    void deleteById(Long id);
}
