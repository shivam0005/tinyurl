package com.shivam.tinyurl.service;

public interface UrlShortenerService {

    String shortenUrl(String longUrl);

    String getLongUrl(String shortCode);
}
