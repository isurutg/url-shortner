package com.app.urlshortener.util;

import com.app.urlshortener.model.ShortenURLMap;
import com.app.urlshortener.repository.ShortenURLMapRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CounterUtil {
    private ShortenURLMapRepository shortenURLMapRepository;
    private static Long counter = 100000000L;

    @Autowired
    public CounterUtil(ShortenURLMapRepository shortenURLMapRepository) {
        this.shortenURLMapRepository = shortenURLMapRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setCounterValue() {
        ShortenURLMap shortenURLMap = shortenURLMapRepository.findFirstByOrderByIdDesc();
        Long maxId = shortenURLMap != null ? shortenURLMap.getId() : counter;
        counter = maxId + 1;
    }

    public Long getCurrentCounterValue() {
        Long currentCounter = counter++;
        return currentCounter;
    }

}