package com.shivam.tinyurl.repository;

public interface UrlRepository {

    void save(String shortCode, String longUrl);
    String findLongUrlByShortCode(String shortCode);
    String findShortCodeByLongUrl(String longUrl);

}
