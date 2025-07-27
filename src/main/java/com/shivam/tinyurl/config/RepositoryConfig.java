package com.shivam.tinyurl.config;

import com.shivam.tinyurl.repository.UrlJpaRepository;
import com.shivam.tinyurl.repository.UrlRepository;
import com.shivam.tinyurl.repository.impl.InMemoryUrlRepository;
import com.shivam.tinyurl.repository.impl.PostgresUrlRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RepositoryConfig {

    @Value("${url.repository.type}")
    String repoType;

    @Bean(name = "inMemory")
    public UrlRepository inMemoryRepository() {
        return new InMemoryUrlRepository();  // manually instantiated
    }

    @Bean(name = "postgres")
    public UrlRepository postgresRepository(UrlJpaRepository jpaRepository) {
        return new PostgresUrlRepository(jpaRepository);  // manually injected
    }

    @Bean
    @Primary
    public UrlRepository urlRepository(
            @Qualifier("inMemory") UrlRepository inMemoryRepo,
            @Qualifier("postgres") UrlRepository postgresRepo){

        return switch (repoType.toLowerCase()) {
            case "postgres" -> postgresRepo;
            case "inmemory" -> inMemoryRepo;
            default -> throw new IllegalArgumentException("Invalid url.repository.type: " + repoType);
        };

    }
}
