package com.shivam.tinyurl.repository.impl;

import com.shivam.tinyurl.model.UrlMapping;
import com.shivam.tinyurl.repository.UrlJpaRepository;
import com.shivam.tinyurl.repository.UrlRepository;
public class PostgresUrlRepository implements UrlRepository {

    UrlJpaRepository jpaRepository;

    public PostgresUrlRepository(UrlJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(String shortCode, String longUrl) {
        UrlMapping mapping = new UrlMapping(shortCode, longUrl);
        jpaRepository.save(mapping);
    }

    @Override
    public String findLongUrlByShortCode(String shortCode) {
        return jpaRepository.findById(shortCode)
                .map(UrlMapping::getLongUrl)
                .orElse(null);
    }

    @Override
    public String findShortCodeByLongUrl(String longUrl) {
        return jpaRepository.findByLongUrl(longUrl)
                .map(UrlMapping::getShortCode)
                .orElse(null);
    }
}
