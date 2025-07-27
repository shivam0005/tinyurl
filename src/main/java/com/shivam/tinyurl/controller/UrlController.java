package com.shivam.tinyurl.controller;

import com.shivam.tinyurl.service.UrlShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/url")
public class UrlController {

    private static final Logger log = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    UrlShortenerService service;


    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody String longUrl){

        log.info("inside shortenUrl method");
        String shortCode = service.shortenUrl(longUrl);

        return ResponseEntity.status(HttpStatus.OK).body(shortCode);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortCode){

        String longUrl = service.getLongUrl(shortCode);

        if (longUrl != null && !longUrl.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND) // 302
                    .header("Location", longUrl)
                    .build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
