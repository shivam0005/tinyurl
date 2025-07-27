package com.shivam.tinyurl.repository.impl;

import com.shivam.tinyurl.repository.UrlRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUrlRepository implements UrlRepository {

    private final Map<String, String> shortToLongMap = new ConcurrentHashMap<>();
    private final Map<String, String> longToShortMap = new ConcurrentHashMap<>();


    @Override
    public void save(String shortCode, String longUrl) {
        shortToLongMap.put(shortCode, longUrl);
        longToShortMap.put(longUrl, shortCode);
    }

    @Override
    public String findLongUrlByShortCode(String shortCode) {
        return shortToLongMap.get(shortCode);
    }

    @Override
    public String findShortCodeByLongUrl(String longUrl) {

        return longToShortMap.get(longUrl);
    }
}
