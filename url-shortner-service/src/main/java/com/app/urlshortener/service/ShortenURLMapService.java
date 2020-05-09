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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

    /**
     * create short url and save in database
     * @param shortenUrlRequest request with longUrl
     * @return shortenUrlResponse with longUrl, shortUrl, expireDate
     * @throws Exception
     */
    public ShortenUrlResponse createShortenURLMap(ShortenUrlRequest shortenUrlRequest) throws Exception {
        ShortenURLMap newShortenURLMap = new ShortenURLMap();
        newShortenURLMap.setLongUrl(shortenUrlRequest.getLongUrl());
        newShortenURLMap.setId(counterUtil.getCurrentCounterValue());
        newShortenURLMap.setShortUrl(ShortCodeUtil.INSTANCE.getShortCode(newShortenURLMap.getId()));
        newShortenURLMap.setExpireAt(dateUtil.getExpireDate());
        shortenURLMapRepository.save(newShortenURLMap);
        log.info("CREATE SHORT URL SAVED");

        ShortenUrlResponse shortenUrlResponse = new ShortenUrlResponse();
        shortenUrlResponse.setLongUrl(newShortenURLMap.getLongUrl());
        shortenUrlResponse.setShortUrl(baseUrl + newShortenURLMap.getShortUrl());
        shortenUrlResponse.setExpireDate(newShortenURLMap.getExpireAt());
        log.info("CREATE SHORT URL RESPONSE", shortenUrlResponse);
        return shortenUrlResponse;
    }

    /**
     * get long url from db for given short code
     * @param shortUrl retrived short code from shour url
     * @return longUrl
     * @throws Exception return not found exception
     */
    public String getLongUrlFromShortUrl(String shortUrl) throws Exception {
        ShortenURLMap shortenURLMap = shortenURLMapRepository.findByShortUrl(shortUrl).orElse(null);
        if (shortenURLMap != null && shortenURLMap.getExpireAt().after((new Date()))) {
            log.info("GO TO URL LONG URL RETRIVED");
            return shortenURLMap.getLongUrl();
        } else {
            log.info("GO TO URL LONG URL NOT FOUND OR EXPIRED");
            shortenURLMapRepository.deleteById(shortenURLMap.getId());
            throw new Exception();
        }
    }
}
