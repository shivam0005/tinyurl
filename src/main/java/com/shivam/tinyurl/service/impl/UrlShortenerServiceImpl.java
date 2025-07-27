package com.shivam.tinyurl.service.impl;

import com.shivam.tinyurl.cache.RedisUrlCache;
import com.shivam.tinyurl.repository.UrlRepository;
import com.shivam.tinyurl.service.UrlShortenerService;
import com.shivam.tinyurl.service.codegenerator.CodeGeneratorStrategy;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {


    private final UrlRepository repository;
    private final CodeGeneratorStrategy codeGenerator;
    private final RedisUrlCache redisCache;

    public UrlShortenerServiceImpl(UrlRepository repository,
                                   CodeGeneratorStrategy codeGenerator, RedisUrlCache redisCache) {
        this.repository = repository;
        this.codeGenerator = codeGenerator;
        this.redisCache = redisCache;
    }

    @Override
    public String shortenUrl(String longUrl) {

        String existingShortCode = repository.findShortCodeByLongUrl(longUrl);
        if (existingShortCode != null) return existingShortCode;

        String shortCode = codeGenerator.generateCode(longUrl);
        repository.save(shortCode, longUrl);
        return shortCode;
    }

    @Override
    public String getLongUrl(String shortCode) {

        // Step 1: Try Redis

        String longUrl = redisCache.getLongUrl(shortCode);
        if (longUrl != null) return longUrl;

        // Step 2: Fallback to DB
        longUrl = repository.findLongUrlByShortCode(shortCode);

        // Step 3: Update cache
        if (longUrl != null) {
            redisCache.cacheShortToLong(shortCode, longUrl);
        }

        return longUrl;
    }
}
