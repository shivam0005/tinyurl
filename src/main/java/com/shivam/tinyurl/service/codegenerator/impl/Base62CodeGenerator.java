package com.shivam.tinyurl.service.codegenerator.impl;

import com.shivam.tinyurl.service.codegenerator.CodeGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class Base62CodeGenerator implements CodeGeneratorStrategy {

    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final AtomicLong counter = new AtomicLong(100000);
    @Override
    public String generateCode(String longUrl) {

        long id = counter.incrementAndGet();
        return encodeBase62(id);
    }

    private String encodeBase62(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(CHAR_SET.charAt((int)(id % 62)));
            id /= 62;
        }
        return sb.reverse().toString();
    }
}
