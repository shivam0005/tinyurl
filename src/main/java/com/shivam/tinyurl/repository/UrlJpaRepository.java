package com.shivam.tinyurl.repository;

import com.shivam.tinyurl.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlJpaRepository extends JpaRepository<UrlMapping, String> {

    Optional<UrlMapping> findByLongUrl(String longUrl);
}
