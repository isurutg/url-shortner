package com.app.urlshortener.controller;

import javax.validation.Valid;

import com.app.urlshortener.exception.NotFoundException;
import com.app.urlshortener.request.ShortenUrlRequest;
import com.app.urlshortener.response.ApiResponse;
import com.app.urlshortener.service.ShortenURLMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/")
@RestController
@Slf4j
public class ShortenURLMapController {
    private ShortenURLMapService shortenURLMapService;

    @Autowired
    public ShortenURLMapController(ShortenURLMapService shortenURLMapService) {
        this.shortenURLMapService = shortenURLMapService;
    }

    /**
     * Get long url from the request and pass to service to create short url.
     * @param shortenUrlRequest request with long url
     * @return response with longUrl and shortUrl and expire date or error.
     */
    @PostMapping
    public ApiResponse createShortUrl(@RequestBody @Valid ShortenUrlRequest shortenUrlRequest) {
        try {
            log.info("CREATE SHORT URL STARTED");
            return new ApiResponse("SUCCESS", "Short URL creation successful",
                    shortenURLMapService.createShortenURLMap(shortenUrlRequest));
        } catch (Exception e) {
            log.error("CREATE SHORT URL FAILED");
            throw new NotFoundException();
        }
    }

    /**
     * get long url for give shorurl
     * @param shortCode shordCode from short url.
     * @return redirect to long url or return not founc exception.
     */
    @GetMapping("/{id}")
    public RedirectView goToUrl(@PathVariable("id") String shortCode) {
        RedirectView redirectView = new RedirectView();
        try {
            log.info("GO TO URL STARTED");
            String redirectUrl = shortenURLMapService.getLongUrlFromShortUrl(shortCode);
            redirectView.setUrl(redirectUrl);
            return redirectView;
        } catch (Exception e) {
            log.error("GO TO URL NOT FOUND");
            throw new NotFoundException();
        }
    }
}
