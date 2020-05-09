package com.app.urlshortener.service;

import java.util.Date;

import com.app.urlshortener.model.ShortenURLMap;
import com.app.urlshortener.repository.ShortenURLMapRepository;
import com.app.urlshortener.request.ShortenUrlRequest;
import com.app.urlshortener.response.ShortenUrlResponse;
import com.app.urlshortener.util.CounterUtil;
import com.app.urlshortener.util.DateUtil;
import com.app.urlshortener.util.ShortCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortenURLMapService {
    private ShortenURLMapRepository shortenURLMapRepository;
    private CounterUtil counterUtil;
    private DateUtil dateUtil;

    @Value("${application.url}")
    private String baseUrl;

    @Autowired
    public ShortenURLMapService(ShortenURLMapRepository shortenURLMapRepository, CounterUtil counterUtil,
            DateUtil dateUtil) {
        this.shortenURLMapRepository = shortenURLMapRepository;
        this.counterUtil = counterUtil;
        this.dateUtil = dateUtil;
    }

    public ShortenUrlResponse createShortenURLMap(ShortenUrlRequest shortenUrlRequest) throws Exception {
        ShortenURLMap newShortenURLMap = new ShortenURLMap();
        newShortenURLMap.setLongUrl(shortenUrlRequest.getLongUrl());
        newShortenURLMap.setId(counterUtil.getCurrentCounterValue());
        newShortenURLMap.setShortUrl(ShortCodeUtil.INSTANCE.getShortCode(newShortenURLMap.getId()));
        newShortenURLMap.setExpireAt(dateUtil.getExpireDate());
        shortenURLMapRepository.save(newShortenURLMap);

        ShortenUrlResponse shortenUrlResponse = new ShortenUrlResponse();
        shortenUrlResponse.setLongUrl(newShortenURLMap.getLongUrl());
        shortenUrlResponse.setShortUrl(baseUrl + newShortenURLMap.getShortUrl());
        shortenUrlResponse.setExpireDate(newShortenURLMap.getExpireAt());
        return shortenUrlResponse;
    }

    public String getLongUrlFromShortUrl(String shortUrl) throws Exception {
        ShortenURLMap shortenURLMap = shortenURLMapRepository.findByShortUrl(shortUrl);
        if (shortenURLMap.getExpireAt().after((new Date()))) {
            return shortenURLMap.getLongUrl();
        } else {
            shortenURLMapRepository.deleteById(shortenURLMap.getId());
            throw new Exception();
        }
    }
}
