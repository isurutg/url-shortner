package com.app.urlshortener.controller;

import javax.validation.Valid;

import com.app.urlshortener.exception.NotFoundException;
import com.app.urlshortener.request.ShortenUrlRequest;
import com.app.urlshortener.response.ApiResponse;
import com.app.urlshortener.service.ShortenURLMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/")
@RestController
public class ShortenURLMapController {
    private ShortenURLMapService shortenURLMapService;

    @Autowired
    public ShortenURLMapController(ShortenURLMapService shortenURLMapService) {
        this.shortenURLMapService = shortenURLMapService;
    }

    @PostMapping
    public ApiResponse createShortUrl(@RequestBody @Valid ShortenUrlRequest ShortenUrlRequest) {
        try {
            return new ApiResponse("SUCCESS", "Short URL creation successful",
                    shortenURLMapService.createShortenURLMap(ShortenUrlRequest));
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GetMapping("/{id}")
    public RedirectView goToUrl(@PathVariable("id") String shortCode) {
        RedirectView redirectView = new RedirectView();
        try {
            String redirectUrl = shortenURLMapService.getLongUrlFromShortUrl(shortCode);
            redirectView.setUrl(redirectUrl);
            return redirectView;
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
